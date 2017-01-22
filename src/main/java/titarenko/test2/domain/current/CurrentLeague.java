package titarenko.test2.domain.current;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by MyMac on 22.01.17.
 */

@Entity
@Table(name = "current_leagues")
public class CurrentLeague {

    @Id
    private String id;
    @Column(name = "league_id")
    private Integer leagueId;
    @Column(name = "league_name")
    private String leagueName;
    @Column(name = "country_id")
    private Integer countryId;
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

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
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
        return "CurrentLeague{" +
                "id='" + id + '\'' +
                ", leagueId=" + leagueId +
                ", leagueName='" + leagueName + '\'' +
                ", countryId=" + countryId +
                ", dateUpdate=" + dateUpdate +
                ", sportName='" + sportName + '\'' +
                '}';
    }
}
