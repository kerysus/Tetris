 

import java.util.ArrayList;
/**
 * Write a description of class Plocha here.
 *
 * @author Marek Kerata
 * @version (a version number or a date)
 */
public class Hra
{
    private Pozadie pozadie;
    private Plocha plocha;    
    private Manazer manazer;
    private Kocka[][] poleKociek;
    private ArrayList<Blok> zoznamBlokov;
    private int pocetRiadkov;
    private int pocetStlpcov;
    
    public Hra()
    {
        this.manazer = new Manazer();
        this.pocetRiadkov = 20;
        this.pocetStlpcov = 10;
        spustiTetris();
    }
    
    public Kocka[] getKockyPoslednehoBloku(Kocka[] zoznamKociek){
        return zoznamKociek;
    }
    
    public void getPoleKociek(Kocka[][] poleKociek){
        this.poleKociek = poleKociek;
    }
    
    public void getZoznamBlokov(ArrayList<Blok> zoznamBlokov){
        this.zoznamBlokov = zoznamBlokov;
    }

    public void vykresliPlochu(){
        this.plocha = new Plocha(pocetRiadkov, pocetStlpcov);
    }
    
    public void nakresliPozadie(){
        this.pozadie = new Pozadie();
        this.pozadie.vykresliPozadie();
    }
    
    public void vytvorBlok(){ 
        this.plocha.vytvorBlok();
        this.spravujBLok(this.plocha.getPoslednyZoZoznamuBlokov());
        this.getKockyPoslednehoBloku(this.plocha.getPoslednyZoZoznamuBlokov().getKocky());
        this.nespravujOstatneBloky();
    }
    
    
    public void updatePlocha(){
        this.plocha.updatePlocha();
    }
    
    public void vykresliVsetko(){
        nakresliPozadie();
        vykresliPlochu();
    }
    
    public void spravujBLok(Blok blok){
        this.manazer.spravujObjekt(blok);
    }
    
    public void spravujHru(){
        this.manazer.spravujObjekt(this);
    }
    
    public void nespravujOstatneBloky(){
        if (this.plocha.getZoznamBlokov().size() == 1){
            return;
        }
        this.manazer.prestanSpravovatObjekt(this.plocha.getZoznamBlokov().get(this.plocha.getZoznamBlokov().size()-2));
    }
    
    public void spustiTetris(){
        vykresliVsetko();
        vytvorBlok();
        spravujHru();
    }

}
