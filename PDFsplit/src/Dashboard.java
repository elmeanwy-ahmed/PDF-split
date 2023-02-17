import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JTextField txt_filepath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public void BreakPDF() throws IOException, DocumentException{
		String infile =txt_filepath.getText();
		System.out.println("reading "+infile);
		PdfReader reader = new PdfReader(infile);
		int n =reader.getNumberOfPages();
		System.out.println("num of pages in pdf are   "+n);
		
		for(int i=1 ; i<=n;i++) {
			String outFile="part_"+i+".pdf";
			Document doucment = new Document(reader.getPageSizeWithRotation(1));
			PdfCopy writer =new PdfCopy(doucment, new FileOutputStream(outFile));
			doucment.open();
			PdfImportedPage page = writer.getImportedPage(reader, i);
			writer.addPage(page);
			doucment.close();
			writer.close();
			System.out.println("The number of the completed page "+i);
		}
		
		
	}
	
	
	public Dashboard() {
		setType(Type.POPUP);
		setTitle("PDF split");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Type file path name:  ");
		lblNewLabel.setBounds(10, 11, 131, 14);
		contentPane.add(lblNewLabel);
		
		txt_filepath = new JTextField();
		txt_filepath.setBounds(151, 8, 367, 20);
		contentPane.add(txt_filepath);
		txt_filepath.setColumns(10);
		
		JButton btn_break = new JButton("Break PDF ");
		btn_break.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BreakPDF();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_break.setBounds(528, 7, 114, 23);
		contentPane.add(btn_break);
		txt_filepath.setText(System.getProperty("user.dir"));
		
	}
}
