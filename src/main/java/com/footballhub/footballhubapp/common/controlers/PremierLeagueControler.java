package com.footballhub.footballhubapp.common.controlers;

import com.footballhub.footballhubapp.common.models.PremierLeagueModel;
import com.footballhub.footballhubapp.common.repository.PremierLeagueRepository;
import com.footballhub.footballhubapp.common.utils.CalculateStandings;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class PremierLeagueControler {
    @Autowired
    private PremierLeagueRepository premierLeagueRepository;

    @GetMapping("/find-by-id/{id}")
    public PremierLeagueModel getById(@PathVariable String id) {
        return premierLeagueRepository.findById(new ObjectId(id)).orElse(null);
    }

    // TODO: update the Standings after each GameWeek
    @GetMapping("/get-standings/{league}")
    public List<HashMap<String, Object>> getStandings(@PathVariable String league) {
        List<HashMap<String, Object>> standings = null;
        if (league.equalsIgnoreCase("premier-league")) {
            CalculateStandings calculateStandings = new CalculateStandings(premierLeagueRepository.findAll());
            standings = calculateStandings.evaluate();
        }
        return standings;
    }
}
