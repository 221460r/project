package PCT2project;

import java.util.ArrayList;

public class Hodnoceni {
    private ArrayList<Integer> hodnoceni;
    private ArrayList<String> komentare;

    public Hodnoceni() {
        this.hodnoceni = new ArrayList<Integer>();
        this.komentare = new ArrayList<String>();
    }

    public void pridatHodnoceni(int hodnoceni, String komentar) {
        this.hodnoceni.add(hodnoceni);
        this.komentare.add(komentar);
    }

    public void vypisHodnoceni() {
        if (this.hodnoceni.isEmpty()) {
            System.out.println("Tento film zatím nemá žádné hodnocení.");
            return;
        }
        System.out.println("Hodnocení filmu:");
        for (int i = 0; i < this.hodnoceni.size(); i++) {
            System.out.println((i+1) + ". " + this.hodnoceni.get(i) + " hvězdiček" +
                    (this.komentare.get(i).isEmpty() ? "" : " - " + this.komentare.get(i)));
        }
    }

    public double prumernaHodnoceni() {
        if (this.hodnoceni.isEmpty()) {
            return 0;
        }
        int suma = 0;
        for (int hodnoceni : this.hodnoceni) {
            suma += hodnoceni;
        }
        return (double)suma / this.hodnoceni.size();
    }
}

