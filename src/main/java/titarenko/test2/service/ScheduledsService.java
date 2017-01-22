package titarenko.test2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import titarenko.test2.domain.Country;
import titarenko.test2.domain.League;
import titarenko.test2.domain.current.CurrentCountry;
import titarenko.test2.domain.current.CurrentLeague;
import titarenko.test2.repo.CurrentCountryRepo;
import titarenko.test2.repo.CurrentLeagueRepo;
import titarenko.test2.service.current.CurrentCountryService;
import titarenko.test2.service.current.CurrentLeagueService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MyMac on 20.01.17.
 */
@Service
public class ScheduledsService {

    @Autowired
    private CountryService countryService;
    @Autowired
    private CurrentCountryRepo currentCountryRepo;
    @Autowired
    private CurrentCountryService currentCountryService;
    @Autowired
    private CurrentLeagueRepo currentLeagueRepo;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private CurrentLeagueService currentLeagueService;


    public static final List<String> allSports = Arrays.asList("Basketball", "Handball", "Soccer", "Hockey", "Tennis", "Volleyball");

    @Scheduled(cron = "/5 * * * * *")
    @Transactional
    public void updateCountrys() {
        for (String sportName : allSports) {
            List<CurrentCountry> toAdd = new ArrayList<>();
            List<CurrentCountry> toRemove = new ArrayList<>();
            List<CurrentCountry> currentCountries = currentCountryRepo.findBySportName(sportName);
            List<Country> countriesData = countryService.getCountriesDataBySport(sportName);

            List<String> currentCountriesNames = currentCountries.stream().map(CurrentCountry::getName).collect(Collectors.toList());
            List<String> countriesDataNames = countriesData.stream().map(Country::getName).collect(Collectors.toList());
            Date date = new Date();
            for (Country country : countriesData) {
                if (!currentCountriesNames.contains(country.getName())) {
                    CurrentCountry objectByData = currentCountryService.createObjectByData(MainService.generate(), country.getId(), country.getName(), date, sportName);
                    toAdd.add(objectByData);
                }
            }
            for (CurrentCountry cc : currentCountries) {
                if (!countriesDataNames.contains(cc.getName())) {
                    toRemove.add(cc);
                }
            }
            currentCountryRepo.delete(toRemove);
            currentCountryRepo.save(toAdd);
            updateLeagues();
        }
    }

    @Transactional
    public void updateLeagues() {
        List<CurrentCountry> allCurrentСountrys = currentCountryRepo.findAll();
        for (CurrentCountry cc : allCurrentСountrys) {
            List<CurrentLeague> toAdd = new ArrayList<>();
            List<CurrentLeague> toRemove = new ArrayList<>();

            List<CurrentLeague> currentLeaguesInBase = currentLeagueRepo.findBySportNameAndCountryId(cc.getSportName(), cc.getCountryId());
            List<League> leaguesInSyte = leagueService.getLeaguesBySportAndCountry(cc.getSportName(), cc.getCountryId());

            List<Integer> currentLeaguesIdsInBase = currentLeaguesInBase.stream().map(CurrentLeague::getLeagueId).collect(Collectors.toList());
            List<Integer> leaguesIdsInSyte = leaguesInSyte.stream().map(League::getId).collect(Collectors.toList());
            Date date = new Date();

            for (League league : leaguesInSyte) {
                if (!currentLeaguesIdsInBase.contains(league.getId())) {
                    CurrentLeague objectByData = currentLeagueService.createObjectByData(MainService.generate(), league.getId(), league.getName(), cc.getCountryId(), date, cc.getSportName());
                    toAdd.add(objectByData);
                }
            }
            for (CurrentLeague currentLeague : currentLeaguesInBase) {
                System.out.println(currentLeague.getLeagueName());
                if (!leaguesIdsInSyte.contains(currentLeague.getLeagueId())) {
                    System.out.println("yas" + currentLeague.getLeagueName());
                    toRemove.add(currentLeague);
                }
            }
            currentLeagueRepo.delete(toRemove);
            currentLeagueRepo.save(toAdd);

        }
    }

    public void updateEvents() {
        List<CurrentLeague> allCurrentLeagues = currentLeagueRepo.findAll();
        for (CurrentLeague cL : allCurrentLeagues) {

        }
    }

}
