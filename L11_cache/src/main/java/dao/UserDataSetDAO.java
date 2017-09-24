package dao;

import dataset.UserDataSet;
import dbservice.DBService;

/**
 * Created by carapooh on 09.09.2017.
 */
public class UserDataSetDAO {

    DBService dbService;

    public UserDataSetDAO(DBService dbService){
        this.dbService = dbService;
    }

    public void save(UserDataSet user){
        dbService.save(user);
    }

    public void saveOrUpdate(UserDataSet user){
        dbService.saveOrUpdate(user);
    }

    public void delete(UserDataSet user){
        dbService.delete(user);
    }

    public UserDataSet load(long id){
        return dbService.load(UserDataSet.class, id);
    }
}
