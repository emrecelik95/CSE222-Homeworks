
public class Main1 {
    public static void main(String[] args) {


        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.offer(7);
        heap.offer(60);
        heap.offer(11);
        heap.offer(1);
        heap.offer(0);
        heap.offer(6);
        heap.offer(4);
        heap.offer(12);
        heap.offer(2);

        System.out.println(heap + "\n");

        System.out.println("poll :" + heap.poll());
        System.out.println("poll :" + heap.poll());

    }
}
