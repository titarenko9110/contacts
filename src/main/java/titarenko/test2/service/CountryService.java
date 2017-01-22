package titarenko.test2.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import titarenko.test2.domain.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyMac on 16.01.17.
 */
@Service
public class CountryService {

    public List<Country> getCountriesDataBySport(String sportName) {
        HttpResponse<String> response = null;
        try {
            String url = "https://api-"+MainService.liveOrPre+".allbestbets.ru/api/v2/" + sportName + "/countries?access_token=" + MainService.getToken();
            response = Unirest.get(url)
                    .header("content-type", "application/json")
                    .asString();
        } catch (UnirestException e) {
        }
        if (response != null && response.getBody() != null) {
            JSONArray jsonArray = null;
            try {
                String body = response.getBody();
                System.out.println(body);
                System.out.println(response);

                JSONObject jsonObject = new JSONObject(body);
                JSONObject sports = jsonObject.getJSONObject("sport");
                jsonArray = sports.getJSONArray("country");
            } catch (Exception e) {
            }
            List<Country> countries = new ArrayList<>();
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject sport = jsonArray.getJSONObject(i);
                    Integer id = sport.getInt("id");
                    String name = sport.getString("name");
                    if (name != null) {
                        Country country = new Country();
                        country.setId(id);
                        country.setName(name);
                        countries.add(country);
                    }
                }
            }
            return countries;
        }
        return null;
    }
}
