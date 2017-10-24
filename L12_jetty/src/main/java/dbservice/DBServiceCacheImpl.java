package dbservice;

import cache.CacheEngine;
import cache.CacheEngineSoftReferenceImpl;
import cache.SoftReferenceCacheElement;
import dataset.DataSet;

/**
 * Created by carapooh on 24.09.2017.
 */
public class DBServiceCacheImpl extends DBServiceHibernateImpl {

    private CacheEngineSoftReferenceImpl<Long, DataSet> cacheEngine =
            new CacheEngineSoftReferenceImpl<>(100000, 10000L, 1000L);

    public DBServiceCacheImpl(CacheEngineSoftReferenceImpl engine){
        cacheEngine = engine;
    }

    @Override
    public void save(DataSet dataSet){
        super.save(dataSet);
        cacheEngine.put(dataSet.getId(), new SoftReferenceCacheElement<>(dataSet, cacheEngine.getCurrentTime()));
    }

    @Override
    public void saveOrUpdate(DataSet dataSet){
        super.saveOrUpdate(dataSet);
        cacheEngine.put(dataSet.getId(), new SoftReferenceCacheElement<>(dataSet, cacheEngine.getCurrentTime()));
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
            T dataSet = super.load(klass, id);
            cacheEngine.put(dataSet.getId(), new SoftReferenceCacheElement<>(dataSet, cacheEngine.getCurrentTime()));
            return dataSet;
        }
    }
}
