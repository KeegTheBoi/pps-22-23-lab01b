package e2.Cells;

import e2.Cells.Cell.Status;
import e2.Cells.Cell.Type;

public class Cells {
    
    public static boolean isBomb(Cell c) {
        return c.getType() == Type.MINE;
    }

    public static boolean isFlagged(Cell c) {
        return c.getType() == Type.FLAG && c.getStatus() == Status.VISIBLE;
    }

    public static boolean isValuable(Cell c) {
        return c.getCount().isPresent();
    }

    public static boolean isEmpty(Cell c) {
        return c.getCount().isPresent() && c.getCount().get() == 0;
    }
}
