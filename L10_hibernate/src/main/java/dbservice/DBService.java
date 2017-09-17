package dbservice;

import dataset.DataSet;

/**
 * Created by carapooh on 10.09.2017.
 */
public interface DBService {

    void save(DataSet dataSet);

    <T extends DataSet> T load(Class<T> klass, long id);
}
