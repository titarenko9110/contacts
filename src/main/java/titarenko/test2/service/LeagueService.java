package titarenko.test2.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.collections.CollectionUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import titarenko.test2.domain.League;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyMac on 16.01.17.
 */

@Service
public class LeagueService {

    public List<League> getLeaguesBySportAndCountry(String sportName, Integer countryId) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get("https://api-"+MainService.liveOrPre+".allbestbets.ru/api/v2/" + sportName + "/countries/" + countryId + "/leagues?access_token=" + MainService.getToken())
                    .header("content-type", "application/json")
                    .asString();
        } catch (UnirestException e) {
        }
        if (response != null && response.getBody() != null) {
            JSONArray leagueJson = null;
            try {
                String body = response.getBody();
                JSONObject jsonObject = new JSONObject(body);
                JSONObject sport = jsonObject.getJSONObject("sport");
                JSONObject country = sport.getJSONObject("country");
                leagueJson = country.getJSONArray("league");
            } catch (Exception e) {
            }
            List<League> leagues = new ArrayList<>();
            if (leagueJson != null){
                for (int i = 0; i < leagueJson.length(); i++) {
                    JSONObject jsonObject = leagueJson.getJSONObject(i);
                    Integer id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    League league = new League();
                    league.setId(id);
                    league.setName(name);
                    leagues.add(league);

                }
            }
            if(CollectionUtils.isNotEmpty(leagues)){
                return leagues;
            }
        }
        return null;
    }
}
