import java.sql.Connection;
import java.sql.DriverManager;

public class Connessione {
	public Connection getConnection() throws Exception{
		try{
			String Driver="org.postgresql.Driver";
			String Url="jdbc:postgresql://progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com:5432/cinemates20";
			String User="postgres";
			String Password="postgres";
		Class.forName(Driver);
		Connection con = DriverManager.getConnection(Url,User,Password);
		return con;
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Classe non trovata");}
		return null;
	   }
}
