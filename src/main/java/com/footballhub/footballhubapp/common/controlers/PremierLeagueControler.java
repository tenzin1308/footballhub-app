package com.footballhub.footballhubapp.common.controlers;

import com.footballhub.footballhubapp.common.models.PremierLeagueModel;
import com.footballhub.footballhubapp.common.repository.PremierLeagueRepository;
import com.footballhub.footballhubapp.common.utils.CalculateStandings;
import com.footballhub.footballhubapp.common.utils.FetchDataset;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class PremierLeagueControler {
    @Autowired
    private PremierLeagueRepository premierLeagueRepository;

    // For Testing Purpose Only
    @GetMapping("/find-by-id/{id}")
    public PremierLeagueModel getById(@PathVariable String id) {
        return premierLeagueRepository.findById(new ObjectId(id)).orElse(null);
    }

    @GetMapping("/get-standings/{league}")
    public List<HashMap<String, Object>> getStandings(@PathVariable String league) {
        List<HashMap<String, Object>> standings = null;
        if (league.equalsIgnoreCase("premier-league")) {
            CalculateStandings calculateStandings = new CalculateStandings(premierLeagueRepository.findAll());
            standings = calculateStandings.evaluate();
        }
        return standings;
    }

    @GetMapping("/fetch-dataset/{league_year}")
    public String fetchDataset(@PathVariable String league_year) {
        String league = league_year.substring(0, league_year.length() - 5);
        String year = league_year.substring(league_year.length() - 4);
        FetchDataset fetchDataset = new FetchDataset(league, year);
        if (league.equalsIgnoreCase("epl")) {
            return fetchDataset.fetchEPLDataset();
        } else {
            return fetchDataset.fetchCLDataset();
        }
    }

    @PostMapping("/fetch-dataset")
    public ResponseEntity<String> postDataset(@RequestBody String jsonBody) {
        JSONObject json = new JSONObject(jsonBody);
        JSONObject command = json.getJSONObject("command");
        String league = command.getString("league");
        String year = command.getString("year");
        FetchDataset fetchDataset = new FetchDataset(league, year);
        try {
            if (league.equalsIgnoreCase("epl")) {
                return ResponseEntity.status(200).body(fetchDataset.fetchEPLDataset());
            } else {
                return ResponseEntity.status(200).body(fetchDataset.fetchCLDataset());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Resource was not found on the server");
        }
    }
}
