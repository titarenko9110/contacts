package titarenko.test2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import titarenko.test2.domain.BookMaker;
import titarenko.test2.domain.Sport;

/**
 * Created by MyMac on 17.01.17.
 */
public interface BookMakerRepo extends JpaRepository<BookMaker, Integer>, JpaSpecificationExecutor<BookMaker> {

}


