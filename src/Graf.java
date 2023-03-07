import java.util.ArrayList;
import java.util.Arrays;

public class Graf {

    private int brojCvorova;
    private Cvor[] cvorovi;
    private ArrayList<Grana> grane;
    private Grana[] mst1;
    private int tezinaMst;

    public Graf(int brojCvorova) {
        this.cvorovi = new Cvor[brojCvorova];
        this.grane = new ArrayList<Grana>();
        this.brojCvorova = brojCvorova;
        for (int i = 0; i < brojCvorova; i++) {
            this.cvorovi[i] = new Cvor(i);
        }
    }

    public void dodajGranu(int od, int ka, int tezina) {
        if ((od >= brojCvorova || od < 0) || (ka >= brojCvorova || ka < 0)) {
            //System.out.println("Nepostojeci cvor u grafu!");
            return;
        }
        Grana g = new Grana(cvorovi[od], cvorovi[ka], tezina);
        if (grane.isEmpty()) {
            grane.add(g);
            //System.out.println("Dodata prva grana " + grane.toString());
            return;
        }
        for (int i = 0; i < grane.size(); i++) {
            if (g.tezina <= grane.get(i).tezina) {
                grane.add(i, g);
               // System.out.println("Dodata grana na tacnu poziciju " + grane.toString());
                return;
            }
        }
        grane.add(g);

    }

    public void mstKruskal() {
        int brojac = 0;
        int brojacUnije = 1;
        mst1 = new Grana[brojCvorova-1];
        for (int i = 0; i < grane.size(); i++) {
            //Cvor OD = grane.get(i).od;
            if(grane.get(i).od.deoUnije == 0 && grane.get(i).ka.deoUnije == 0){
                grane.get(i).od.deoUnije = brojacUnije;
                grane.get(i).ka.deoUnije = brojacUnije;
                mst1[brojac] = grane.get(i);
                brojac++;
                brojacUnije++;
            }
            else if(grane.get(i).od.deoUnije == 0 || grane.get(i).ka.deoUnije == 0){
                mst1[brojac] = grane.get(i);
                brojac++;
                if(grane.get(i).od.deoUnije == 0) grane.get(i).od.deoUnije = grane.get(i).ka.deoUnije;
                else grane.get(i).ka.deoUnije = grane.get(i).od.deoUnije;
            }
            else if(grane.get(i).od.deoUnije != grane.get(i).ka.deoUnije){
                mst1[brojac] = grane.get(i);
                int manjaUnija, vecaUnija;
                if(mst1[brojac].od.deoUnije < mst1[brojac].ka.deoUnije){
                    manjaUnija = mst1[brojac].od.deoUnije;
                    vecaUnija = mst1[brojac].ka.deoUnije;
                }
                else{
                    manjaUnija = mst1[brojac].ka.deoUnije;
                    vecaUnija =  mst1[brojac].od.deoUnije;
                }
                brojac++;
                for (int j = 0; j < brojac; j++) {
                    if(mst1[j].od.deoUnije == vecaUnija) mst1[j].od.deoUnije = manjaUnija;
                    if(mst1[j].ka.deoUnije == vecaUnija) mst1[j].ka.deoUnije = manjaUnija;
                }
            }
        }
        for (int i = 0; i < mst1.length; i++) {
            tezinaMst += mst1[i].tezina;
        }
        System.out.println(Arrays.toString(mst1));
        System.out.println("Tezina stabla je: " + tezinaMst);
    }


    public void ispisiGrane(){
        for (int i = 0; i < grane.size(); i++) {
            System.out.print(grane.get(i).tezina + " ");
        }
    }

    public String toString() {
        return Arrays.toString(mst1);
    }

}
