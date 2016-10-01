package titarenko.test2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import titarenko.test2.domain.Contact;
import titarenko.test2.repo.jdbc.JdbcContactRepoImpl;
import titarenko.test2.service.ContactService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by MyMac on 30.09.16.
 */
@RunWith(MockitoJUnitRunner.class )
public class ContactServiceTest {

    @Mock
    private JdbcContactRepoImpl jdbcContactRepoImpl;
    @InjectMocks
    ContactService contactService;


    @Test
    public void filterTest() {
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact();
        contact.setId(1);
        contact.setName("Abcd");
        Contact contact2 = new Contact();
        contact2.setName("sdcs");
        contact2.setId(2);
        contacts.add(contact);
        contacts.add(contact2);
        List<Contact> filter = contactService.filter("^A.*$", contacts);
        assertThat(filter.size(), is(1));
        assertEquals(filter.get(0), contact2);
    }

}