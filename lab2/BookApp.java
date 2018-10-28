package lab2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import zip.Person;

public class BookApp extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Book book;
	
	private static final String GREETING_MESSAGE =			"Autor : Jacek Czerwonka\n"
			+ "nr indeksu : 241373\n"
			+ "Data : 25.10.2018r.\n"
			+ "Program : BookApp okienkowy\n";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new BookApp();
	}
	
	Font font= new Font("MonoSpaced", Font.BOLD, 12);
	
	JLabel titleLabel = new JLabel("Tytuł : ");
	JLabel autorLabel = new JLabel("Autor : ");
	JLabel dataLabel  = new JLabel("Rok Wydania : ");
	JLabel liczbaStronLabel = new JLabel("Liczba stron : ");
	JLabel bookTypeLabel = new JLabel("Typ Książki : ");
	
	JTextField titleField = new JTextField(12);
	JTextField autorField = new JTextField(12);
	JTextField dataField  = new JTextField(12);
	JTextField liczbaStronField = new JTextField(12);	
	JTextField bookTypeField = new JTextField(12);
	
	JButton newButton = new JButton("Nowa książka");
	JButton editButton = new JButton("Zmień dane");
	JButton saveButton = new JButton("Zapisz do pliku");
	JButton loadButton = new JButton("Wczytaj z pliku");
	JButton deleteButton = new JButton("Usuń książę");
	JButton infoButton   = new JButton("O programie");
	JButton exitButton   = new JButton("Zako�cz aplikację");
	
	
	public BookApp(){
		
	setTitle("BookApp");
	setSize(320, 320);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setResizable(false);
	setLocationRelativeTo(null);
	
	titleLabel.setFont(font);
	autorLabel.setFont(font);
	dataLabel.setFont(font);
	bookTypeLabel.setFont(font);
	liczbaStronLabel.setFont(font);
	
	titleField.setEditable(false);
	autorField.setEditable(false);
	dataField.setEditable(false);
	liczbaStronField.setEditable(false);
	bookTypeField.setEditable(false);
	
	newButton.addActionListener(this);
	editButton.addActionListener(this);
	saveButton.addActionListener(this);
	loadButton.addActionListener(this);
	deleteButton.addActionListener(this);
	infoButton.addActionListener(this);
	exitButton.addActionListener(this);
	
	JPanel panel = new JPanel();
	panel.add(titleLabel);
	panel.add(titleField);
	
	panel.add(autorLabel);
	panel.add(autorField);
	
	panel.add(dataLabel);
	panel.add(dataField);
	
	panel.add(liczbaStronLabel);
	panel.add(liczbaStronField);
	
	panel.add(bookTypeLabel);
	panel.add(bookTypeField);

	panel.add(newButton);
	panel.add(deleteButton);
	panel.add(saveButton);
	panel.add(loadButton);
	panel.add(editButton);
	panel.add(infoButton);
	panel.add(exitButton);
	
	setContentPane(panel);
	
	showMyBook();
	
	setVisible(true);

	
	}
	
	private void showMyBook() {
		if (book==null) {
			titleField.setText("");
			autorField.setText("");
			dataField.setText("");
			liczbaStronField.setText("");
			bookTypeField.setText("");			
		}
		else {
			titleField.setText(book.getTitle());
			autorField.setText(book.getAuthor());
			dataField.setText("" + book.getReleaseDate());
			liczbaStronField.setText("" + book.getLiczbaStron());
			bookTypeField.setText("" + book.getType());	
		}		
			//?
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object eventSource = ev.getSource();
		
		try {
			if(eventSource == newButton) book = BookDialog.createNewBook();
			
			if(eventSource == deleteButton) 
				book = null;
			
			if(eventSource == saveButton) {
				String fileName = JOptionPane.showInputDialog("Podaj nazwę pliku");
				if (fileName == null || fileName.equals("")) return;  // Cancel lub pusta nazwa pliku.
				Book.printToFile(fileName, book);
			}
			if(eventSource == loadButton) {
				String fileName = JOptionPane.showInputDialog("Podaj nazwe pliku");
				if (fileName == null || fileName.equals("")) return;  // Cancel lub pusta nazwa pliku. 
				book = Book.readFromFile(fileName);
			}
			if(eventSource == editButton) {
				if(book== null)throw new BookException("Brak książki do edytowania");
				BookDialog.editBookRecord(this, book);
			}
			if(eventSource == infoButton) {
				JOptionPane.showMessageDialog(this, GREETING_MESSAGE);
			}
			if(eventSource == exitButton) {
				System.exit(0);
			}
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "er0r Message", JOptionPane.ERROR_MESSAGE);
		}
		showMyBook();
		// TODO Auto-generated method stub

	}
}
