package dao;

import dataset.PhoneDataSet;
import dbservice.DBService;

/**
 * Created by carapooh on 17.09.2017.
 */
public class PhoneDataSetDAO {

    DBService dbService;

    public PhoneDataSetDAO(DBService dbService){
        this.dbService = dbService;
    }

    public void save(PhoneDataSet phoneDataSet){
        dbService.save(phoneDataSet);
    }

    public void saveOrUpdate(PhoneDataSet phoneDataSet){
        dbService.saveOrUpdate(phoneDataSet);
    }

    public void delete(PhoneDataSet phoneDataSet){
        dbService.delete(phoneDataSet);
    }

    public PhoneDataSet load(long id){
        return dbService.load(PhoneDataSet.class, id);
    }

}
