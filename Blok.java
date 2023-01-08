import java.util.ArrayList;
/**
 * Táto trieda slúži na vytvorenie bloku z kociek využitím triedy Kocka().
 * Pri privátnych atribútoch "blokO", "blokI", "blokL", "blokZ", "blokJ", "blokJ", "blokT" som využil kód autora "Melinda Green" zo stránky https://superliminal.com/tetris/Tetris.java
 * 
 * @author Marek Kerata
 * @version 7.1.2023
 */
public class Blok {
    private int typBloku;
    private int lastX = 3;
    private int lastY = 1;
    private int dlzkaBloku;
    private int vyskaBloku;
    private int pohybCount;
    private int pocetRiadkovPlochy;
    private int pocetStlpcovPlochy;
    
    private boolean pohybBlokuDole;
    private boolean[][] tvar;
    
    private String farba;
    
    private ArrayList < Kocka > polozeneKocky;
    private Kocka[] kocky;

    // O tom ako môžem ukladať blok a ako ho vykreslit je viacero spôsobov a chcel som mať taký, ktorému najlepšie rozumiem. 
    // Mal som viacero vlastných nápadov, ale nakoniec sa mi zapáčil spôsob ako tento problém vyriešil autor na tejto stránke:
    // https://superliminal.com/tetris/Tetris.java
    
    private boolean[][] blokO = {
        {
            true,
            true
        },
        {
            true,
            true
        }
    };

    private boolean[][] blokI = {
        {
            true,
            true,
            true,
            true
        },
        {
            false,
            false,
            false,
            false
        },
        {
            false,
            false,
            false,
            false
        },
        {
            false,
            false,
            false,
            false
        }
    };

    private boolean[][] blokL = {
        {
            false,
            true,
            false
        },
        {
            false,
            true,
            false
        },
        {
            false,
            true,
            true
        }
    };

    private boolean[][] blokZ = {
        {
            false,
            true,
            false
        },
        {
            true,
            true,
            false
        },
        {
            true,
            false,
            false
        }
    };

    private boolean[][] blokS = {
        {
            false,
            true,
            false
        },
        {
            false,
            true,
            true
        },
        {
            false,
            false,
            true
        }
    };

    private boolean[][] blokJ = {
        {
            false,
            true,
            false
        }, // f, f, t
        {
            false,
            true,
            false
        }, // t, t, t   ==> transponovana matica
        {
            true,
            true,
            false
        }
    }; // f, f, f

    private boolean[][] blokT = {
        {
            false,
            true,
            false
        },
        {
            true,
            true,
            true
        },
        {
            false,
            false,
            false
        }
    };
    
    /**
     * Konštruktor triedy. Nastaví základné hodnoty ako polohu, tvar a farbu bloku.
     */
    public Blok(int riadok, int stlpec, int randomTvar, int pocetRiadkovPlochy, int pocetStlpcovPlochy, String farba) {
        this.pocetRiadkovPlochy = pocetRiadkovPlochy;
        this.pocetStlpcovPlochy = pocetStlpcovPlochy;
        
        switch (randomTvar) {
            case 1:
                this.tvar = blokO;
                this.dlzkaBloku = 2;
                this.vyskaBloku = 2;
                break;
            case 2:
                this.tvar = blokI;
                this.dlzkaBloku = 4;
                this.vyskaBloku = 1;
                break;
            case 3:
                this.tvar = blokL;
                this.dlzkaBloku = 2;
                this.vyskaBloku = 4;
                break;
            case 4:
                this.tvar = blokZ;
                this.dlzkaBloku = 3;
                this.vyskaBloku = 2;
                break;
            case 5:
                this.tvar = blokS;
                this.dlzkaBloku = 2;
                this.vyskaBloku = 3;
                break;
            case 6:
                this.tvar = blokJ;
                this.dlzkaBloku = 2;
                this.vyskaBloku = 4;
                break;
            case 7:
                this.tvar = blokT;
                this.dlzkaBloku = 3;
                this.vyskaBloku = 2;
                break;
            default:
                System.out.println("Chyba vo vybere tvaru");
        }
        this.farba = farba;
        this.pohybBlokuDole = true;
        this.kocky = new Kocka[this.tvar.length * this.tvar.length];
        this.vytvorBlok(this.tvar);
        this.pohybCount = 0;
    }

    /**
     *  Vráti hodnotu o tom aký tvar má blok.
     */
    public boolean[][] getTvar() {
        return this.tvar;
    }
    
    /**
     *  Vráti všetky kocky, ktoré tvoria tento blok.
     */
    public Kocka[] getKocky() {
        return this.kocky;
    }

    /**
     *  Vráti hodnotu o tom či môžme vytvoriť nový blok.
     */
    public boolean getDalsiBlok() {
        return this.pohybCount > 0;
    }
    
    /**
     *  Setter na atribút triedy. Vráti zoznam už položených kociek na ploche. Túto informácia využíva pri kontrole pohybu.
     */
    public void setPolozeneKocky(ArrayList < Kocka > zoznamKociek) {
        this.polozeneKocky = zoznamKociek;
    }
    
