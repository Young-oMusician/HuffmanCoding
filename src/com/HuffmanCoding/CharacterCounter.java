package com.HuffmanCoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Klasa <code>CharacterCounter</code> zlicza liczbę wystąpień poszczególnych znaków w podanym tekście
 */
public class CharacterCounter{
    /**
     * Funkcja <code>calculate</code> oblicza ilość wystąpień poszczególnych znaków w podanym tekście
     * @param data tekst do wyliczenia
     * @return ArrayList<HuffmanNode> zwraca listę obiketów węzłów zawierających informację i znaku i jego
     * częstotliwości występowania w tekście
     */
    public static ArrayList<HuffmanNode> calculate(String data){
        Map<Character, Integer> characters = new HashMap<>();
        for(char character : data.toCharArray()){
            if(!characters.containsKey(character)){
                characters.put(character, 1);
            }
            else{
                Integer newValue = characters.get(character) + 1;
                characters.replace(character, newValue);
            }
        }
        ArrayList<HuffmanNode> result = new ArrayList<>();
        characters.forEach((k, v) -> {
            result.add(new HuffmanNode(k, v, null, null));
        });

        return result;
    }
}