package cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by carapooh on 24.09.2017.
 */
public class CacheEngineSoftReferenceImpl<K, T> implements CacheEngine<K, SoftReferenceCacheElement<T>> {

    private final int maxSize;
    private final long lifeTimeInMilliseconds;
    private final long idleTimeInMilliseconds;

    private final Map<K, SoftReferenceCacheElement<T>> elements;
    private final Timer timer = new Timer();

    private int hits;
    private int misses;

    public CacheEngineSoftReferenceImpl(int maxSize, long lifeTimeInMilliseconds,
                                        long idleTimeInMilliseconds){
        this.maxSize = maxSize > 0 ? maxSize : 10;
        this.lifeTimeInMilliseconds = lifeTimeInMilliseconds > 0 ? lifeTimeInMilliseconds : 10000L;
        this.idleTimeInMilliseconds = idleTimeInMilliseconds > 0 ? idleTimeInMilliseconds : 1000L;
        elements = new HashMap<>(this.maxSize);

        timer.scheduleAtFixedRate(getTimerTask(), idleTimeInMilliseconds, idleTimeInMilliseconds);
    }

    @Override
    public void put(K key, SoftReferenceCacheElement<T> element) {
        if (elements.size() == maxSize){
            elements.remove(elements.keySet().iterator().next());
        }
        elements.put(key, element);
    }

    @Override
    public SoftReferenceCacheElement<T> get(K key) {
        if (elements.containsKey(key)){
            if (containsCache(key)) {
                hits++;
                elements.get(key).setJustAccessed();
                return elements.get(key);
            }
            else {
                misses++;
                elements.remove(key);
                return null;
            }
        }
        else {
            misses++;
            return null;
        }
    }

    @Override
    public void remove(K key) {
        if (elements.containsKey(key)){
            elements.remove(key);
        }
    }

    @Override
    public int getMissCount() {
        return misses;
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    public boolean containsCache(K key){
        return elements.get(key) != null && elements.get(key).getValue().get() != null;
    }

    private TimerTask getTimerTask(){
        return new TimerTask() {
            @Override
            public void run() {
                for(K key : elements.keySet()){
                    SoftReferenceCacheElement<T> value = elements.get(key);
                    if (value == null || value.getCurrentTime() - value.getAccessedTime() > idleTimeInMilliseconds
                            || value.getCurrentTime() - value.getCreationTime() > lifeTimeInMilliseconds) {
                        elements.remove(key);
                    }
                }
            }
        };
    }
}
