package titarenko.test2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import titarenko.test2.domain.Market;
import titarenko.test2.domain.Sport;

/**
 * Created by MyMac on 15.01.17.
 */
public interface MarketRepo extends JpaRepository<Market, Integer>, JpaSpecificationExecutor<Market> {
}
