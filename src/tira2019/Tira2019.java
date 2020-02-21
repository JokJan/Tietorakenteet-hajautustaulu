package tira2019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.io.InputStreamReader;

/**
 * Ohjelma lukee kahdesta tiedostosta korkeintaan 10000 numeroa, joiden arvo on korkeintaan 9999.
 * Ne talletetaan kahteen hajautustauluun, joille suoritetaan joukko-operaatiot AND, OR ja XOR.
 * Niiden tuloksena syntyvät hajautustaulut kirjoitetaan tekstitiedostoihin.
 * Ennen tiedostojen kirjoittamista käyttäjä voi poistaa haluamansa alkiot hajautustauluista.
 * @author Sebastian Jokela sebastian.jokela@tuni.fi. readInput, writeOutput ja main operaatioiden pohjat sain tehtävänannon mukana
 */
public class Tira2019 {
    /**
     * Luetaan tiedostosta arvoja, ja tallennetaan ne hajautustauluun
     * @param tiedostoNimi  tiedosto josta arvot luetaan
     * @return tiedostosta luetut arvot hajautustaulussa
     */
    private HashTable readInput(String tiedostoNimi) {
        String rivi;
        int riviNumero = 1;
        
        HashTable luettu = new HashTable();

        try {
            BufferedReader br = new BufferedReader(new FileReader(tiedostoNimi));

            while((rivi = br.readLine()) != null) {
                String[] arvot = rivi.split("\n");
                Integer luku = Integer.valueOf(arvot[0]);
                luettu.put(luku, riviNumero);
                riviNumero++;
            }

        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (NumberFormatException e) {
            System.out.println("Tarkista että tiedosto" + tiedostoNimi + " sisältää vain numeroita");
        }
        
        return luettu;
    }
    
    /**
     * Kirjoitetaan hajautustaulu tiedostoon
     * @param kirjoitettava  Hajautustaulu joka halutaan kirjoittaa
     * @param tiedostoNimi  Tiedoston nimi, johon hajautustaulu kirjoitetaan
     */
    private void writeOutput(HashTable kirjoitettava, String tiedostoNimi) {
        System.out.println("Kirjoitetaan tiedostoa " + tiedostoNimi);
        
        //Annetaan käyttäjälle mahdollisuus poistaa haluamansa alkiot
        poistaAlkio(kirjoitettava);
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(tiedostoNimi));
            for (int i = 0; i < 10000; i++) {
                //Haetaan hajautustaulusta linkitetty lista, joka sisältää i:n arvoiset alkiot
                LinkedList<Alkio> indeksiLista = kirjoitettava.get(i);
                //Jos arvolla i olevia arvoja löytyy hajautustaulusta
                if (indeksiLista.size() > 0) {
                    Alkio ensimmainen = indeksiLista.getFirst();
                    String tulostus = ensimmainen.avain()+ " " + ensimmainen.arvo();
                    bw.write(tulostus);
                    bw.newLine();
                }
                
            }
            bw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
    
    /**
     * Luetaan käyttäjältä luvut, jotka hän haluaa poistaa hajautustaulusta
     * @param muokattava  Hajautustaulu josta poistetaan alkioita
     */
    public void poistaAlkio(HashTable muokattava) {
        boolean luetaan = true;
        BufferedReader lukija =
                   new BufferedReader(new InputStreamReader(System.in));
        
        try {
            while (luetaan) {
                try {
                    System.out.println("Hajautustaulussa on " + muokattava.koko() + " alkiota");
                    System.out.println("Kirjoita poistettava alkio, tai 'e' jos et halua poistaa alkiota");
                    String syote = lukija.readLine();
                    if (syote.equals("e")) {
                        luetaan = false;
                    } else {
                        int poistettava = Integer.parseInt(syote);
                        muokattava.poista(poistettava);
                    }
                    if (muokattava.koko() < 1) {
                        System.out.println("Hajautustaulu on tyhjä");
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Syöte ei ole numero");
                }
                
            } 
        } catch (IOException e) {
            System.out.println("Ongelma lukemisessa");
        } 
    }
    
    /**
     * Laskee joukkoperaatiot AND, OR ja XOR kahdelle hajautustaululle
     * @param joukkoA  Ensimmäinen hajautustaulu
     * @param joukkoB  Toinen hajautustaulu
     * @return Taulukko, joka sisältää hajautustaulut a:n ja b:n unionille, leikkaukselle ja poissulkevalle taille (xor)
     */
    public HashTable[] joukkoOperaatiot(HashTable joukkoA, HashTable joukkoB) {
        //Luodaan hajautustaulut eri joukko-operaatioiden tuloksille, sekä taulukko jossa ne palautetaan
        HashTable[] tulos = new HashTable[3];
        HashTable unioni = new HashTable();
        HashTable leikkaus = new HashTable();
        HashTable xor = new HashTable();
        
        
        for (int i = 0; i < 10000; i++) {
            //Haetaan kummastakin hajautustaulusta linkitetty lista, joka sisältää i:n arvoiset alkiot
            LinkedList<Alkio> aAlkio = joukkoA.get(i);
            LinkedList<Alkio> bAlkio = joukkoB.get(i);
            
            //Jos alkio löytyy molemmista tauluista
            if (aAlkio.size() > 0 && bAlkio.size() > 0) {
                /*haetaan ensimmäinen alkio a:sta, sillä sen muuttuja "arvo" kertoo rivin,
                jolla alkio esiintyi ensimmäistä kertaa tiedostossa "setA.txt"*/
                leikkaus.put(i, aAlkio.getFirst().arvo());
                //listojen koon summa kertoo kuinka monta kertaa kukin luku esiintyi syötetiedostoissa
                unioni.put(i, aAlkio.size() + bAlkio.size());
            } 
            //Jos luku on toisessa taulussa, mutta ei toisessa
            else if ((aAlkio.size() > 0 && bAlkio.isEmpty()) || (aAlkio.isEmpty() && bAlkio.size() > 0)) {
                //Merkataan 1, jos luku on taulussa a, tai kaksi jos se on taulussa b
                if (bAlkio.isEmpty()) {
                    xor.put(i, 1);
                }
                else {
                    xor.put(i, 2);
                }
                unioni.put(i, aAlkio.size() + bAlkio.size());
            }
        }
        tulos[0] = unioni;
        tulos[1] = leikkaus;
        tulos[2] = xor;
        return tulos;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tira2019 ht = new Tira2019();
        System.out.println("Ohjelmalle ei voi syöttää mielivaltaisen suuria alkioita");
        HashTable joukkoA = ht.readInput("setA.txt");
        HashTable joukkoB = ht.readInput("setB.txt");
        HashTable[] tulokset = ht.joukkoOperaatiot(joukkoA, joukkoB);
        ht.writeOutput(tulokset[0], "or.txt");
        ht.writeOutput(tulokset[1], "and.txt");
        ht.writeOutput(tulokset[2], "xor.txt");
    }
    
}
