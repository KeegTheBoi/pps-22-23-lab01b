package e2.Cells;

import java.util.Optional;

public class SimpleCell extends AbstractCell {

    private Optional<Integer> value;

    public SimpleCell(int value) {
        super(Status.INVISIBLE);
        this.value = Optional.of(value);
    }

    @Override
    public Type getType() {
        return Type.GROUND;
    }

    @Override
    public Optional<Integer> getCount() {
        return value;
    }

}
