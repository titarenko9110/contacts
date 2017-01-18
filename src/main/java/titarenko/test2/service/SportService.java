package titarenko.test2.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import titarenko.test2.domain.Sport;
import titarenko.test2.repo.SportRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyMac on 15.01.17.
 */

@Service
public class SportService {
    @Autowired
    private SportRepo sportRepo;
    @Autowired
    private MainService mainService;


    public List<Sport> saveAllSports(List<Sport> sports) throws Exception {
        return sportRepo.save(sports);
    }

    public List<Sport> getSportsFromDb() {
        return sportRepo.findAll();
    }

    public List<Sport> getAllSportsFromSite() throws Exception {
        JSONObject allData = mainService.getAllData();
        JSONArray sports = null;
        try {
            sports = allData.getJSONArray("sports");
        } catch (Exception e) {
        }
        List<Sport> out = new ArrayList<>();
        if (sports != null) {
            for (int i = 0; i < sports.length(); i++) {
                JSONObject jsonObject = sports.getJSONObject(i);
                String name = jsonObject.getString("name");
                Integer id = jsonObject.getInt("id");
                if (name != null) {
                    Sport sport = new Sport();
                    sport.setId(id);
                    sport.setName(name);
                    out.add(sport);
                }
            }
            return out;
        }

        return null;
    }


}
