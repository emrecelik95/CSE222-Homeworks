
import java.util.NavigableMap;


public class Main
{
    public static void main(String args[]){
        System.out.println("------------------------------------------------------------\nTEST 1\n");
        final Boolean q1 = Q1Test();
        System.out.println("------------------------------------------------------------\nTEST 2\n");
        final Boolean q2 = Q2Test();
        if (q1 == q2 == Boolean.TRUE) {
            System.out.println("Your tests is done. Make sure that you test all methods of class!! " );
            return;
        }
    }
    public static Boolean Q1Test(){

        NavigableMap<String,String> turkey = new BinaryNavMap<String,String>();
        turkey.put("uskudar","istanbul");
        turkey.put("kadıkoy","istanbul");
        turkey.put("cekirge","bursa");
        turkey.put("gebze","kocaeli");
        turkey.put("niksar","tokat");
        turkey.put("kecıoren","ankara");
        turkey.put("aksaray","istanbul");
        turkey.put("foca","izmir");
        turkey.put("manavgat","antalya");
        turkey.put("kahta","adıyaman");
        turkey.put("biga","canakkale");

        System.out.println("The original set odds is \n" + turkey);
        NavigableMap<String,String> m = turkey.subMap("gebze",true,"manavgat",false);
        System.out.println("The ordered set m is \n" + m);
        System.out.println("The first entry is " +turkey.firstEntry());
        System.out.println("The last entry entry is " +turkey.lastEntry());
        System.out.println("The first key entry is " +turkey.firstKey());
        System.out.println("The last key entry is " +turkey.lastKey() + "\n");
//////////////////////////////////////////////////////////////////////////////

        System.out.println("Entry set is \n" + turkey.entrySet() + "\n");

        System.out.println("lowerEntry -> manavgat is " + turkey.lowerEntry("manavgat"));
        System.out.println("lowerKey -> manavgat is " + turkey.lowerKey("manavgat") + "\n");

        System.out.println("floorEntry -> gelibolu is " + turkey.floorEntry("gelibolu"));
        System.out.println("floorKey -> gebze is " + turkey.floorKey("gebze") +"\n");

        System.out.println("ceilingEntry -> kartal is " + turkey.ceilingEntry("kartal"));
        System.out.println("ceilingKey -> kahta is " + turkey.ceilingKey("kahta") +"\n");

        System.out.println("higherEntry -> foca is " + turkey.higherEntry("foca"));
        System.out.println("higherKey -> foca is " + turkey.higherKey("foca") +"\n");

        System.out.println("poll first entry is " + turkey.pollFirstEntry());
        System.out.println("poll last entry is " + turkey.pollLastEntry() +"\n");

        System.out.println("Descending map is \n" + turkey.descendingMap() + "\n");

        System.out.println("navigableKeySet is " + turkey.navigableKeySet() + "\n");
        System.out.println("descendingKeySet is " + turkey.descendingKeySet() + "\n");

        System.out.println("headMap is \n" + turkey.headMap("foca",true) + "\n");
        System.out.println("tailMap is \n" + turkey.tailMap("kahta",true) + "\n");
        //you should write more test function to show your solution
        //your test must contain all methods to get full points!!!
        //you also may need to owerwrite some methods to provide BST RULES

        /* *some links to help you

           https://docs.oracle.com/javase/8/docs/api/java/util/NavigableMap.html
           https://docs.oracle.com/javase/8/docs/api/java/util/AbstractMap.html

        * */
        return Boolean.TRUE;

    }
    public static Boolean Q2Test(){
        HashMap<String,String> turkey=new HashTableChaining<String,String>();
        turkey.put("edremit","balikesir");
        turkey.put("edremit","van");
        turkey.put("kemalpasa","bursa");
        turkey.put("kemalpasa","izmir");
        turkey.put("ortakoy","istanbul");//we assume a district
        turkey.put("ortakoy","aksaray");
        turkey.put("ortakoy","corum");
        turkey.put("kecıoren","ankara");
        turkey.put("pinarbasi","kastamonu");
        turkey.put("pinarbasi","kayseri");
        turkey.put("eregli","konya");
        turkey.put("eregli","tekirdag");
        turkey.put("eregli","zonguldak");
        turkey.put("golbasi","adıyaman");
        turkey.put("golbasi","ankara");
        turkey.put("biga","canakkale");

        System.out.print("get -> kemalpasa is " + turkey.get("kemalpasa") + "\n");
        System.out.print("remove -> ortakoy is " + turkey.remove("ortakoy") + "\n");
        System.out.print("size of turkey is " + turkey.size() + "\n\n");
        /* *test all

            V get(Object key);

            V put(K key, V value);

            V remove(Object key);

            int size();

        * */


        return Boolean.TRUE;
    }


}
