package titarenko.test2.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.collections.CollectionUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import titarenko.test2.domain.Period;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyMac on 16.01.17.
 */

@Service
public class PeriodService {

    public List<Period> getPeriodsBySportAndLeagueAndMarket(String sportName, Integer leaguesId, Integer marketId)  {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get("https://api-"+MainService.liveOrPre+".allbestbets.ru/api/v2/" + sportName + "/leagues/" + leaguesId + "/markets/" + marketId + "/periods?access_token=" + MainService.getToken())
                    .header("content-type", "application/json")
                    .asString();
        } catch (UnirestException e) {
        }
        JSONArray periodJson = null;
        if (response != null && response.getBody() != null) {
            try {
                String body = response.getBody();
                JSONObject jsonObject = new JSONObject(body);
                JSONObject sport = jsonObject.getJSONObject("sport");
                JSONObject country = sport.getJSONObject("country");
                JSONObject league = country.getJSONObject("league");
                JSONObject market = league.getJSONObject("market");
                periodJson = market.getJSONArray("period");
            } catch (Exception e) {
            }
        }
        List<Period> periods = new ArrayList<>();
        if (periodJson != null) {
            for (int i = 0; i < periodJson.length(); i++) {
                JSONObject jsonObject = periodJson.getJSONObject(i);
                String name = jsonObject.getString("name");
                Integer identifier = jsonObject.getInt("identifier");
                Period period = new Period();
                period.setIdentifier(identifier);
                period.setName(name);
                periods.add(period);
            }
        }
        if (CollectionUtils.isNotEmpty(periods)) {
            return periods;
        }
        return null;
    }
}
