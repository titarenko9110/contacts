package titarenko.test2.service;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import titarenko.test2.Application;
import titarenko.test2.domain.*;
import titarenko.test2.domain.current.CurrentCountry;
import titarenko.test2.repo.CurrentCountryRepo;
import titarenko.test2.repo.SportRepo;

import java.awt.print.Book;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by MyMac on 15.01.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class MainServiceTest {

    @Autowired
    private MainService mainService;
    @Autowired
    private SportRepo sportRepo;
    @Autowired
    private SportService sportService;
    @Autowired
    private MarketService marketService;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private EventsService eventsService;
    @Autowired
    private PeriodService periodService;
    @Autowired
    private BetService betService;
    @Autowired
    private BookMakerSerice bookMakerSerice;
    @Autowired
    private ParseHtml parseHtml;
    @Autowired
    private CurrentCountryRepo currentCountryRepo;
    @Autowired
    private ScheduledsService scheduledsService;

    @Test
    public void name() throws Exception {
        List<String> list1 = Arrays.asList("a","b","c");
        List<String> list2 = Arrays.asList("b","c");

    }

    @Test
    public void name2() throws Exception {
        LocalTime now1 = LocalTime.now();
        scheduledsService.updateCountrys();
        scheduledsService.updateLeagues();
        LocalTime now2 = LocalTime.now();
        System.out.println(now1 + " " + now2);
    }

    @Test
    public void dccsd() throws Exception {
        //        bookMakerSerice.save(bookMakerSerice.getAllBookmakerFromSute());
//        parseHtml.saveHtmlBookmaker();
        Sport sport = new Sport();
        sport.setName("Hockey");
        betService.doMain(sport);
//        Sport sport3 = new Sport();
//        sport3.setLeagueName("Basketball");
//        betService.doMain(sport3);

        Thread.sleep(4000000);

    }

//    @Test
    public void getToken() throws Exception {

        Sport sport = new Sport();
        sport.setName("Basketball");
//        List<Sport> sportsFromDb = sportService.getSportsFromDb();
//        for (Sport sport : sportsFromDb) {
        LocalTime now1 = LocalTime.now();
        System.out.println(sport.getName());
        List<Country> countriesDataBySport = countryService.getCountriesDataBySport(sport.getName());
        for (Country country : countriesDataBySport) {
            List<League> leaguesBySportAndCountry = leagueService.getLeaguesBySportAndCountry(sport.getName(), country.getId());
            for (League league : leaguesBySportAndCountry) {
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
                                    Map<OutCome, List<Bet>> bets = betService.getBetsBySportEventMarketPeriod(sport.getName(), event.getId(), marketId, period.getIdentifier());
                                    if(bets.size() > 0){
                                        System.out.println("=====================================================================");
                                        System.out.println("Country" + country.getName() + " League " + league.getName() + " Event " + event.getName() + " Market " + marketId + "Period" + period.getName());
                                        for (Map.Entry<OutCome, List<Bet>> entryBet : bets.entrySet()){
                                            int count = 0;
                                            for (Bet b : entryBet.getValue()) {
                                                String bookmaker = b.getBookmaker();
                                                if(bookmaker.contains("Bet365") || bookmaker.contains("Pinnacle") || bookmaker.contains("Sbobet") || bookmaker.contains("PaddyPower")){
                                                    count++;
                                                }
                                            }
                                            if(!(count >3)){
                                                continue;
                                            }
                                            System.out.println("---------------------------------------------------------------------");
                                            System.out.println("OutCame : " + entryBet.getKey());
                                            for (Bet bet : entryBet.getValue()) {
                                                System.out.println("BET");
                                                System.out.println("Boookmaker" + bet.getBookmaker() + " Odd " + bet.getOdd());
                                            }
                                            System.out.println("+++++++++");
                                            System.out.println(betService.getMinOdd(entryBet.getValue()));
                                            System.out.println(betService.getMaxOdd(entryBet.getValue()));
                                            System.out.println(betService.getMiddleOdd(entryBet.getValue()));
                                            System.out.println("+++++++++");
                                            System.out.println("---------------------------------------------------------------------");
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
        System.out.println(now1 + " " + now2);
//        System.out.println("per "+now2. - now1);
        System.out.println("Count " + MainService.count);
//        }
    }
}