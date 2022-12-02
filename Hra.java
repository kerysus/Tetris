 

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
    
    private int pocetRiadkov;
    private int pocetStlpcov;
    
    public Hra()
    {
        this.manazer = new Manazer();
        this.pocetRiadkov = 20;
        this.pocetStlpcov = 10;
        spustiTetris();
    }

    public void vykresliPlochu(){
        this.plocha = new Plocha(pocetRiadkov, pocetStlpcov);
    }
    
    public int getPlochaPocetRiadkov(){
        return this.plocha.getRiadky();
    }
    
    public int getPlochaPocetStlpcov(){
        return this.plocha.getStlpce();
    }
    
    public void nakresliPozadie(){
        this.pozadie = new Pozadie();
        this.pozadie.vykresliPozadie();
    }
    
    public void vytvorBlok(){
        this.plocha.vytvorBlok();
        this.spravujBLok(this.plocha.getPoslednyZoZoznamuBlokov());
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
