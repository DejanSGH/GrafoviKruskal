public class Grana {

    protected int tezina;
    protected Cvor od;
    protected Cvor ka;


    public Grana(Cvor od, Cvor ka, int tezina) {
        this.od = od;
        this.ka = ka;
        this.tezina = tezina;
    }

    public String toString(){
        return "OD " + this.od + " KA " + this.ka;
    }
}
