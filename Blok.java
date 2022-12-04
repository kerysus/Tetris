/**
 * Write a description of class Blok here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Blok {
    private int typBloku;
    private int lastX = 3;
    private int lastY = 1;
    private Kocka[] kocky;
    private boolean[][] tvar;
    private int pocetRiadkovPlochy;
    private int pocetStlpcovPlochy;

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

    public Blok(int riadok, int stlpec, int cislo, int pocetRiadkovPlochy, int pocetStlpcovPlochy) {
        this.pocetRiadkovPlochy = pocetRiadkovPlochy;
        this.pocetStlpcovPlochy = pocetStlpcovPlochy;
        switch (cislo) {
            case 1:
                this.tvar = blokO;
                break;
            case 2:
                this.tvar = blokI;
                break;
            case 3:
                this.tvar = blokL;
                break;
            case 4:
                this.tvar = blokZ;
                break;
            case 5:
                this.tvar = blokS;
                break;
            case 6:
                this.tvar = blokJ;
                break;
            case 7:
                this.tvar = blokT;
                break;
            default:
                System.out.println("Chyba vo vybere tvaru");
        }
        this.kocky = new Kocka[this.tvar.length * this.tvar.length];
        this.vytvorBlok(this.tvar);
    }

    public boolean[][] getTvar() {
        return this.tvar;
    }

    public Kocka[] getKocky() {
        return this.kocky;
    }

    public void vytvorBlok(boolean[][] tvar) {
        int poradie = 0;
        for (int riadok = 0; riadok < tvar.length; riadok++) {
            for (int stlpec = 0; stlpec < tvar[riadok].length; stlpec++) {
                if (tvar[riadok][stlpec]) {
                    this.kocky[poradie] = new Kocka(stlpec + this.lastX, riadok + this.lastY, true, "red");
                    poradie++;
                } else {
                    this.kocky[poradie] = new Kocka(stlpec + this.lastX, riadok + this.lastY, false, "green");
                    poradie++;
                }
            }
        }
    }

    public void vykresliKocky() {
        for (Kocka kocka: this.kocky) {
            kocka.update();
        }
    }

    public void znicBlok(boolean[][] blok) {
        for (Kocka kocka: this.kocky) {
            kocka.zmenFarbuKocky("white");
            kocka = null;
        }
    }

    public void posunHore() {
        for (Kocka kocka: this.kocky) {
            kocka.posunKockyHore();
            kocka.update();
        }
    }

    public void posunDole() {
        for (Kocka kocka: this.kocky) {
            if (kocka.getY() < this.pocetRiadkovPlochy - 1) {
                kocka.posunKockyDole();
                kocka.update();
            }
            continue;
        }
    }

    public void posunVlavo() {
        for (Kocka kocka: this.kocky) {
            kocka.posunKockyVlavo();
            kocka.update();
        }
    }

    public void posunVpravo() {
        for (Kocka kocka: this.kocky) {
            kocka.posunKockyVpravo();
            kocka.update();
        }
    }

    public void vypis(boolean[][] thingy) {
        for (int riadok = 0; riadok < 3; riadok++) {
            for (int stlpec = 0; stlpec < 3; stlpec++) {
                System.out.print(thingy[riadok][stlpec] + " | ");
            }
            System.out.println("");
        }
    }

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