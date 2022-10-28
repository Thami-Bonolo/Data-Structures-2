/**
 *
 * @author bonolo
 * This class is used to save the data. In other words its our "datatype"
 */
public class Data {
    //Instance variables to save the data
    String date;
    String power;
    String voltage;
    Data next;
    
    /**
     *
     * @param date
     * @param power
     * @param voltage
     */
    public Data(String date, String power, String voltage){
        this.date = date;
        this.power = power;
        this.voltage = voltage;
    }
    
    @Override
    //Overriding the toString method to return data when needed.
    public String toString(){
        return "Date = " + date + " Power = " + power + " Voltage = " + voltage;
    }
}