package db.data.entity;



import com.connect.mysql.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * The user method is the method that is used to instatiate and 
 * set all of the attributes that are related to the user.
 */

/**
 *
 * @author Victor
 */
public class User {
           private DBConnect con;
           private ResultSet rs;
           private PreparedStatement ps;   //Used when executing an update or delete
           private String userName, email;
           public User() {
               con = new DBConnect();
           }
        /*The getData function retrieves data from the mysql database.*/
	public List<String> displayUsers() {
                con.openConnection();
                List<String> users = new ArrayList();
		  try{
                        String sql =   "select * from useraccount";
            
                        rs = con.getStatement().executeQuery(sql);
                while(rs.next()) {
                    users.add(rs.getString("userName"));
                }
                con.closeConnection();
                return users;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
			
	}
          /**
         * A convenience method that retrieves the user having the given userID.
         * 
         * @param userID - the unique identifier of the user to be retrieved.
         * @return the <code>ResultSet</code> object containing the user having the given userID, 
         *   or <code>null</code> if there is no such user.
         */
        public String getUserById(int userID) {
                con.openConnection();
                try {
                        rs = con.getStatement().executeQuery("SELECT userName FROM useraccount WHERE userID = " + userID + ";");
                        while(rs.next())
                            userName = rs.getString("userName"); 
                        close();
                        return userName;
                }
                catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
        /**
         * A convenience method that retrieves the user having the given userID.
         * 
         * @param email - the unique identifier of the user to be retrieved.
         * @return the <code>ResultSet</code> object containing the user having the given userID, 
         *   or <code>null</code> if there is no such user.
         */
        public String getUserByEmail(String email) {
                con.openConnection();
                try {
                        rs = con.getStatement().executeQuery("SELECT userName FROM useraccount WHERE userEmail = " + email + ";");
                        while(rs.next())
                            userName = rs.getString("userName"); 
                        close();
                        return userName;
                }
                catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
         /**This method returns all of the comics of the
         * user logged in
         * 
         * @param name
         * @return 
         */

        public List<String> getUserComics(String name){
            con.openConnection();
            List<String> comics = new ArrayList();
            try{
                rs = con.getStatement().executeQuery("call getComics('" + name + "')");
                while(rs.next()) {
                    comics.add(rs.getString("comicName") + " " + rs.getString("issueNumber"));
                }
                close();
                return comics;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        
         /**This method returns all of the boxes of the
         * user logged in
         * 
         * @param name
         * @return 
         */
        public HashMap getUserBoxes(String name){
            con.openConnection();
            HashMap<Integer, String> boxes = new HashMap();
            try{
                rs = con.getStatement().executeQuery("call getBoxInfo('" + name + "')");
                while(rs.next()) {
                    boxes.put(Integer.parseInt(rs.getString("boxID")), rs.getString("boxTracker"));
                }
                close();
                return boxes;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
            /**This method returns all of the boxes of the
         * user logged in and the comics that it contains.
         * 
         * @param box_id
         * @return 
         */
        public List<String> getUserComicsinBox(int box_id){
            con.openConnection();
            List<String> comics = new ArrayList();
            try{
                rs = con.getStatement().executeQuery("call getComicsinBox(" +box_id + ")");
                while(rs.next()) {
                    comics.add(rs.getString("comicName") + " #" + rs.getString("issueNumber"));
                }
                close();
                return comics;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        
          public List<Integer> getUserComicID(int box_id){
            con.openConnection();
            List<Integer> comicID = new ArrayList();
            try{
                rs = con.getStatement().executeQuery("call getComicsinBox(" +box_id + ")");
                while(rs.next()) {
                    comicID.add(Integer.parseInt(rs.getString("comicID")));
                }
                close();
                return comicID;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
       public int getUserBoxID(String username, String boxTracker){
            con.openConnection();
            int boxID = -1;
            try{
                rs = con.getStatement().executeQuery("call getUserBoxID('" + username + "','" + boxTracker + "')");
                while(rs.next()) {
                    boxID = Integer.parseInt(rs.getString("boxID"));
                }
                close();
                return boxID;
            }catch(SQLException e){
                e.printStackTrace();
                return -1;
            }
        }
       
       public List<String> getUserSearchedComicName(String username, String comicname){
            con.openConnection();
            List<String> comicName = new ArrayList();
            try{
                rs = con.getStatement().executeQuery("call getUserComicandBox('" + username + "','" + comicname + "')");
                while(rs.next()) {
                    comicName.add(rs.getString("comicName"));
                }
                close();
                return comicName;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        public List<String> getUserSearchedComicIssue(String username, String comicname){
            con.openConnection();
            List<String> comicIssue = new ArrayList();
            try{
                rs = con.getStatement().executeQuery("call getUserComicandBox('" + username + "','" + comicname + "')");
                while(rs.next()) {
                    comicIssue.add(rs.getString("issueNumber"));
                }
                close();
                return comicIssue;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
         public List<String> getUserSearchedComicID(String username, String comicname){
            con.openConnection();
            List<String> comicID = new ArrayList();
            try{
                rs = con.getStatement().executeQuery("call getUserComicandBox('" + username + "','" + comicname + "')");
                while(rs.next()) {
                    comicID.add(rs.getString("comicID"));
                }
                close();
                return comicID;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        public List<String> getUserSearchedBoxName(String username, String comicname){
            con.openConnection();
            List<String> boxName = new ArrayList();
            try{
                rs = con.getStatement().executeQuery("call getUserComicandBox('" + username + "','" + comicname + "')");
                while(rs.next()) {
                    boxName.add(rs.getString("boxTracker"));
                }
                close();
                return boxName;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
         public List<String> getUserSearchedBoxID(String username, String comicname){
            con.openConnection();
            List<String> boxID = new ArrayList();
            try{
                rs = con.getStatement().executeQuery("call getUserComicandBox('" + username + "','" + comicname + "')");
                while(rs.next()) {
                    boxID.add(rs.getString("boxID"));
                }
                close();
                return boxID;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
         /**This method returns all of the user information
         * based on the username provided
         * 
         * @param un
         * @return 
         */
        public List<String> getUserByUsername(String un){
            con.openConnection();
            List<String> user = new ArrayList();
            try{
                rs = con.getStatement().executeQuery("select * from useraccount where userName = '" + un + "'");
                while(rs.next()) {
                    userName = "Username: " + rs.getString("userName");
                    email = "Email: " + rs.getString("userEmail");
                    user.add(userName + " " + email);
                }
                close();
                return user;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        //REPLACE WITH A CLASS TO VALIDATE AND RETURN THE USER ID TO SET THE SESSION!
	/*Tests to see if the user name and password combination exists in the database
	 * If the combination exists, the function returns true
	 * if the combination does not exist, the function returns false
	 */
	public boolean validate(String u, String p) {
                con.openConnection();
		boolean result = false; 
		String username=null, password=null;
		try {
			String sql = "select userName, password from useraccount"; //Stores the query
			rs = con.getStatement().executeQuery(sql); //Runs the query
			
			while(rs.next()) {  //Iterator that iterates through  all of the database entries
				username = rs.getString("userName");
				password = rs.getString("password");
				
				if(username.equals(u) && password.equals(p))
					result = true;
			}
                        close();
		} catch(Exception ex) {
			
		}	
		return result;
	}
        /*
	 * This function createAccount() creates the account by inserting all of the 
         * necessary data into the database
	 * It accepts the form data from the creatAccount.jsp and inserts it into the 
	 * database and saves the user info.
	 */
	public void createAccount(String un, String pw,  String e) {
                con.openConnection();
		try {
		String sql = "insert into useraccount(username, password, userEmail) values(?,?,?)"; 	//Stores the insertion query: can take integer values
		ps = con.getConnection().prepareStatement(sql);	//Executes the sql
		ps.setString(1, un);                            //Stores the user name
		ps.setString(2, pw);                            //Stores the password
		ps.setString(3, e);                             //Stores the emil.

		//use execute update when using insert, update, delete...
		ps.executeUpdate(); //Executes the sql
                close();
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		} 
	}
        public void deleteComicInBox(int boxID, int comicID) {
                con.openConnection();
		try {
                    String sql = "delete from comic_box_junction where comicID=? && boxID=?";
                    ps = con.getConnection().prepareStatement(sql); 	//Executes the sql
                    ps.setInt(1, comicID);
                    ps.setInt(2, boxID);
                    ps.executeUpdate();
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
