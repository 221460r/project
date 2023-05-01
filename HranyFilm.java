package PCT2project;

import java.util.ArrayList;

public abstract class HranyFilm extends Film {
    private ArrayList<String> seznamHercu;
    private int hodnoceni;

    public HranyFilm(String nazev, String reziser, int rokVydani, ArrayList<String> seznamHercu, int hodnoceni) {
        super(nazev, reziser, rokVydani);
        this.seznamHercu = seznamHercu;
        this.hodnoceni = hodnoceni;
    }

    public ArrayList<String> getSeznamHercu() {
        return seznamHercu;
    }

    public void setSeznamHercu(ArrayList<String> seznamHercu) {
        this.seznamHercu = seznamHercu;
    }

    public int getHodnoceni() {
        return hodnoceni;
    }

    public void setHodnoceni(int hodnoceni) {
        this.hodnoceni = hodnoceni;
    }

    public void vypisFilm() {
        System.out.println("Název filmu: " + nazev);
        System.out.println("Režisér: " + reziser);
        System.out.println("Rok vydání: " + rokVydani);
        System.out.println("Seznam herců: " + seznamHercu);
        System.out.println("Hodnocení diváků: " + hodnoceni);
    }

	protected abstract Object getMainActor();

	public Object getSupportingActor() {
		return null;
	}


	}
}
