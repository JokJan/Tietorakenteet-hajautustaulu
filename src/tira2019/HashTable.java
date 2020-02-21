package tira2019;

import java.util.LinkedList;

/**
 * Luokka, joka toteuttaa hajautustaulun, johon voi laittaa 10000 alkiota, joiden maksimikoko on 9999.
 * Hajautustaulu toteutetaan taulukolla, jonka alkioita ovat Alkio-luokan olioita sisältävät linkitetyt listat.
 * Sebastian Jokela
 * sebastian.jokela@tuni.fi
 * 
 */
public class HashTable {
    //attribuutit
    
    private LinkedList<Alkio>[] hajautustaulu = new LinkedList[10000];
    //Hajautustaulun alkioiden määrä
    private int koko;
    
    //aksessorit
    
    public int koko() {
        return koko;
    }

    //rakentajat
    
    public HashTable() {
        //lisätään jokaiseen taulukon alkioon linkitetty lista
        for(int i=0; i<10000; i++) {
            hajautustaulu[i] = new LinkedList<>();
        }
        koko = 0;
    }

    //metodit
    
    /**
     * Lisätään taulukkoon alkio, joka koostuu avain-arvo parista
     * @param uusiAvain  taulukkoon talletettava avain
     * @param uusiArvo  taulukkoon talletettava arvo
     */
    public void put(Integer uusiAvain, Integer uusiArvo) {
        if (uusiAvain < 10000) {
            Alkio lisättävä = new Alkio(uusiAvain, uusiArvo);
            //lista pidetään lisäysjärjestyksessä
            hajautustaulu[uusiAvain].addLast(lisättävä);
            koko++;
        }
        else {
            System.out.println("Luku " + uusiAvain + " on liian suuri");
        }
    }
    
    /**
     * Palautetaan lista alkioista, joiden avain on annettu
     * @param indeksi avain jonka alkiot halutaan
     * @return  lista alkioista jotka vastaavat haluttua avainta
     */
    public LinkedList<Alkio> get(Integer indeksi) {
        return hajautustaulu[indeksi];
    }
    
    /**
     * Poistetaan viimeksi lisätty alkio, joka kuuluu listaan annetussa indeksissä
     * @param indeksi  Se indeksi, josta halutaan poistaa alkio
     */
    public void poista(int indeksi) {
        if (hajautustaulu[indeksi].isEmpty()) {
            System.out.println("Tälläistä alkiota ei löydy hajautustaulusta");
        }
        else {
            hajautustaulu[indeksi].removeLast();
            koko--;
        }
    }
}