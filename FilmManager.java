package PCT2project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FilmManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Film> films = new ArrayList<>();

        loadFromDatabase(films);

        while (true) {
            System.out.println("Vyberte funkci:");
            System.out.println("1. Přidání nového filmu");
            System.out.println("2. Úprava filmu");
            System.out.println("3. Smazání filmu");
            System.out.println("4. Přidání hodnocení filmu");
            System.out.println("5. Výpis filmů");
            System.out.println("6. Vyhledání filmu");
            System.out.println("7. Výpis herců nebo animátorů, kteří se podíleli na více než jednom filmu");
            System.out.println("8. Výpis všech filmů, které obsahují konkrétního herce nebo animátora");
            System.out.println("9. Uložení informace o vybraném filmu do souboru");
            System.out.println("10. Načtení informace o filmu ze souboru");
            System.out.println("11. Uložení informací do databáze a ukončení programu");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addFilm(scanner, films);
                    break;
                case 2:
                    editFilm(scanner, films);
                    break;
                case 3:
                    deleteFilm(scanner, films);
                    break;
                case 4:
                    addRating(scanner, films);
                    break;
                case 5:
                    listFilms(films);
                    break;
                case 6:
                    searchFilm(scanner, films);
                    break;
                case 7:
                    listActorsWithMultipleFilms(films);
                    break;
                case 8:
                    listFilmsWithActorOrAnimator(scanner, films);
                    break;
                case 9:
                    saveFilmToFile(scanner, films);
                    break;
                case 10:
                    loadFilmFromFile(scanner, films);
                    break;
                case 11:
                    saveToDatabase(films);
                    return;
                default:
                    System.out.println("Neplatná volba.");
            }
        }
    }

    private static void loadFromDatabase(List<Film> films) {
        try {
           
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filmy", "root", "heslo");
           
            String query = "SELECT * FROM filmy";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            
            while (rs.next()) {
                String title = rs.getString("nazev");
                String director = rs.getString("reziser");
                int year = rs.getInt("rok_vydani");
                int type = rs.getInt("typ");

                if (type == 1) {
                    String mainActor = rs.getString("hlavni_herec");
                    String supportingActor = rs.getString("vedlejsi_herec");
                    films.add(new HranyFilm(title, director, year, mainActor, supportingActor));
                } else if (type == 2) {
                    String mainAnimator = rs.getString("hlavni_animator");
                    String supportingAnimator = rs.getString("vedlejsi_animator");
                    films.add(new AnimFilm(title, director, year, mainAnimator, supportingAnimator));
                }
            }

            
            conn.close();

        } catch (SQLException e) {
            System.out.println("Nepodařilo se načíst data z databáze.");
        }
    }


	private static void addFilm(Scanner scanner, List<Film> films) {
        System.out.println("Vyberte druh filmu (1 - hraný, 2 - animovaný):");
        int type = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Zadejte název filmu:");
        String title = scanner.nextLine();

        System.out.println("Zadejte jméno režiséra:");
        String director = scanner.nextLine();

        System.out.println("Zadejte rok vydání:");
        int year = scanner.nextInt();
        scanner.nextLine();

        if (type == 1){
        System.out.println("Zadejte jméno hlavního herce:");
        String mainActor = scanner.nextLine();
        System.out.println("Zadejte jméno vedlejšího herce:");
        String supportingActor = scanner.nextLine();
        films.add(new HranyFilm(title, director, year, mainActor, supportingActor));
        } else if (type == 2) {
        System.out.println("Zadejte jméno hlavního animátora:");
        String mainAnimator = scanner.nextLine();
        System.out.println("Zadejte jméno vedlejšího animátora:");
        String supportingAnimator = scanner.nextLine();
        films.add(new AnimFilm(title, director, year, mainAnimator, supportingAnimator));
        } else {
        System.out.println("Neplatná volba.");
        }
}
	private static void editFilm(Scanner scanner, List<Film> films) {
	    System.out.println("Zadejte název filmu, který chcete upravit:");
	    String title = scanner.nextLine();

	
	    Film filmToEdit = findFilmByTitle(title, films);
	    if (filmToEdit == null) {
	        System.out.println("Film s názvem " + title + " nebyl nalezen.");
	        return;
	    }

	    
	    System.out.println("Vybrali jste následující film:");
	    System.out.println(filmToEdit.toString());

	   
	    System.out.println("Zadejte nový název filmu:");
	    String newTitle = scanner.nextLine();

	    System.out.println("Zadejte jméno nového režiséra:");
	    String newDirector = scanner.nextLine();

	    System.out.println("Zadejte nový rok vydání:");
	    int newYear = scanner.nextInt();
	    scanner.nextLine(); 

	    if (filmToEdit instanceof HranyFilm) {
	        
	        System.out.println("Zadejte jméno nového hlavního herce:");
	        String newMainActor = scanner.nextLine();

	        System.out.println("Zadejte jméno nového vedlejšího herce:");
	        String newSupportingActor = scanner.nextLine();

	        
	        HranyFilm newFilm = new HranyFilm(newTitle, newDirector, newYear, newMainActor, newSupportingActor);
	        films.set(films.indexOf(filmToEdit), newFilm);

	    } else if (filmToEdit instanceof AnimFilm) {
	
	        System.out.println("Zadejte jméno nového hlavního animátora:");
	        String newMainAnimator = scanner.nextLine();

	        System.out.println("Zadejte jméno nového vedlejšího animátora:");
	        String newSupportingAnimator = scanner.nextLine();

	        
	        AnimFilm newFilm = new AnimFilm(newTitle, newDirector, newYear, newMainAnimator, newSupportingAnimator);
	        films.set(films.indexOf(filmToEdit), newFilm);
	    }
	    System.out.println("Film byl upraven.");
	}
	private static void deleteFilm(Scanner scanner, List<Film> films) {
	    System.out.println("Zadejte název filmu, který chcete smazat:");
	    String title = scanner.nextLine();

	    Film film = findFilmByTitle(title, films);
	    if (film == null) {
	        System.out.println("Film nebyl nalezen.");
	        return;
	    }

	    films.remove(film);

	    System.out.println("Film byl úspěšně smazán.");
	}
	private static Film findFilmByTitle(String title, List<Film> films) {
		return null;
	}

	private static void addRating(Scanner scanner, List<Film> films) {
	    System.out.println("Zadejte název filmu, kterému chcete přidat hodnocení:");
	    String title = scanner.nextLine();

	    Film film = findFilmByTitle(title, films);
	    if (film == null) {
	        System.out.println("Film nebyl nalezen.");
	        return;
	    }

	    System.out.println("Zadejte hodnocení (0-10):");
	    int rating = scanner.nextInt();
	    scanner.nextLine(); 

	    film.addRating(rating);

	    System.out.println("Hodnocení bylo úspěšně přidáno.");
	}
	
	private static void searchFilm(Scanner scanner, List<Film> films) {
		System.out.println("Zadejte název filmu pro vyhledání:");
		String searchTerm = scanner.nextLine();

		List<Film> foundFilms = new ArrayList<>();
		for (Film film : films) {
		    if (film.getTitle().equalsIgnoreCase(searchTerm)) {
		        foundFilms.add(film);
		    }
		}

		if (foundFilms.isEmpty()) {
		    System.out.println("Film nebyl nalezen.");
		} else {
		    System.out.println("Výsledky hledání:");
		    for (Film film : foundFilms) {
		        System.out.println(film);
		    }
		}

	}
	private static void listFilmsWithActorOrAnimator(Scanner scanner, List<Film> films) {
	    System.out.println("Zadejte jméno herce nebo animátora:");
	    String name = scanner.nextLine();

	    boolean found = false;

	    for (Film film : films) {
	        if (film instanceof HranyFilm) {
	            HranyFilm hranyFilm = (HranyFilm) film;
	            if (hranyFilm.getMainActor().equals(name) || hranyFilm.getSupportingActor().equals(name)) {
	                System.out.println(film);
	                found = true;
	            }
	        } else if (film instanceof AnimFilm) {
	            AnimFilm animFilm = (AnimFilm) film;
	            if (animFilm.getAnimator().equals(name)) {
	                System.out.println(film);
	                found = true;
	            }
	        }
	    }

	    if (!found) {
	        System.out.println("Nenalezen žádný film s tímto hercem nebo animátorem.");
	    }
	}

	
}
