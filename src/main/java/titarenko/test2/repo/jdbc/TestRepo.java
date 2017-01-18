package titarenko.test2.repo.jdbc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import titarenko.test2.domain.TestDb;

/**
 * Created by MyMac on 19.01.17.
 */
public interface TestRepo extends JpaRepository<TestDb, Integer>, JpaSpecificationExecutor<TestDb> {

}
