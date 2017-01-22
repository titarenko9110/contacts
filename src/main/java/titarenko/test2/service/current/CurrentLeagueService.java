package titarenko.test2.service.current;

import org.springframework.stereotype.Service;
import titarenko.test2.domain.current.CurrentLeague;

import java.util.Date;

/**
 * Created by MyMac on 22.01.17.
 */
@Service
public class CurrentLeagueService {
    public CurrentLeague createObjectByData(String id, Integer leagueId,String leagueName, Integer countryId, Date updateDate, String sportName) {
        CurrentLeague currentLeague = new CurrentLeague();
        currentLeague.setId(id);
        currentLeague.setLeagueId(leagueId);
        currentLeague.setLeagueName(leagueName);
        currentLeague.setCountryId(countryId);
        currentLeague.setDateUpdate(updateDate);
        currentLeague.setSportName(sportName);
        return currentLeague;
    }
}
