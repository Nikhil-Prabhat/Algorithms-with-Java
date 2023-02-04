package Miscellenous;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
* Huffman Encoding is a lossless data compression algorithm. The idea is to assign variable length codes to input characters, lengths
* of the assigned codes are based on the frequencies of the corresponding characters.
* It is based on the optimal merge pattern.
* It is a greedy approach.
* Time complexity : O(nlogn) where n is the no of unique characters and extractMin() takes O(logn) time to minHeapify()
* */

// Basic Structure of Huffman Node to generate the tree
class HuffmanNode {
    int data;
    char letter;

    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(){

    }

    public HuffmanNode(int data, char c) {
        this.data = data;
        this.letter = c;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", letter=" + letter +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}


public class Huffman_Encoding {

    public static void main(String[] args) {

        char[] letters = new char[]{'a','b','c','d','e','f'};
        int[] frequency = new int[]{ 5, 9, 12, 13, 16, 45 };

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new Comparator<HuffmanNode>() {
            @Override
            public int compare(HuffmanNode node1, HuffmanNode node2) {
                return node1.data - node2.data;
            }
        });

        // Iterate all the nodes and add it to the priority queue
        for(int i=0;i<letters.length;i++)
        {
            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.data = frequency[i];
            huffmanNode.letter = letters[i];

            priorityQueue.add(huffmanNode);
        }

        // Create a root node
        HuffmanNode root = null;

        // Extract two minimum values from the priorityqueue and merge it and add it again to the queue
        // till there is only one element in the queue

        while (priorityQueue.size() > 1)
        {
            HuffmanNode node1 = priorityQueue.poll();
            HuffmanNode node2 = priorityQueue.poll();

            HuffmanNode node = new HuffmanNode();
            node.data = node1.data + node2.data;
            node.letter = '-';

            // Set first extracted node as left and second extracted node as right node
            node.left = node1;
            node.right = node2;

            // Set the node as the root node
            root = node;

            priorityQueue.add(node);
        }

        // Print the Huffman Tree
        Huffman_Encoding.printHuffmanTree(root,"");

    }

    private static void printHuffmanTree(HuffmanNode root, String s)
    {
        // Base case : If both the left and right node is null, then it is a leaf node
        if(root.left == null && root.right == null && Character.isLetter(root.letter))
        {
            System.out.println(root.letter + " : "+ s);
            return;
        }

        // If we go to the left, add 0 to the string
        // If we go to the right, add 1 to the string

        printHuffmanTree(root.left,s+"0");
        printHuffmanTree(root.right,s+"1");
    }
}

/*
* References : Abdul Bari Algorithms : Huffman Encoding
* GeeksforGeeks : https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
* */