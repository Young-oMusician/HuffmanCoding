package com.HuffmanCoding;

import java.util.ArrayList;

/**
 * Klasa <code>HuffmanTreeMaker</code> posiada statyczą funkcję układającą listę <code>HuffmanNode</code> w
 * drzewo binarne
 */
public class HuffmanTreeMaker {
    /**
     * Funkcja <code>makeTree</code> realizuje łączenie listy <code>HuffmanNode</code> w drzewo binarne
     * @param list  lista węzłów
     * @return zwraca korzeń drzewa
     */
    public static HuffmanNode makeTree(ArrayList<HuffmanNode> list){
        list.sort(new HuffmanComparator());
        while(list.size() > 1){
            int freqSum = list.get(0).getFrequency() + list.get(1).getFrequency();
            list.add(new HuffmanNode((char) 0, freqSum, list.get(0), list.get(1)));
            list.remove(0);
            list.remove(0);
            list.sort(new HuffmanComparator());
        }
        return list.get(0);
    }
    /**
     * Funkcja <code>makeTree</code> realizuje łączenie listy <code>HuffmanNode</code> w drzewo binarne
     * @param data  tekst w oparciu o, który tworzone jest drzewo
     * @return zwraca korzeń drzewa
     */
    public static HuffmanNode makeTree(String data){
        ArrayList<HuffmanNode> list = CharacterCounter.calculate(data);
        return makeTree(list);
    }
}
