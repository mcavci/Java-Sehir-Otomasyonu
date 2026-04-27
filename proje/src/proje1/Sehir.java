package proje1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mustafa Can Avcı - mustafa.avci3@ogr.sakarya.edu.tr
 * @since 15.04.2026
 * <p>
 * Sehir sınıfı, simülasyon hiyerarşisinin en üst birimidir.
 * Şehre bağlı ilçeleri yönetir ve alt birimlerin toplam nüfusunu hesaplar.
 * </p>
 */
public class Sehir {
    private String ad;
    private List<Ilce> ilceler;

    // Sadece ad ve başlangıç için gerekli parametreleri alıyoruz
    public Sehir(String ad, int onlar, int birler) {
        this.ad = ad;
        this.ilceler = new ArrayList<>();
        
    }

    public void ilceEkle(Ilce ilce) {
        this.ilceler.add(ilce);
    }

    // Şehrin nüfusu, kendine bağlı ilçelerin nüfuslarının toplamıdır
    public int getNufus() {
        int toplam = 0;
        for (Ilce ilce : ilceler) {
            toplam += ilce.getNufus();
        }
        return toplam;
    }

    public String getAd() { return ad; }
    public List<Ilce> getIlceler() { return ilceler; }
}