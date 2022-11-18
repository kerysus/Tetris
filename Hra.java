 

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
    
    private int pocetRiadkov;
    private int pocetStlpcov;
    
     
    //boolean[][] tvarI = {{true,false,true},{false, true, false}};
    
    
    public Hra()
    {
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
    }
    
    public void updatePlocha(){
        this.plocha.updatePlocha();
    }
    
    public void vykresliVsetko(){
        nakresliPozadie();
        vykresliPlochu();
    }
    
    public void spustiTetris(){
        vykresliVsetko();
    }

}
