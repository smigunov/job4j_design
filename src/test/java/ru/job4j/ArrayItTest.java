package ru.job4j;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ArrayItTest {

    @Test
    void whenMultiCallHasNextThenTrue() {
        ArrayIt iterator = new ArrayIt(
                new int[] {1, 2, 3}
        );
        boolean result = iterator.hasNext();
        assertThat(result).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenReadSequence() {
        ArrayIt iterator = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(3);
    }

    @Test
    void whenReadSequence1() {
        ArrayIt iterator = new ArrayIt(
                new int[] {4, 5, 6}
        );
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.next()).isEqualTo(5);
        assertThat(iterator.next()).isEqualTo(6);
    }
}