package lab2;
/*
 *	   Program: Klasa Book i operacje na obiektach tej klasy.
 *  	  Plik: Book.java        
 *  	 Autor: Jacek Czerwonka
 *	nr indeksu: 241373"
 *  	  Data: 10 pazdziernika 2018 r.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

enum BookType{
	UNKNOWN,
	HISTORYCZNA,
	LEKTURA,
	FANTASTYKA,
	SENSACJA,
	OBYCZAJOWA;
	
	
	public boolean equals(String a) {
		return a.equals(this.toString());
	}
}
class BookException extends Exception {

	private static final long serialVersionUID = 1410L;

	public BookException(String m) {
		super(m);
	}
}



public class Book {
	private String title;
	private String author;
	private int releaseDate;
	private int liczbaStron;	
	private BookType type;
	


	
	public Book(String newTitle, String newAuthor) throws BookException {
		setTitle(newTitle);
		setAuthor(newAuthor);
	}
	
	public void setTitle(String newTitle) throws BookException {
		if(newTitle==null || newTitle.equals("")) 
			throw new BookException("Pusty tutuł");
		title=newTitle;
	}
	
	public void setAuthor(String newAuthor) throws BookException {
		if(newAuthor==null || newAuthor.equals("")) 
			throw new BookException("Pusty autor");
		author=newAuthor;
	}
	public void setReleaseDate(int date) {
		releaseDate=date;
	}
	
	public void setReleaseDate(String date)  throws BookException{
		try {
			setReleaseDate(Integer.parseInt(date));
		}
		catch(NumberFormatException e){
			throw new BookException("Rok ma być liczbą całkowitą");
		}
	}
	
	public void setLiczbaStron(int strony)  {
		liczbaStron=strony;
	}
	
	public void setLiczbaStron(String strony)  throws BookException{
		try {
			setLiczbaStron(Integer.parseInt(strony));
		}
		catch(NumberFormatException e){
			throw new BookException("liczba stron ma być liczbą całkowitą");
		}
	}
	
	
	public void setType(String newType) throws BookException{
	
		if(newType==null || newType.equals("")) {
			type=BookType.UNKNOWN;
			return;
		}
		for(BookType i : BookType.values()) {
			if(i.equals(newType)) {
				this.type=i;
				return;
			}
			
		}
		this.type=BookType.UNKNOWN;
		throw new BookException("typ ksiazki nie istnieje");
	}
	
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public int getReleaseDate() {
		return releaseDate;
	}
	public int getLiczbaStron() {
		return liczbaStron;
	}
	
	public BookType getType() {
		return type;
	}
	
	@Override
	public String  toString() {
		return title;
	}
	public static void printToFile(PrintWriter writer, Book book) {
		writer.println(book.title + "#" + book.author + "#" + book.releaseDate + "#" + book.liczbaStron+ "#" + book.type.toString());
	}
	public static void printToFile(String fileName, Book book) throws BookException {
		try (PrintWriter writer = new PrintWriter(fileName)) {
			printToFile(writer, book);
		} catch (FileNotFoundException e){
			throw new BookException("nie ma takiego pliku ");
		}
	}
	
	public static Book readFromFile(BufferedReader reader) throws BookException{
		try 
		{
			String linia = reader.readLine();
			String[] tab = linia.split("#");
			Book book = new Book(tab[0], tab[1]);
			book.setReleaseDate(tab[2]);
			book.setLiczbaStron(tab[3]);	
			book.setType(tab[4]);
			return book;
		} 
		catch(IOException e)
		{
			throw new BookException("bład zapisu");
		}	
	}
	
	public static Book readFromFile(String fileName) throws BookException {
		try (BufferedReader reader = new BufferedReader( new FileReader(new File(fileName) ) ) ) {
			return Book.readFromFile(reader);
		} 
		catch (FileNotFoundException e){
			throw new BookException("Nie odnaleziono pliku " + fileName);
		} 
		catch(IOException e){
			throw new BookException("błąd odczytu");
		}	
	}

}
//koniec pliku Book.java
