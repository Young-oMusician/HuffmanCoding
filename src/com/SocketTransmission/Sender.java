package com.SocketTransmission;

import com.HuffmanCoding.HuffmanDictionary;
import com.HuffmanCoding.HuffmanEncode;
import com.HuffmanCoding.HuffmanNode;
import com.HuffmanCoding.HuffmanTreeMaker;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Sender {
    private String data;
    private int portNumber;
    private String address;

    private HuffmanNode root;
    private Map<Character, String> dictionary;
    private String encodedString;
    byte[] encodedData;

    public Sender(String data, String address, int portNumber) {
        this.data = data;
        this.portNumber = portNumber;
        this.address = address;
        
        this.dictionary = new HashMap<>();
    }

    public void encode(){
        root = HuffmanTreeMaker.makeTree(data);
        HuffmanDictionary.createDictionary(root, dictionary, "");
        encodedString = HuffmanEncode.encode(dictionary, data);
        encodedData = HuffmanEncode.encodedStringtoByteArray(encodedString);
    }

    public void send()throws IOException{
        Socket socket = new Socket(address, portNumber);
        ObjectOutputStream treeSender = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        treeSender.writeObject(root);
        treeSender.close();
        socket.close();
        socket = new Socket(address, portNumber);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        dos.writeInt(encodedString.length());
        dos.write(encodedData);
        dos.close();
        socket.close();
    }

    public String getEncodedString() {
        return encodedString;
    }
}
