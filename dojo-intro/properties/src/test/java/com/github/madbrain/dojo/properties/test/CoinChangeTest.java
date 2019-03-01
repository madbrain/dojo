package com.github.madbrain.dojo.properties.test;

import com.github.madbrain.dojo.properties.CoinChanger;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://github.com/doktor500/coin-change-kata
 */
public class CoinChangeTest {

    @Test
    public void testChange1Cents() {
        CoinChanger coinChanger = new CoinChanger(1, 5, 10, 25);

        assertThat(coinChanger.change(1)).containsOnly(1);
    }

    @Test
    public void testChange2Cents() {
        CoinChanger coinChanger = new CoinChanger(1, 5, 10, 25);

        assertThat(coinChanger.change(2)).containsOnly(1, 1);
    }

    @Test
    public void testChange15Cents() {
        CoinChanger coinChanger = new CoinChanger(1, 5, 10, 25);

        assertThat(coinChanger.change(15)).containsOnly(5, 10);
    }

    @Test
    public void testChange48Cents() {
        CoinChanger coinChanger = new CoinChanger(1, 5, 10, 25);

        assertThat(coinChanger.change(48)).containsOnly(25, 10, 10, 1, 1, 1);
    }
}
