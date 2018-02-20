import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args){

        FamilyTree<String> tree = new FamilyTree<>();

        FileReader reader = null;
        try {
            reader = new FileReader("family.txt");
            Scanner sc = new Scanner(reader);

            tree.add(sc.nextLine().trim());

            while(sc.hasNext()){
                String[] names = sc.nextLine().split(",");
                tree.add(names[0].trim() , names[1].trim() , names[2].trim());
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        } catch (Exception e){
            System.out.println("Exception caught : " + e.toString());
        }

        System.out.println(tree.toString());
    }
}
