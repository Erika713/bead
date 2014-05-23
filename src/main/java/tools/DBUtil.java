package tools;

import higherorlower.Game;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *SQL parancsokat végrehajtó osztály.
 */
public class DBUtil {
	

	
	/**Egy játékállás mentése az adatbázisba.
	 * @param g egy játékállás
	 */
	public static void save(Game g){
		try {
			Connection conn = ConnectionHandler.getConnection();
			Statement stmt=conn.createStatement();
			
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "PLAYERS", null);
			
			if(!rs.next()){	
			String create =
				        "CREATE TABLE PLAYERS (PLAYERNAME VARCHAR2(50) NOT NULL," +
				        " PLAYERMONEY FLOAT NOT NULL," +
				        " CORRECTANSWERS INT NOT NULL," +
				        " WRONGANSWERS INT NOT NULL," +
				        " PRIMARY KEY (PLAYERNAME))";
			
			stmt.executeUpdate(create);
			stmt.close();
			rs.close();
			
			}
				
			rs = stmt.executeQuery("SELECT * FROM PLAYERS WHERE PLAYERNAME = '" + g.getPlayer1().getName() + "'");
				
			if(rs.next()){
					
				String updateValues="UPDATE PLAYERS SET PLAYERMONEY = " + g.getPlayer1().getCash() + ", CORRECTANSWERS = " + g.getCorrectAnswers() +   ", WRONGANSWERS = " + g.getWrongAnswers() + "WHERE PLAYERNAME = '" +  g.getPlayer1().getName() + "'";
				stmt.executeUpdate(updateValues);
				
					//stmt.close();
			}
			else{
				stmt.execute("INSERT INTO PLAYERS VALUES('" + g.getPlayer1().getName() + "', " +g.getPlayer1().getCash() + ", " + g.getCorrectAnswers() + ", " + g.getWrongAnswers() +")" );
				//stmt.close();
			}
			
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
