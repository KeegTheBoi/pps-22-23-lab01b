package e2;

import java.util.Optional;

import e2.Cells.Cell;

public interface Logics {

    void hit(Coord pos);

    boolean isOver();

    boolean hasWon();

    void flag(Coord pos);

    Cell getResult(Coord c);

    
}
