package lab2;
/**
 *	   Program: main i petla główna programu.
 *  	  Plik: MyConsoleApp.java
 *  	 Autor: Jacek Czerwonka
 *	nr indeksu: 241373"
 *  	  Data: 13 pazdziernika 2018 r.
 */
import java.util.Arrays;

public class MyConsoleApp {

	private static final String GREETING_MESSAGE = 
		"Program Book - wersja konsolowa\n"
		+ "Autor: Jacek Czerwonka\n"
		+ "Nr indeksu: 241373\n" 
		+ "Data:  październik 2018 r.\n";
	private static final String MENU = 
		"       MENU\n" +
		"1 - Podaj dane nowej ksiązki\n" 
		+ "2 - Usuń dane ksiązki\n"
		+ "3 - Modyfikuj dane ksiązki\n" 
		+ "4 - Wczytaj dane z pliku\n" 
		+ "5 - Zapisz dane do pliku\n" 
		+ "0 - Zakończ program\n";
	
	private static final String MODIFY_MENU = 
			" MENU MODYFIKACJI KSIAZKI\n"
			+ "1 - Zmień tytuł\n"
			+ "2 - Zmień autora\n"
			+ "3 - Zmień datę wydania\n"
			+ "4 - Zmień liczbę stron\n"
			+ "5 - Zmień typ\n"
			+ "0 - powrót\n";
			
	private static ConsoleUserDialog ui = new ConsoleUserDialog();
	private Book book = null;
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyConsoleApp app=new MyConsoleApp();
		app.loop();
	}
	*/
	
	public void loop() {
		
		while(true) {
			ui.clearConsole();
			ui.printMessage(GREETING_MESSAGE);
			showBook(book);
			
			try {
				switch(ui.enterInt(MENU + "<> ")) {
				case 1:
					book = createNewBook();
					break;
				case 2:
					book=null;
					ui.printInfoMessage("Książka usunięta");
					break;
				case 3:
					if (book==null)
						throw new BookException("Brak książki do edytowania");
					modifyBook(book);
					break;
				case 4:
					String fileName = ui.enterString("Podaj nazwe pliku ");
					book = Book.readFromFile(fileName);
					ui.printInfoMessage("wczytano z pliku");
					break;
				case 5:
					String fileName2 = ui.enterString("Podaj nazwe pliku ");
					Book.printToFile(fileName2, book);
					ui.printInfoMessage("zapisano do pliku");
					break;
				case 0:
					ui.printInfoMessage("Kończę pracę");
					System.exit(0);
				}
			}
			
			catch(BookException e) {
				ui.printErrorMessage(e.getMessage());
			}
		}
	}
	
	
	private void modifyBook(Book book) {
		// TODO Auto-generated method stub
		while(true) {
			ui.clearConsole();
			showBook(book);
			
			try {
				switch(ui.enterInt( MODIFY_MENU + "<>")) {
				case 1:
					book.setTitle(ui.enterString("Podaj tytuł: "));
					break;
				case 2:
					book.setAuthor(ui.enterString("Podaj autora: "));
					break;
				case 3:
					book.setReleaseDate(ui.enterString("Podaj datę: "));
					break;
				case 4:
					book.setLiczbaStron(ui.enterString("Podaj liczbę storn: "));
					break;
				case 5:
					ui.printMessage("Dozwolone typy: " + Arrays.deepToString(BookType.values()));
					book.setType(ui.enterString("Podaj typ: "));
					break;
				case 0:
					return;
				}
			}
			catch(BookException e) {
				ui.printErrorMessage(e.getMessage());
			}
		}
		
	}
	
	private Book createNewBook() {
		// tworzenie nowej ksiazki
		String title=ui.enterString("Podaj tytuł: ");
		String author=ui.enterString("Podaj autora: ");
		String date=ui.enterString("Podaj datę wydania: ");
		String strony=ui.enterString("Podaj liczbę stron: ");
		ui.printMessage("Dozwolone typy: " + Arrays.deepToString(BookType.values()));
		String typ=ui.enterString("Podaj typ ksiązki: ");
		Book newBook=null;
		
		try {
			newBook = new Book(title, author);
			newBook.setReleaseDate(date);
			newBook.setLiczbaStron(strony);
			newBook.setType(typ);
		}
		catch(BookException e){
			ui.printErrorMessage(e.getMessage());
		}
		
		return newBook;
	}
	
	private void showBook(Book book) {
		// wyświetlanie książki
		String message="";
		if(book==null)
			message = "Brak ksiązki do wyświetlenia";
			
		else {
			message = "Aktualna książka \n"
					+ "Tytuł: " + book.getTitle() + "\n"
					+ "Autor: " + book.getAuthor() + "\n"
					+ "Data wydania: " + book.getReleaseDate() + "\n"
					+ "Ilość stron: " + book.getLiczbaStron() + "\n"
					+ "Typ książki: " + book.getType() + "\n";
		}
		ui.printMessage(message);
	}
	

}
//koniec pliku MyConsoleApp.java