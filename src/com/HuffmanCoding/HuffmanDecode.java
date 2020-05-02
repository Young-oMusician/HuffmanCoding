package com.HuffmanCoding;

public class HuffmanDecode {

    public static String decode(HuffmanNode root, byte[] encodedData, int actualEncodedStringLength){
        String encodedString = byteArraytoString(encodedData);
        return decode(root, encodedString, actualEncodedStringLength);
    }

    public static String decode(HuffmanNode root, String encodedString, int actualEncodedStringLength){
        String result = new String("");
        HuffmanNode currentNode = root;
        for(int i = 0; i <= actualEncodedStringLength; i++){
            if((currentNode.getRightNode() == null) &&
                    (currentNode.getLeftNode() == null) &&
                    (currentNode.getCharacter() != 0)){
                result += currentNode.getCharacter();
                currentNode = root;
                i--;
            }
            else{
                if(encodedString.charAt(i) == '0'){
                    currentNode = currentNode.getLeftNode();
                }
                else{
                    currentNode = currentNode.getRightNode();
                }
            }
        }
        return result;
    }

    private static String byteArraytoString(byte[] encodedData){
        String result = new String("");
        for(byte it : encodedData) {
            result += String.format("%8s", Integer.toBinaryString(it & 0xFF)).replace(' ', '0');
        }
        return result;
    }
}
