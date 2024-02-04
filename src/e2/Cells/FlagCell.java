package e2.Cells;

import java.util.Optional;

public class FlagCell extends AbstractCell{

    public FlagCell() {
        super(Status.VISIBLE);
    }

    @Override
    public Type getType() {
        return Type.FLAG;
    }

    @Override
    public Optional<Integer> getCount() {
        return Optional.empty();
    }

}
