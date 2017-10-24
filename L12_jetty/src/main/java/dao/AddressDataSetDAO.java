package dao;

import dataset.AddressDataSet;
import dbservice.DBService;

/**
 * Created by carapooh on 16.09.2017.
 */
public class AddressDataSetDAO {

    DBService dbService;

    public AddressDataSetDAO(DBService dbService){
        this.dbService = dbService;
    }

    public void save(AddressDataSet addressDataSet){
        dbService.save(addressDataSet);
    }

    public void saveOrUpdate(AddressDataSet addressDataSet){
        dbService.saveOrUpdate(addressDataSet);
    }

    public void delete(AddressDataSet addressDataSet){
        dbService.delete(addressDataSet);
    }

    public AddressDataSet load(long id){
        return dbService.load(AddressDataSet.class, id);
    }
}
