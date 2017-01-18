package titarenko.test2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by MyMac on 17.01.17.
 */

@Entity
@Table(name = "outcomes")
public class OutCome {

    @Id
    @Column(name = "variation")
    String variation;

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    @Override
    public String toString() {
        return "OutCome{" +
                "variation='" + variation + '\'' +
                '}';
    }
}
