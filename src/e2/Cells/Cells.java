package e2.Cells;

import e2.Cells.Cell.Type;
import e2.Cells.Cell.Status;

public class Cells {
    
    public static boolean isBomb(Cell c) {
        return c.getType() == Type.MINE;
    }

    public static boolean isValuable(Cell c) {
        return c.getCount().isPresent();
    }

    public static boolean isEmpty(Cell c) {
        return c.getCount().isPresent() && c.getCount().get() == 0;
    }

    public static boolean isVeiled(Cell c) {
        return c.getStatus() == Status.INVISIBLE;
    }
}