    /**
     *  Vytvorí nový blok pomocou kociek z triedy Kocka.
     */
    public void vytvorBlok(boolean[][] tvar) {
        int poradie = 0;
        for (int riadok = 0; riadok < tvar.length; riadok++) {
            for (int stlpec = 0; stlpec < tvar[riadok].length; stlpec++) {
                if (tvar[riadok][stlpec]) {
                    this.kocky[poradie] = new Kocka(stlpec + this.lastX, riadok + this.lastY, true, this.farba, true);
                    poradie++;
                } else {
                    this.kocky[poradie] = new Kocka(stlpec + this.lastX, riadok + this.lastY, false, "green", false);
                    poradie++;
                }
            }
        }
    }

    /**
     *  Zobrazí všetky kocky bloku.
     */
    public void vykresliKocky() {
        for (Kocka kocka: this.kocky) {
            kocka.update();
        }
    }

    /**
     *  Zničí blok tým že všetky kocky prefarbí a nastaví ich na hodnotu null.
     */
    public void znicBlok(boolean[][] blok) {
        for (int i = 0; i < this.kocky.length; i++) {
            this.kocky[i].setFarbaKocky("white");
            this.kocky[i] = null;
        }
    }
    
    /**
     *  Podľa hodnoty parametra "smer" kontroluje či sa blok do daného smeru môže posunúť. 
     *  down = D, up = U, left = L, right = R
     */
    public boolean kontrolaPohybu(char smer) {
        boolean pohyb = true;
        switch (smer) {
            case 'D':
                pohyb = true;
                for (Kocka kocka: this.kocky) {
                    if (kocka.getZobraz()) {
                        int x = kocka.getX();
                        int y = kocka.getY();
                        if (y == this.pocetRiadkovPlochy - 1) {
                            pohyb = false;
                            break;
                        }

                        if (this.polozeneKocky == null) {
                            break;
                        } else {
                            for (Kocka polozenaKocka: this.polozeneKocky) {
                                if ((kocka.getY() + 1 == polozenaKocka.getY()) && (kocka.getX() == polozenaKocka.getX())) {
                                    pohyb = false;
                                    return pohyb;
                                } else {
                                    pohyb = true;
                                }
                            }
                        }
                    }
                }
                break;

            case 'L':
                pohyb = true;
                for (Kocka kocka: this.kocky) {
                    int x = kocka.getX();
                    int y = kocka.getY();
                    if (kocka.getZobraz()) {
                        if (x == 0) {
                            pohyb = false;
                            break;
                        }

                        if (this.polozeneKocky == null) {
                            break;
                        } else {
                            for (Kocka polozenaKocka: this.polozeneKocky) {
                                if ((kocka.getX() - 1 == polozenaKocka.getX()) && (kocka.getY() == polozenaKocka.getY())) {
                                    pohyb = false;
                                    return pohyb;
                                } else {
                                    pohyb = true;
                                }
                            }
                        }
                    }
                }
                break;

            case 'R':
                pohyb = true;
                for (Kocka kocka: this.kocky) {
                    int x = kocka.getX();
                    int y = kocka.getY();
                    if (kocka.getZobraz()) {
                        if (x == this.pocetStlpcovPlochy - 1) {
                            pohyb = false;
                            break;
                        }
                        if (this.polozeneKocky == null) {
                            break;
                        } else {
                            for (Kocka polozenaKocka: this.polozeneKocky) {
                                if ((kocka.getX() + 1 == polozenaKocka.getX()) && (kocka.getY() == polozenaKocka.getY())) {
                                    pohyb = false;
                                    return pohyb;
                                } else {
                                    pohyb = true;
                                }
                            }
                        }

                    }
                }
                break;
        }
        return pohyb;
    }
    
    /**
     *  Posunie blok dole.
     */
    public void posunDole() {
        this.pohybBlokuDole = this.kontrolaPohybu('D');
        if (this.pohybBlokuDole) {
            for (Kocka kocka: this.kocky) {
                kocka.posunKockyDole();
                kocka.update();
            }
        } else {
            this.pohybCount++;
        }
    }
    
    /**
     *  Posunie blok vľavo.
     */
    public void posunVlavo() {
        this.pohybBlokuDole = this.kontrolaPohybu('L');
        if (this.pohybBlokuDole) {
            for (Kocka kocka: this.kocky) {
                kocka.posunKockyVlavo();
                kocka.update();
            }
        } else {
            this.pohybCount++;
        }
    }
    
    /**
     *  Posunie blok vpravo.
     */
    public void posunVpravo() {
        this.pohybBlokuDole = this.kontrolaPohybu('R');
        if (this.pohybBlokuDole) {
            for (Kocka kocka: this.kocky) {
                kocka.posunKockyVpravo();
                kocka.update();
            }
        } else {
            this.pohybCount++;
        }
    }
    
    /**
     *  Otáča blok v protismere hodinových ručičiek.
     */
    public void rotuj() {
        boolean[][] novyBlok = new boolean[this.tvar.length][this.tvar.length];
        int counter = this.tvar.length - 1;
        for (int riadok = 0; riadok < this.tvar.length; riadok++) {
            for (int prvok = 0; prvok < this.tvar.length; prvok++) {
                novyBlok[(counter - prvok)][riadok] = this.tvar[riadok][prvok];
            }
        }
        this.tvar = novyBlok;
        this.lastX = this.kocky[0].getX();
        this.lastY = this.kocky[0].getY();
        this.znicBlok(this.tvar);
        novyBlok = null;
        this.vytvorBlok(this.tvar);
    }
}