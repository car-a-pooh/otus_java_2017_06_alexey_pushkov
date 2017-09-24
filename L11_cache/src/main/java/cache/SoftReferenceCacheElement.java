package cache;

import java.lang.ref.SoftReference;

/**
 * Created by carapooh on 24.09.2017.
 */
public class SoftReferenceCacheElement<T> implements CacheElement<SoftReference<T>> {

    private final SoftReference<T> value;
    private final long creationTimeInMilliseconds;
    private long lastAccessTimeInMilliseconds;

    public SoftReferenceCacheElement(T t, long creationTimeInMilliseconds){
        value = new SoftReference<T>(t);
        this.creationTimeInMilliseconds = creationTimeInMilliseconds;
        lastAccessTimeInMilliseconds = creationTimeInMilliseconds;
    }

    @Override
    public SoftReference<T> getValue() {
        return value;
    }

    @Override
    public long getCreationTime() {
        return creationTimeInMilliseconds;
    }

    @Override
    public long getAccessedTime() {
        return lastAccessTimeInMilliseconds;
    }

    @Override
    public void setJustAccessed() {
        lastAccessTimeInMilliseconds = getCurrentTime();
    }
}
