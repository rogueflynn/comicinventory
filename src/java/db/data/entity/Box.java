/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.data.entity;

import com.connect.mysql.DBConnect;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;

/**
 *
 * @author Victor
 */
public class Box {
      private DBConnect con;
      private PreparedStatement ps;   //Used when executing an update or delete
      private CallableStatement cs;   //Needed for use of stored procedures
      
      public Box() {
          con = new DBConnect();
      }
      /*
	 * This function createBox() creates a box by inserting all of the 
         * necessary data into the database
	 * It accepts the form data from the creatBox.jsp and inserts it into the 
	 * database and saves the box info.
	 */
	public void createBox(String un, String bn) {
                con.openConnection();
		try {
		cs = con.getConnection().prepareCall("{call createBox(?, ?)}");
		cs.setString(1, un);                    //Stores the user name
		cs.setString(2, bn);                    //Stores the boxname

		//use execute update when using insert, update, delete...
		cs.executeUpdate(); //Executes the sql
                close();
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		} 
	}
        
        /*
            The deleteBox method is used to delete 
            a users box based on the boxID.  It only
            takes in one paramater.
        */
        public void deleteBox(int boxID) {
                con.openConnection();
		try {
                    String sql = "delete from box where boxID=?";
                    ps = con.getConnection().prepareStatement(sql); 	//Executes the sql
                    ps.setInt(1, boxID);
                    ps.executeUpdate();
                    close();
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
        private void close() {
            con.closeConnection();
            try { cs.close(); } catch(Exception e) {}
            try { ps.close(); } catch(Exception e) {}
        }
        /*END OF THE NEW CODE BLOCK*/
}
