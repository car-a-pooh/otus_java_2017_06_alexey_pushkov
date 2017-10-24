package cache;

/**
 * Created by carapooh on 24.09.2017.
 */
public interface CacheElement<V> {

    V getValue();

    long getCreationTime();

    long getAccessedTime();

    void setJustAccessed();

    default long getCurrentTime(){
        return System.currentTimeMillis();
    }
}
