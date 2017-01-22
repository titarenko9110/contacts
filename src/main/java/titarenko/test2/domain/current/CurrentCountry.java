package titarenko.test2.domain.current;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by MyMac on 20.01.17.
 */

@Entity
@Table(name = "current_countrys")
public class CurrentCountry {

    @Id
    private String id;
    @Column(name = "country_id")
    private Integer countryId;
    @Column(name = "name")
    private String name;
    @Column(name = "date_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;
    @Column(name = "sport_name")
    private String sportName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    @Override
    public String toString() {
        return "CurrentCountry{" +
                "id='" + id + '\'' +
                ", countryId=" + countryId +
                ", name='" + name + '\'' +
                ", dateUpdate=" + dateUpdate +
                ", sportName=" + sportName +
                '}';
    }
}
