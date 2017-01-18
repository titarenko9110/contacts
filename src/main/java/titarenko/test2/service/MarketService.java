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
import titarenko.test2.domain.Market;
import titarenko.test2.repo.MarketRepo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MyMac on 15.01.17.
 */
@Service
public class MarketService {

    @Autowired
    private MarketRepo marketRepo;
    @Autowired
    private MainService mainService;

    public List<Market> save(List<Market> markets) {
        return marketRepo.save(markets);
    }

    public List<Market> getAllMarketsFromSite() throws Exception {
        JSONObject allData = mainService.getAllData();
        JSONArray markets = null;
        try {
            markets = allData.getJSONArray("markets");
        } catch (Exception e) {
        }
        List<Market> out = new ArrayList<>();
        if (markets != null) {
            try {
                for (int i = 0; i < markets.length(); i++) {
                    JSONObject jsonObject = markets.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String shortTitle = jsonObject.getString("short_title");
                    Integer arbTypeId = jsonObject.getInt("arb_type_id");
                    Integer id = jsonObject.getInt("id");
                    if (title != null) {
                        Market market = new Market();
                        market.setId(id);
                        market.setTitle(title);
                        market.setShortTitle(shortTitle);
                        market.setArbTypeId(arbTypeId);
                        out.add(market);
                    }
                }
            }catch (Exception e){
            }
            if(CollectionUtils.isNotEmpty(out)){
                return out;
            }
        }
        return null;
    }

    public List<Market> getEventsBySportAndCountry(String sportName, Integer leagueId){
        HttpResponse<String> response = null;
        try {
            response = Unirest.get("https://api-"+MainService.liveOrPre+".allbestbets.ru/api/v2/"+sportName+"/leagues/"+leagueId+"/markets?access_token=" + MainService.getToken())
                    .header("content-type", "application/json")
                    .asString();
        } catch (UnirestException e) {
        }
        JSONArray marketJson = null;
        if (response != null && response.getBody() != null) {
            try {
                String body = response.getBody();
                JSONObject jsonObject = new JSONObject(body);
                JSONObject sport = jsonObject.getJSONObject("sport");
                JSONObject country = sport.getJSONObject("country");
                JSONObject league = country.getJSONObject("league");
                marketJson = league.getJSONArray("market");
            }catch (Exception e){
            }
        }
        List<Market> markets = new ArrayList<>();
        if (marketJson != null){
            for (int i = 0; i < marketJson.length(); i++) {
                JSONObject jsonObject = marketJson.getJSONObject(i);
                String title = jsonObject.getString("title");
                Integer id = jsonObject.getInt("id");
                String shortTitle = jsonObject.getString("short_title");
                Market market = new Market();
                market.setId(id);
                market.setTitle(title);
                market.setShortTitle(shortTitle);
                markets.add(market);
            }
        }
        if(CollectionUtils.isNotEmpty(markets)){
            return markets;
        }
        return null;
    }
}
