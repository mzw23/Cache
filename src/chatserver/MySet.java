/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @title MySet
 * @author shantanubobhate
 * @date November 21st, 2015
 * 
 */
public class MySet {
    
    /* Properties and Elements */
    // Set name or type of data it holds
    private final char identifier;
    // Hashmap to store strings and their hash values
    private final HashMap data;
    // Hashmap to store strings and their popularities
    private final HashMap popularities;
    // Hash ID of the set
    private int ID;
    // Number of elements in the set
    private int numberOfElements;
    
    /* Constructor */
    public MySet(char name,int tableSize) {
        identifier = name;
        data = new HashMap(tableSize);
        popularities = new HashMap(tableSize);
        numberOfElements = 0;
    }
    
    // Function to add pair to HashMap
    public void addNewEntry(int key, String value, int popularity) {
        data.put(key, value);
        popularities.put(value, popularity);
        ID += key;
        ID %= 5000;
        numberOfElements++;
    }
    
    // Function to get identifier
    public char getName() {
        return identifier;
    }
    
    // Function to get ID
    public int getMyID() {
        return ID;
    }
    
    // Function of get numberOfElements
    public int getSize() {
        return numberOfElements;
    }
    
    // Function to print HashTable
    public void printMap() {
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue() + " -> " + popularities.get(pair.getValue()));
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
