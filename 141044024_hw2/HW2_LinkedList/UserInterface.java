/**
 * Kullanıcı arayüz
 * abstract methodlara sahip
 * @author emre
 */
public interface UserInterface {
     /**
     * id getter(override interface)
     * @return id id yi dondurur
     */
    public int getId();
    /**
     * id setter(override interface)
     * @param id id yi degistirir
     */
    public void setId(int id); 
    /**
     * isim getter(override interface)
     * @return name ismi dondurur
     */
    public String getName(); 
    /**
     * isim setter(override interface)
     * @param name ismi degistirir
     */
    public void setName(String name); 
    /**
     * şifre getter(override interface)
     * @return password sifreyi dondurur
     */
    public String getPassword();
    /**
     * şifre setter(override interface)
     * @param password şifreyi degistirir
     */
    public void setPassword(String password);
    /**
     * yetki getter(override interface)
     * @return authority yetkiyi dondurur
     */
    public String getAuthority();
    /**
     * yetki setter(override interface)
     * @param authority yetkiyi deigstirir
     */
    public void setAuthority(String authority);
    
}
