package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenCountryThenRussia() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("country")).isEqualTo("Russia");
    }

    @Test
    void whenLangThenJava() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("lang")).isEqualTo("Java");
    }

    @Test
    void whenCommentAndEmptyLines() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("lang")).isEqualTo("Java");
    }

    @Test
    void whenNoValueForKey() {
        String path = "./data/corrupted_pairs.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("keyWithoutValue=");
    }

    @Test
    void whenTwoEq() {
        String path = "./data/two_eq.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("class_name")).isEqualTo("Config=1");
    }

    @Test
    void whenTwoEq2() {
        String path = "./data/two_eq2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value=");
    }
}