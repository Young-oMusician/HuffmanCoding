package com.HuffmanCoding;

import java.util.ArrayList;

public class HuffmanTreeMaker {
//    private ArrayList<HuffmanNode> list;
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

    public static HuffmanNode makeTree(String data){
        ArrayList<HuffmanNode> list = CharacterCounter.calculate(data);
        return makeTree(list);
    }
}
