package e2.cells;
import java.util.*;

public abstract class AbstractCell implements Cell{

    private Status visibility;
    protected boolean flag;

    public AbstractCell(Status status) {
        this.visibility = status;
    }

    public abstract Type getType();

    public Status getStatus() {
        return visibility;
    }

    public boolean isFlagged() {
        return this.flag;
    }

    public void reveal() {
        visibility = Status.VISIBLE;
    }

    public abstract Optional<Integer> getCount();

}
