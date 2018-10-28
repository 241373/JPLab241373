package lab2;
/*Autor: Jacek Czerwonka
 * nr indeksu : 241373
 * Plik: BookDilaog.java
 * data: 27.10.2018r.
 * 
 * 
 */

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Book book;
	
	Font font = new Font("MonoSpaced", Font.BOLD, 12);
	
	JLabel titleLabel = 	 	new JLabel("       Tytuł : ");
	JLabel autorLabel =			new JLabel("       Autor : ");
	JLabel dataLabel  =			new JLabel(" Rok Wydania : ");
	JLabel liczbaStronLabel = 	new JLabel("Liczba stron : ");
	JLabel bookTypeLabel = 		new JLabel(" Typ Książki : ");
	
	JTextField titleField = new JTextField(12);
	JTextField autorField = new JTextField(12);
	JTextField dataField  = new JTextField(12);
	JTextField liczbaStronField = new JTextField(12);	
	JComboBox<BookType> bookTypeBox = new JComboBox<BookType>(BookType.values());
	
	JButton OKButton =	 	new JButton("  OK  ");
	JButton CancelButton = 	new JButton("Anuluj");

	public BookDialog(Window parent, Book outerBook) {
		super(parent, Dialog.ModalityType.DOCUMENT_MODAL);
		//
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(220, 300);
		setLocationRelativeTo(parent);
		
		book=outerBook;
		
		if (book==null) 
			setTitle("Nowa Książka");
		else {
			titleField.setText(book.getTitle());
			autorField.setText(book.getAuthor());
			dataField.setText("" + book.getReleaseDate());
			liczbaStronField.setText("" + book.getLiczbaStron());
			bookTypeBox.setSelectedItem(book.getType());
		}
		
		OKButton.addActionListener( this );
		CancelButton.addActionListener( this );
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		
		panel.add(titleLabel);
		panel.add(titleField);
		
		panel.add(autorLabel);
		panel.add(autorField);
		
		panel.add(dataLabel);
		panel.add(dataField);
		
		panel.add(liczbaStronLabel);
		panel.add(liczbaStronField);
		
		panel.add(bookTypeLabel);
		panel.add(bookTypeBox);
		
		panel.add(OKButton);
		panel.add(CancelButton);
		
		
		setContentPane(panel);
		
		setVisible(true);
		
	}
	
	public static Book createNewBook(Window parent) {
		BookDialog dialog = new BookDialog(parent, null);
		return dialog.book;
	}

	public static void editBookRecord(Window parent, Book book) {
		new BookDialog(parent, book);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source == OKButton) {
			try {
				if(book==null) {
					book=new Book(titleField.getText(), autorField.getText());
				}
				else {
					book.setTitle(titleField.getText());
					book.setAuthor(autorField.getText());
				}
				book.setReleaseDate(dataField.getText());
				book.setLiczbaStron(liczbaStronField.getText());
				book.setType((bookTypeBox.getSelectedItem()).toString());
				
				dispose();							
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "nieprawidłowe dane", JOptionPane.ERROR_MESSAGE);

			}
		}
		if(source == CancelButton) {
			dispose();
		}
		
	}

	
	
	
}
