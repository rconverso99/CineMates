import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ControllerTest {

	@Test 
	public void testSendEmailFilm_InvalidEmail() {
		String titolo = "";
		Utente u = new Utente();
		u.setEmail("email");
		ArrayList<Utente> lista = new ArrayList<Utente>();
		lista.add(u);
		Controller c = new Controller();
		assertEquals(false,c.SendEmailFilm(titolo, lista));
	}
	@Test
	public void testSendEmailFilm_nullMovie() {
		String titolo=null;
		Utente u = new Utente();
		u.setEmail("email@email.com");
		ArrayList<Utente> lista = new ArrayList<Utente>();
		lista.add(u);
		Controller c = new Controller();
		assertEquals(true,c.SendEmailFilm(titolo, lista));
	}
	
	@Test
	public void testSendEmailFilm_emptyList() {
		String titolo="";
		Utente u = new Utente();
		u.setEmail("email@email.com");
		ArrayList<Utente> lista = new ArrayList<Utente>();
		Controller c = new Controller();
		assertEquals(true,c.SendEmailFilm(titolo, lista));
	}
	
	@Test
	public void testSendEmailFilm_nullEmail() {
		String titolo="";
		Utente u = new Utente();
		ArrayList<Utente> lista = new ArrayList<Utente>();
		lista.add(u);
		Controller c = new Controller();
		assertEquals(false,c.SendEmailFilm(titolo, lista));
	}
	
	@Test
	public void testCercaModeratore_null_null() throws Exception{
		String username = null;
		String password = null;
		Controller c = new Controller();
		assertEquals(false,c.cercaModeratore(username, password));
	
	}
	
	@Test
	public void testCercaModeratore_success_success() throws Exception{
		String username = "admin";
		String password = "admin";
		Controller c = new Controller();
		assertEquals(true,c.cercaModeratore(username, password));
	
	}
	
	@Test
	public void testCercaModeratore_notsuccess_notsuccess() throws Exception{
		String username = "usr";
		String password = "psw";
		Controller c = new Controller();
		assertEquals(false,c.cercaModeratore(username, password));
	}
	
	@Test
	public void testCercaModeratore_success_notsuccess() throws Exception{
		String username = "admin";
		String password = "psw";
		Controller c = new Controller();
		assertEquals(false,c.cercaModeratore(username, password));
	}
	
	@Test
	public void testCercaModeratore_notsuccess_success() throws Exception{
		String username = "usr";
		String password = "admin";
		Controller c = new Controller();
		assertEquals(false,c.cercaModeratore(username, password));
	}
	
	@Test
	public void testCercaModeratore_null_notsuccess() throws Exception{
		String username = null;
		String password = "psw";
		Controller c = new Controller();
		assertEquals(false,c.cercaModeratore(username, password));
	}
	
	@Test
	public void testCercaModeratore_notsuccess_null() throws Exception{
		String username = "usr";
		String password = null;
		Controller c = new Controller();
		assertEquals(false,c.cercaModeratore(username, password));
	}
	
	@Test
	public void testCercaModeratore_success_null() throws Exception{
		String username = "admin";
		String password = null;
		Controller c = new Controller();
		assertEquals(false,c.cercaModeratore(username, password));
	}
	
	@Test
	public void testCercaModeratore_null_success() throws Exception{
		String username = null;
		String password = "admin";
		Controller c = new Controller();
		assertEquals(false,c.cercaModeratore(username, password));
	}
	

	
	@Test (expected = NullPointerException.class)
	public void testcercaFilmDallId_idnull(){
		Controller c = new Controller();
		String id =null;
		assertEquals(null,c.cercaFilmDalId(id));
	}
	
	@Test 
	public void testcercaFilmDallId_idNotSuccess(){
		Controller c = new Controller();
		String id ="84912648914781942";
		assertEquals(null,c.cercaFilmDalId(id));
	}
	
	@Test 
	public void testcercaFilmDallId_idEmpty(){
		Controller c = new Controller();
		String id ="";
		assertEquals(null,c.cercaFilmDalId(id));
	}
	
	@Test 
	public void testcercaFilmDallId_idSuccess(){
		Controller c = new Controller();
		String id ="557";
		assertNotNull(c.cercaFilmDalId(id));
	}
	
	@Test
	public void testgetImageByUrl_Success(){
		Controller c = new Controller();
		String link = "https://www.at-languagesolutions.com/wp-content/uploads/2016/06/http-1.jpg";
		assertNotNull(c.getImageByUrl(link));
	}
	
	@Test
	public void testgetImageByUrl_notSuccess(){
		Controller c = new Controller();
		String link = "https://www.at-languagesolutions.com/wp.jpg";
		assertNull(c.getImageByUrl(link));
	}
	
	@Test
	public void testgetImageByUrl_nullLink(){
		Controller c = new Controller();
		String link = null;
		assertNull(c.getImageByUrl(link));
	}
	
	
	
	

}
