# NewsTeller App

## Spring & MongoDB

![](spring-mongodb.png)

### Talimatlar

1. Öncelikle bilgisayarınızda MongoDB kurulu değilse;

* https://www.mongodb.com/try/download/community?tck=docs_server
  linkinden indirip, kurabilirsiniz.

2. Aşağıdaki linkten kurulum talimatlarına erişebilirsiniz.

* https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/#create-database-directory

3. Spring uygulamasında MongoDB'yi kullanabilmeniz için uygulamaya "spring-boot-starter-data-mongodb" paketini eklemeniz
   gerekmektedir (pom.xml dosyası içerisinde görebilirsiniz).
4. Uygulamanın başlayabilmesi için MongoDB server'ının arka tarafta çalışıyor olması gerekmektedir. Bunun için de
   bilgisayarınıza MongoDB veritabanını kurmanız, ardından da çalıştırmanız gerekmektedir. Bunun için 1. ve 2. adımı
   takip edebilirsiniz.


5. Kurulum işlemi gerçekleştikten sonra (veritabanınızı hangi dizine kurduysanız eğer) bu komut ile MongoDB veritabanını
   başlatabilirsiniz. Artık MongoDB gelecek olan bağlantı isteklerine cevap verebilecektir.
   > mongod --dbpath C:\MongoDB\data\db
6. MongoDB veritabanını uygulamanızda kullanabilmeniz için son olarak application.properties dosyasında konfigürasyon
   ayarlamalarını aşağıdaki gibi yapmanız gerekmektedir.
    ```
    # application.properties
      
      /* Veritabanın adı */
      spring.data.mongodb.database=newstellerdb
      
      /* Veritabanın çalışacağı port */
      spring.data.mongodb.port=27017
    ```
7. Bu işlemleri başarıyla gerçekleştirdikten sonra artık MongoDB'yi uygulamanızda kullanabilirsiniz. Veri ekleme, veri
   güncelleme, veri silme vb. işlemleri bu projedeki gibi kolaylıkla kullanabilirsiniz.
