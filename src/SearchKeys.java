
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bonolo
 */
public class SearchKeys {

    /**
     * @param args the command line arguments
     */
    static File file;

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Generates the search keys
        
        List<String>keys = new ArrayList<>();
        file = new File("cleaned_data.csv");
        
        try{
            Scanner key = new Scanner(file);
            key.next();
            while(key.hasNext()){
                String data = key.next();
                Scanner data1 = new Scanner(data);
                data1.useDelimiter(",");
                String date = data1.next();
                keys.add(date);
            }
        }
        catch(FileNotFoundException e){
            
        }
        
        //Shuffle the list
        
        Collections.shuffle(keys);
        
        File file1 = new File("Keys.csv");
        if(!file1.exists()){
            file1.createNewFile();
        }
        
        FileWriter fw = new FileWriter(file1);
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i = 0; i < 500; i++){
            bw.write(keys.get(i));
            bw.newLine();
        }
        bw.close();
    }
    
}
