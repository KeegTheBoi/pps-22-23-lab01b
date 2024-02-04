package e2;

import e2.Cells.Cell;
import e2.Cells.Cells;
import e2.Cells.FlagCell;
import e2.Cells.MineCell;
import e2.Cells.SimpleCell;

import java.util.*;
import java.util.function.*;
import static java.util.function.Predicate.not;

public class SweepMinerImpl implements SweepMiner{
    
    private MineBoard<Cell> board;
    private int limit;
    private int hitted;

    public SweepMinerImpl(MineBoard<Cell> board) {
        this.board = board;
    }

    @Override
    public void flag(Coord c) {
        this.setCoordType(c, FlagCell::new);
    }

    private void setCoordType(Coord c, Supplier<Cell> supplier) {
        this.board.setValue(c, supplier.get());
    }

    @Override
    public void unflag(Coord c) {
        this.board.getCell(c).changeVisibility();
    }

    @Override
    public void seedBombs(int diffculty) {
        limit = board.size() % diffculty;
        while(board.bound() < limit) {
            board.setValue(board.randomCoord(), new MineCell());
        }
    }

    public void fillRemaining() {
        // alternative using iterable
        board.all().filter(c -> Objects.isNull(board.getCell(c)))
            .forEach(pos -> board.setValue(pos, new SimpleCell(countAdjaxBombs(pos))));
    }

    @Override
    public int bombsSize() {
        return limit;
    }

    private int countAdjaxBombs(Coord pos) {
        return (int)board.adjaxOf(pos).stream().map(board::getCell).filter(Cells::isBomb).count();
    }

    public void action(Coord pos) {
        this.hitted++;
        board.getCell(pos).changeVisibility();
    }

    public void recursiveDiscoveryOf(Coord c) {
        Optional.of(c).map(board::getCell).filter(Cells::isEmpty).filter(e -> !this.isOver(c))
            .ifPresent(h -> board.adjaxOf(c).stream()
                .map(board::getCell)
                .filter(Cells::isValuable)
                .filter(not(Cells::isEmpty))
                .map(board::getCoord)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(this::action)
            );
    }

    @Override
    public boolean isOver(Coord c) {
        return Cells.isBomb(board.getCell(c));
    }

    @Override
    public int hitCount() {
        return this.hitted;
    }



    
}
