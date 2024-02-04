package e2.Cells;

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
