package proje1;

import java.util.Scanner;

/**
 * @author Mustafa Can Avcı - mustafa.avci3@ogr.sakarya.edu.tr
 * @since 16.04.2026
 * <p>
 * Main sınıfı, programın başlangıç noktasıdır. Kullanıcı girişlerini 
 * alır, simülasyonu başlatır ve sonuç tablosundan şehir detaylarını listeler.
 * </p>
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Oyun oyun = new Oyun();

        try {
            System.out.println("--------------------------------------------------");
            System.out.println("SEHİR SİMÜLASYONU OYUNU");
            System.out.println("--------------------------------------------------");
            System.out.print("Oyunun çalışacağı tur sayısını giriniz: ");
            int tur = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Başlangıç sayılarını aralarında boşluk bırakarak giriniz: ");
            String sayilarStr = scanner.nextLine().trim();

            oyun.baslat(sayilarStr, tur);

            while (true) {
                System.out.print("\nDetaylarını görmek istediğiniz şehrin satır indeksini giriniz (Çıkış için -1): ");
                String satirGirdi = scanner.nextLine().trim();
                
                if (satirGirdi.equals("-1")) {
                    System.out.println("Simülasyon sonlandırıldı.");
                    break;
                }

                int satir = Integer.parseInt(satirGirdi);

                System.out.print("Detaylarını görmek istediğiniz şehrin sütun indeksini giriniz: ");
                int sutun = Integer.parseInt(scanner.nextLine().trim());

                Sehir secilenSehir = oyun.getSehirByMatris(satir, sutun);

                if (secilenSehir != null) {
                    sehirDetaylariniYazdir(secilenSehir);
                } else {
                    System.out.println("\nHata: Belirtilen koordinatlarda şehir bulunamadı!");
                }
            }

        } catch (Exception e) {
            System.out.println("\nHata: Lütfen geçerli değerler giriniz!");
        } finally {
            scanner.close();
        }
    }

    private static void sehirDetaylariniYazdir(Sehir sehir) {
        
        System.out.println("\nŞehir: " + sehir.getAd() + " - Nüfus: " + sehir.getNufus());

        for (Ilce ilce : sehir.getIlceler()) {
            System.out.println("İlçe: " + ilce.getAd() + " – Nüfus: " + ilce.getNufus());

            for (Mahalle mahalle : ilce.getMahalleler()) {
                System.out.println("Mahalle: " + mahalle.getAd() + " – Nüfus: " + mahalle.getNufus());
                System.out.println("Kişiler:");

                for (Kisi kisi : mahalle.getKisiler()) {
                    System.out.println(kisi.getId() + " - " + kisi.getIsimSoyisim() + " – " + kisi.getYas());
                }
            }
        }
    }
}