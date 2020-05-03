package com.HuffmanCoding;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa <code>HuffmanDictionary</code> zawiera statyczne metody tworzące słownik słów kodowych koodwania Huffmana w
 * oparciu o drzewo binarne
 */
public class HuffmanDictionary {
    /**
     * Funcja <code>createDictionary</code> tworzy słownik słów kodowych kodowania Huffmana
     * @param root korzeń drzewa
     * @param dictionary mapa do której ma być zapisany słownik
     * @param str parametr pomocniczy wykożystywany w rekurencji. Jego wartość wywołania powinna być pustym łańcuchem ""
     */
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
