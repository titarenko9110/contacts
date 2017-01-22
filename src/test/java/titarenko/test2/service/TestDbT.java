package titarenko.test2.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import titarenko.test2.Application;
import titarenko.test2.domain.TestDb;
import titarenko.test2.repo.jdbc.TestRepo;

import java.util.Date;

/**
 * Created by MyMac on 19.01.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
//@WebIntegrationTest
public class TestDbT {
    @Autowired
    private TestRepo testRepo;

//    @Test
    public void name() throws Exception {
        Date date = new Date();

        TestDb testDb = new TestDb();
        TestDb testDb2 = new TestDb();

        testDb.setName("A");
        testDb2.setName("B");
        testDb.setId("1");
        testDb2.setId("2");
        testDb.setDate(date);
        testDb2.setDate(date);

        testRepo.save(testDb);
        testRepo.save(testDb2);


    }
}
