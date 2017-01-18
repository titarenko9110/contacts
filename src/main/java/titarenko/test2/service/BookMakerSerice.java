package titarenko.test2.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import titarenko.test2.domain.BookMaker;
import titarenko.test2.domain.Sport;
import titarenko.test2.repo.BookMakerRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyMac on 17.01.17.
 */

@Service
public class BookMakerSerice {

    @Autowired
    private MainService mainService;
    @Autowired
    private BookMakerRepo bookMakerRepo;

    public List<BookMaker> save(List<BookMaker> bookmakers) {
        return bookMakerRepo.save(bookmakers);
    }

    public List<BookMaker> getAll() {
        return bookMakerRepo.findAll();
    }


    public List<BookMaker> getAllBookmakerFromSute() throws Exception {
        JSONObject allData = mainService.getAllData();
        JSONArray bookmakers = null;
        try {
            bookmakers = allData.getJSONArray("bookmakers");
        } catch (Exception e) {
        }
        List<BookMaker> out = new ArrayList<>();
        if (bookmakers != null) {
            for (int i = 0; i < bookmakers.length(); i++) {
                JSONObject jsonObject = bookmakers.getJSONObject(i);
                String name = jsonObject.getString("name");
                String url = jsonObject.getString("url");
                Integer id = jsonObject.getInt("id");
                if (name != null) {
                    BookMaker bookMaker = new BookMaker();
                    bookMaker.setId(id);
                    bookMaker.setName(name);
                    bookMaker.setUrl(url);
                    out.add(bookMaker);
                }
            }
            return out;
        }

        return null;
    }
}
