package chatserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 
 * @title Data Structure to store text file
 * @author shantanubobhate
 * @date November 25th, 2015
 * 
 */
public class StringHashing {
    
    public static void main(String[] args) {
        //Initialize Trie
        TrieST<Integer> st = new TrieST<Integer>();

        // Decalare a FileReader and initialize it to null
        FileReader myFileReader = null;
        
        // Make sets of size below (avarage from statistics)
        int size = 5326;
        
        // Create an array of the alphabet
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f',
                           'g', 'h', 'i', 'j', 'k', 'l',
                           'm', 'n', 'o', 'p', 'q', 'r',
                           's', 't', 'u', 'v', 'w', 'x',
                           'y', 'z'};
        
        // Create Sets and store them in an ArrayList
        ArrayList<MySet> sets = new ArrayList<>();
        for (char c : alphabet)
        {
            // Add set with the character as the identifier and a specified size
            sets.add(new MySet(c, size));
        }
        
        try {
            // Try to read from the file
            myFileReader = new FileReader("/Users/Webster/NetBeansProjects/cache/src/chatserver/DC1-sampleQueries.txt");
            BufferedReader myBufferedReader = new BufferedReader(myFileReader);
            String line;
            while ((line = myBufferedReader.readLine()) != null) {
                // Split the line at any whitespace
                String[] split = line.split("\\s+");
                // Get the first character to determine set
                char firstChar = line.charAt(0);
                // Iterate through the sets
                for (MySet s : sets)
                {
                    // If the first character matches the set identifier
                    // Add the string, its hash and its popularity to the set
                    if (s.getName() == firstChar){
                        s.addNewEntry(Hash(line), split[0], Integer.parseInt(split[1]));
                        //Build Trie
                        st.put(split[0], Integer.parseInt(split[1]));
                    }
                }
            }
        // Catch any exceptions
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        } finally {
            // Try to close the file
            try {
                if (myFileReader != null) myFileReader.close();
            // Catch any exceptions
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
        // Print message if sets were built successfully
        System.out.println("Done adding elements.");
        
        // Print out the set properties
        sets.stream().forEach((s) -> {
            System.out.println("Set " + s.getName() + 
                               " has a hash value of " + s.getMyID() +  
                               " with " + s.getSize() + " elements");
        });
        
        // Print out the hash table in each set
        sets.stream().forEach((s) -> {
            s.printMap();
        });
        
        //Trie out test
        System.out.println("keysWithPrefix(\"x\"):");
        for (String s : st.keysWithPrefix("x"))
            System.out.println(s + " " + st.get(s));
        
        //Blank
        System.out.println();
        
        System.out.println("The total number of keys added is " + st.size());
        
        //Blank
        System.out.println();

        //Initialize GUI
        TrieGUI test = new TrieGUI();
        test.setVisible(true);
        //Load current Trie into GUI
        test.setTrie(st);
        
    }
    
    // Function to hash strings
    private static int Hash(String key) {
        int strlen = key.length();
        int hashValue = 11;
        for (int i = 0; i < strlen; i++) {
            hashValue = hashValue*13 + key.charAt(i);
        }
        return Math.abs(hashValue);
    }
}
