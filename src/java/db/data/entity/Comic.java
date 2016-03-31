/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.data.entity;

import com.connect.mysql.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor
 */
public class Comic {
      private DBConnect con;
      private ResultSet rs;
      private PreparedStatement ps;   //Used when executing an update or delete
      private String comicName;
      private String issueNumber,photo;
      
    public Comic() {
        con = new DBConnect();
    }
    
     
     /*The getData function retrieves the comics by title.*/
	public List<Integer> getComicsID(String search) {
            con.openConnection();
            List<Integer> comics = new ArrayList();
            try{
                String sql =   "SELECT comicID FROM comics WHERE comicName LIKE '%"+ search +"%'";
            
                rs = con.getStatement().executeQuery(sql);
                while(rs.next()) {
                    comics.add(Integer.parseInt(rs.getString("comicID")));
                }
                close();
                return comics;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }		
	}
        
        //Returnst cover of the comic
         public String getComicPhoto(int comicID) {
                con.openConnection();
                try {
                        rs = con.getStatement().executeQuery("select photo from comics where comicID = " + comicID + ";");
                        while(rs.next())
                            photo = rs.getString("photo"); 
                        close();
                        return photo;
                }
                catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
         
           //Returns the issue number of the comic
           public String getComicIssue(int comicID) {
                con.openConnection();
                try {
                        rs = con.getStatement().executeQuery("select issueNumber from comics where comicID = " + comicID + ";");
                        while(rs.next())
                            issueNumber = rs.getString("issueNumber"); 
                        close();
                        return issueNumber;
                }
                catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
          //Returns the title of the comic
           public String getComicTitle(int comicID) {
                con.openConnection();
                try {
                        rs = con.getStatement().executeQuery("select comicName from comics where comicID = " + comicID + ";");
                        while(rs.next())
                            comicName = rs.getString("comicName"); 
                        close();
                        return comicName;
                }
                catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
     /*
                The addComictoInventory will add the comic to the 
                inventory of the user currently logged in.
    */
    public void addComictoInventory(int comicID, int boxID) {
                con.openConnection();
		try {
		String sql = "insert into comic_box_junction(comicID, boxID) values(?,?)"; 	//Stores the insertion query: can take integer values
		ps = con.getConnection().prepareStatement(sql); 	//Executes the sql
		ps.setInt(1, comicID);                  //Stores the user name
		ps.setInt(2, boxID);                    //Stores the password
                
		//use execute update when using insert, update, delete...
		ps.executeUpdate(); //Executes the sql
                
                close();
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		} 
	}
        
	/*
           The addComic method allows the user to add the comic to the site
           database.
	 */
	public void addComic(String cn, String in,  String pb, String u, String y, int pt) {
                con.openConnection();
		try {
		String sql = "insert into comics(comicName, issueNumber, publisher, printing, photo, yearReleased) values(?,?,?,?,?,?)"; 	//Stores the insertion query: can take integer values
		ps = con.getConnection().prepareStatement(sql); 	//Executes the sql
		ps.setString(1, cn);                                    //Stores the comic name
		ps.setString(2, in);                                    //Stores the issue number
		ps.setString(3, pb);                                    //Stores the publisher
                ps.setInt(4, pt);                                       //Stores the print
                ps.setString(5, u);                                     //Stores the url
                ps.setString(6, y);                                     //Stores the url
                
		//use execute update when using insert, update, delete...
		ps.executeUpdate(); //Executes the sql
                
                close();
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		} 
	}
        private void close() {
            con.closeConnection();
            try { rs.close(); } catch(Exception e) {}
            try { ps.close(); } catch(Exception e) {}
        }
}