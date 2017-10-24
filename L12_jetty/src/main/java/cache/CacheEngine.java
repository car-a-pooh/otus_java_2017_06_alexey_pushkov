package cache;

/**
 * Created by carapooh on 24.09.2017.
 */
public interface CacheEngine<K, T extends CacheElement> {

    void put(K key, T element);

    T get(K key);

    void remove(K key);

    int getMissCount();

    int getHitCount();

    int getSize();

    default long getCurrentTime(){
        return System.currentTimeMillis();
    }
}
