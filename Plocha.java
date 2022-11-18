import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class Plocha here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plocha
{
    // instance variables - replace the example below with your own
    private int riadky;
    private int stlpce;
    private Kocka[][] zoznamKociek;
    private ArrayList<Blok> zoznamBlokov = new ArrayList<Blok>();
              
    public Plocha(int riadky, int stlpce)
    {
        this.riadky = riadky;
        this.stlpce = stlpce;
        
        vytvorPlochu();
    }
    
    public void vytvorPlochu(){
        this.zoznamKociek = new Kocka[riadky][stlpce];
        for (int stlpec = 0; stlpec < riadky; stlpec++){
            for (int riadok = 0; riadok < stlpce; riadok++){
                zoznamKociek[stlpec][riadok] = new Kocka(riadok, stlpec, false, "blue");
            }
        }
        
        Kocka a = zoznamKociek[0][0];
        a.zmenFarbuPolicka("red");
        
        Kocka b = zoznamKociek[this.riadky-1][0];
        b.zmenFarbuPolicka("yellow");
        
        Kocka c = zoznamKociek[0][this.stlpce-1];
        c.zmenFarbuPolicka("green");
        
        Kocka d = zoznamKociek[this.riadky-1][this.stlpce-1];
        d.zmenFarbuPolicka("white");
    }
    
    public void updatePlocha(){
        for (int stlpec = 0; stlpec < riadky; stlpec++){
            for (int riadok = 0; riadok < stlpce; riadok++){
                this.zoznamKociek[stlpec][riadok].update();
            }
        }
    }
    
    public void vytvorBlok(){
        Random random = new Random();
        int randomCislo = random.nextInt((7 - 1) + 1) + 1;
        zoznamBlokov.add(new Blok(2, 2, randomCislo));
    }
}