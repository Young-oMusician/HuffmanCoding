package com.HuffmanCoding;

import java.util.Map;

public class HuffmanEncode {
    public static String encode(Map<Character, String> dictionary, String data){
        String encodeStr = new String("");
        for(char character : data.toCharArray()){
            encodeStr += dictionary.get(character);
        }
        return encodeStr;
    }

    public static byte[] encodedStringtoByteArray(String encodedString){
        byte[] encodeBytes = new byte[(int) Math.ceil((double)encodedString.length()/8)];
        for(int i = 0; i < Math.ceil(encodedString.length()/8); i++){
            encodeBytes[i] = (byte) Integer.parseInt(encodedString.substring(i*8, i*8+8),2);
        }
        String lastByte = makeLastByte(encodedString.substring((encodeBytes.length - 1) * 8));
        encodeBytes[encodeBytes.length - 1] = (byte) Integer.parseInt(lastByte, 2);
        return encodeBytes;
    }

    private static String makeLastByte(String lastByte){
        String result = new String(lastByte);
        while(result.length() != 8){
            result += '0';
        }
        return result;
    }
}
