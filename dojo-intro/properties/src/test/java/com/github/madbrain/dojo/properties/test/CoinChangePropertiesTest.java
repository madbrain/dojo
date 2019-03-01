package com.github.madbrain.dojo.properties.test;

import com.github.madbrain.dojo.properties.CoinChanger;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

/**
 * http://pholser.github.io/junit-quickcheck/site/0.8.1/index.html
 */
@RunWith(JUnitQuickcheck.class)
public class CoinChangePropertiesTest {

    public void testChanges(int value) {
        CoinChanger coinChanger = new CoinChanger(1, 5, 10, 25);

        int[] result = coinChanger.change(value);

        // assertThat some properties on result are checked
    }
}
