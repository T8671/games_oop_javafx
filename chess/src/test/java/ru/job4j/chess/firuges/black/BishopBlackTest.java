package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopBlackTest {

    @Test
    void whenSetPositionA1ThenReturnSamePositionA1() {
        Cell initialPosition = Cell.A1;
        BishopBlack bishopBlack = new BishopBlack(initialPosition);
        assertThat(bishopBlack.position()).isEqualTo(initialPosition);
    }

    @Test
    void whenSetCopyB1ThenReturnSameCopyB1() {
        Cell initialPosition = Cell.B1;
        BishopBlack bishopBlack = new BishopBlack(initialPosition);
        assertThat(bishopBlack.position()).isEqualTo(initialPosition);
    }

    @Test
    void whenWayC1ToG5ThenD2E3F4G5() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] actual = bishopBlack.way(Cell.G5);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(actual).isEqualTo(expected);

    }
}
