
package com.ntls;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 *
 * @author ntls
 */
public class Chaunter {

    private final Map<Character, Integer> map = new ConcurrentHashMap<>();
    
    private static final Comparator<Map.Entry<Character, Integer>> keyComparator
            = (o1, o2) -> Character.compare(o1.getKey(), o2.getKey());
    private static final Comparator<Map.Entry<Character, Integer>> valueComparator
            = (o1, o2) -> Integer.compare(o1.getValue(), o2.getValue());
    
    /**
     * Prints the characters sorted in ascending order.
     */
    public void print(){
        printSorted(keyComparator, false); 
    }

    
    public void printSortedByKey(boolean descending){
        printSorted(keyComparator, descending); 
    }

    
    /**
     * Prints the characters sorted by occurrence in descending order if true,
     * in ascending order otherwise.
     *
     * @param descending
     */
    public void printSortedByValue(boolean descending){
        printSorted(valueComparator, descending);
    }

    
    private void printSorted(Comparator<Map.Entry<Character, Integer>> compare, boolean descending) {
        compare = descending ? Collections.reverseOrder(compare) : compare;
        
        map.entrySet().stream()
       .sorted(compare)
       .forEach(entry ->
               System.out.println(entry.getKey() + " : " + entry.getValue())
       ); 
    }

    
    /**
     * Gets a Stream<String> with lines.
     * For every letter in a line if it is absent, is added to the hashMap
     * with default value 1, else increment its value by 1.
     *
     * @param lines
     */
    public void countCharacters(Stream<String> lines){
        lines.parallel().forEach( line -> countCharacters(line) );
    }

    
    /**
     * Gets a Stream<String> with lines.
     * For every letter in a line if it is absent, is added to the hashMap
     * with default value 1, else increment its value by 1. Characters are set
     * to their lowercase equivalent if applicable.
     *
     * @param lines
     */
    public void countCharactersIgnoreCase(Stream<String> lines){
       lines.parallel().forEach( line -> countCharacters(line.toLowerCase()) );
    }
    
    
    public void countCharacters(String line){
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            map.compute(c, (k,v) -> v == null ? 1 : v + 1); 
        }
    }

 
}
