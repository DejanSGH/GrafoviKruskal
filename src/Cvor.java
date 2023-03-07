import java.util.ArrayList;

public class Cvor {

    private int id;
    protected ArrayList<Grana> grane;
    protected int deoUnije = 0;

    public Cvor(int id) {
        this.id = id;
        this.grane = new ArrayList<Grana>();
    }

    public String toString(){
        return this.id + " ";
    }
}
