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
        this.pohybBlokuDole = true;
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
                    this.kocky[poradie] = new Kocka(stlpec + this.lastX, riadok + this.lastY, true, "red", true);
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
        for (Kocka kocka: this.kocky) {
            kocka.zmenFarbuKocky("white");
            kocka = null;
            //pouzit klasicky for loop
        }
    }
    
    //down = D, up = U, left = L, right = R
    public boolean kontrolaPohybu(char smer) {
        boolean pohyb = true;
        switch(smer){ 
            case 'D':
                int i = 0;
                for (Kocka kocka : this.kocky) {
                    if (kocka.getZobraz()) {
                        i++;
                        System.out.println(i);
                        int x = kocka.getX();
                        int y = kocka.getY();
                        if (y == this.pocetRiadkovPlochy-3) {
                            pohyb = false;
                            break;
                        }
                        
                        if (this.polozeneKocky == null) {
                            break;
                        }
                        
                        else {
                            for (Kocka polozenaKocka : this.polozeneKocky){
                                if ((kocka.getY()+1 == polozenaKocka.getY()) && (kocka.getX() == polozenaKocka.getX())) {
                                    System.out.print("je tam kocka, ");
                                    pohyb = false;
                                    return pohyb;
                                }
                                else{
                                    System.out.print("idem dalej, ");
                                    pohyb = true;
                                }
                            }
                        }
                    }
                }
                break;
                
            case 'L':
                for (Kocka kocka : this.kocky) {
                    int x = kocka.getX();
                    int y = kocka.getY();
                    System.out.print(x+" "+y+", ");
                    if (kocka.getZobraz()) {
                        if (x == 0){
                            pohyb = false;
                            break;
                        }
                        
                        if (this.polozeneKocky == null) {
                            break;
                        }
                        
                        else {
                            for (Kocka polozenaKocka : this.polozeneKocky){
                                if ((kocka.getX()-1 == polozenaKocka.getX()) && (kocka.getY() == polozenaKocka.getY())) {
                                    pohyb = false;
                                    return pohyb;
                                }
                                else{
                                    pohyb = true;
                                }
                            }
                        }
                    }
                }
                break;
                
            case 'R':
               for (Kocka kocka : this.kocky) {
                    int x = kocka.getX();
                    int y = kocka.getY();
                    System.out.print(x+" "+y+", ");
                    if (kocka.getZobraz()) {
                        if (x == this.pocetStlpcovPlochy-1){
                            pohyb = false;
                            break;
                        }
                        if (this.polozeneKocky == null) {
                            break;
                        }
                        
                        else {
                            for (Kocka polozenaKocka : this.polozeneKocky){
                                if ((kocka.getX()+1 == polozenaKocka.getX()) && (kocka.getY() == polozenaKocka.getY())) {
                                    pohyb = false;
                                    return pohyb;
                                }
                                else{
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
    
    public void setPolozeneKocky(ArrayList < Kocka > zoznamKociek){
        this.polozeneKocky = zoznamKociek;
    }
    
    public void posunDole() {
        this.pohybBlokuDole = kontrolaPohybu('D');
        if(this.pohybBlokuDole){
            for (Kocka kocka: this.kocky) {
                    kocka.posunKockyDole();
                    kocka.update();
            }
        }
    }

    public void posunVlavo() {
        this.pohybBlokuDole = kontrolaPohybu('L');
        if(this.pohybBlokuDole){
            for (Kocka kocka: this.kocky) {
                kocka.posunKockyVlavo();
                kocka.update();
            }
        }
    }

    public void posunVpravo() {
        this.pohybBlokuDole = kontrolaPohybu('R');
        if(this.pohybBlokuDole){
            for (Kocka kocka: this.kocky) {
                kocka.posunKockyVpravo();
                kocka.update();
            }
        }
    }
    
    public void posunHore() {
        this.pohybBlokuDole = kontrolaPohybu('U');
        if(this.pohybBlokuDole){
            for (Kocka kocka: this.kocky) {
                kocka.posunKockyHore();
                kocka.update();
            }
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