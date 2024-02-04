package e2.game;

import e2.Coord;
import e2.Cells.Cell;

public interface SweepMiner {
    
    Cell handleFlag(Coord c);

    void seedBombs(int diffuculty);

    void fillRemaining();

    void action(Coord pos);

    int bombsSize();

    boolean isOver(Coord c);

    public void recursiveDiscoveryOf(Coord c);

    int hitCount();

}
