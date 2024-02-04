package e2.Cells;

import java.util.Optional;

public class DecoratorCell extends AbstractCell{

    private Cell decorated;

    public DecoratorCell(Cell cell) {
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
