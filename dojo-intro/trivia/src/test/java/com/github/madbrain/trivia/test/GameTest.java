package com.github.madbrain.trivia.test;

import com.github.madbrain.trivia.Game;
import com.github.madbrain.trivia.IReporter;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class GameTest {

    private IReporter reporter;
    private Game game = new Game();

    @Test
    public void gameCanHavePlayer() {
        game.add("John");
        Assertions.assertThat(game.howManyPlayers()).isEqualTo(1);
    }

    @Test
    public void emptyGameIsNotPlayable() {

        Assertions.assertThat(game.isPlayable()).isFalse();

    }

    @Test
    public void needTwoPlayersToPlay() {
        game.add("John");

        Assertions.assertThat(game.isPlayable()).isFalse();

        game.add("Paul");

        Assertions.assertThat(game.isPlayable()).isTrue();
    }

    @Test
    public void mustRollDiceToPlay() {
        game.add("John");
        game.add("Paul");

        game.roll(1);

        Assertions.assertThat(reporter.getLastSentence(2)).isEqualTo("John's new location is 1");
    }

    @Test
    public void location1IsScience() {
        game.add("John");
        game.add("Paul");

        game.roll(1);

        Assertions.assertThat(reporter.getLastSentence(0)).isEqualTo("Science Question 0");
    }

    @Test
    public void goToPenaltyBoxOnWrongAnswer() {
        game.add("John");
        game.add("Paul");

        game.roll(1);

        game.wrongAnswer();

        Assertions.assertThat(reporter.getLastSentence(0)).isEqualTo("John was sent to the penalty box");
    }

    @Test
    public void gainOnePointOnGoodAnswer() {
        game.add("John");
        game.add("Paul");

        game.roll(1);

        game.wasCorrectlyAnswered();

        Assertions.assertThat(reporter.getLastSentence(0)).isEqualTo("John now has 1 Gold Coins");
    }

    @Test
    public void playerRollDiceInTurn() {
        game.add("John");
        game.add("Paul");

        game.roll(1);

        game.wasCorrectlyAnswered();

        game.roll(2);

        Assertions.assertThat(reporter.getLastSentence(4)).isEqualTo("Paul is the current player");
    }

    @Test
    public void stayInPenaltyOnEvenRoll() {
        game.add("John");
        game.add("Paul");

        game.roll(1);

        game.wrongAnswer();

        game.roll(2);

        game.wasCorrectlyAnswered();

        game.roll(2);

        Assertions.assertThat(reporter.getLastSentence(0)).isEqualTo("John is not getting out of the penalty box");
    }

    @Test
    public void getOutOfPenaltyOnOddRoll() {
        game.add("John");
        game.add("Paul");

        game.roll(1);

        game.wrongAnswer();

        game.roll(2);

        game.wasCorrectlyAnswered();

        game.roll(3);

        Assertions.assertThat(reporter.getLastSentence(3)).isEqualTo("John is getting out of the penalty box");
    }

    @Test
    public void winOn6GoodAnswers() {
        game.add("John");
        game.add("Paul");

        boolean hasNoWinner = false;
        for (int i = 0; i < 6; ++i) {

            game.roll(1);

            game.wrongAnswer();

            game.roll(1);

            hasNoWinner = game.wasCorrectlyAnswered();
        }
        Assertions.assertThat(hasNoWinner).isFalse();
    }
}
