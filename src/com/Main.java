package com;

import com.HuffmanCoding.*;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Main {

    public static void main(String[] args){

//        String data = new String("no nie wiem jak tak twoja szmaciura zrogowaciala niedzwiedzica");
//        HuffmanNode root = HuffmanTreeMaker.makeTree(data);
//        Map<Character, String> dictionary = new HashMap<>();
//        HuffmanDictionary.createDictionary(root, dictionary, "");
//        System.out.println(dictionary);
//        String encodedString = HuffmanEncode.encode(dictionary, data);
//        byte[] encode = HuffmanEncode.encodedStringtoByteArray(encodedString);
//        System.out.println(Arrays.toString(encode));
//        String decodeStr = HuffmanDecode.decode(root, encode, encodedString.length());
//        System.out.println(decodeStr);

        switch (args[0]){
            case "s":
                String data = new String("ty no nie wiem jak tam twoja szmaciura zrogowaciala niedzwiedzica");
                HuffmanNode root = HuffmanTreeMaker.makeTree(data);
                Map<Character, String> dictionary = new HashMap<>();
                HuffmanDictionary.createDictionary(root, dictionary, "");
                String encodedString = HuffmanEncode.encode(dictionary,data);
                byte[] encode = HuffmanEncode.encodedStringtoByteArray(encodedString);
                Socket socket = null;
                try {
                    socket = new Socket(args[1], Integer.parseInt(args[2]));
                    System.out.println("Connected");
                } catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                ObjectOutputStream dictionaryOut = null;
                try {
                    dictionaryOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    dictionaryOut.writeObject(root);
                    dictionaryOut.close();
                    socket.close();
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                DataOutputStream dos = null;
                try {
                    socket = new Socket(args[1], Integer.parseInt(args[2]));
                    System.out.println("Connected");
                } catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                try{
                    dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    dos.writeInt(encodedString.length());
                    dos.write(encode);
                    dos.close();
                }catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
                break;
            case "r":
                Socket rSocket = null;
                ServerSocket server = null;
                try{
                    server = new ServerSocket(Integer.parseInt(args[2]));
                    System.out.println("Waiting for clinet...");
                    rSocket = server.accept();
                    System.out.println("Connected");
                }catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
                ObjectInputStream dictionaryIn = null;
                HuffmanNode rRoot = null;
                try{
                     dictionaryIn = new ObjectInputStream(new BufferedInputStream(rSocket.getInputStream()));
                     rRoot =(HuffmanNode) dictionaryIn.readObject();
                     dictionaryIn.close();
                     rSocket.close();
                }catch(IOException | ClassNotFoundException ex){
                    System.out.println(ex.getMessage());
                }
                int actualEncodedStringLength = 0;
                byte[] encoded = null;
                DataInputStream in = null;

                try{
                    rSocket = server.accept();
                    System.out.println("Connected");
                }catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
                try{
                    in = new DataInputStream(new BufferedInputStream(rSocket.getInputStream()));
                    actualEncodedStringLength = in.readInt();
                    encoded = new byte[(int) Math.ceil((double) actualEncodedStringLength/8)];
                    for(int i = 0; i < encoded.length; i++){
                       encoded[i] = in.readByte();
                    }
                    in.close();
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                String decoded = HuffmanDecode.decode(rRoot, encoded, actualEncodedStringLength);
                System.out.println(decoded);
        }
    }
}
