package titarenko.test2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import titarenko.test2.domain.Contact;
import titarenko.test2.service.ContactService;

import java.util.List;

/**
 * Created by MyMac on 27.09.16.
 */
@Controller
@RequestMapping("/hello")
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/contacts")
    @ResponseBody
    public List<Contact> getListContacts() {
        return contactService.getFilteredContacts();
    }
}
