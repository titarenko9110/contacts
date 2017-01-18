package titarenko.test2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by MyMac on 16.01.17.
 */

@Entity
@Table(name = "periods")
public class Period {


    @Id
    @Column(name = "identifier")
    private Integer identifier;
    @Column(name = "name")
    private String name;

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Period{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                '}';
    }
}


