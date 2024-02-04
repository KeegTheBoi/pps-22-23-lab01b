package e2.Cells;

import java.util.*;

public interface Cell {

    enum Type{
        MINE, GROUND
    }

    enum Status {
        VISIBLE, INVISIBLE
    }

    Type getType();

    Status getStatus();

    Optional<Integer> getCount();

    boolean isFlagged();

    void reveal();
}
