package titarenko.test2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;
import titarenko.test2.domain.Contact;
import titarenko.test2.service.ContactService;

import java.io.IOException;
import java.util.List;

/**
 * Created by MyMac on 27.09.16.
 */
@Controller
@RequestMapping("/hello")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getListContacts(@RequestParam(value = "nameFilter", required = false) String nameFilter) throws IOException {
        return contactService.getFilteredContacts(nameFilter);
    }
}
