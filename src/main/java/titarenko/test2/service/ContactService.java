package titarenko.test2.service;

import org.springframework.stereotype.Service;
import titarenko.test2.domain.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyMac on 27.09.16.
 */

@Service
public class ContactService {

    public static List<Contact> items = new ArrayList<Contact>();

    static {
        items.add(new Contact(1, "abc"));
    }

    public List<Contact> getFilteredContacts() {
        return items;
    }
}
