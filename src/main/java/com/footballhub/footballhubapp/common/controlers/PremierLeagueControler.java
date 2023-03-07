package com.footballhub.footballhubapp.common.controlers;

import com.footballhub.footballhubapp.common.models.PremierLeagueModel;
import com.footballhub.footballhubapp.common.repository.PremierLeagueRepository;
import com.footballhub.footballhubapp.common.utils.CalculateStandings;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PremierLeagueControler {
    @Autowired
    private PremierLeagueRepository premierLeagueRepository;

    @GetMapping("/find-by-id/{id}")
    public PremierLeagueModel getById(@PathVariable String id) {
        return premierLeagueRepository.findById(new ObjectId(id)).orElse(null);
    }

    // TODO: update the Standings after each GameWeek

    //    @GetMapping("/command-get-epl-standings")
//    public List<PremierLeagueModel> listPremierLeagueStandings() {
//        CalculateStandings calculateStandings = new CalculateStandings(premierLeagueRepository.findAll());
//        List<PremierLeagueModel> standings = calculateStandings.evaluate();
//        return standings;
//    }
    @GetMapping("/command-get-epl-standings")
    public List<Map.Entry<String, Integer>> listPremierLeagueStandings() {
        CalculateStandings calculateStandings = new CalculateStandings(premierLeagueRepository.findAll());
        List<Map.Entry<String, Integer>> standings = calculateStandings.evaluate();
        return standings;
    }
}
