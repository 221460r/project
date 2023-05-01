package PCT2project;

import java.util.ArrayList;

public class SeznamFilmu {
    private ArrayList<Film> seznamFilmu;
    
    public SeznamFilmu() {
        this.seznamFilmu = new ArrayList<Film>();
    }
    
    public void pridejFilm(Film film) {
        seznamFilmu.add(film);
    }
    
    public ArrayList<String> getNazvyFilmu() {
        ArrayList<String> nazvyFilmu = new ArrayList<String>();
        for (Film film : seznamFilmu) {
            nazvyFilmu.add(film.getNazev());
        }
        return nazvyFilmu;
    }
    
    public void vypisFilmy() {
        for (Film film : seznamFilmu) {
            System.out.println(film.getNazev() + " (" + film.getRokVydani() + ")");
            System.out.println("Režie: " + film.getReziser());
            System.out.println("Herci: " + film.getHerci().toString());
            System.out.println("-------------------------------------------------");
        }
    }
}





