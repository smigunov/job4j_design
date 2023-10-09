package ru.job4j.list;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.addAfter(input, 0, 2);
        input.add(4);
        input.add(5);
        Predicate<Integer> predNotEven = i -> (i % 2 > 0);
        ListUtils.removeIf(input, predNotEven);
        assertThat(input).hasSize(2).containsSequence(2, 4);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.addAfter(input, 0, 2);
        input.add(4);
        input.add(5);
        Predicate<Integer> predNotEven = i -> (i % 2 > 0);
        ListUtils.replaceIf(input, predNotEven, 0);
        assertThat(input).hasSize(5).containsSequence(0, 2, 0, 4, 0);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.addAfter(input, 0, 2);
        input.add(4);
        input.add(5);
        ListUtils.removeAll(input, Arrays.asList(1, 3));
        assertThat(input).hasSize(3).containsSequence(2, 4, 5);
    }
}