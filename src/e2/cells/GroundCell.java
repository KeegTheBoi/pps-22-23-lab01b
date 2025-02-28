package e2.cells;

import java.util.Optional;

public class GroundCell extends AbstractCell {

    private Optional<Integer> value;

    public GroundCell(int value) {
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
