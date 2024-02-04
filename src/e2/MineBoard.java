package e2;

import java.util.*;
import java.util.stream.*;

public interface MineBoard<C> {

    int size();

    int bound();

    C getCell(Coord coord);

    Optional<Coord> getCoord(C c);

    Coord randomCoord();

    Set<Coord> adjaxOf(Coord pos);

    void setValue(Coord coord, C c);

    Stream<Coord> all();

}
