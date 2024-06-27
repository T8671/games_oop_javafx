package ru.job4j.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {
    private Logic logic;

    @BeforeEach
    void setUp() {
        logic = new Logic();
    }

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    void whenFigureFoundThenIndexReturned() throws Exception {
        logic.add(new BishopBlack(Cell.C1));
        int index = logic.findBy(Cell.C1);
        assertThat(index).isNotEqualTo(-1);
    }

    @Test
    void whenWayFromH1ToA8ThenReturnG2F3E4D5C6B7A8() {
        BishopBlack bishop = new BishopBlack(Cell.H1);
        Cell[] actualPath = bishop.way(Cell.A8);
        Cell[] expectedPath = {Cell.G2, Cell.F3, Cell.E4, Cell.D5, Cell.C6, Cell.B7, Cell.A8};
        assertThat(actualPath).isEqualTo(expectedPath);
    }

    @Test
    void whenCopyThenNewPositionIsSet() {
        Cell initialPosition = Cell.C1;
        BishopBlack bishop = new BishopBlack(initialPosition);
        Cell newPosition = Cell.D4;
        BishopBlack newBishop = (BishopBlack) bishop.copy(newPosition);
        assertThat(newBishop.position()).isEqualTo(newPosition);
    }

    @Test
    void whenMoveFromC1ToG5ButG5OccupiedThenThrowOccupiedCellException() {
        logic.add(new BishopBlack(Cell.C1));
        assertThatThrownBy(() -> logic.move(Cell.C1, Cell.G5))
                .isInstanceOf(OccupiedCellException.class)
                .hasMessageContaining("Cell G5 is occupied.");
    }

    @Test
    void whenMoveFromC1ToC2ThenThrowImpossibleMoveException() {
        logic.add(new BishopBlack(Cell.C1));
        assertThatThrownBy(() -> logic.move(Cell.C1, Cell.C2))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessageContaining("Could not move by diagonal from C1 to C2");
    }

    @Test
    void whenMoveFromEmptyCellThenThrowFigureNotFoundException() {
        assertThatThrownBy(() -> logic.move(Cell.C1, Cell.G5))
                .isInstanceOf(FigureNotFoundException.class)
                .hasMessageContaining("Figure not found on the board.");
    }
}
