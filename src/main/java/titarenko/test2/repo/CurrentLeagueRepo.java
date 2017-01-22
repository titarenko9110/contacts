package titarenko.test2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import titarenko.test2.domain.current.CurrentLeague;

import java.util.List;

/**
 * Created by MyMac on 22.01.17.
 */
@Repository
public interface CurrentLeagueRepo extends JpaRepository<CurrentLeague, String>, JpaSpecificationExecutor<CurrentLeague> {

    List<CurrentLeague> findBySportNameAndCountryId(String sportName,Integer countryId);
}
