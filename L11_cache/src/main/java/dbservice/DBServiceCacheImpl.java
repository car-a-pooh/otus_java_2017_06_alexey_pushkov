package dbservice;

import cache.CacheElement;
import cache.CacheEngineSoftReferenceImpl;
import cache.SoftReferenceCacheElement;
import dataset.DataSet;

/**
 * Created by carapooh on 24.09.2017.
 */
public class DBServiceCacheImpl extends DBServiceHibernateImpl {

    private final CacheEngineSoftReferenceImpl<Long, DataSet> cacheEngine =
            new CacheEngineSoftReferenceImpl<>(100000, 10000L, 1000L);

    @Override
    public void save(DataSet dataSet){
        cacheEngine.put(dataSet.getId(), new SoftReferenceCacheElement<>(dataSet, cacheEngine.getCurrentTime()));
        super.save(dataSet);
    }

    @Override
    public void saveOrUpdate(DataSet dataSet){
        cacheEngine.put(dataSet.getId(), new SoftReferenceCacheElement<>(dataSet, cacheEngine.getCurrentTime()));
        super.saveOrUpdate(dataSet);
    }

    @Override
    public void delete(DataSet dataSet){
        cacheEngine.remove(dataSet.getId());
        super.delete(dataSet);
    }

    @Override
    public <T extends DataSet> T load(Class<T> klass, long id){
        SoftReferenceCacheElement<DataSet> element = cacheEngine.get(id);
        if (element != null && element.getValue() != null && element.getValue().get() != null) {
            return (T) element.getValue().get();
        }
        else {
            return super.load(klass, id);
        }
    }
}
