package com.HuffmanCoding;

import java.util.HashMap;
import java.util.Map;

public class HuffmanDictionary {
//    private Map<Character, String> dictionary;

    public static void createDictionary(HuffmanNode root, Map<Character, String> dictionary, String str){
        if((root.getLeftNode() == null) &&
                (root.getRightNode() == null) &&
                (root.getCharacter() != 0)){
            dictionary.put(root.getCharacter(), str);
            return;
        }
        createDictionary(root.getLeftNode(),dictionary,str + "0");
        createDictionary(root.getRightNode(), dictionary, str + "1");
    }

    public static Map<Character, String> createDictionary(String data){
        HuffmanNode root = HuffmanTreeMaker.makeTree(CharacterCounter.calculate(data));
        Map<Character, String> dictionary = new HashMap<>();
        createDictionary(root, dictionary, "");
        return dictionary;
    }
}
