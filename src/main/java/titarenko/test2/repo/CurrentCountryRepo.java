package titarenko.test2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import titarenko.test2.domain.current.CurrentCountry;

import java.util.List;

/**
 * Created by MyMac on 20.01.17.
 */
@Repository
public interface CurrentCountryRepo extends JpaRepository<CurrentCountry, String>, JpaSpecificationExecutor<CurrentCountry> {

    List<CurrentCountry> findBySportName(String sportName);
}
