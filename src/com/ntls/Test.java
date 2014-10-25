
package com.ntls;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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


        Chaunter chaunter = new Chaunter();
        for (String arg : args) {
            try (Stream<String> lines = Files.lines(Paths.get(arg))) {
                chaunter.countCharacters(lines);
            }
        }
        
        
        System.out.println("-------- Print results --------");
        chaunter.printSortedByValue(true);
        
    }    
}
