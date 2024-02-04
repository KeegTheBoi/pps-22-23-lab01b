package e2;

public interface SweepMiner {
    
    void flag(Coord c);

    void unflag(Coord c);

    void seedBombs(int diffuculty);

    void fillRemaining();

    void action(Coord pos);

    int bombsSize();

    boolean isOver(Coord c);

    public void recursiveDiscoveryOf(Coord c);

    int hitCount();

}
