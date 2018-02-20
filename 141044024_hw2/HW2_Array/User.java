/**
 * abstract Kullanıcı sınıfı,kullanıcı arayüzünü implement etti
 * diğer iki tip kullanıcı sınıfı bu sınıftan türeyecek
 * @author emre
 */
public abstract class User implements UserInterface{
    /**
     * kullanıcının id si(eşsiz)
     */
    private int id;
    /**
     * kullanıcının ismi
     */
    private String name;
    /**
     * kullanıcının şifresi
     */
    private String password;
    /**
     * kullanıcının yetkisi
     */
    private String authority;
    
    /**
     * No parameter constructor
     */
    public User(){}
    
    /**
     * Parametrelerle kullanıcıyı ilklendiren constructor.
     * @param id        id yi ilklendirir
     * @param name      ismi ilklendirir
     * @param password  şifreyi ilklendirir
     * @param authority  yetkiyi ilklendirir
     */
    public User(int id,String name,String password,String authority){
        this.id = id;
        this.name = name;
        this.password = password;
        this.authority = authority;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String getPassword(){
        return password;
    }
    
    @Override
    public void setPassword(String password){
        this.password = password;
    } 
    
    @Override
    public String getAuthority(){
        return authority;
    }
    
    @Override
    public void setAuthority(String password){
        this.authority = authority;
    } 
    

}
