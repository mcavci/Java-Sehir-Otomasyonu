package proje1;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Mustafa Can Avcı - mustafa.avci3@ogr.sakarya.edu.tr
 * @since 17.04.2026
 * <p>
 * Nesnelerin oluşturulmasından, matematiksel büyüme ve hiyerarşik bölünme 
 * kurallarının işletilmesine kadar tüm süreci kontrol eden yönetici sınıftır.
 * </p>
 */
public class Oyun {
    private List<Sehir> sehirler;
    private Faker faker;
    private String[] isimHavuzu; 

    public Oyun() {
        this.sehirler = new ArrayList<>();
        this.faker = new Faker(new Locale("tr"));
        this.isimHavuzu = new String[10000];
        
        for (int i = 0; i < isimHavuzu.length; i++) {
            isimHavuzu[i] = faker.name().fullName();
        }
    }

    public void baslat(String sayilarStr, int turSayisi) {
        String[] sayilar = sayilarStr.split(" ");
        for (String s : sayilar) {
            sehirOlustur(Integer.parseInt(s));
        }

        konsoluTemizle();
        System.out.println("Baslangic:");
        ekranaYazdir();

        for (int i = 1; i <= turSayisi; i++) {
            turOynat(false); // Büyüme
            turOynat(true);  // Bölünme
            
            System.out.println("\n\n" + i + ". tur sonu:");
            ekranaYazdir();
            
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
        
        System.out.println("\n\nSon durum:");
        ekranaYazdir();
    }

    private void sehirOlustur(int ilkSayi) {
        int ilceSayisi = ilkSayi / 10; 
        if (ilceSayisi == 0) ilceSayisi = 1; 
        int mahalleSayisi = ilkSayi % 10; 

        if (mahalleSayisi == 0 || mahalleSayisi % ilceSayisi != 0) {
            boolean yukariBulundu = false;
            for (int i = mahalleSayisi + 1; i <= 9; i++) {
                if (i % ilceSayisi == 0) {
                    mahalleSayisi = i;
                    yukariBulundu = true;
                    break;
                }
            }
            if (!yukariBulundu) {
                for (int i = 1; i <= 9; i++) {
                    if (i % ilceSayisi == 0) {
                        mahalleSayisi = i;
                        break;
                    }
                }
            }
        }

        int yeniYapiSayisi = (ilceSayisi * 10) + mahalleSayisi;
        int finalNufus = yeniYapiSayisi;
        while (finalNufus % mahalleSayisi != 0) {
            finalNufus++; 
        }

        Sehir sehir = new Sehir(faker.address().city(), ilceSayisi, mahalleSayisi);
        int mahalleBasiKisi = finalNufus / mahalleSayisi;
        int ilceBasinaMahalle = mahalleSayisi / ilceSayisi;

        for (int i = 0; i < ilceSayisi; i++) {
           
            Ilce ilce = new Ilce(faker.address().cityName());
            for (int j = 0; j < ilceBasinaMahalle; j++) {
                
                Mahalle mahalle = new Mahalle(faker.name().firstName() + " Mahallesi");
                for (int k = 0; k < mahalleBasiKisi; k++) {
                    String isim = isimHavuzu[ThreadLocalRandom.current().nextInt(isimHavuzu.length)];
                    // YAŞ: 0-50 arası random
                    mahalle.kisiEkle(new Kisi(isim, ThreadLocalRandom.current().nextInt(51))); 
                }
                ilce.mahalleEkle(mahalle);
            }
            sehir.ilceEkle(ilce);
        }
        sehirler.add(sehir);
    }

    private void turOynat(boolean bolunme) {
        if (!bolunme) {
            for (Sehir sehir : sehirler) {
                int n = sehir.getNufus();
                int katsayi = (n % 10) + ((n / 10) % 10);
                
                for (Ilce ilce : sehir.getIlceler()) {
                    for (Mahalle mahalle : ilce.getMahalleler()) {
                        int mevcutPop = mahalle.getKisiler().size();
                        int hedefPop = (katsayi == 0) ? mevcutPop + 1 : mevcutPop * katsayi; 
                        int eklenecek = hedefPop - mevcutPop;
                        
                        for (int j = 0; j < eklenecek; j++) {
                            String isim = isimHavuzu[ThreadLocalRandom.current().nextInt(isimHavuzu.length)];
                            // YAŞ: Yeni eklenenler de 0-50 arası random
                            mahalle.kisiEkle(new Kisi(isim, ThreadLocalRandom.current().nextInt(51)));
                        }
                        
                        for (Kisi k : mahalle.getKisiler()) {
                            k.yaslandir(); 
                        }
                    }
                }
            }
        } else {
            List<Sehir> yeniOlusanlar = new ArrayList<>();
            for (Sehir s : sehirler) {
                if (s.getNufus() >= 1000) { 
                    yeniOlusanlar.add(sehirBol(s));
                }
            }
            sehirler.addAll(yeniOlusanlar); 
        }
    }

    private Sehir sehirBol(Sehir eskiSehir) {
        Sehir yeniSehir = new Sehir(faker.address().city(), 0, 0); 
        List<Ilce> eskiIlceler = eskiSehir.getIlceler();
        int ilceSayisi = eskiIlceler.size();
        
        if (ilceSayisi > 1) {
            int tasinacak = ilceSayisi / 2; 
            for (int i = 0; i < tasinacak; i++) {
                yeniSehir.ilceEkle(eskiIlceler.remove(eskiIlceler.size() - 1));
            }
        } else {
            Ilce tekIlce = eskiIlceler.get(0);
            List<Mahalle> eskiMahalleler = tekIlce.getMahalleler();
            if (eskiMahalleler.size() > 1) {
                int tasinacak = eskiMahalleler.size() / 2;
                
                Ilce yeniIlce = new Ilce(faker.address().cityName());
                for (int i = 0; i < tasinacak; i++) {
                    yeniIlce.mahalleEkle(eskiMahalleler.remove(eskiMahalleler.size() - 1));
                }
                yeniSehir.ilceEkle(yeniIlce);
            } else {
                Mahalle tekMahalle = eskiMahalleler.get(0);
                List<Kisi> eskiKisiler = tekMahalle.getKisiler();
                int tasinacak = eskiKisiler.size() / 2;

                Ilce yeniIlce = new Ilce(faker.address().cityName());
                Mahalle yeniMahalle = new Mahalle(faker.name().firstName() + " Mahallesi");
                for (int i = 0; i < tasinacak; i++) {
                    yeniMahalle.kisiEkle(eskiKisiler.remove(eskiKisiler.size() - 1));
                }
                yeniIlce.mahalleEkle(yeniMahalle);
                yeniSehir.ilceEkle(yeniIlce);
            }
        }
        return yeniSehir;
    }

    public void ekranaYazdir() {
        for (int i = 0; i < sehirler.size(); i++) {
            System.out.print("[" + sehirler.get(i).getNufus() + "]");
            if ((i + 1) % 5 == 0 || i == sehirler.size() - 1) {
                System.out.println();
            } else {
                System.out.print("-"); 
            }
        }
    }

    public Sehir getSehirByMatris(int satir, int sutun) {
        int index = (satir * 5) + sutun;
        return (index >= 0 && index < sehirler.size()) ? sehirler.get(index) : null;
    }

    public static void konsoluTemizle() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J"); System.out.flush();
        } catch (Exception e) {}
    }
}