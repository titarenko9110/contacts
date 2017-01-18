package titarenko.test2.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import titarenko.test2.domain.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by MyMac on 17.01.17.
 */

@Service
public class BetService {



    @Autowired
    private LeagueService leagueService;
    @Autowired
    private EventsService eventsService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private PeriodService periodService;
    @Autowired
    private CountryService countryService;

    private static final ExecutorService threadPool = Executors.newCachedThreadPool();


    public Map<OutCome, List<Bet>> getBetsBySportEventMarketPeriod(String sportName, Integer eventId, Integer marketId, Integer periodId) {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get("https://api-"+MainService.liveOrPre+"."+MainService.syte+".ru/api/v2/" + sportName + "/events/" + eventId + "/markets/" + marketId + "/periods/" + periodId + "/bets?access_token=" + MainService.getToken())
                    .header("content-type", "application/json")
                    .asString();
        } catch (UnirestException e) {
        }
        JSONArray outcome = null;
        if (response != null && response.getBody() != null) {
            try {
                String body = response.getBody();
                JSONObject jsonObject = new JSONObject(body);
                JSONObject sport = jsonObject.getJSONObject("sport");
                JSONObject country = sport.getJSONObject("country");
                JSONObject league = country.getJSONObject("league");
                JSONObject event = league.getJSONObject("event");
                JSONObject market = event.getJSONObject("market");
                JSONObject period = market.getJSONObject("period");
                outcome = period.getJSONArray("outcome");
            } catch (Exception e) {
            }
        }
        Map<OutCome, List<Bet>> outcomeBetsOut = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        if (outcome != null) {
            for (int i = 0; i < outcome.length(); i++) {
                JSONObject jsonObject = outcome.getJSONObject(i);
                String variation = jsonObject.getString("variation");
                OutCome outComeObj = new OutCome();
                outComeObj.setVariation(variation);

                List<Bet> betList = new ArrayList<>();

                JSONArray bets = jsonObject.getJSONArray("bet");
                if (bets.length() > 0) {
                    for (int j = 0; j < bets.length(); j++) {
                        JSONObject bet = null;
                        try {
                            bet = bets.getJSONObject(j);
                        } catch (Exception e) {
                        }
                        if (bet != null) {
                            String bookmaker = bet.getString("bookmaker");
                            Integer id = bet.getInt("id");
                            Double odd = bet.getDouble("odd");
                            String updated_at = bet.getString("updated_at");
                            Date date = null;
                            try {
                                date = formatter.parse(updated_at.replaceAll("Z$", "+0000"));
                            } catch (ParseException e) {
                            }
                            Bet betObj = new Bet();
                            betObj.setId(id);
                            betObj.setBookmaker(bookmaker);
                            betObj.setUpdatedAt(date);
                            betObj.setOdd(odd);
                            betList.add(betObj);
                        }
                    }
                }
                if (CollectionUtils.isNotEmpty(betList)) {
                    outcomeBetsOut.put(outComeObj, betList);
                }
            }
        }
        return outcomeBetsOut;
    }


    public Double getMiddleOdd(List<Bet> bets) {
        Double mid = 0.0d;
        for (Bet bet : bets) {
            mid += bet.getOdd();
        }
        return mid / bets.size();
    }

    public Double getMinOdd(List<Bet> bets) {
        Double min = null;
        for (Bet bet : bets) {
            Double odd = bet.getOdd();
            if (min == null) {
                min = odd;
            } else {
                if (min > bet.getOdd()) {
                    min = odd;
                }
            }
        }
        return min;
    }

    public Double getMaxOdd(List<Bet> bets) {
        Double max = null;
        for (Bet bet : bets) {
            Double odd = bet.getOdd();
            if (max == null) {
                max = odd;
            } else {
                if (max < bet.getOdd()) {
                    max = odd;
                }
            }
        }
        return max;
    }


    public void mainInNewThread(Sport sport){
        threadPool.execute(() -> {
            doMain(sport);
//            if(sport.getName().equals("Basketball")){
//                for (int i = 0; i < 30; i++){
//                    try {
//                        FileUtils.writeStringToFile(new File("/Users/MyMac/Desktop/BETS/BasketballIO.txt"), "\n "+LocalTime.now()+" \n",true);
//                    } catch (IOException e) {
//                    }
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            if(sport.getName().equals("Hockey")){
//                for (int i = 0; i < 30; i++){
//                    try {
//                        FileUtils.writeStringToFile(new File("/Users/MyMac/Desktop/BETS/HockeyIO.txt"), "\n "+LocalTime.now()+" \n",true);
//                    } catch (IOException e) {
//                    }
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }


        });
    }



    public void doMain(Sport sport)  {
        System.out.println(sport.getName());
//        List<Sport> sportsFromDb = sportService.getSportsFromDb();
//        for (Sport sport : sportsFromDb) {
        LocalTime now1 = LocalTime.now();
//            System.out.println(sport.getName());
        List<Country> countriesDataBySport = countryService.getCountriesDataBySport(sport.getName());
        for (Country country : countriesDataBySport) {
            List<League> leaguesBySportAndCountry = leagueService.getLeaguesBySportAndCountry(sport.getName(), country.getId());
             for (League league : leaguesBySportAndCountry) {
                System.out.println("here" + sport.getName());
                List<Event> eventsBySportAndCountry = eventsService.getEventsBySportAndCountry(sport.getName(), league.getId());
                List<Market> markets = marketService.getEventsBySportAndCountry(sport.getName(), league.getId());
                Map<Integer, List<Period>> periodsInMarket = new HashMap<>();
                if (CollectionUtils.isNotEmpty(markets)) {
                    for (Market market : markets) {
                        List<Period> periods = periodService.getPeriodsBySportAndLeagueAndMarket(sport.getName(), league.getId(), market.getId());
                        periodsInMarket.put(market.getId(), periods);
                    }
                    for (Event event : eventsBySportAndCountry) {
                        for (Map.Entry<Integer, List<Period>> entry : periodsInMarket.entrySet()) {
                            Integer marketId = entry.getKey();
                            List<Period> periods = entry.getValue();
                            if (CollectionUtils.isNotEmpty(periods)) {
                                for (Period period : periods) {
                                    Map<OutCome, List<Bet>> bets = getBetsBySportEventMarketPeriod(sport.getName(), event.getId(), marketId, period.getIdentifier());
                                    if(bets.size() > 0){
                                        for (Map.Entry<OutCome, List<Bet>> entryBet : bets.entrySet()){
                                            int count = 0;
                                            for (Bet b : entryBet.getValue()) {
                                                String bookmaker = b.getBookmaker();
                                                if(bookmaker.contains("Bet365") || bookmaker.contains("Pinnacle") || bookmaker.contains("Sbobet") || bookmaker.contains("PaddyPower")){
                                                    count++;
                                                }
                                            }
                                            if(!(count >1)){
                                                continue;
                                            }
                                            writeToFile(sport.getName(),""+"=====================================================================\n");
                                            writeToFile(sport.getName(),""+"Country" + country.getName() + " League " + league.getName() + " Event " + event.getName() + " Market " + marketId + "Period" + period.getName() +"\n");
                                            writeToFile(sport.getName(),""+"---------------------------------------------------------------------"+"\n");
                                            writeToFile(sport.getName(),""+"OutCame : " + entryBet.getKey()+"\n");
                                            for (Bet bet : entryBet.getValue()) {
                                                writeToFile(sport.getName(),""+"BET"+"\n");
                                                writeToFile(sport.getName(),""+"Boookmaker" + bet.getBookmaker() + " Odd " + bet.getOdd()+"\n");
                                            }
                                            writeToFile(sport.getName(),""+"+++++++++"+"\n");
                                            writeToFile(sport.getName(),""+getMinOdd(entryBet.getValue())+"\n");
                                            writeToFile(sport.getName(),""+getMaxOdd(entryBet.getValue())+"\n");
                                            writeToFile(sport.getName(),""+getMiddleOdd(entryBet.getValue())+"\n");
                                            writeToFile(sport.getName(),"+++++++++"+"\n");
                                            writeToFile(sport.getName(),"---------------------------------------------------------------------"+"\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        LocalTime now2 = LocalTime.now();
        writeToFile(sport.getName(),""+now1 + " " + now2+"\n");
        writeToFile(sport.getName(),""+"Count " + MainService.count+"\n");
//        }
    }

    public void writeToFile(String sport,String text) {
        String path = "/Users/MyMac/Desktop/BETS/OtherIO.txt";
        if(sport.equals("Basketball")){
            path = "/Users/MyMac/Desktop/BETS/BasketballIO.txt";
        }
        if(sport.equals("Hockey")){
            path = "/Users/MyMac/Desktop/BETS/HockeyIO.txt";
        }
        if(sport.equals("Soccer")){
            path = "/Users/MyMac/Desktop/BETS/SoccerIO.txt";
        }
        if(sport.equals("Tennis")){
            path = "/Users/MyMac/Desktop/BETS/TennisIO.txt";
        }
        try {
            FileUtils.writeStringToFile(new File(path), text,true);
        } catch (IOException e) {
        }

    }
}
