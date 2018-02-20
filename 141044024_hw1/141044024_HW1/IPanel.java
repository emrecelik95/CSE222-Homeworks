/**
 *
 * @author emre
 *
 */
public interface IPanel {

    /**
     * Sistemi baslat methodu mesaj iletip mainMenu() methodunu çağırır.
     */
    public void startSystem(); 

    /**
     * Anamenu methodu
     * kullanıcı icin gerekli secenekleri sunup 
     * gerekli methodları çağırır.
     */
    public void mainMenu();
}
