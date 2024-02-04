package e2.gameBoard;

import java.util.*;
import java.util.stream.*;

import e2.Coord;

public interface Board<C> {

    int size();

    int bound();

    C getCell(Coord coord);

    Optional<Coord> getCoord(C c);

    Coord randomCoord();

    Set<Coord> adjaxOf(Coord pos);

    void setValue(Coord coord, C c);

    Stream<Coord> all();

    int mapSize();

}
