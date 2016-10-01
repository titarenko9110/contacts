package titarenko.test2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import titarenko.test2.domain.Contact;
import titarenko.test2.repo.jdbc.JdbcContactRepoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MyMac on 27.09.16.
 */

@Service
public class ContactService {

    @Autowired
    private JdbcContactRepoImpl jdbcContactRepoImpl;

    public List<Contact> getFilteredContacts(String regex) {
        Integer from= 0;
        Integer to = 50001;
        List<Contact> out = new ArrayList<>();
        List<Contact> fromDb;
        do {
            fromDb = jdbcContactRepoImpl.getContactsPart(from, to);
            List<Contact> filter = filter(regex, fromDb);
            out.addAll(filter);
            from = to - 1;
            to = to + 50000;
        } while (!fromDb.isEmpty());
        return out;
    }

    public List<Contact> filter(String regex, List<Contact> fromDb) {
        return fromDb.stream().filter(s -> !s.getName().matches(regex)).collect(Collectors.toList());
    }
}
