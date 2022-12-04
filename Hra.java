/**
 * Write a description of class Plocha here.
 *
 * @author Marek Kerata
 * @version (a version number or a date)
 */
public class Hra {
    private Pozadie pozadie;
    private Plocha plocha;
    private int pocetRiadkov;
    private int pocetStlpcov;

    public Hra() {
        this.pocetRiadkov = 20;
        this.pocetStlpcov = 10;
        this.spustiTetris();
    }

    public Kocka[] getKockyPoslednehoBloku(Kocka[] zoznamKociek) {
        return zoznamKociek;
    }

    public void vytvorPlochu() {
        this.plocha = new Plocha(this.pocetRiadkov, this.pocetStlpcov);
    }

    public void nakresliPozadie() {
        this.pozadie = new Pozadie();
        this.pozadie.vykresliPozadie();
    }

    public void vykresliVsetko() {
        this.nakresliPozadie();
        this.vytvorPlochu();
    }

    public void vypisHodnotyPola() {
        this.plocha.vypisPolaKociek();
    }

    public void spustiTetris() {
        this.vykresliVsetko();
        this.plocha.vytvorBlok();
    }

}