package com.HuffmanCoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CharacterCounter{
//    private String data;
//    private Map<Character, Integer> characters;

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