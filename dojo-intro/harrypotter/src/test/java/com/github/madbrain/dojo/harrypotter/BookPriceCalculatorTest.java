package com.github.madbrain.dojo.harrypotter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookPriceCalculatorTest {

    private BookPriceCalculator calculator = new BookPriceCalculator();

    /**
     * testBasics
     */

    @Test
    public void test_no_book() {
        assertThat(calculator.price()).isEqualTo(0);
    }

//    @Test
//    public void test_one_book() {
//        assertThat(calculator.price(0)).isEqualTo(8);
//        assertThat(calculator.price(1)).isEqualTo(8);
//        assertThat(calculator.price(2)).isEqualTo(8);
//        assertThat(calculator.price(3)).isEqualTo(8);
//        assertThat(calculator.price(4)).isEqualTo(8);
//    }
//
//    @Test
//    public void test_two_books() {
//        assertThat(calculator.price(0, 0)).isEqualTo(8 * 2);
//        assertThat(calculator.price(1, 1, 1)).isEqualTo(8 * 3);
//    }

    /**
     * testSimpleDiscounts
     */
//    @Test
//    public void test_two_different_books() {
//        assertThat(calculator.price(0, 1)).isEqualTo(8 * 2 * 0.95);
//    }
//
//    @Test
//    public void test_three_different_books() {
//        assertThat(calculator.price(0, 2, 4)).isEqualTo(8 * 3 * 0.90);
//    }
//
//    @Test
//    public void test_four_different_books() {
//        assertThat(calculator.price(0, 1, 2, 4)).isEqualTo(8 * 4 * 0.80);
//    }
//
//    @Test
//    public void test_five_different_books() {
//        assertThat(calculator.price(0, 1, 2, 3, 4)).isEqualTo(8 * 5 * 0.75);
//    }

    /**
     * testSeveralDiscounts
     */
//    @Test
//    public void test_several_discounts() {
//        assertThat(calculator.price(0, 0, 1)).isEqualTo(8 + (8 * 2 * 0.95));
//        assertThat(calculator.price(0, 0, 1, 1)).isEqualTo(2 * (8 * 2 * 0.95));
//        assertThat(calculator.price(0, 0, 1, 2, 2, 3)).isEqualTo((8 * 4 * 0.80) + (8 * 2 * 0.95));
//        assertThat(calculator.price(0, 1, 1, 2, 3, 4)).isEqualTo((8 * 5 * 0.75) + 8);
//    }

    /**
     * testEdgeCases
     */
//    @Test
//    public void test_cheapest() {
//        assertThat(calculator.price(0, 0, 1, 1, 2, 2, 3, 4)).isEqualTo(2 * (8 * 4 * 0.8));
//        assertThat(calculator.price(
//                0, 0, 0, 0, 0,
//                1, 1, 1, 1, 1,
//                2, 2, 2, 2,
//                3, 3, 3, 3, 3,
//                4, 4, 4, 4)).isEqualTo(3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8));
//    }

}
