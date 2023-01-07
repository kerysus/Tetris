/**
 * Write a description of class Policko here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Kocka {
    private Stvorec stvorec;
    private int sirka = 20;
    private int odstup = 2;
    private int x;
    private int y;
    private boolean jeBlok;
    private String farba;
    private boolean pohybKocky;
    private boolean zobraz;

    public Kocka(int x, int y, boolean jeBlok, String farba, boolean zobraz) {
        // vytvorenie pozadia hry
        this.stvorec = new Stvorec();
        this.x = x;
        this.y = y;
        this.stvorec.zmenStranu(this.sirka - odstup);
        this.stvorec.posunVodorovne(x * sirka);
        this.stvorec.posunZvisle(y * sirka);
        this.stvorec.zmenFarbu(farba);
        this.jeBlok = jeBlok;
        this.zobraz = zobraz;
        if (zobraz) {
            this.stvorec.zobraz();
        }
        this.pohybKocky = true;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getJeBlok() {
        return this.jeBlok;
    }
    
    public boolean getZobraz() {
        return this.zobraz;
    }
    
    public boolean getPohybKocky() {
        return this.pohybKocky;
    }

    public void setJeBlok(boolean hodnota) {
        this.jeBlok = hodnota;
    }
    
    public void setPohybKocky(boolean hodnota) {
        this.pohybKocky = hodnota;
    }
    
    public void setFarbaKocky(String farba) {
        this.stvorec.zmenFarbu(farba);
    }

    public void update() {
        if (this.zobraz) {
            this.stvorec.zobraz();
        }
    }
    
    public void posunKockyHore() {
        this.y -= 1;
        this.stvorec.posunZvisle(-this.sirka);
    }

    public void posunKockyDole() {
        this.y += 1;
        this.stvorec.posunZvisle(this.sirka);
    }

    public void posunKockyVlavo() {
        this.x -= 1;
        this.stvorec.posunVodorovne(-this.sirka);
    }

    public void posunKockyVpravo() {
        this.x += 1;
        this.stvorec.posunVodorovne(this.sirka);
    }
}