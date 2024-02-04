package e2.game;

import e2.Coord;
import e2.Cells.Cell;
import e2.Cells.Cells;
import e2.Cells.DecoratorCell;
import e2.Cells.MineCell;
import e2.Cells.SimpleCell;
import e2.gameBoard.Board;

import java.util.*;
import static java.util.function.Predicate.not;

public class SweepMinerImpl implements SweepMiner{
    
    private Board<Cell> board;
    private Double limit;
    private int hitted;

    public SweepMinerImpl(Board<Cell> board) {
        this.board = board;
    }

    @Override
    public Cell handleFlag(Coord c) {
        if(board.getCell(c) instanceof DecoratorCell cell) {
            return cell.unwrap();
        }
        return new DecoratorCell(board.getCell(c));
    }


    @Override
    public void seedBombs(int difficulty) {
        double ratio = (difficulty / 10.0);
        double filler = board.bound() / 2.0;
        ratio = ratio < 0.5 ? -ratio : ratio;
        double scount = ratio * board.bound();
        limit = board.bound() - (filler - scount);
        while(board.mapSize() < (int)Math.ceil(limit)) {
            board.setValue(board.randomCoord(), new MineCell());
        }
        System.out.println(limit);
    }

    public void fillRemaining() {
        // alternative using iterable
        board.all().filter(c -> Objects.isNull(board.getCell(c)))
            .forEach(pos -> board.setValue(pos, new SimpleCell(countAdjaxBombs(pos))));
    }

    @Override
    public int bombsSize() {
        return (int)Math.ceil(limit);
    }

    private int countAdjaxBombs(Coord pos) {
        return (int)board.adjaxOf(pos).stream().map(board::getCell)
            .filter(Objects::nonNull)
            .filter(Cells::isBomb).count();
    }

    public void action(Coord pos) {
        this.hitted++;
        System.out.println(hitted);
        board.getCell(pos).reveal();
    }

    public void recursiveDiscoveryOf(Coord c) {
        action(c);
        Optional.of(c).map(board::getCell).filter(Cells::isEmpty).filter(e -> !this.isOver(c))
            .ifPresent(h -> board.adjaxOf(c).stream()
                .map(board::getCell)
                .filter(Cells::isVeiled)
                .filter(Cells::isValuable)
                .map(board::getCoord)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(this::recursiveDiscoveryOf)
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
