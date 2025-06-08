package com.javaproject.SpringbootFirstApplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // allows frontend to access API
public class MatchBetController {

    private List<FootballMatch> matchList = new ArrayList<>();
    private int nextMatchId = 1;

    private List<Bet> betList = new ArrayList<>();
    private int nextBetId = 1;

    public MatchBetController() {
        matchList.add(new FootballMatch(nextMatchId++, "FC Real Madrid", "FC Barcelona", "2024-09-05", "18:00"));
        betList.add(new Bet(nextBetId++, "Match Winner", "Football", 1.75));
    }

    // --- Matches ---

    @GetMapping("/matches")
    public List<FootballMatch> getMatchList() {
        return matchList;
    }

    @GetMapping("/matches/{id}")
    public ResponseEntity<FootballMatch> getMatchById(@PathVariable int id) {
        return matchList.stream()
                .filter(match -> match.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/matches/add")
    public FootballMatch addMatch(@RequestBody FootballMatch newMatch) {
        newMatch.setId(nextMatchId++);
        matchList.add(newMatch);
        return newMatch;
    }

    @DeleteMapping("/matches/delete/{id}")
    public String deleteMatch(@PathVariable int id) {
        return matchList.removeIf(match -> match.getId() == id)
                ? "Match deleted successfully."
                : "Match not found.";
    }

    @PatchMapping("/matches/update/{id}")
    public FootballMatch updateMatch(@PathVariable int id, @RequestBody FootballMatch updatedFields) {
        for (FootballMatch match : matchList) {
            if (match.getId() == id) {
                if (updatedFields.getHomeTeam() != null) match.setHomeTeam(updatedFields.getHomeTeam());
                if (updatedFields.getAwayTeam() != null) match.setAwayTeam(updatedFields.getAwayTeam());
                if (updatedFields.getDate() != null) match.setDate(updatedFields.getDate());
                if (updatedFields.getTime() != null) match.setTime(updatedFields.getTime());
                return match;
            }
        }
        return null;
    }

    @PostMapping("/matches/addBet/{matchId}/{betId}")
    public String addBetToMatch(@PathVariable int matchId, @PathVariable int betId, @RequestBody double newOdd) {
        Optional<FootballMatch> matchOpt = matchList.stream().filter(m -> m.getId() == matchId).findFirst();
        Optional<Bet> betOpt = betList.stream().filter(b -> b.getId() == betId).findFirst();

        if (matchOpt.isPresent() && betOpt.isPresent()) {
            FootballMatch match = matchOpt.get();

            boolean isOddUnique = match.getBets().stream().noneMatch(bet -> bet.getOdd() == newOdd);
            if (!isOddUnique) return "Error: Duplicate odd for this match.";

            Bet originalBet = betOpt.get();
            Bet newBet = new Bet(originalBet.getId(), originalBet.getName(), originalBet.getSport(), newOdd);
            match.addBet(newBet);

            return "Bet added to match " + match.getHomeTeam() + " vs " + match.getAwayTeam();
        }

        return "Error: Match or Bet not found.";
    }

    @PatchMapping("/matches/updateOdd/{matchId}/{betId}")
    public String updateOddsByMatchAndBetId(@PathVariable int matchId, @PathVariable int betId, @RequestBody double newOdds) {
        return matchList.stream()
                .filter(m -> m.getId() == matchId)
                .findFirst()
                .map(match -> {
                    for (Bet bet : match.getBets()) {
                        if (bet.getId() == betId) {
                            bet.setOdd(newOdds);
                            return "Odds updated.";
                        }
                    }
                    return "Bet not found in match.";
                }).orElse("Match not found.");
    }

    // --- Bets ---

    @GetMapping("/bets")
    public List<Bet> getBetList() {
        return betList;
    }

    @PostMapping("/bets/add")
    public Bet addBet(@RequestBody Bet newBet) {
        newBet.setId(nextBetId++);
        betList.add(newBet);
        return newBet;
    }

    @DeleteMapping("/bets/delete/{id}")
    public String deleteBet(@PathVariable int id) {
        return betList.removeIf(b -> b.getId() == id)
                ? "Bet removed."
                : "Bet not found.";
    }

    @PatchMapping("/update/{id}")
    public Bet updateBet(@PathVariable int id, @RequestBody Bet updatedBetDetails) {
        for (Bet bet : betList) {
            if (bet.getId() == id) {
                if (updatedBetDetails.getName() != null) bet.setName(updatedBetDetails.getName());
                if (updatedBetDetails.getSport() != null) bet.setSport(updatedBetDetails.getSport());
                if (updatedBetDetails.getOdd() != 0.0) bet.setOdd(updatedBetDetails.getOdd());
                return bet;
            }
        }
        return null;
    }

    @GetMapping("/bets/sport/{sportName}")
    public List<Bet> getBetsBySport(@PathVariable String sportName) {
        return betList.stream()
                .filter(b -> b.getSport().equalsIgnoreCase(sportName))
                .toList();
    }
}
