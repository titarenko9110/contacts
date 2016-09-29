package titarenko.test2.service;

import org.apache.commons.collections.CollectionUtils;
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
        List<Contact> out = new ArrayList<>();
        Long from = 0L;
        Long to = 50000L;
        boolean hasNext = true;
        while (hasNext) {
            List<Contact> fromDb = jdbcContactRepoImpl.getContactsPart(from, to);
            if (CollectionUtils.isNotEmpty(fromDb)) {
                hasNext = false;
            } else {
                List<Contact> filter = filter(regex, fromDb);
                out.addAll(filter);
                from = to - 1;
                to = to + 50000;
            }

        }
        return out;
    }

    public List<Contact> filter(String regex, List<Contact> fromDb){
        return fromDb.stream().filter(s -> s.getName().matches(regex)).collect(Collectors.toList());
    }
}
