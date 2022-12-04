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
    private int odstup = 2;
    private int x;
    private int y;
    private boolean jeBlok;
    private String farba;

    public Kocka(int x, int y, boolean jeBlok, String farba)
    {
        // vytvorenie pozadia hry
        this.stvorec = new Stvorec();
        this.x = x;
        this.y = y;
        this.stvorec.zmenStranu(sirka-odstup);
        this.stvorec.posunVodorovne(x*sirka);
        this.stvorec.posunZvisle(y*sirka);
        this.stvorec.zmenFarbu(farba);
        this.jeBlok = jeBlok;
        this.stvorec.zobraz();
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public boolean getJeBlok(){
        return this.jeBlok;
    }
    
    public void setJeBlok(boolean hodnota){
        this.jeBlok = hodnota;
    }
    
    public void update(){
        this.stvorec.zobraz();
    }
    
    public void zmenFarbuKocky(String farba){
        this.stvorec.zmenFarbu(farba);
    }
    
    public void posunKockyHore(){
        this.y -= 1;
        this.stvorec.posunZvisle(-sirka);
    }
    
    public void posunKockyDole(){
        this.y += 1;
        this.stvorec.posunZvisle(sirka);
    }
    
    public void posunKockyVlavo(){
    this.x -= 1;
        this.stvorec.posunVodorovne(-sirka);
    }
    
    public void posunKockyVpravo(){
        this.x += 1;
        this.stvorec.posunVodorovne(sirka);
    }
}
