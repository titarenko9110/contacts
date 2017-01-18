package titarenko.test2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by MyMac on 16.01.17.
 */
@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "bookmaker")
    private String bookmaker;
    @Column(name = "odd")
    private Double odd;
    @Column(name = "updated_at")
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookmaker() {
        return bookmaker;
    }

    public void setBookmaker(String bookmaker) {
        this.bookmaker = bookmaker;
    }

    public Double getOdd() {
        return odd;
    }

    public void setOdd(Double odd) {
        this.odd = odd;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", bookmaker='" + bookmaker + '\'' +
                ", odd='" + odd + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
