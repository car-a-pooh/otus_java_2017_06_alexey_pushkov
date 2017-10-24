package dataset;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by carapooh on 09.09.2017.
 */

@MappedSuperclass
public abstract class DataSet {

    @Id
    private long id = 0;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
}
