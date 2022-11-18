
/**
 * Write a description of class Blok here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Blok
{
    private Kocka[][] kocky;
    private boolean[][] tvar;
    
    private boolean[][] blokO = {{ true, true },
                                  { true, true }};
                                  
    private boolean[][] blokI = {{ false, false,  false, false },
                                  { false, false,  false, false },
                                  { false, false,  false, false },
                                  { true, true,  true, true }};
                                  
    private boolean[][] blokL = {{ false, true,  false},
                              { false, true,  false},
                              { false, true,  true }};
                              
    private boolean[][] blokZ = {{ false, true,  false },
                                  { true,  true,  false },
                                  { true,  false, false }};
         
    private boolean[][] blokS = {{ false, true,  false },
                                  { false, true,  true  },
                                  { false, false, true  }};
                                  
    private boolean[][] blokJ = {{ false, true,  false},
                                { false, true,  false},
                                { true,  true,  false}};
    
    private boolean[][] blokT = {{ false, true,  false },
                                { true,  true,  true  },
                                { false, false, false }};
                                
    public Blok(int riadok,int stlpec, int cislo)
    {
        System.out.println("Random cislo: " + cislo);
        switch(cislo){
        case 1: this.tvar = blokO; break;
        case 2: this.tvar = blokI; break;
        case 3: this.tvar = blokL; break;
        case 4: this.tvar = blokZ; break;
        case 5: this.tvar = blokS; break;
        case 6: this.tvar = blokJ; break;
        case 7: this.tvar = blokT; break;
        default: System.out.println("Chyba vo vybere tvaru");
        }
        vytvorBlok();
    }
    
    
    public void blokKocka(){}
    
    public void vytvorBlok(){ 
        for (int riadok = 0; riadok < tvar.length; riadok++){
            for (int stlpec = 0; stlpec < tvar[riadok].length; stlpec++){
                if (tvar[riadok][stlpec] == true){new Kocka(stlpec+5, riadok+5, true, "red");}
            }
        }
    }

}
