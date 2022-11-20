 

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
        spravujBLok();
    }
    
    public void updatePlocha(){
        this.plocha.updatePlocha();
    }
    
    public void vykresliVsetko(){
        nakresliPozadie();
        vykresliPlochu();
    }
    
    public void spravujBLok(){
        this.manazer.spravujObjekt(this.plocha.getZoznamBlokov());
    }
    
    public void nespravujBlok(){
        this.manazer.prestanSpravovatObjekt(this.plocha.getZoznamBlokov());
    }
    
    public void spustiTetris(){
        vykresliVsetko();
        vytvorBlok();
    }

}
