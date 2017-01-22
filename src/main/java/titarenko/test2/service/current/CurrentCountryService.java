package titarenko.test2.service.current;

import org.springframework.stereotype.Service;
import titarenko.test2.domain.current.CurrentCountry;

import java.util.Date;

/**
 * Created by MyMac on 21.01.17.
 */

@Service
public class CurrentCountryService {

    public CurrentCountry createObjectByData(String id,Integer countryId,String name,Date updateDate,String sportName){
        CurrentCountry currentCountry = new CurrentCountry();
        currentCountry.setId(id);
        currentCountry.setCountryId(countryId);
        currentCountry.setName(name);
        currentCountry.setDateUpdate(updateDate);
        currentCountry.setSportName(sportName);
        return currentCountry;
    }
}
