package titarenko.test2.domain;

/**
 * Created by MyMac on 16.01.17.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "started_at")
    private Date startedAt;
    @Column(name = "home")
    private String home;
    @Column(name = "away")
    private String away;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String hame) {
        this.home = hame;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String awey) {
        this.away = awey;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startedAt=" + startedAt +
                ", hame='" + home + '\'' +
                ", awey='" + away + '\'' +
                '}';
    }
}
