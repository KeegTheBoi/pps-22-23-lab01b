package e2.gameBoard;

import java.util.*;
import java.util.stream.*;

import e2.Coord;

import static java.util.function.Predicate.not;


public class BoardImpl<C> implements Board<C> {

    private int size;
    private final Map<Coord, C> boardMap;

    public BoardImpl(int size) {
        this.size = size;
        boardMap = new HashMap<>();
    }

    @Override
    public int size() {
        return size * size;
    }

    @Override
    public Set<Coord> adjaxOf(Coord pos) {
        return all().filter(not(pos::equals)).filter(pos::isAdjax).collect(Collectors.toSet());
    }

    public Stream<Coord> all() {
        return IntStream.range(0, size).boxed().flatMap(i -> IntStream.range(0, size).mapToObj(j -> new Coord(i, j)));
    }

    @Override
    public Optional<Coord> getCoord(C c) {
        return boardMap.entrySet().stream()
            .filter(e -> e.getValue().equals(c))
            .map(Map.Entry::getKey)
            .findFirst();
    }

    @Override
    public Coord randomCoord() {
        Random rand = new Random();
        return new Coord(rand.nextInt(size), rand.nextInt(size));
    }

    @Override
    public C getCell(Coord c) {
        return boardMap.get(c); 
    }

    @Override
    public void setValue(Coord coord, C cell) {
        this.boardMap.put(coord, cell);
    }

    @Override
    public int bound() {
        return size;
    }

    @Override
    public int mapSize() {
        return this.boardMap.size();
    }

}
