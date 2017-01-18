package titarenko.test2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by MyMac on 17.01.17.
 */
@Entity
@Table(name = "bookmakers")
public class BookMaker {

    @Id
    private Integer id;
    private String name;
    private String url;
    private String reliability;
    private String cutting;
    @Column(name = "amount_arbs")
    private String amountArbs;
    private String coefficients;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReliability() {
        return reliability;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
    }

    public String getCutting() {
        return cutting;
    }

    public void setCutting(String cutting) {
        this.cutting = cutting;
    }

    public String getAmountArbs() {
        return amountArbs;
    }

    public void setAmountArbs(String amountArbs) {
        this.amountArbs = amountArbs;
    }

    public String getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(String coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public String toString() {
        return "BookMaker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", reliability='" + reliability + '\'' +
                ", cutting='" + cutting + '\'' +
                ", amountArbs='" + amountArbs + '\'' +
                ", coefficients='" + coefficients + '\'' +
                '}';
    }


}
