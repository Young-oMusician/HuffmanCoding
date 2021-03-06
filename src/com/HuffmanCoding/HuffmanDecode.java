package com.HuffmanCoding;

/**
 * Klasa <code>HuffmanDecode</code> zawiera statyczne metody służące dekodowaniu danych zakodowanych metodą Huffmana
 */
public class HuffmanDecode {
    /**
     * Funkcja dekodująca dane zakodowane metodą Huffmana
     * @param root korzeń drzewa
     * @param encodedData zakodowane dane
     * @param actualEncodedStringLength właściwa długość zakodowanej wartości (ilość bitów zakodowanej zawartości nie
     *                                  musi być na równi z bajtem)
     * @return <code>String</code> z odkodowaną treścią
     */
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
