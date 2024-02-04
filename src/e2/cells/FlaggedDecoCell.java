package e2.cells;

import java.util.Optional;

public class FlaggedDecoCell extends AbstractCell{

    private Cell decorated;

    public FlaggedDecoCell(Cell cell) {
        super(Status.INVISIBLE);
        this.decorated = cell;
        this.flag = true;
    }

    @Override
    public Type getType() {
        return decorated.getType();
    }

    @Override
    public Optional<Integer> getCount() {
        return decorated.getCount();
    }

    public Cell unwrap() {
        return decorated;
    }

}
