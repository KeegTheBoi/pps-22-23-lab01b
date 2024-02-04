package e2.Cells;
import java.util.*;

public abstract class AbstractCell implements Cell{

    private Status visibility;

    public AbstractCell(Status status) {
        this.visibility = status;
    }

    public abstract Type getType();

    public Status getStatus() {
        return visibility;
    }

    public void changeVisibility() {
        visibility = visibility == Status.VISIBLE ? Status.INVISIBLE : Status.VISIBLE;
    }

    public abstract Optional<Integer> getCount();

}
