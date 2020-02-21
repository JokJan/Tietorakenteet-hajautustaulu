package tira2019;

/**
 * Luokka joka tallettaa avain-arvo-pareja
 * Sebastian Jokela
 * sebastian.jokela@tuni.fi
 * 
 */
public class Alkio {
    //attribuutit
    private Integer avain;
    private Integer arvo;

    //aksessorit
    public Integer avain() {
        return avain;
    }
    
    public void avain(Integer uusiArvo) {
        avain = uusiArvo;
    }
    
    public Integer arvo() {
        return arvo;
    }
    
    public void arvo(Integer uusiArvo) {
        arvo = uusiArvo;
    }

    //rakentajat
    public Alkio(Integer lukuarvo, Integer operaatioArvo) {
        avain = lukuarvo;
        arvo = operaatioArvo;
    }

    //metodit
}
