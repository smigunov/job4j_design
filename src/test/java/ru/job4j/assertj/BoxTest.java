package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isCubeAreaCorrect() {
        Box box = new Box(8, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(600);
    }

    @Test
    void isTetrahedronAreaCorrect() {
        Box box = new Box(4, 10);
        double area = box.getArea();
        assertThat(area).isCloseTo(173, withPrecision(0.3d));
    }

    @Test
    void isSphereHasNoVerices() {
        Box box = new Box(0, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(0);
    }

    @Test
    void isCubeHasEightVerices() {
        Box box = new Box(8, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(8);
    }

    @Test
    void isUnknownFigureNotExists() {
        Box box = new Box(15, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isEqualTo(false);
    }

    @Test
    void isKnowsFigureExists() {
        Box box = new Box(8, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isEqualTo(true);
    }

}