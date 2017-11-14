package pl.bk.dart;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class Game {

    private static final int POINT_TO_REACH_TO_WIN = 0;
    private final static int NUMBER_OF_THROWS_IN_ROUND = 3;
    private final List<Player> players;
    private final PointAsker pointAsker;

    public Game(PointAsker pointAsker){
        players = new ArrayList<>();
        this.pointAsker = pointAsker;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public List<Player> takeAllPlayers(){
        return players;
    }

    public void startGame(){

        if(checkIfAtLeastOnePlayerIsAdded()){
         return;
        }

        while (!checkIfAnyoneWonGame()){
            playRound();
        }

        log.debug("Game is over.");
        findWinner(players).forEach(player -> log.debug("The winner is "+ player.getName()));
    }

    private boolean checkIfAtLeastOnePlayerIsAdded() {
        return players.isEmpty();
    }

    private List<Player> findWinner(List<Player>players){
        int lowerNumberOfPoints = getLeastNumberOfPointsFromAnyPlayer(players);
        return players.stream()
               .filter(player -> player.getNumberOfPoints() == lowerNumberOfPoints)
               .collect(Collectors.toList());
    }

    private int getLeastNumberOfPointsFromAnyPlayer(List<Player> players) {
        return players.stream()
                    .mapToInt(Player::getNumberOfPoints)
                    .min()
                    .getAsInt();
    }

    private boolean checkIfAnyoneWonGame() {
        return players.stream()
                .anyMatch(player -> player.getNumberOfPoints() <= POINT_TO_REACH_TO_WIN );
    }

    private void playRound() {
        players.forEach(this::playPlayerRound);
    }

    private void playPlayerRound(Player player){
        IntStream.range(1, NUMBER_OF_THROWS_IN_ROUND + 1)
                .forEach(i -> {
                    log.debug("Player " + player.getName() + " is shooting. It is his "+ i +" shoot.");
                    player.subtractPoints(getPointsToSubtract());
                });
    }

    private int getPointsToSubtract() {
        log.debug("Give points for shoot: ");
        int result = pointAsker.ask();
        log.debug(String.valueOf(result));
        return result;
    }

}