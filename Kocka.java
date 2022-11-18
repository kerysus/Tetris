/**
 * Write a description of class Policko here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Kocka
{
    private Stvorec stvorec;
    private int sirka = 20;
    private int odstup = 1;
    private int polohaX;
    private int polohaY;
    private boolean jeBlok;
    private String farba;

    public Kocka(int x, int y, boolean jeBlok, String farba)
    {
        // initialise instance variables
        this.stvorec = new Stvorec();
        
        this.polohaX = x;
        this.polohaY = y;
        this.stvorec.zmenStranu(sirka-odstup);
        this.stvorec.posunVodorovne(x*sirka);
        this.stvorec.posunZvisle(y*sirka);
        
        this.stvorec.zmenFarbu(farba);
        
        this.jeBlok = jeBlok;
        
        this.stvorec.zobraz();
    }
    
    public void update(){
        this.stvorec.zobraz();
    }
    
    public void zmenFarbuPolicka(String farba){
        this.stvorec.zmenFarbu(farba);
    }
}
