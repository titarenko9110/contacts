package titarenko.test2.repo;

import titarenko.test2.domain.Contact;

import java.util.List;

/**
 * Created by MyMac on 29.09.16.
 */

public interface ContactRepo {

    Integer getCount();

    List<Contact> getContactsPart(Integer from, Integer to);
}
