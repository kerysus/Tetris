/**
 * Táto trieda slúži na spustenie samotnej hry, kde volá metódy na vykreslenie.
 *
 * @author Marek Kerata
 * @version 7.1.2023
 */
public class Hra {
    private Pozadie pozadie;
    private Plocha plocha;
    private int pocetRiadkov;
    private int pocetStlpcov;

    /**
     * Konštruktor triedy, ktorý nastaví rozmery plochy a spustí metódu na spustenie hry.
     */
    public Hra() {
        this.pocetRiadkov = 20;
        this.pocetStlpcov = 10;
        this.spustiTetris();
    }

    /**
     * Vytvorí nový objekt triedy Plocha, ktorý vykreslí hracú plochu.
     */
    public void vytvorPlochu() {
        this.plocha = new Plocha(this.pocetRiadkov, this.pocetStlpcov);
    }

    /**
     *  Vytvorí nový objekt triedy Pozadie, ktorý vykreslí pozadie hry.
     */
    public void nakresliPozadie() {
        this.pozadie = new Pozadie();
        this.pozadie.vykresliPozadie();
    }

    /**
     *  Volá metódy na vytvorenie objektov tried Plocha a Pozadie, ktoré vykreslia celú hru.
     */
    public void spustiTetris() {
        this.nakresliPozadie();
        this.vytvorPlochu();
    }

}