import java.util.ArrayList;
import java.util.Random;

/**
 * Táto trieda slúži na vykreslenie plochy na ktorej spravuje jednotlivé bloky.
 *
 * @author Marek Kerata
 * @version 7.1 2023
 */

public class Plocha {
    private int riadky;
    private int stlpce;
    
    private boolean kontrolaRiadku = false;
    
    private Kocka[][] poleKociek;
    private ArrayList < Blok > zoznamBlokov;
    private Manazer manazer = new Manazer();
    private Blok spravovanyBlok;
    private ArrayList < Kocka > zoznamPolozenychKociek;
    private String[] zoznamFarieb = new String[]{"red", "blue", "green", "magenta"};
    
    /**
     * Konštruktor triedy. Nastaví základné hodnoty ako polohu a farbu kocky, hodnoty o tom či je súčasťou bloku a či sa má táto kocka zobraziť.
     * Na konci vytvorí jeden blok.
     */
    public Plocha(int riadky, int stlpce) {
        this.manazer.spravujObjekt(this);
        this.riadky = riadky;
        this.stlpce = stlpce;
        this.zoznamBlokov = new ArrayList < Blok > ();
        this.zoznamPolozenychKociek = new ArrayList < Kocka > ();
        this.vytvorPlochu();
        this.spravovanyBlok = new Blok(2, 2, 2, this.riadky, this.stlpce, "red");
        this.zoznamBlokov.add(this.spravovanyBlok);
    }
    
    /**
     * Getter na atribút "poleKociek".
     */
    public Kocka[][] getPoleKociek() {
        return this.poleKociek;
    }
    
    /**
     * Getter na atribút "zoznamBlokov".
     */
    public ArrayList < Blok > getZoznamBlokov() {
        return this.zoznamBlokov;
    }
    
    /**
     * Vráti prvý blok zo zoznamu blokov.
     */
    public Blok getPrvyZoZoznamuBlokov() {
        return this.getZoznamBlokov().get(0);
    }
    
    /**
     * Vráti posledný blok zo zoznamu blokov.
     */
    public Blok getPoslednyZoZoznamuBlokov() {
        return this.zoznamBlokov.get(this.zoznamBlokov.size() - 1);
    }
    
    /**
     * Setter na atribút "poleKociek".
     */
    public void setPoleKociek(Kocka[][] listKociek) {
        this.poleKociek = listKociek;
    }
    
    /**
     * Setter na atribút "zoznamBlokov".
     */
    public void setZoznamBlokov(ArrayList < Blok > listBlokov) {
        this.zoznamBlokov = listBlokov;
    }
    
    /**
     * Setter na atribút "polozeneKocky" aktuálne spravovaného bloku.
     */
    public void setPolozeneKockyBlokov(ArrayList < Kocka > zoznamKociek) {
        this.spravovanyBlok.setPolozeneKocky(zoznamKociek);
    }

    /**
     * Vykreslí hraciu plochu pomocou kociek.
     */
    public void vytvorPlochu() {
        this.poleKociek = new Kocka[this.riadky][this.stlpce];
        for (int stlpec = 0; stlpec < riadky; stlpec++) {
            for (int riadok = 0; riadok < stlpce; riadok++) {
                this.poleKociek[stlpec][riadok] = new Kocka(riadok, stlpec, false, "white", true);
            }
        }
    }
    
    /**
     * Vytvorí náhodný blok, pridá ho do zoznamu blokov.
     */
    public void vytvorBlok() {
        Random random = new Random();
        int randomTvar = random.nextInt((7 - 1) + 1) + 1;
        int randomFarba = random.nextInt(this.zoznamFarieb.length);
        this.spravovanyBlok = new Blok(2, 2, randomTvar, this.riadky, this.stlpce, this.zoznamFarieb[randomFarba]);
        this.zoznamBlokov.add(this.spravovanyBlok);
        this.updatePoleKociek(this.spravovanyBlok);
        this.nespravujOstatneBloky();
    }
    
    /**
     * Prestane spravovať ostatné bloky aby nedostávali input z klávesnice.
     */
    public void nespravujOstatneBloky() {
        if (this.getZoznamBlokov().size() == 1) {
            return;
        }
        Blok poslednyBlok = this.getZoznamBlokov().get(this.getZoznamBlokov().size() - 2);
        this.pridajDoPolozenychKociek(poslednyBlok);
        this.manazer.prestanSpravovatObjekt(poslednyBlok);
    }
    
    /**
     * Pridá kocky do zoznamu ako položené s ktorými sa nedá pohnúť.
     */
    public void pridajDoPolozenychKociek(Blok blok) {
        for (Kocka kocka: blok.getKocky()) {
            if (kocka.getZobraz()) {
                this.zoznamPolozenychKociek.add(kocka);
            }
        }
        this.setPolozeneKockyBlokov(this.zoznamPolozenychKociek);
    }

    /**
     * Volá metódy na posun bloku dole.
     */
    public void posunBlokDole() {
        this.spravovanyBlok.posunDole();
        this.updatePoleKociek(this.spravovanyBlok);
    }
    
    /**
     * Volá metódy na posun bloku vlavo.
     */
    public void posunBlokVlavo() {
        this.spravovanyBlok.posunVlavo();
        this.updatePoleKociek(this.spravovanyBlok);
    }
    
    /**
     * Volá metódy na posun bloku vpravo.
     */
    public void posunBlokVpravo() {
        this.spravovanyBlok.posunVpravo();
        this.updatePoleKociek(this.spravovanyBlok);
    }

    /**
     * Volá metódy na otáčanie bloku.
     */
    public void rotujBlok() {
        this.spravovanyBlok.rotuj();
        this.updatePoleKociek(this.spravovanyBlok);
    }

    /**
     * Aktualizuje hodnoty a tým znova vykreslí kocky.
     */
    public void updatePoleKociek(Blok blok) {
        for (Kocka kocka: blok.getKocky()) {
            if (kocka.getJeBlok()) {
                this.poleKociek[kocka.getY()][kocka.getX()].setJeBlok(true);
            } else {
                this.poleKociek[kocka.getY()][kocka.getX()].setJeBlok(false);
            }
        }
    }

    /**
     * Skontroluje riadok či je zaplnený kockami.
     */
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
            }

        }
        this.kontrolaRiadku = false;
    }

    /**
     * Vyčistí riadok ak je plný.
     */
    public void vycistiRiadok() {
        int riadok = 19;
        int pocet = 0;
        for (int stlpec = 0; stlpec < this.poleKociek[riadok].length; stlpec++) {
            this.poleKociek[riadok][stlpec].setJeBlok(false);
            this.poleKociek[riadok][stlpec].setFarbaKocky("white");
        }
    }
    
    /**
     * Tik pravidelne posiela správy objektom.
     */
    public void tik() {
        if (!this.kontrolaRiadku) {
            if (this.spravovanyBlok.getDalsiBlok()) {
                this.vytvorBlok();
                //this.skontrolujRiadok();
            } else {
                this.posunBlokDole();
            }
        }
    }

}