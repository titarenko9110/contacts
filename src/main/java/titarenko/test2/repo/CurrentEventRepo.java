package titarenko.test2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import titarenko.test2.domain.current.CurrentCountry;
import titarenko.test2.domain.current.CurrentEvent;

/**
 * Created by MyMac on 22.01.17.
 */

@Repository
public interface CurrentEventRepo extends JpaRepository<CurrentEvent, String>, JpaSpecificationExecutor<CurrentEvent> {
}
