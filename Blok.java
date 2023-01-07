import java.util.ArrayList;
public class Blok {
    private int typBloku;
    private int lastX = 3;
    private int lastY = 1;
    private Kocka[] kocky;
    private boolean[][] tvar;
    private int pocetRiadkovPlochy;
    private int pocetStlpcovPlochy;
    private boolean pohybBlokuDole;
    private ArrayList < Kocka > polozeneKocky;
    private int dlzkaBloku;
    private int vyskaBloku;
    private int pohybCount;
    private String farba;

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

    public boolean[][] getTvar() {
        return this.tvar;
    }

    public Kocka[] getKocky() {
        return this.kocky;
    }

    public boolean getDalsiBlok() {
        return this.pohybCount > 0;
    }

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

    public void vykresliKocky() {
        for (Kocka kocka: this.kocky) {
            kocka.update();
        }
    }

    public void znicBlok(boolean[][] blok) {
        for (int i = 0; i < this.kocky.length; i++) {
            this.kocky[i].setFarbaKocky("white");
            this.kocky[i] = null;
        }
    }

    //down = D, up = U, left = L, right = R
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

            case 'U':
                break;
        }
        return pohyb;
    }

    public void setPolozeneKocky(ArrayList < Kocka > zoznamKociek) {
        this.polozeneKocky = zoznamKociek;
    }

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

    public void posunHore() {
        this.pohybBlokuDole = this.kontrolaPohybu('U');
        if (this.pohybBlokuDole) {
            for (Kocka kocka: this.kocky) {
                kocka.posunKockyHore();
                kocka.update();
            }
        } else {
            this.pohybCount++;
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