package com.HuffmanCoding;

import java.io.Serializable;

public class HuffmanNode implements Serializable {
    private char character;
    private int frequency;
    private HuffmanNode leftNode;
    private HuffmanNode rightNode;

    @Override
    public String toString() {
        return new String(" "+character+"->"+frequency+" ");
    }

    public HuffmanNode(char character, int frequency, HuffmanNode leftNode, HuffmanNode rightNode) {
        this.character = character;
        this.frequency = frequency;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public HuffmanNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HuffmanNode leftNode) {
        this.leftNode = leftNode;
    }

    public HuffmanNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HuffmanNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public boolean equals(Object obj) {
        return this.character == ((HuffmanNode) obj).getCharacter();
    }
}