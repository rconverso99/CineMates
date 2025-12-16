import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Controller {
	
	JPanel panel;
	JScrollPane scroll;
	Utente utente = new Utente();
	Film film = new Film();
	TmdbWebService om = new TmdbWebService();
	DaoUtente dao_utente = new DaoUtente();
	DaoModeratore dao_moderatore = new DaoModeratore();
	Login log = new Login(this);
	Home h = new Home(this);
	ScegliUtenti seguiutenti = new ScegliUtenti(this);
	SchedaFilm schedafilm= new SchedaFilm(this);
	Moderatore mod = new Moderatore();
	CreaModeratore crea_m = new CreaModeratore(this);
	Film film_selezionato = new Film();
	JLabel lbl_nome_film,lbl_data_film,lbl_codice_film,lbl_poster_film;
	JTextArea lbl_descrizione_film;
	public static void main(String[] args) throws Exception {
		Controller ctrl=new Controller();
		Login login = new Login(ctrl);
		login.setVisible(true);
		CaricamentoDialog cd = new CaricamentoDialog();
		//cd.setVisible(true);
		
		
	CercaFilm cercaFilm = new CercaFilm(ctrl);
		ScegliUtenti su = new ScegliUtenti(ctrl);
		Home h = new Home(ctrl);
		//h.setVisible(true);
		
		
		/*OmdbWebServiceClient om = new OmdbWebServiceClient();
		String jsonResponse = om.searchMovieByTitle("batman","73c18ca6");
		String jsonResponse2 = om.searchMovieByImdb("tt1569923","73c18ca6");
		String titolo = om.retrieveTitle(jsonResponse2);
		System.out.println(titolo);*/
	
		//String[] array_titoli = om.retrieveElencoTitoli(jsonResponse);
		//for(int i=0;i<array_titoli.length;i++){
		//System.out.println(array_titoli[i]);}
	/*ProvaDao pd = new ProvaDao();
	if(pd.preleva()==true){
		System.out.println("true");
	}else {
		System.out.println("false");
	}*/
		
		
	//cercaFilm.setVisible(true);
	//su.setVisible(true);	

	}
	CercaFilm cercaFilm = new CercaFilm(this);
	
	
	
public void cambiaFrame(JFrame frame){

	frame.setVisible(true);
	}

public Film[] cercaFilmDalTitolo(String titolo){
	String jsonResponse = om.searchMovieByTitle(titolo,"2e3034b8c110830b946972c7d30f5cb5");
	if(jsonResponse ==null){
		return null;
	}else{
	return om.retrieveElencoFilm(jsonResponse);}
	
	
}

public Film cercaFilmDalId(String id){
	String jsonResponse = om.searchMovieByImdb(id,"2e3034b8c110830b946972c7d30f5cb5");
	if(jsonResponse ==null){
		return null;
	}else{
	return om.retrieveFilm(jsonResponse);}
}


public Image getImageByUrl(String link){
	try {
		URL url = new URL(link);
		Image image = ImageIO.read(url);
		return image;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}

public boolean checkTextEmpty(String text, JLabel asterisco){
	if(text.equals("")){
		asterisco.setVisible(true);
		return false;
	}else{
		asterisco.setVisible(false);
		return true;
	}
}

public void setFilmSelezionato(int cod_film,String data,String titolo, String poster,String descrizione){
	film_selezionato.setCodice(cod_film);
	film_selezionato.setAnno(data);
	film_selezionato.setTitolo(titolo);
	film_selezionato.setPoster(poster);
	film_selezionato.setDescrizione(descrizione);
	System.out.println(descrizione);
	cambiaFrame(new SchedaFilm(this));
}

public void setCampiSchedaFilm(){
	ImageFileFiltrer iff = new ImageFileFiltrer();
	lbl_nome_film.setText(film_selezionato.getTitolo());
	lbl_data_film.setText(film_selezionato.getAnno());
	lbl_codice_film.setText(film_selezionato.getCodice().toString());
	lbl_descrizione_film.setText(film_selezionato.getDescrizione());
	System.out.println(film_selezionato.getDescrizione());
	if(film_selezionato.getPoster().equals("https://image.tmdb.org/t/p/w500null")){
	}else{
	lbl_poster_film.setIcon(new ImageIcon(iff.getScaledImage(new ImageIcon(getImageByUrl(film_selezionato.getPoster())).getImage(),200, 250)));
	}
}

public void setLabelSchedaFilm(JLabel titoloFilm,JLabel dataFilm ,JLabel posterFilm,JLabel codiceFilm,JTextArea descrizioneFilm){
	lbl_nome_film=titoloFilm;
	lbl_data_film=dataFilm;
	lbl_poster_film=posterFilm;
	lbl_codice_film=codiceFilm;
	lbl_descrizione_film=descrizioneFilm;
}

public Utente[] cercaUtenti(String username){
	try {
		return dao_utente.cercaUtente(username);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}

public boolean cercaNellaLista(ArrayList<Utente> lista,String key){
	for(Utente u:lista){
		if(u.getUsername().equals(key)){
			return true;
		}
		}
	return false;
}

public boolean SendEmailFilm(String titolo , ArrayList<Utente> lista){
	
	for(Utente utente:lista){
		try {
			JavaMailUtil.sendMail(utente.getEmail(),titolo);
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	return true;
	
}

public boolean SendEmailPromozionale(){
	
	try {
		for(Utente utente:dao_utente.cercaUtente("")){
			try {
				JavaMailUtil.sendMailPromozionale(utente.getEmail());
			} catch (MessagingException e) {
				e.printStackTrace();
				return false;
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return true;
	
}

public boolean cercaModeratore(String username, String password) throws Exception{
	return dao_moderatore.cercaModeratore(username, password);
}

public boolean verificaModeratore(String username) throws Exception{
	return dao_moderatore.verificaModeratore(username);
}

public void creaModeratore(String username, String password) throws Exception{
	dao_moderatore.creaModeratore(username, password);
}




}
