
# 🏙️ Java Şehir Nüfus Simülasyonu

Bu proje, Java dili kullanılarak geliştirilmiş konsol tabanlı bir şehir/nüfus simülasyonudur. 
Program, Nesne Yönelimli Programlama (OOP) prensiplerine uygun olarak tasarlanmış olup 
dinamik bir veri yapısı üzerinden şehirlerin nüfus değişimini simüle eder.

## 🚀 Projenin Amacı
Kullanıcıdan alınan başlangıç tur sayısı ve nüfus değerlerine göre:
- Şehir, İlçe, Mahalle ve Kişi hiyerarşisini oluşturmak
- Nüfus artışını belirli matematiksel kurallara göre simüle etmek
- Nüfusu 1000 ve üzerine çıkan şehirleri bölerek yeni şehirler oluşturmak

## 🧱 Kullanılan Yapılar
Projede aşağıdaki sınıflar bulunmaktadır:
- `Main` → Programın başlangıç noktası
- `Oyun` → Simülasyonun ana kontrol mekanizması
- `Sehir` → Şehir yapısı ve nüfus yönetimi
- `Ilce` → İlçe yönetimi
- `Mahalle` → Mahalle yapısı
- `Kisi` → Bireyleri temsil eder

## ⚙️ Özellikler
- ✔️ Nesne Yönelimli Programlama (OOP) prensiplerine uygun yapı
- ✔️ Single Responsibility Principle (Tek Sorumluluk Prensibi)
- ✔️ Dinamik nüfus artışı algoritması
- ✔️ Şehir bölünme mekanizması (1000+ nüfus)
- ✔️ Gerçekçi veri üretimi için Java Faker kullanımı
- ✔️ Konsol tabanlı simülasyon

## 🔢 Nüfus Artış Kuralı
Her turda şehir nüfusu:
- Sayının birler ve onlar basamağındaki değerlerin toplamı kadar artırılır.

## 🔀 Şehir Bölünme Mantığı
- Nüfus 1000 ve üzerine ulaştığında şehir ikiye bölünür
- İlçeler dengeli şekilde yeni şehre aktarılır
- Mahalle ve kişiler veri kaybı olmadan taşınır

## 📦 Kullanılan Kütüphaneler
- Java Faker (manuel olarak .jar şeklinde projeye eklenmiştir)

## ⚠️ Bilinen Eksikler
- Konsol çıktısında hizalama sorunları
- Hiyerarşik yazdırmada girinti eksikliği
- Konsol temizleme bazı ortamlarda tam çalışmayabilir

## 🛠️ Geliştirme Notları
Bu proje, OOP mantığını anlamak, veri yapıları ile çalışmak ve 
simülasyon algoritmaları geliştirmek amacıyla hazırlanmıştır.

---

👨‍💻 Geliştirici: Mustafa Can Avcı
