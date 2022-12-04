import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class Plocha here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plocha
{
    // instance variables - replace the example below with your own
    private int riadky;
    private int stlpce;
    private Kocka[][] poleKociek;
    private ArrayList<Blok> zoznamBlokov; 
    private Manazer manazer = new Manazer();
    private Blok spravovanyBlok;
              
    public Plocha(int riadky, int stlpce)
    {
        this.manazer.spravujObjekt(this);
        this.riadky = riadky;
        this.stlpce = stlpce;
        this.zoznamBlokov = new ArrayList<Blok>();
        vytvorPlochu();
    }
    
    public Kocka[][] getPoleKociek(){
        return this.poleKociek;
    }
    
    public ArrayList<Blok> getZoznamBlokov(){
        return this.zoznamBlokov;
    }
    
    public Blok getPrvyZoZoznamuBlokov(){
        return this.getZoznamBlokov().get(0);
    }

    public Blok getPoslednyZoZoznamuBlokov(){
        return this.zoznamBlokov.get(zoznamBlokov.size()-1);
    }
    
    public void setPoleKociek(Kocka[][] listKociek){
        this.poleKociek = listKociek;
    }
    
    public void setZoznamBlokov(ArrayList<Blok> listBlokov){
        this.zoznamBlokov = listBlokov;
    }
    
    public void vytvorPlochu(){
        this.poleKociek = new Kocka[riadky][stlpce];
        for (int stlpec = 0; stlpec < riadky; stlpec++){
            for (int riadok = 0; riadok < stlpce; riadok++){
                poleKociek[stlpec][riadok] = new Kocka(riadok, stlpec, false, "white");
            }
        }
        
        Kocka a = this.poleKociek[0][0];
        a.zmenFarbuKocky("red");
        
        Kocka b = this.poleKociek[this.riadky-1][0];
        b.zmenFarbuKocky("yellow");
        
        Kocka c = this.poleKociek[0][this.stlpce-1];
        c.zmenFarbuKocky("green");
        
        Kocka d = this.poleKociek[this.riadky-1][this.stlpce-1];
        d.zmenFarbuKocky("blue");
    }
    
    public void vytvorBlok(){
        Random random = new Random();
        int randomCislo = random.nextInt((7 - 1) + 1) + 1;
        this.spravovanyBlok = new Blok(2, 2, randomCislo, this.riadky, this.stlpce);
        this.zoznamBlokov.add(spravovanyBlok);
        this.updatePoleKociek(spravovanyBlok);
        this.nespravujOstatneBloky();
    }
    
    public void nespravujOstatneBloky(){
        if (this.getZoznamBlokov().size() == 1){
            return;
        }
        this.manazer.prestanSpravovatObjekt(this.getZoznamBlokov().get(this.getZoznamBlokov().size()-2));
    }
    
    public void posunBlokHore(){
        this.spravovanyBlok.posunHore();
        this.updatePoleKociek(this.spravovanyBlok);
    }
    
    public void posunBlokDole(){
        this.spravovanyBlok.posunDole();
        this.updatePoleKociek(this.spravovanyBlok);
    }
    
    public void posunBlokVlavo(){
        this.spravovanyBlok.posunVlavo();
        this.updatePoleKociek(this.spravovanyBlok);
    }
    
    public void posunBlokVpravo(){
        this.spravovanyBlok.posunVpravo();
        this.updatePoleKociek(this.spravovanyBlok);
    }
    
    public void rotujBlok(){
        this.spravovanyBlok.rotuj();
        this.updatePoleKociek(this.spravovanyBlok);
    }
    
    public void updatePoleKociek(Blok blok){
        for (Kocka kocka : blok.getKocky()){
            if (kocka.getJeBlok()){ 
                this.poleKociek[kocka.getY()][kocka.getX()].setJeBlok(true);
            }
            else {
                this.poleKociek[kocka.getY()][kocka.getX()].setJeBlok(false);
            }
            //tuna to treba spravit pre blok ked sa znici, lebo hodnoty budu zmene na stale na true
            //pred znicenim bloku treba nastavit jeho kockam ze nie su blok a updatenut znovu
            //a taktiez je nutne nastavit updatovanie po kazdom pohybu bloku asi (bruh)
            //vytvoril som optimalizovanu metodu podobnej tejto, ale ako parameter budu len suradnice
        }
    }  
    
    public void vypisPolaKociek(){
        for (int riadok = 0; riadok < this.poleKociek.length; riadok++){
            for (int prvok = 0; prvok < this.poleKociek[riadok].length; prvok++){
                System.out.print(this.poleKociek[riadok][prvok].getJeBlok() + ", ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------------------------------------------");
    }
    
}
