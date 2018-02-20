

/**
 * Kütüphane yönetimi ve verileri için gerekli arayüz.
 * 
 * @author emre
 */
public interface LibData {
    /**
     * Kütüphanenin kitaplarını tutacak olan dosya isminin getterı.
     * @return booksFilename kitapları tutan dosyanın ismi dondurulur
     */
    public String getBooksFilename();
    /**
     * Kütüphanenin kullanıcılarını tutacak olan dosya isminin getterı.
     * @return usersFilename kullanıcıları tutan dosyanın ismi dondurulur
     */
    public String getUsersFilename();

    /**
     * Verilen bilgilere sahip kullanıcının varlığını dosyadan kontrol eder ,
     * varsa bu kullanıcıyı döndürür,yoksa null döndürür.
     * @param name     userın adı
     * @param password userın şifresi
     * @param authority userın yetkisi
     * @return user  aranan userı dondurur
     */
    public User getUser(String name,String password,String authority);
    /**
     * Verilen id'ye sahip kitabın varlığını dosyadan kontrol eder ,
     * varsa bu kitabı döndürür,yoksa null döndürür.
     * @param id    kitabın id si
     * @return book aranan id ye sahip kitabı dondur
     */
    public Book getBook(int id);
    /**
     * Verilen dosyaya bakıp en son olan kayıdın id'sine 1 ekleyip yeni id 
     * yaratır , eğer kayıt yoksa default olarak 1 döndürür.
     * @param filename  bakılacak dosya adı
     * @return id
     */
    public int generateID(String filename);
    /**
     * Verilen ismin verilen dosyada olup olmadığını kontrol eder.
     * @param name     aranacak isim
     * @param filename aranacak dosyanın ismi
     * @return boolean kayıtlarda bu isim varsa true,yoksa false dondurur
     */
    public boolean checkName(String name,String filename);
    /**
     * Verilen id'nin verilen dosyada olup olmadığını kontrol ederç
     * @param id       kontrol edilecek kaydın id si
     * @param filename kontrol edilecek kaydı içeren dosyanın adı
     * @return boolean kayıtlarda varsa true i yoksa false dondurur        
     */
    public boolean checkID(String id,String filename);
    /**
     * Verilen id 'ye sahip kitabın ödünç alınıp alınmadığını kontrol eder.
     * @param id bakılacak kitabın id si
     * @return boolean ödünç alınıp alınmadığına göre boolean dondurur
     */
    public boolean checkBorrowing(String id);
    /**
     * Verilen dosyadaki kayıtları görüntüler.
     * @param filename görüntülenecek kayıtları içeren dosya adı
     */
    public void viewRecords(String filename);
    /**
     * Verilen dosyadan verilen id'ye sahip kaydı görüntüler.
     * @param filename bakılacağı dosya adı
     * @param id görüntülenecek kaydın id si
     */
    public void viewRecord(String filename,int id);
    /**
     * Verilen kullanıcıyı dosyada günceller.
     * @param user güncellenecek kullanıcı
     */
    public void updateUser(User user);
    /**
     * Verilen kitabı dosyada günceller.
     * @param book  güncellenecek kitap
     */
    public void updateBook(Book book);
}
