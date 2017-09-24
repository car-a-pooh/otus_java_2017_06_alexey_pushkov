package dataset;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            targetEntity = PhoneDataSet.class, mappedBy = "user")
    private List<PhoneDataSet> phones;

    public UserDataSet(){
        super();
    }

    public UserDataSet(long id, String name, int age, AddressDataSet address, List<PhoneDataSet> phones){
        setId(id);
        this.name = name;
        this.age = age;
        this.address = address;
        this.phones = phones;
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

    public AddressDataSet getAddress(){
        return address;
    }

    public void setAddress (AddressDataSet address){
        this.address = address;
    }

    public List<PhoneDataSet> getPhones(){
        return phones;
    }

    public void setPhones(List<PhoneDataSet> phones){
        this.phones = phones;
    }
}
