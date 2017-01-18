package titarenko.test2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by MyMac on 15.01.17.
 */
@Entity
@Table(name = "markets")
public class Market {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "short_title")
    private String shortTitle;
    @Column(name = "arb_type_id")
    private Integer arbTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public Integer getArbTypeId() {
        return arbTypeId;
    }

    public void setArbTypeId(Integer arbTypeId) {
        this.arbTypeId = arbTypeId;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortTitle='" + shortTitle + '\'' +
                ", arbTypeId=" + arbTypeId +
                '}';
    }
}
