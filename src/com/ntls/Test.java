
package com.ntls;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author ntls
 */
public class Test {
    
   /**
     * For testing purposes.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String... args) throws IOException {

        if (args.length == 0) {
            System.out.println("Please specify at least one text file..");
            System.exit(1);
        }

        
        Chaunter chaunter7 = new Chaunter();
        for (String arg : args) {
            try (Scanner scanner = new Scanner(new FileReader(arg))) {
                try{
                    while( true ){ //stops when nextLine() throws NoSuchElementException
                        chaunter7.countCharacters( scanner.nextLine() );
                    }
                }catch(java.util.NoSuchElementException nse){
                }

            }
        }

        Chaunter chaunter8 = new Chaunter();
        for (String arg : args) {
            try (Stream<String> lines = Files.lines(Paths.get(arg))) {
                chaunter8.countCharacters(lines);
            }
        }
        
        System.out.println("Print results -----------------------");
        chaunter7.printSortedByValue(true);
        
        System.out.println("Print results with lambdas-----------------------");
        chaunter8.printSortedByValue(true);
        
    }    
}
