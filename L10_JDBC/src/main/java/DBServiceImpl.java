import java.sql.SQLException;

/**
 * Created by carapooh on 10.09.2017.
 */
public class DBServiceImpl implements DBService {

    private UserDataSetDAO dataSetDAO;

    public DBServiceImpl(UserDataSetDAO dataSetDAO){
        this.dataSetDAO = dataSetDAO;
    }

    @Override
    public void save(DataSet dataSet) {
        try {
            dataSetDAO.save((UserDataSet) dataSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends DataSet> T load(Class<T> klass, long id) {
        DataSet dataSet = null;
        try {
             dataSet = dataSetDAO.load(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (T) dataSet;
    }

}
