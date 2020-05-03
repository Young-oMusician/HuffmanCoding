package com.SocketTransmission;

import com.HuffmanCoding.HuffmanDecode;
import com.HuffmanCoding.HuffmanNode;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Receiver {
    private int portNumber;

    private HuffmanNode root;
    private byte[] encodedData;
    private int actualEncodedStringLength;

    private String decodedData;

    public Receiver(int portNumber) {
        this.portNumber = portNumber;
    }

    public void receive() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket socket = serverSocket.accept();
        ObjectInputStream treeReceive = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        root = (HuffmanNode) treeReceive.readObject();
        treeReceive.close();
        socket.close();
        socket = serverSocket.accept();
        DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        actualEncodedStringLength = dis.readInt();
        encodedData = new byte[(int) Math.ceil((double) actualEncodedStringLength/8)];
        for(int i = 0; i < encodedData.length; i++){
            encodedData[i] = dis.readByte();
        }
    }

    public void decode(){
        decodedData = HuffmanDecode.decode(root, encodedData, actualEncodedStringLength);
    }

    public byte[] getEncodedData() {
        return Arrays.copyOf(encodedData, encodedData.length);
    }

    public int getActualEncodedStringLength() {
        return actualEncodedStringLength;
    }

    public String getDecodedData() {
        return decodedData;
    }
}
