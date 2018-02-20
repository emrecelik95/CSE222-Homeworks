/**
 * Ana class
 *
 * @author emre
 * @see LibSystem
 */
public class Main {
    
    /** 
     * Main methodu yalnızca LibSystem objesi olusturur ve bu objenin 
     * startSystem() methodunu çağırır.
     * 
     * @param args argumanlar olarak string dizisi alır
     * @see LibSystem
     */
    public static void main(String []args) // MAIN METHOD
    {   
        LibSystem libSystem = new LibSystem();          // LIBRARY SISTEM OBJESI
        libSystem.startSystem();            // LIBRARY SISTEMININ CALISTIRILMASI
    }
}
