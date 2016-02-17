package dz.caar.tb.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.*;

/**
 *
 * @author Aissani & Tedjar
 */
public class DatabaseConnection {
    
    public Connection connect()
    {
    	try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	String url = "jdbc:oracle:thin:@localhost:1521:orcl"; 
      
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "admin");
        props.setProperty("password", "admin");
      
        //creating connection to Oracle database using JDBC
        Connection conn;
        try {
            conn = DriverManager.getConnection(url,props);
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

  
    }
    
}
