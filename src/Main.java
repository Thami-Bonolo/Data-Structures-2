/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bonolo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int tableSize = Integer.parseInt(args[0]);
        String collision = args[1];
        String directory = args[2];
        int keys = Integer.parseInt(args[3]);
        Hashing hash = new Hashing(tableSize, collision, directory, keys);
    }
    
}
