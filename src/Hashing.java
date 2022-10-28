
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author bonolo
 */
public class Hashing {
    File file;
    int tableSize, entries, probes, numOfkeys, searchprobes;
    Data[] HashTable;
    String[] list = new String[500];
    Data p;
    int numOfChains = 0;
    int see;
    String collision, date, power, voltage;
    boolean tablefull = false;
    
    /**
     *
     * @param tableSize
     * @param collision
     * @param directory
     * @param numOfkeys
     */
    public Hashing(int tableSize, String collision, String directory, int numOfkeys){
        //The constructor initialises the instant variable & Scans the file
        this.tableSize = tableSize;
        this.collision = collision;
        this.numOfkeys = numOfkeys;
        HashTable = new Data[tableSize];
        file = new File(directory);
        entries = 0;
        probes = 0;
        if(!this.isPrime(tableSize)){ //Checks the given table size if its prime.
            System.out.println("Table size is not a prime");
            return;
        }
        try{
            Scanner keys = new Scanner(file);
            keys.next();
            while(keys.hasNext() && tablefull==false){
                String data = keys.next();
                Scanner data1 = new Scanner(data);
                data1.useDelimiter(",");
                date = data1.next();
                power = data1.next();
                data1.next();
                voltage = data1.next();
                inserting(collision);
                entries++;
            }
            
            if(collision.equals("Quadratic") | collision.equals("Linear")){
                System.out.println("The load factor is = "+1.00*entries/(tableSize));
            }
            if(collision.equals("Chaining")){
                
                for(int i = 0; i < HashTable.length; i++){
                    if(HashTable[i] != null){
                        numOfChains++;
                    }
                }   
                System.out.println("The load factor is = "+1.00*entries/(numOfChains));
            }
            System.out.println("Total number of probes = " + probes);
            list();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     *
     * @param collision
     */
    public final void inserting(String collision){ // Insert method
        if(collision.equals("Linear")){
            tablefull = (entries == tableSize);
            if(tablefull){
                System.out.println("Hash table is full");
            }
            LinearProb(date, power, voltage);
        }
        if(collision.equals("Quadratic")){
            QuadraticProb(date, power, voltage);
        }
        else{
            Chaining(date, power, voltage);
        }
    }
    
    /**
     *
     * @throws FileNotFoundException
     */
    public void searching() throws FileNotFoundException{ //Search method
        int n = numOfkeys;
        
        while(numOfkeys > 0){
            date = list[numOfkeys];
            if(collision.equals("Linear")){
                System.out.println(FindLinear(date));
            }
            if(collision.equals("Quadratic")){
                System.out.println(FindQuad(date));
            }
            else if (collision.equals("Chaining")){
                System.out.println(FindChain(date));
            }
            numOfkeys--;
        }
        if(n!=0){
            System.out.println();
            System.out.println("The total number of probes generated for searching " +n +" keys is "+searchprobes);
            }
    }
    
    /**
     *
     * @param key
     * @return
     */
    public int hash(String key){ // Hash Function
        int hashVal = 0;
        for(int i = 0; i < key.length(); i++){
            hashVal = 37*hashVal + key.charAt(i);
        }
        hashVal %= tableSize;
        if(hashVal < 0){
            hashVal += tableSize;
        }
        return hashVal;
    }
    
    /**
     *
     * @param date
     * @param power
     * @param voltage
     */
    public void LinearProb(String date, String power, String voltage){ // Insert method for Linear
        int h = hash(date);
        while(HashTable[h] != null){
            h = (h+1) % tableSize;
            probes++;
        }
        HashTable[h] = new Data(date, power, voltage);
    }

    /**
     *
     * @param key
     * @return
     */
    public Data FindLinear(String key){ // Search method for linear
        int h = hash(key);
        while(HashTable[h] != null){
            if(HashTable[h].date.equals(key)){
                return HashTable[h];
            }
            searchprobes++;
            h = (h+1) % tableSize;
        }
        return null;
    }
    
    /**
     *
     * @param date
     * @param power
     * @param voltage
     */
    public void QuadraticProb(String date, String power, String voltage){ //Insert method for Quad
        int h = hash(date);
        int H = hash(date);
        int i = 1;
        while(HashTable[h] != null && tablefull==false){
            try{
                int a = 1/(tableSize - i);
                h = (H + i*i) % tableSize;
                see = i;
                probes++;
                i++;
            }
            catch(Exception e){
                System.err.println("Stack Over Flow!!!!!");
                tablefull = true;
                
            }
        }
        HashTable[h] = new Data(date, power, voltage);
    }
    
    /**
     *
     * @param key
     * @return
     */
    public Data FindQuad(String key){ // Search method for Quadratic
        int h = hash(key);
        int H = hash(key);
        int i = 1;
        while(HashTable[h] != null){
            if(HashTable[h].date.equals(key)){
               return HashTable[h]; 
            }
            h=(H + i*i) % tableSize;
            i++;
        }
        return null;
    }
    
    /**
     *
     * @param date
     * @param power
     * @param voltage
     */
    public void Chaining(String date, String power, String voltage){ //Insert method for Chaining
        int h = hash(date);
        FindChain(date);
        p = new Data(date, power, voltage);
        p.next = HashTable[h];
        HashTable[h] = p;
    }
    
    /**
     *
     * @param key
     * @return
     */
    public Data FindChain(String key){ //Search method for chaining
        int h = hash(key);
        p = HashTable[h];
        while (p != null && !p.date.equals(key)){
            p = p.next;
            probes++;
        }
        return p;
    }
    
    /**
     *
     * @param num
     * @return
     */
    public boolean isPrime(int num){ //Checks if a number is a prime number
        for(int i = 2; i< num; i++ ){
            if(num%i == 0){
                return (num%i != 0);
            }
        }
        return true;
    }
    
    /**
     *
     * @throws FileNotFoundException
     */
    public void list() throws FileNotFoundException{//Gets the list of  keys to be searched and append into an array
        File file3 = new File("../Keys.csv");
        try (Scanner dates = new Scanner(file3)) {
            int i = 0;
            while(dates.hasNext()){
                list[i] = dates.next();
                i++;
            }
        }
        catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
}
