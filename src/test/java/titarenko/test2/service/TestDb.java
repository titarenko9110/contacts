package titarenko.test2.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import titarenko.test2.Application;
import titarenko.test2.repo.jdbc.TestRepo;

/**
 * Created by MyMac on 19.01.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
//@WebIntegrationTest
public class TestDb {

    @Autowired
    private TestRepo testRepo;

    @Test

    public void name() throws Exception {

    }
}
