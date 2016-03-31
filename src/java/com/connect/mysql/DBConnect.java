package com.connect.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBConnect {
	
//Variables needed for the database connection
	private Connection con; //Opens and closes the database connection
	private Statement st;	//Interprets the sql statements
	private ResultSet rs; 	//Returns the results of the query
	private PreparedStatement ps;   //Used when executing an update or delete
        private CallableStatement cs;   //Needed for use of stored procedures
        private String connectionGood;  //Used to test if the connection is good

	public DBConnect() {}
       
        /*Opens the connection to the database*/
        public void openConnection() {
            try {
			Class.forName("com.mysql.jdbc.Driver"); //Utilizes the java database driver

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/comicinventory", "vic", "emily");
			st = con.createStatement();
                        connectionGood = "The database has been connected.";

		} catch(Exception ex) {
			connectionGood = "The database has not been connected";
		}
        }
        
        //Used to test if the connection as successful 
        public String testConnection() {
            return connectionGood;
        }
        
        /** Returns a statement associated with the JDBC connection      
         * used to aid refactoring.
         * @return reference to current working statement
         */
       
        public Statement getStatement(){
            return this.st;
        }
        
        public Statement getPrepStatement(){
            return this.ps;
        }
        
        public Connection getConnection(){
            return this.con;
        }
	   
        //Close the connection to the database
        public void closeConnection() {
		try { rs.close(); } catch(Exception e) { connectionGood = "Error";}
		try { st.close(); } catch(Exception e) { connectionGood = "Error";}
                try { cs.close(); } catch(Exception e) { connectionGood = "Error";}
		try { ps.close(); } catch(Exception e) { connectionGood = "Error";}
		try { con.close(); } catch(Exception e) { connectionGood = "Error";}
                connectionGood = "Connection closed";
	}
}