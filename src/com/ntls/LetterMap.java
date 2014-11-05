
package com.ntls;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author ntls
 */

/**
 * This class is intended to be used as a map between one or more letters 
 * to another letter.
 * 
 * e.g. Greek letters: "ΐ" to 'ι', "ί" to 'ι', "ἄ" to 'α'
 * 
 */
public class LetterMap {
    
    private final Map<String, Character> dictionary = new ConcurrentHashMap<>();
    
    public void put(String key, Character value){
        dictionary.put(key, value);
    }
    
    public Set<Entry<String, Character>> entrySet(){
        return dictionary.entrySet();
    }
    
}
