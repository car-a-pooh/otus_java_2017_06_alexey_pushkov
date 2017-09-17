import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by carapooh on 09.09.2017.
 */

@Entity
@Table(name = "userdataset")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name = "";

    @Column(name = "age")
    private int age = 0;

    public UserDataSet(){
        super();
    }

    public UserDataSet(long id, String name, int age){
        setId(id);
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        assert (age >= 0);
        this.age = age;
    }
}
