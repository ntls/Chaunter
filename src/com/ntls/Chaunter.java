
package com.ntls;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * @author ntls
 */
public class Chaunter {
    
    HashMap<Character, Integer> hashMap;
    
    
    public Chaunter(){
        hashMap = new HashMap<>();
    }
    
    
    /**
     * Prints the characters sorted in ascending order.
     */
    public void print(){
        Comparator<Map.Entry<Character, Integer>> compare = (o1, o2) -> o1.getKey() - o2.getKey();
        printSorted(compare);
    }
    
    
    public void printSortedByKey(boolean descending){
        Comparator<Map.Entry<Character, Integer>> compare;
        
        if(descending){
            compare = (Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) -> o2.getKey()- o1.getKey();
        } else{
            compare = (Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) -> o1.getKey() - o2.getKey();
        }
        
        printSorted(compare);
    }
    
    
    /**
     * Prints the characters sorted by occurrence in descending order if true,
     * in ascending order otherwise.
     * 
     * @param descending 
     */
    public void printSortedByValue(boolean descending){
        Comparator<Map.Entry<Character, Integer>> compare;
        
        if(descending){
            compare = (Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) -> o2.getValue() - o1.getValue();
        } else{
            compare = (Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) -> o1.getValue() - o2.getValue();
        }
        
        printSorted(compare);
    }
        
    
    private void printSorted(Comparator<Map.Entry<Character, Integer>> compare){
        
        Set<Map.Entry<Character, Integer>> entrySet = hashMap.entrySet();
        
        entrySet.stream()
         .sorted(compare)
         .forEach((entrySet1) -> {
             System.out.println( entrySet1.getKey() + " : " + entrySet1.getValue() );
         });  
    }
    
    
    /**
     * Gets a Stream<String> with lines.
     * For every letter in a line if it is absent, is added to the hashMap
     * with default value 1, else increment its value by 1.
     * 
     * @param lines
     */
    public void countCharacters(Stream<String> lines){

        lines.parallel().forEach((String line) -> {
            for (int i = 0; i < line.length(); i++) {
                hashMap.put( line.charAt(i), hashMap.getOrDefault(line.charAt(i), 1)+1 );
            }
        });

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

        lines.parallel().forEach((String line) -> {
            for (int i = 0; i < line.length(); i++) {
                hashMap.put( line.toLowerCase().charAt(i), hashMap.getOrDefault(line.toLowerCase().charAt(i), 1)+1 );
            }
        });

    }
    
    
    /**
     * For testing purposes.
     * 
     * @param args
     * @throws IOException 
     */
    public static void main(String... args) throws IOException {
  
        if ( args.length == 0){
            System.out.println("Please specify at least one text file..");
            System.exit( 1 );
        }
        
        Chaunter chaunter = new Chaunter();
        
        for (String arg : args) {
            try( Stream<String> lines = Files.lines(Paths.get(arg)) ){
                System.out.println("Reading file: " + arg);
                chaunter.countCharactersIgnoreCase(lines);
            }
        }
        
        
        System.out.println("-----------------------");
        chaunter.print();
        System.out.println("-----------------------");
        chaunter.printSortedByValue(true);
    }
}
