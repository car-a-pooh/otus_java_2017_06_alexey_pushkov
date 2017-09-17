package dataset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by carapooh on 16.09.2017.
 */

@Entity
@Table(name = "addressdataset")
public class AddressDataSet extends DataSet {

    @Column
    private String street = "";

    public AddressDataSet(){super();}

    public AddressDataSet(long id, String street){
        setId(id);
        this.street = street;
    }

    public String getStreet(){
        return street;
    }
}
