package e2.cells;

import java.util.*;

public interface Cell {

    Type getType();

    Status getStatus();

    Optional<Integer> getCount();

    boolean isFlagged();

    void reveal();
}
