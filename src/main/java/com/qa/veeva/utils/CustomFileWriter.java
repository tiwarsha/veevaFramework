package com.qa.veeva.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CustomFileWriter {
    public String fileWriter (Map<?, ?> hs) {
        
        String outputFilePath = ".//target//output.txt";

        // Step 3: Write the HashMap details to the text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Entry<?, ?> entry : hs.entrySet()) {
                writer.write("Key: " + entry.getKey() + ", Value: " + entry.getValue());
                writer.newLine(); // New line for each entry
            }
            System.out.println("HashMap details have been written to " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return outputFilePath;
    }
}
