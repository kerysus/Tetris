/**
 * Táto trieda slúži na vytvorenie kociek, pomocou ktorých sa skladá blok a plocha hry.
 *
 * @author Marek Kerata
 * @version 7.1.2023
 */
public class Kocka {
    private int sirka = 20;
    private int odstup = 2;
    private int x;
    private int y;
    
    private boolean jeBlok;
    private boolean pohybKocky;
    private boolean zobraz;
    
    private String farba;
    
    private Stvorec stvorec;
    
    /**
     * Konštruktor triedy. Nastaví základné hodnoty ako polohu a farbu kocky, hodnoty o tom či je súčasťou bloku a či sa má táto kocka zobraziť.
     */
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
    
    /**
     * Vráti x-ovú polohu kocky na ploche.
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Vráti y-ovú polohu kocky na ploche.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Vráti hodnotu o tom, či je kocka súčasťou bloku.
     */
    public boolean getJeBlok() {
        return this.jeBlok;
    }
    
    /**
     * Vráti hodnotu o tom či je daná kocka zobrazená na ploche.
     */
    public boolean getZobraz() {
        return this.zobraz;
    }
    
    /**
     * Setter na hodnotu "jeBlok".
     */
    public void setJeBlok(boolean hodnota) {
        this.jeBlok = hodnota;
    }
    
    /**
     * Setter na hodnotu "pohybKocky".
     */
    public void setPohybKocky(boolean hodnota) {
        this.pohybKocky = hodnota;
    }
    
    /**
     * Nastaví farbu kocky na požadovanú farbu.
     */
    public void setFarbaKocky(String farba) {
        this.stvorec.zmenFarbu(farba);
    }

    /**
     * Vykreslí kocku na plochu ak je nastavená platí podmienka.
     */
    public void update() {
        if (this.zobraz) {
            this.stvorec.zobraz();
        }
    }
    
    /**
     * Posunie kocku dole o hodnotu 1.
     */
    public void posunKockyDole() {
        this.y += 1;
        this.stvorec.posunZvisle(this.sirka);
    }
    
    /**
     * Posunie kocku vlavo o hodnotu 1.
     */
    public void posunKockyVlavo() {
        this.x -= 1;
        this.stvorec.posunVodorovne(-this.sirka);
    }
    
    /**
     * Posunie kocku vpravo o hodnotu 1.
     */
    public void posunKockyVpravo() {
        this.x += 1;
        this.stvorec.posunVodorovne(this.sirka);
    }
}