package PCT2project;

public class AnimFilm extends Film {
    
    private String studio;
    
    public AnimFilm(String nazev, String reziser, int delka, String studio, String newSupportingAnimator) {
        super(nazev, delka, reziser, studio);
        this.studio = studio;
    }
    
    public String getStudio() {
        return studio;
    }
    
    public void setStudio(String studio) {
        this.studio = studio;
    }
    
    public void vypisInformace() {
        System.out.println("Název: " + super.getNazev());
        System.out.println("Režisér: " + super.getReziser());
        System.out.println("Délka: " + super.getDelka());
        System.out.println("Studia: " + studio);
    }
    
    public void zahrajFilm() {
        System.out.println("Hraje animovaný film: " + super.getNazev());
    }

	public Object getAnimator() {
		return null;
	}
}
