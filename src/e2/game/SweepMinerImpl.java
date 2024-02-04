package e2.game;

import e2.Coord;
import e2.cells.Cell;
import e2.cells.Cells;
import e2.cells.DecoratorCell;
import e2.cells.MineCell;
import e2.cells.SimpleCell;
import e2.gameBoard.Board;

import java.util.*;
import java.util.stream.*;
import static java.util.function.Predicate.not;

public class SweepMinerImpl implements SweepMiner{
    
    private Board<Coord, Cell> board;
    private Double limit;
    private int hitted;

    public SweepMinerImpl(Board<Coord, Cell> board) {
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
            board.setValue(this.randomCoord(), new MineCell());
        }
        System.out.println(limit);
    }

    public void fillRemaining() {
        // alternative using iterable
        this.all().filter(c -> Objects.isNull(board.getCell(c)))
            .forEach(pos -> board.setValue(pos, new SimpleCell(countAdjaxBombs(pos))));
    }

    @Override
    public int bombsSize() {
        return (int)Math.ceil(limit);
    }

    private int countAdjaxBombs(Coord pos) {
        return (int)this.adjaxOf(pos).stream().map(board::getCell)
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
            .ifPresent(h -> this.adjaxOf(c).stream()
                .map(board::getCell)
                .filter(Cells::isVeiled)
                .filter(Cells::isValuable)
                .map(board::getCoord)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(this::recursiveDiscoveryOf)
            );
    }

    private Set<Coord> adjaxOf(Coord pos) {
        return all().filter(not(pos::equals)).filter(pos::isAdjax).collect(Collectors.toSet());
    }

    private Stream<Coord> all() {
        return IntStream.range(0, board.bound())
            .boxed().flatMap(i -> IntStream.range(0, board.bound())
                .mapToObj(j -> new Coord(i, j)));
    }

    @Override
    public boolean isOver(Coord c) {
        return Cells.isBomb(board.getCell(c));
    }

    @Override
    public int hitCount() {
        return this.hitted;
    }

    private Coord randomCoord() {
        Random rand = new Random();
        return new Coord(rand.nextInt(board.bound()), rand.nextInt(board.bound()));
    }


    
}
