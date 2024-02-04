package e2;

import e2.cells.Cell;

public interface Logics {

    void hit(Coord pos);

    boolean isOver();

    boolean hasWon();

    void flag(Coord pos);

    Cell getResult(Coord c);

    
}
