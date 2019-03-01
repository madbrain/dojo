package com.github.madbrain.dojo.bowling;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {

    private BowlingGame game = new BowlingGame();

    /**
     * Sum Of Rolls: When the player does not strike or spare
     * the score is the sum of the two rolls
     */

    @Test
    public void sum_of_rolls_1() {
        for (int i = 0; i < 10; ++i) {
            game.roll(0);
            game.roll(0);
        }
        assertThat(game.score()).isEqualTo(0);
    }

//    @Test
//    public void sum_of_rolls_2() {
//        for (int i = 0; i < 10; ++i) {
//            game.roll(2);
//            game.roll(2);
//        }
//        assertThat(game.score()).isEqualTo(40);
//    }
//
//    @Test
//    public void sum_of_rolls_3() {
//        rolls(0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 5, 4, 3, 2, 1);
//        assertThat(game.score()).isEqualTo(50);
//    }

    /**
     * Spare: When the player knocks down all pins in two rolls
     * the score for that frame is 10 plus the next roll
     */

//    @Test
//    public void one_spare() {
//        rolls(3, 7, 4);
//        for (int i = 0; i < 17; ++i) {
//            game.roll(0);
//        }
//        assertThat(game.score()).isEqualTo(10 + 4 + 4);
//    }
//
//    @Test
//    public void spare_in_last_frame() {
//        for (int i = 0; i < 18; ++i) {
//            game.roll(0);
//        }
//        rolls(2, 8, 3);
//        assertThat(game.score()).isEqualTo(10 + 3);
//    }

    /**
     * Spare: When the player knocks down all pins in one roll
     * the score for that frame is 10 plus the next two rolls
     */

//    @Test
//    public void one_strike() {
//        rolls(10, 2, 4);
//        for (int i = 0; i < 16; ++i) {
//            game.roll(0);
//        }
//        assertThat(game.score()).isEqualTo(10 + 6 + 6);
//    }
//
//    @Test
//    public void strike_on_last_frame() {
//        for (int i = 0; i < 18; ++i) {
//            game.roll(0);
//        }
//        rolls(10, 8, 3);
//        assertThat(game.score()).isEqualTo(10 + 11);
//    }
//
//    @Test
//    public void perfect_game() {
//        for (int i = 0; i < 12; ++i) {
//            game.roll(10);
//        }
//        assertThat(game.score()).isEqualTo(300);
//    }

    private void rolls(int... pinsList) {
        for (int pins : pinsList) {
            game.roll(pins);
        }
    }
}
