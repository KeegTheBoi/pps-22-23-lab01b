package e2.cells;


public class CellsUtils {
    
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
