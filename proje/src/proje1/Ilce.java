package proje1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mustafa Can Avcı - mustafa.avci3@ogr.sakarya.edu.tr
 * @since 28.03.2026
 * <p>
 * İlçe sınıfı, bir şehre bağlı olan ilçeleri temsil eder.
 * İçerisinde kendisine bağlı mahallelerin listesini tutar.
 * </p>
 */
public class Ilce {
    private String ad;
    private List<Mahalle> mahalleler;

    public Ilce(String ad) {
        this.ad = ad;
        this.mahalleler = new ArrayList<>();
    }

    public void mahalleEkle(Mahalle mahalle) {
        this.mahalleler.add(mahalle);
    }

    public int getNufus() {
        int toplamNufus = 0;
        for (Mahalle mahalle : mahalleler) {
            toplamNufus += mahalle.getNufus();
        }
        return toplamNufus;
    }

    public List<Mahalle> getMahalleler() {
        return new ArrayList<>(mahalleler);
    }

    public String getAd() { return ad; }
}