package fr.younes.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SdzConnection{

	  /**
	  * URL de connection
	  */
	  private static String url = "jdbc:postgresql://localhost:5432/java_fin";

	  /**
	  * Nom du user
	  */
	  private static String user = "postgres";

	  /**
	  * Mot de passe du user
	  */
	  private static String passwd = "nino";

	  /**
	  * Objet Connection
	  */
	  private static Connection connect;

	  /**
	  * Méthode qui va retourner notre instance
	  * et la créer si elle n'existe pas...
	  * @return
	  */
	  //Constructeur privé
	  private SdzConnection(){
	    try {
	      connect = DriverManager.getConnection(url, user, passwd);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	   
	  //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
	  public static Connection getInstance(){
		  if(connect == null){
		    new SdzConnection();
		    System.out.println("INSTANCIATION DE LA CONNEXION SQL ! ");
		  }
		  else{
		    System.out.println("CONNEXION SQL EXISTANTE ! ");
		  }
		  return connect;   
		}
}