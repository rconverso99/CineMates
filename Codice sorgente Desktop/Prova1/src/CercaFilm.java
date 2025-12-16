import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Component;

public class CercaFilm extends JFrame {

	private JPanel contentPane;
	private JTextField textCerca;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CercaFilm(Controller ctrl) {
		Controller controller=ctrl;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1213, 743);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setBounds(583, 152, 593, 304);
		contentPane.add(scrollPane);
		
		JPanel base = new RoundedPanel(30,new Color(255, 165, 0));
		base.setForeground(new Color(255, 140, 0));
		base.setBorder(null);
		base.setAutoscrolls(true);
		scrollPane.setViewportView(base);
		base.setLayout(new MigLayout("insets 0", "40[fill,:200:200]100","20[grow,fill,:50:250]"));
		
		textCerca = new RoundJTextField(500);
		textCerca.setFont(new Font("Tahoma", Font.BOLD, 20));
		textCerca.setText("Cerca");
		textCerca.setForeground(new Color(255, 165, 0));
		textCerca.setBounds(612, 92, 495, 44);
		textCerca.setBorder(BorderFactory.createCompoundBorder(
		        textCerca.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.add(textCerca);
		textCerca.setColumns(10);
		
		
		
		
		
		
		
		
		JButton btnAggiungiBottone = new JButton("");
		btnAggiungiBottone.setIcon(null);
		btnAggiungiBottone.setOpaque(false);
		btnAggiungiBottone.setBorder(null);
		btnAggiungiBottone.setBackground(new Color(245, 245, 245));
		btnAggiungiBottone.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAggiungiBottone.addActionListener(new ActionListener() {
			//int num_click=0;
			//ArrayList elenco_bottoni=new ArrayList();
			int x=1161;
			int y=62;
			int o=82;
			int v=49;
			public void actionPerformed(ActionEvent arg0) {
				CaricamentoDialog cd = new CaricamentoDialog();
				cd.setLocationRelativeTo(null);
				cd.setVisible(true);
				Thread t = new Thread(new Runnable(){
			        public void run(){
				base.removeAll();
				//elenco_bottoni.clear();
				System.out.println(o+" "+v);
				Film[] array_film = ctrl.cercaFilmDalTitolo(textCerca.getText().replaceAll("\\s+", "%20"));
				if(array_film == null){
					textCerca.setText("Ricerca senza risultati");
				}else{
				for(int i=0 ; i< array_film.length ; i++){
					//elenco_bottoni.add(new JButton());
					JButton bottone= new JButton();//elenco_bottoni.get(i);
					bottone.setName(array_film[i].getCodice().toString());
					bottone.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
						System.out.println("Hai selezionato:"+ctrl.cercaFilmDalId(bottone.getName()).getTitolo()+" uscito l'anno "+ctrl.cercaFilmDalId(bottone.getName()).getAnno());
						controller.setFilmSelezionato(controller.cercaFilmDalId(bottone.getName()).getCodice(), controller.cercaFilmDalId(bottone.getName()).getAnno(),controller.cercaFilmDalId(bottone.getName()).getTitolo(), controller.cercaFilmDalId(bottone.getName()).getPoster(),controller.cercaFilmDalId(bottone.getName()).getDescrizione()  );
						controller.setCampiSchedaFilm();
						setVisible(false);
			         }
					  });
					bottone.setBorder(null);
					bottone.setToolTipText(ctrl.cercaFilmDalId(bottone.getName()).getTitolo());
					bottone.setVerticalAlignment(SwingConstants.BOTTOM);
					bottone.setBounds(o, v, 196, 245);
					ImageFileFiltrer iff = new ImageFileFiltrer();
					if(array_film[i].getPoster().equals("https://image.tmdb.org/t/p/w500null")){
						bottone.setText(ctrl.cercaFilmDalId(bottone.getName()).getTitolo());
						bottone.setVerticalAlignment(SwingConstants.CENTER);
						base.add(bottone);
					}else{
					bottone.setIcon(new ImageIcon(iff.getScaledImage(new ImageIcon(ctrl.getImageByUrl(array_film[i].getPoster())).getImage(),200, 250)));
					base.add(bottone);
					}
				}
				scrollPane.setViewportView(base);
				cd.setVisible(false);
				
				
					
						
				}
			}});
				t.start();
				
				
				//num_click=num_click+1;
				
				
				
				//scrollPane.setViewportView(base);		
			}
		});
		btnAggiungiBottone.setBounds(1122, 84, 38, 52);
		contentPane.add(btnAggiungiBottone);
		
		JButton back = new JButton("\r\n");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				controller.cambiaFrame(new Home(controller));
			}
		});
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				back.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/undo_button_pressed.jpg")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				back.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/undo_button.jpg")));
			}
		});
		back.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/undo_button.jpg")));
		back.setFocusPainted(false);
		back.setBorder(null);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setBounds(1109, 587, 67, 52);
		contentPane.add(back);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(CercaFilm.class.getResource("/Images/magnifying-glass.png")));
		lblSearch.setBounds(1122, 92, 54, 52);
		contentPane.add(lblSearch);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(255, 140, 0));
		separator.setBackground(new Color(255, 140, 0));
		separator.setBounds(532, 48, 37, 582);
		contentPane.add(separator);
		
		JLabel lblConsigliaFilm = new JLabel("Consiglia Film");
		lblConsigliaFilm.setForeground(new Color(255, 140, 0));
		lblConsigliaFilm.setFont(new Font("Trebuchet MS", Font.BOLD, 60));
		lblConsigliaFilm.setBounds(15, 62, 435, 84);
		contentPane.add(lblConsigliaFilm);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CercaFilm.class.getResource("/Images/film_icon.jpg")));
		lblNewLabel.setBounds(147, 187, 233, 237);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CercaFilm.class.getResource("/Images/text_1.jpg")));
		label.setBounds(69, 421, 412, 218);
		contentPane.add(label);
		
		
		
		
	}
}
