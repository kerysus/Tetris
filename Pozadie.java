/**
 * Táto trieda slúži na vytvorenie čierneho pozadia hry, ktoré sa nachádza za kockami.
 *
 * @author Marek Kerata
 * @version 7.1.2023
 */
public class Pozadie {
    private Obdlznik pozadieHry;
    private Obdlznik pozadiePlochy;

    private String farbaPozadiaHry;
    private String farbaPozadiaPlochy;

    private int rozmeryPozadiaHryX;
    private int rozmeryPozadiaHryY;
    private int rozmeryPozadiaPlochyX;
    private int rozmeryPozadiaPlochyY;

    private int polohaPozadiaHryX;
    private int polohaPozadiaHryY;
    private int polohaPozadiaPlochyX;
    private int polohaPozadiaPlochyY;

    /**
     * Konštruktor triedy, ktorý nastaví rozmery a farby pozadia.
     */
    public Pozadie() {

        //farba pozadia hry je za stvorcekami
        //farba plochy je cela hra
        this.pozadieHry = new Obdlznik();
        this.pozadiePlochy = new Obdlznik();
        this.zmenFarbuPozadi("black", "brown");
        this.zmenRozmeryPozadia(210, 410, 0, 0);
        this.zmenPolohyPozadi(-5, -5, -20, -20);
    }

    /**
     * Zmení farbu pozadia od parametra.
     */
    public void zmenFarbuPozadi(String farbaHry, String farbaPlochy) {
        this.pozadieHry.zmenFarbu(farbaHry);
        this.pozadiePlochy.zmenFarbu(farbaPlochy);
    }
    
    /**
     * Zmení rozmery pozadia od parametra.
     */
    public void zmenRozmeryPozadia(int rozmeryPozadiaHryX, int rozmeryPozadiaHryY, int rozmeryPozadiaPlochyX, int rozmeryPozadiaPlochyY) {
        this.pozadieHry.zmenStrany(rozmeryPozadiaHryX, rozmeryPozadiaHryY);
        this.pozadiePlochy.zmenStrany(rozmeryPozadiaPlochyX, rozmeryPozadiaPlochyY);
    }
    
    /**
     * Zmení polohu pozadia od parametra.
     */
    public void zmenPolohyPozadi(int posunPozadiaHryX, int posunPozadiaHryY, int posunPozadiaPlochyX, int posunPozadiaPlochyY) {
        this.pozadieHry.pomalyPosunVodorovne(posunPozadiaHryX);
        this.pozadieHry.posunZvisle(posunPozadiaHryY);

        this.pozadiePlochy.pomalyPosunVodorovne(posunPozadiaPlochyX);
        this.pozadiePlochy.posunZvisle(posunPozadiaPlochyY);
    }
    
    /**
     * Volá metódy na vykreslenie pozadia.
     */
    public void vykresliPozadie() {
        this.pozadiePlochy.zobraz();
        this.pozadieHry.zobraz();
    }
}