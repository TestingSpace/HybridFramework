package database;

import java.sql.DriverManager;
import java.sql.SQLException;
 

import base.Practice;

public class Connect {

	Practice sqliteOBJ = new Practice();
	//Method to connect database
	public void Connect(){
    try {
      Class.forName(sqliteOBJ.strSQLite);
      sqliteOBJ.conn = DriverManager.getConnection(sqliteOBJ.strDBPath);
      sqliteOBJ.conn.setAutoCommit(false);
      
      System.out.println("connected database successfully");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
  }
	
	// Method to execute Query
    public void exeQuery(String query) throws SQLException{
    	sqliteOBJ.stmt = null;
	  	sqliteOBJ.stmt = sqliteOBJ.conn.createStatement();
    	sqliteOBJ.stmt.executeUpdate(query);
		sqliteOBJ.stmt.close();
		sqliteOBJ.conn.commit();
  }

}
