package e2;
import e2.Cells.Cell;

public class LogicsImpl implements Logics {

    private Coord current;
    private final MineBoard<Cell> board;
    private final SweepMiner game;

    public LogicsImpl(int size, int diffuculty) {
        board = new MineBoardImpl<Cell>(size);
        this.game = new SweepMinerImpl(board);
    }

    @Override
    public void hit(Coord pos) {
        this.current = pos;
        game.action(this.current);
        game.recursiveDiscoveryOf(current);
    }

    @Override
    public boolean isOver() {
        return game.isOver(current);
    }

    @Override
    public boolean hasWon() {
        return board.size() - game.hitCount() == game.bombsSize();
    }

    @Override
    public void flag(Coord pos) {
        game.flag(pos);
    }

    @Override
    public Cell getResult(Coord c) {
        return board.getCell(c);
    }

}
