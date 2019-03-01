package com.github.madbrain.dojo.markov;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkovGeneratorTest {

    private MarkovGenerator generator = MarkovGenerator.from(getClass().getResourceAsStream("/data.txt"));

    @Test
    public void test_generate() {
        assertThat(generator.generate(10)).isEqualTo("Essayer le TDD c'est l'adopter");
    }

}
