package dataset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by carapooh on 16.09.2017.
 */

@Entity
@Table(name = "phonedataset")
public class PhoneDataSet extends DataSet {

    @Column
    private String number;

    public PhoneDataSet(){super();}

    public PhoneDataSet(long id, String number){
        setId(id);
        this.number = number;
    }
}
