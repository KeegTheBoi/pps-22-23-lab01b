package e2;

public record Coord(int x, int y) {

    public boolean isAdjax(Coord d) {
        return Math.abs(x - d.x()) <= 1 && Math.abs(y - d.y()) <= 1;
    }
}
