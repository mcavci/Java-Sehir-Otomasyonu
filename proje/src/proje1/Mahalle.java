package proje1;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author Mustafa Can Avcı - mustafa.avci3@ogr.sakarya.edu.tr
* @since 27.03.2026
* <p>
* Mahalle sınıfı, bir ilçeye bağlı olan yerleşim birimlerini temsil eder.
* İçerisinde o mahallede yaşayan kişilerin listesini tutar ve nüfus 
* verilerinin yönetiminden sorumludur.
* </p>
*/
public class Mahalle {
    private String ad;
    private List<Kisi> kisiler;

    public Mahalle(String ad) {
        this.ad = ad;
        this.kisiler = new ArrayList<>();
    }

    public void kisiEkle(Kisi kisi) {
        this.kisiler.add(kisi);
    }

    public int getNufus() {
        return this.kisiler.size();
    }

    public void yaslandir() {
        for (Kisi k : kisiler) {
            k.yaslandir();
        }
    }

    public void nufusArttir(int katsayi, Faker faker) {
        int mevcut = kisiler.size();

        int eklenecek;

        if (katsayi == 0) {
            eklenecek = 1;
        } else {
            int yeniNufus = mevcut * katsayi;
            eklenecek = yeniNufus - mevcut;
        }

        for (int i = 0; i < eklenecek; i++) {
            kisiler.add(new Kisi(faker.name().fullName(), 0));
        }
    }

    public String getAd() { return ad; }

    public List<Kisi> getKisiler() {
        return new ArrayList<>(kisiler);
    }
}