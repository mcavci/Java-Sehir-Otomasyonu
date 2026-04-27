package proje1;

/**
*
* @author Mustafa Can Avcı - mustafa.avci3@ogr.sakarya.edu.tr
* @since 26.03.2026
* <p>
* Kişi sınıfı, oyundaki en küçük birimi temsil eder.
* Her kişi için benzersiz bir ID, ad-soyad ve yaş bilgisi tutulur.
* </p>
*/
	public class Kisi {
	    // Benzersiz ID üretmek için statik sayaç
	    private static int idSayaci = 1; 

	    private int id;
	    private String isimSoyisim;
	    private int yas;

	    public Kisi(String isimSoyisim, int yas) {
	        this.id = idSayaci++; 
	        this.isimSoyisim = isimSoyisim;
	        this.yas = yas;
	    }
	    
	    public void yaslandir() {
	        this.yas++;
	    }

	    public int getId() { return id; }
	    public String getIsimSoyisim() { return isimSoyisim; }
	    public int getYas() { return yas; }
	}

