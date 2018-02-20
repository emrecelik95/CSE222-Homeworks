import javax.management.InstanceAlreadyExistsException;
import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * BinaryTree extend eden FamilyTree sınıfı
 * @param <E>
 */
public class FamilyTree<E extends Comparable<E>>  extends BinaryTree<E> {
    /**
     * no parameter constructor
     */
    public FamilyTree() {
        root = null;
    }

    /**
     * data ile rootu ilklendiren FamilyTree parameter constructor
     *
     * @param data
     */
    public FamilyTree(E data) {
        root = new Node<>(data);
    }

    /**
     * Verilen kisiyi agacta ilgili yere ekler.Hata durumu varsa Exception fırlatır.
     *
     * @param data
     * @param parentName
     * @param parentNickName
     * @return hep true dondurur
     * @throws Exception
     */
    public boolean add(String data, String parentName, String parentNickName) throws Exception {
        if (root == null) {
            root = new Node<E>((E) data);
            return true;
        }
        Node<E> localNode = null;

        String[] nickName = parentNickName.split("-");

        if (nickName[0].equalsIgnoreCase("ebu")) {
            if ((localNode = find((E) parentName)) == null)
                throw new NoSuchElementException();

            if (localNode.left == null) {
                if (nickName[1].equals(data))
                    localNode.left = new Node<E>((E) data);
                else
                    throw new NoSuchElementException();
            } else {
                if (findCount(root, (E) parentName, (E) nickName[1]) > 1)///// hata kontrolu
                    throw new Exception("Parent : " + parentName + " , Child : " + nickName[1]);

                if ((localNode = find(localNode, (E) nickName[1])) == null)
                    throw new NoSuchElementException();


                while (localNode.right != null) {
                    if (localNode.right.data.equals(data)) {
                        System.out.println("This child is already exists!");
                        throw new InstanceAlreadyExistsException();
                    }
                    localNode = localNode.right;
                }

                localNode.right = new Node<E>((E) data);
                return true;
            }
        } else if (nickName[0].equalsIgnoreCase("ibn")) {
            if (findCount(root, (E) nickName[1], (E) parentName) > 1)///////hata kontrolu
                throw new Exception("Parent : " + parentName + " , Child : " + nickName[1]);

            if ((localNode = find((E) nickName[1])) == null)
                throw new NoSuchElementException();

            if ((localNode = find(localNode, (E) parentName)) == null)
                throw new NoSuchElementException();

            if (localNode.left == null)
                localNode.left = new Node<E>((E) data);
            else {
                localNode = localNode.left;
                while (localNode.right != null) {
                    if (localNode.right.data.equals(data)) {
                        System.out.println("This child is already exists!");
                        throw new InstanceAlreadyExistsException();
                    }

                    localNode = localNode.right;
                }
            }

        }

        return true;
    }

    /**
     * Verilen datayı bulup node dondurur.
     *
     * @param data
     * @return
     */
    public Node<E> find(E data) {
        return find(root, data);
    }

    /**
     * Verilen noddan itibaren parentı bulup , cocuguna bakar ve parent,child ikilisinin sayısını dondurur.
     *
     * @param node
     * @param parent
     * @param data
     * @param count
     * @return integer
     */
    private int findCount(Node<E> node, E parent, E data, Integer count) {
        if (node == null)
            return 0;

        if (node.data.equals(parent)) {
            count += (isChildOf(node, data)) ? 1 : 0;
        }

        find(node.left, data);
        find(node.right, data);

        return count;
    }

    /**
     * Countu parametre olarak verebilmek icin helper method.
     *
     * @param node
     * @param parent
     * @param data
     * @return integer
     */
    private int findCount(Node<E> node, E parent, E data) {
        Integer count = new Integer(0);
        return findCount(node, parent, data, count);
    }

    /**
     * Verilen data verilen node un cocugu olup olmadıgını soyler.
     *
     * @param node
     * @param data
     * @return boolean
     */
    private boolean isChildOf(Node<E> node, E data) {
        return (node.left.data.equals(data));
    }

    /**
     * verilen roottan itibaren baslayıp target'ı arar ve target'ın bulundugu node'u dondurur.
     *
     * @param localRoot
     * @param target
     * @return node
     */
    private Node<E> find(Node<E> localRoot, E target) {
        if (localRoot == null)
            return null;

        if (target.equals(localRoot.data))
            return localRoot;

        Node<E> node = find(localRoot.left, target);

        if (node == null)
            node = find(localRoot.right, target);

        return node;
    }

    /**
     * elemanın agacta olup olmadıgını soyler
     *
     * @param target
     * @return boolean
     */
    public boolean contains(E target) {
        return find(target) != null;
    }

}