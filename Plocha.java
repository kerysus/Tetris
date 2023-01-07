import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Plocha here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Plocha {
    // instance variables - replace the example below with your own
    private int riadky;
    private int stlpce;
    private Kocka[][] poleKociek;
    private ArrayList < Blok > zoznamBlokov;
    private Manazer manazer = new Manazer();
    private Blok spravovanyBlok;
    private ArrayList < Kocka > zoznamPolozenychKociek;
    private boolean kontrolaRiadku = false;

    public Plocha(int riadky, int stlpce) {
        this.manazer.spravujObjekt(this);
        this.riadky = riadky;
        this.stlpce = stlpce;
        this.zoznamBlokov = new ArrayList < Blok > ();
        this.zoznamPolozenychKociek = new ArrayList < Kocka > ();
        this.vytvorPlochu();
    }

    public Kocka[][] getPoleKociek() {
        return this.poleKociek;
    }

    public ArrayList < Blok > getZoznamBlokov() {
        return this.zoznamBlokov;
    }

    public Blok getPrvyZoZoznamuBlokov() {
        return this.getZoznamBlokov().get(0);
    }

    public Blok getPoslednyZoZoznamuBlokov() {
        return this.zoznamBlokov.get(this.zoznamBlokov.size() - 1);
    }

    public void setPoleKociek(Kocka[][] listKociek) {
        this.poleKociek = listKociek;
    }

    public void setZoznamBlokov(ArrayList < Blok > listBlokov) {
        this.zoznamBlokov = listBlokov;
    }

    public void vytvorPlochu() {
        this.poleKociek = new Kocka[this.riadky][this.stlpce];
        for (int stlpec = 0; stlpec < riadky; stlpec++) {
            for (int riadok = 0; riadok < stlpce; riadok++) {
                this.poleKociek[stlpec][riadok] = new Kocka(riadok, stlpec, false, "white", true);
            }
        }
    }

    public void vytvorBlok() {
        Random random = new Random();
        int randomTvar = random.nextInt((7 - 1) + 1) + 1;
        this.spravovanyBlok = new Blok(2, 2, randomTvar, this.riadky, this.stlpce, "red");
        this.zoznamBlokov.add(this.spravovanyBlok);
        this.updatePoleKociek(this.spravovanyBlok);
        this.nespravujOstatneBloky();
    }

    public void nespravujOstatneBloky() {
        if (this.getZoznamBlokov().size() == 1) {
            return;
        }
        Blok poslednyBlok = this.getZoznamBlokov().get(this.getZoznamBlokov().size() - 2);
        this.pridajDoPolozenychKociek(poslednyBlok);
        this.manazer.prestanSpravovatObjekt(poslednyBlok);
    }

    public void pridajDoPolozenychKociek(Blok blok) {
        for (Kocka kocka: blok.getKocky()) {
            if (kocka.getZobraz()) {
                this.zoznamPolozenychKociek.add(kocka);
            }
        }
        this.setPolozeneKockyBlokov(this.zoznamPolozenychKociek);
    }

    public void setPolozeneKockyBlokov(ArrayList < Kocka > zoznamKociek) {
        this.spravovanyBlok.setPolozeneKocky(zoznamKociek);
    }

    public void posunBlokHore() {
        this.spravovanyBlok.posunHore();
        this.updatePoleKociek(this.spravovanyBlok);
    }

    public void posunBlokDole() {
        this.spravovanyBlok.posunDole();
        this.updatePoleKociek(this.spravovanyBlok);
    }

    public void posunBlokVlavo() {
        this.spravovanyBlok.posunVlavo();
        this.updatePoleKociek(this.spravovanyBlok);
    }

    public void posunBlokVpravo() {
        this.spravovanyBlok.posunVpravo();
        this.updatePoleKociek(this.spravovanyBlok);
    }

    public void rotujBlok() {
        this.spravovanyBlok.rotuj();
        this.updatePoleKociek(this.spravovanyBlok);
    }

    public void updatePoleKociek(Blok blok) {
        for (Kocka kocka: blok.getKocky()) {
            if (kocka.getJeBlok()) {
                this.poleKociek[kocka.getY()][kocka.getX()].setJeBlok(true);
            } else {
                this.poleKociek[kocka.getY()][kocka.getX()].setJeBlok(false);
            }
        }
    }

    public void vypisPolaKociek() {
        for (int riadok = 0; riadok < this.poleKociek.length; riadok++) {
            for (int prvok = 0; prvok < this.poleKociek[riadok].length; prvok++) {
                System.out.print(this.poleKociek[riadok][prvok].getJeBlok() + ", ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void skontrolujRiadok() {
        this.kontrolaRiadku = true;
        int riadok = 19;
        int pocet = 0;
        for (int stlpec = 0; stlpec < this.poleKociek[riadok].length; stlpec++) {
            if (this.poleKociek[riadok][stlpec].getJeBlok()) {
                pocet++;
                if (pocet >= 8) {
                    this.vycistiRiadok();
                }
                System.out.println(pocet);
            }

        }
        this.kontrolaRiadku = false;
    }

    public void vycistiRiadok() {
        int riadok = 19;
        int pocet = 0;
        for (int stlpec = 0; stlpec < this.poleKociek[riadok].length; stlpec++) {
            this.poleKociek[riadok][stlpec].setJeBlok(false);
            this.poleKociek[riadok][stlpec].setFarbaKocky("white");
        }
    }

    public void tik() {
        if (!this.kontrolaRiadku) {
            if (this.spravovanyBlok.getDalsiBlok()) {
                this.vytvorBlok();
                this.skontrolujRiadok();
            } else {
                this.posunBlokDole();
            }
        }
    }

}