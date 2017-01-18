package titarenko.test2.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.collections.CollectionUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import titarenko.test2.domain.Event;
import titarenko.test2.domain.League;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MyMac on 16.01.17.
 */

@Service
public class EventsService {

    @Autowired
    private LeagueService leagueService;

    public List<Event> getEventsBySportAndCountry(String sportName, Integer leaguesId) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get("https://api-"+MainService.liveOrPre+".allbestbets.ru/api/v2/" + sportName + "/leagues/" + leaguesId + "/events?access_token=" + MainService.getToken())
                    .header("content-type", "application/json")
                    .asString();
        } catch (UnirestException e) {
        }
        JSONArray eventJson = null;
        if (response != null && response.getBody() != null) {
            try {
                String body = response.getBody();
                JSONObject jsonObject = new JSONObject(body);
                JSONObject sport = jsonObject.getJSONObject("sport");
                JSONObject country = sport.getJSONObject("country");
                JSONObject league = country.getJSONObject("league");
                eventJson = league.getJSONArray("event");
            }catch (Exception e){
            }
        }
        List<Event> events = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        if (eventJson != null){
            for (int i = 0; i < eventJson.length(); i++) {
                JSONObject jsonObject = eventJson.getJSONObject(i);
                String name = jsonObject.getString("name");
                Integer id = jsonObject.getInt("id");
                String home = jsonObject.getString("home");
                String away = jsonObject.getString("away");
                String startedAt = jsonObject.getString("started_at");
                Date date = null;
                try {
                    date = formatter.parse(startedAt.replaceAll("Z$", "+0000"));
                } catch (ParseException e) {
                }
                Event event = new Event();
                event.setId(id);
                event.setName(name);
                event.setAway(away);
                event.setHome(home);
                event.setStartedAt(date);
                events.add(event);
            }
        }
        if(CollectionUtils.isNotEmpty(events)){
            return events;
        }
        return null;
    }

}
