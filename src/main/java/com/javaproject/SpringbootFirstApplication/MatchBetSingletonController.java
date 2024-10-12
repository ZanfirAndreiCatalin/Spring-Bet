package com.javaproject.SpringbootFirstApplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


    // Singleton Class to manage both FootballMatchController and BetController
    @RestController
    @RequestMapping("/api")
    public class MatchBetSingletonController {

        private static MatchBetSingletonController instance;

        // FootballMatchController-related members
        private List<FootballMatch> matchList = new ArrayList<>();
        private int nextMatchId = 1;

        // BetController-related members
        private List<Bet> betList = new ArrayList<>();
        private int nextBetId = 1;

        // Private constructor to prevent instantiation from outside
        private MatchBetSingletonController() {
            // Initialize default match and bet
            matchList.add(new FootballMatch(nextMatchId++, "FC Real Madrid", "FC Barcelona", "2024-09-05", "18:00"));
            betList.add(new Bet(nextBetId++, "Match Winner", "Football", 0.00));
        }

        // Method to provide access to the single instance
        public synchronized MatchBetSingletonController getInstance() {
            if (instance == null) {
                instance = new MatchBetSingletonController();
            }
            return instance;
        }

        // FootballMatchController methods

        @GetMapping("/matches")
        public List<FootballMatch> getMatchList() {
            return matchList;
        }

        @PostMapping("/matches/add")
        public FootballMatch addMatch(@RequestBody FootballMatch newMatch) {
            newMatch.setId(nextMatchId++);
            matchList.add(newMatch);
            return newMatch;
        }

        @PostMapping("/matches/addBet/{matchId}/{betId}")
        public String addBetToMatch(@PathVariable int matchId, @PathVariable int betId, @RequestBody double newOdd) {
            Optional<FootballMatch> matchOpt = matchList.stream().filter(match -> match.getId() == matchId).findFirst();
            Optional<Bet> betOpt = betList.stream().filter(bet -> bet.getId() == betId).findFirst();

            if (matchOpt.isPresent() && betOpt.isPresent()) {
                FootballMatch match = matchOpt.get();
                Bet betFromList = betOpt.get();

                boolean isOddUnique = match.getBets().stream().noneMatch(bet -> bet.getOdd() == newOdd);
                if (!isOddUnique) {
                    return "Error: A bet with the same odd already exists for this match. Please choose a unique odd.";
                }

                Bet betToAdd = new Bet(betFromList.getId(), betFromList.getName(), betFromList.getSport(), newOdd);
                match.addBet(betToAdd);
                return "Bet added successfully to match between " + match.getHomeTeam() + " and " + match.getAwayTeam() + " with updated odd: " + newOdd;
            }
            return "Error: Match or Bet not found.";
        }

        @DeleteMapping("/matches/delete/{Id}")
        public String deleteMatch(@PathVariable int Id) {
            Optional<FootballMatch> matchToDelete = matchList.stream().filter(m -> m.getId() == Id).findFirst();
            if (matchToDelete.isPresent()) {
                matchList.remove(matchToDelete.get());
                return "Match deleted successfully.";
            }
            return "Match not found.";
        }

        @PatchMapping("/matches/update/{id}")
        public FootballMatch updateMatch(@PathVariable int id, @RequestBody FootballMatch updatedFields) {
            Optional<FootballMatch> matchToUpdate = matchList.stream().filter(m -> m.getId() == id).findFirst();
            if (matchToUpdate.isPresent()) {
                FootballMatch match = matchToUpdate.get();
                if (updatedFields.getHomeTeam() != null) match.setHomeTeam(updatedFields.getHomeTeam());
                if (updatedFields.getAwayTeam() != null) match.setAwayTeam(updatedFields.getAwayTeam());
                if (updatedFields.getDate() != null) match.setDate(updatedFields.getDate());
                if (updatedFields.getTime() != null) match.setTime(updatedFields.getTime());
                return match;
            }
            return null;
        }

        @PatchMapping("/matches/updateOdd/{matchId}/{betId}")
        public String updateOddsByMatchAndBetId(@PathVariable int matchId, @PathVariable int betId, @RequestBody double newOdds) {
            Optional<FootballMatch> matchOpt = matchList.stream().filter(match -> match.getId() == matchId).findFirst();
            if (matchOpt.isPresent()) {
                FootballMatch match = matchOpt.get();
                Optional<Bet> betOpt = match.getBets().stream().filter(bet -> bet.getId() == betId).findFirst();
                if (betOpt.isPresent()) {
                    Bet betToUpdate = betOpt.get();
                    betToUpdate.setOdd(newOdds);
                    return "Odds updated successfully for bet with ID " + betId;
                }
                return "Bet not found.";
            }
            return "Match not found.";
        }

        // ---- BetController methods ----

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
            Optional<Bet> betToDelete = betList.stream().filter(b -> b.getId() == id).findFirst();
            if (betToDelete.isPresent()) {
                betList.remove(betToDelete.get());
                return "Bet removed successfully.";
            }
            return "Bet not found.";
        }

        @GetMapping("/bets/sport/{sportName}")
        public List<Bet> getBetsBySport(@PathVariable String sportName) {
            return betList.stream().filter(bet -> bet.getSport().equalsIgnoreCase(sportName)).collect(Collectors.toList());
        }
        @PatchMapping("/update/{id}")
        public Bet updateBet(@PathVariable int id, @RequestBody Bet updatedBetDetails) {
            Optional<Bet> betToUpdate = betList.stream().filter(bet -> bet.getId() == id).findFirst();

            if (betToUpdate.isPresent()) {
                Bet bet = betToUpdate.get();

                if (updatedBetDetails.getName() != null) {
                    bet.setName(updatedBetDetails.getName());
                }

                if (updatedBetDetails.getSport() != null) {
                    bet.setSport(updatedBetDetails.getSport());
                }

                if (updatedBetDetails.getOdd() != 0.0) {
                    bet.setOdd(updatedBetDetails.getOdd());
                }

                return bet;
            } else {
                return null; // or throw a custom exception for "Bet not found"
            }
        }
        @GetMapping("/matches/{id}")
        public ResponseEntity<FootballMatch> getMatchById(@PathVariable int id) {
            Optional<FootballMatch> matchOpt = matchList.stream().filter(match -> match.getId() == id).findFirst();

            if (matchOpt.isPresent()) {
                return ResponseEntity.ok(matchOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found if the match isn't found
            }
        }
    }
