package e2.game;

import e2.Coord;
import e2.cells.Cell;

public interface SweepMiner {
    
    Cell handleFlag(Coord c);

    void seedBombs(int diffuculty);

    void fillRemaining();

    void unveil(Coord pos);

    int bombsSize();

    boolean isOver(Coord c);

    public void recursiveDiscoveryOf(Coord c);

    int hitCount();

}
