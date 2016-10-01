package titarenko.test2.repo.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import titarenko.test2.Application;
import titarenko.test2.domain.Contact;
import titarenko.test2.service.ContactService;
import titarenko.test2.web.ContactController;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * Created by MyMac on 29.09.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class JdbcContactRepoImplTest {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test(){
    }

//    @Test
    public void getContactsTest() throws Exception {
        ResponseEntity<List<Contact>> responseEntity = restTemplate.exchange("http://localhost:8090/hello/contacts?nameFilter=^A.*$",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Contact>>() {
                });

        List<Contact> body = responseEntity.getBody();
        System.out.println(body);
        assertThat(body.size(), is(1));
        assertEquals(body.get(0).getName(), "cdf");
    }






}