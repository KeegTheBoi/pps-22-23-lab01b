package e2.cells;

import java.util.Optional;

public class MineCell extends AbstractCell{

    public MineCell() {
        super(Status.INVISIBLE);
    }

    @Override
    public Type getType() {
        return Type.MINE;
    }

    @Override
    public Optional<Integer> getCount() {
        return Optional.empty();
    }

}
