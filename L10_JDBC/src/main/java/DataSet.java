import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by carapooh on 09.09.2017.
 */

@Entity
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
