package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Egy adatbáziskapcsolatot megvalósító osztály.
 */

public class ConnectionHandler {

	/**
	 * Egy kapcsolat. 
	 */
	private static Connection conn;
	
	/**
	 * Privát konstruktor, így az osztály nem példányosítható.
	 */
	private ConnectionHandler(){}
	
	/**Létrehoz egy adatbázis kapcsolatot.
	 * @return az adatbázis kapcsolat
	 * @throws SQLException ha hiba történik az adatbázis kapcsolat létrehozásakor
	 */
	public static Connection getConnection() throws SQLException{
		if(conn == null){
			synchronized (ConnectionHandler.class) {
				if(conn == null){
					conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g","h_hin8ce","kassai");
					conn.setAutoCommit(false);
				}
			}
		}
		return conn;
	}
	
}
