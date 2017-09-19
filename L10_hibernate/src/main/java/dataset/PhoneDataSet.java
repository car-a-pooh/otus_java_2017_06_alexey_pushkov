package dataset;

import javax.persistence.*;

/**
 * Created by carapooh on 16.09.2017.
 */

@Entity
@Table(name = "phonedataset")
public class PhoneDataSet extends DataSet {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDataSet user;

    private String number;

    public PhoneDataSet(){super();}

    public PhoneDataSet(long id, String number){
        setId(id);
        this.number = number;
    }

    public void setUser(UserDataSet user){
        this.user = user;
    }

    public UserDataSet getUser(){
        return user;
    }

    public String getNumber(){
        return number;
    }
}
