package PCT2project;

public class Film {
   
    private String nazev;
    private int rokVydani;
    private String reziser;
	private String delka;


    public Film(String nazev, int rokVydani, String reziser, String delka) {
        this.nazev = nazev;
        this.rokVydani = rokVydani;
        this.reziser = reziser;
        this.delka = delka;
    }

 
    public Film(String nazev2, String reziser2, int rokVydani2) {

	}


	public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getRokVydani() {
        return rokVydani;
    }

    public void setRokVydani(int rokVydani) {
        this.rokVydani = rokVydani;
    }

    public String getReziser() {
        return reziser;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public String toString() {
        return "Film [nazev=" + nazev + ", rokVydani=" + rokVydani + ", reziser=" + reziser + "]";
    }


	public String getDelka() {
	return delka;
	}


	public void addRating(int rating) {
		
	}


	public String getTitle() {
		return null;
	}
}
