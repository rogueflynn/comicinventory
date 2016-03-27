package com.connect.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Calendar;
import java.sql.SQLException;


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
        
        public Connection getConnection(){
            return this.con;
        }
	
        /*NEW CODE BLOCK*/
        
	/*The getData function retrieves data from the mysql database.*/
	public ResultSet displayUsers() {
		  try{
                String sql =   "select * from useraccount";
            
                rs = st.executeQuery(sql);
                
                return rs;
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
        public ResultSet getUserById(int userID) {
                try {
                        rs = st.executeQuery("SELECT * FROM useraccount WHERE userID = " + userID + ";");
                        return rs;
                }
                catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }
        
        /**overloaded method that returns a user based on email.
         * 
         * @param email
         * @return 
         */
        public ResultSet getUserByemail(String email){
            try{
                rs = st.executeQuery("select * from useraccount where userEmail = '" + email + "'");
                return rs;
            }catch(SQLException e){
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

        public ResultSet getUserComics(String name){
            try{
                rs = st.executeQuery("call getComics('" + name + "')");
                return rs;
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
        public ResultSet getUserBoxes(String name){
            try{
                rs = st.executeQuery("call getBoxInfo('" + name + "')");
                return rs;
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        /**This method returns all of the boxes of the
         * user logged in and the comics that it contains.
         * 
         * @param name
         * @return 
         */
        public ResultSet getUserComicAndBoxes(String name){
            try{
                rs = st.executeQuery("call getComicandBox('" + name + "')");
                return rs;
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
        public ResultSet getUserComicsinBox(int box_id){
            try{
                rs = st.executeQuery("call getComicsinBox(" +box_id + ")");
                return rs;
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
        public ResultSet getUserByUsername(String un){
            try{
                rs = st.executeQuery("select * from useraccount where userName = '" + un + "'");
                return rs;
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
		boolean result = false; 
		String username=null, password=null;
		try {
			String sql = "select userName, password from useraccount"; //Stores the query
			rs = st.executeQuery(sql); //Runs the query
			
			while(rs.next()) {  //Iterator that iterates through  all of the database entries
				username = rs.getString("userName");
				password = rs.getString("password");
				
				if(username.equals(u) && password.equals(p))
					result = true;
			}
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
		try {
		String sql = "insert into useraccount(username, password, userEmail) values(?,?,?)"; 	//Stores the insertion query: can take integer values
		ps = con.prepareStatement(sql); 	//Executes the sql
		ps.setString(1, un);                    //Stores the user name
		ps.setString(2, pw);                    //Stores the password
		ps.setString(3, e);			//Stores the emil.

		//use execute update when using insert, update, delete...
		ps.executeUpdate(); //Executes the sql
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		} 
	}
	/*
	 * This function createAccount() creates the account by inserting all of the 
         * necessary data into the database
	 * It accepts the form data from the creatAccount.jsp and inserts it into the 
	 * database and saves the user info.
	 */
	public void addComic(String cn, String in,  String pb, String u, String y, int pt) {
		try {
		String sql = "insert into comics(comicName, issueNumber, publisher, printing, photo, yearReleased) values(?,?,?,?,?,?)"; 	//Stores the insertion query: can take integer values
		ps = con.prepareStatement(sql); 	//Executes the sql
		ps.setString(1, cn);                    //Stores the comic name
		ps.setString(2, in);                    //Stores the issue number
		ps.setString(3, pb);			//Stores the publisher
                ps.setInt(4, pt);			//Stores the print
                ps.setString(5, u);			//Stores the url
                ps.setString(6, y);			//Stores the url
                
		//use execute update when using insert, update, delete...
		ps.executeUpdate(); //Executes the sql
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		} 
	}
        /*
	 * This function createBox() creates a box by inserting all of the 
         * necessary data into the database
	 * It accepts the form data from the creatBox.jsp and inserts it into the 
	 * database and saves the box info.
	 */
	public void createBox(String un, String bn) {
		try {
		cs = con.prepareCall("{call createBox(?, ?)}");
		cs.setString(1, un);                    //Stores the user name
		cs.setString(2, bn);                    //Stores the password

		//use execute update when using insert, update, delete...
		cs.executeUpdate(); //Executes the sql
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
		try {
                    String sql = "delete from box where boxID=?";
                    ps = con.prepareStatement(sql); 	//Executes the sql
                    ps.setInt(1, boxID);
                    ps.executeUpdate();
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
        /*END OF THE NEW CODE BLOCK*/
        
 
        //This is verstion of the sendMessage will send a message based on the email entered
        public void sendMessage(String sender, String recipient, String subject, String message) {
            try {
               
                int senderID = 0;
                int destinationID = 0;
                Calendar insertDate = Calendar.getInstance();
                String date = insertDate.getTime().toString();
                
                con.setAutoCommit(false);
                
                String sql = "select userID from user where email='"+ sender+"'";
                
                
                rs = st.executeQuery(sql);
                 if (rs.next())
                    senderID = rs.getInt("userID"); // get posting userID for FK in table posting                   

                
                //Get the destinationID based off of the email
                sql = "Select userID from user where email='" + recipient+"'";
                rs = st.executeQuery(sql);
                 if(rs.next()) { 
                    destinationID = rs.getInt("userID"); // get posting userID for FK in table posting                   
                }
                 
                 
                sql = "insert into messages (senderID, destinationID, " 
                             + "messageDate, messageSubject, messageBody) "
                             + "values(?,?,?,?,?)";
                
                ps = con.prepareStatement(sql); 	//Executes the sql
		ps.setInt(1, senderID);		
		ps.setInt(2, destinationID);		
		ps.setString(3, date);		
		ps.setString(4, subject);		
		ps.setString(5, message);	
                
		//use execute update when using insert, update, delete...
		ps.executeUpdate(); //Executes the sql
                con.commit(); // end transactio
            } catch(Exception ex) {
                System.out.println("you dun goofed!");
            }
        }
        
        public ResultSet getMessages(String email,String type) {
            try {
                int userID = 0;
                String sql = "select userID from user where email='"+ email+"'";
                
                
                rs = st.executeQuery(sql);
                
                 if (rs.next())
                    userID = rs.getInt("userID"); // get posting userID for FK in table posting                   

                if(type.equals("Sent")) {
                     sql =    "select s.fname,  r.fname,messages.messageDate, messages.messageSubject, "
                              + "messages.messageBody,messages.messageID "
                              + "from user as s, user as r, messages "
                              + "where s.userID = messages.senderID " 
                              + "and r.userID=messages.destinationID " 
                              + "and messages.sFlag=1 and messages.senderID=" + userID;

                            

                rs = st.executeQuery(sql);
                } else if(type.equals("Inbox")) {
                         sql =    "select s.fname,  r.fname,messages.messageDate, messages.messageSubject, "
                              + "messages.messageBody, messages.messageID "
                              + "from user as s, user as r, messages "
                              + "where s.userID = messages.senderID " 
                              + "and r.userID=messages.destinationID "
                              + "and messages.rFlag=1 and messages.destinationID=" + userID;

                rs = st.executeQuery(sql);
                }
                return rs;
                
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        
        /*
            Returns the message from the database;
        */
        public ResultSet showMessage(int messageID) {
            try {
                String sql = "select messages.messageBody, user.email "
                             +"from messages, user "
                             + "where user.userID=messages.senderID and messages.messageID="+messageID;
                rs = st.executeQuery(sql);
                return rs;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        
        /*
        
            Deletes a message based on the info entered.
        */
        public void deleteMessage(String email, int messageID) {
            try {
                int userID = 0;
                int senderID = 0;
                int destinationID = 0;
                
                String sql = "select userID from user where email='"+ email+"'";
             
                 rs = st.executeQuery(sql);
                
                 if (rs.next())
                    userID = rs.getInt("userID"); 
                 
                 sql = "select senderID, destinationID from messages where messageID=" + messageID;
                 rs = st.executeQuery(sql);
                 
                 while(rs.next()) {
                     senderID = rs.getInt("senderID");
                     destinationID = rs.getInt("destinationID");
                 }
                 
                 
                 if(senderID == userID) {
                     sql = "update messages set sFlag=0 where messageID="+messageID;
                 } else if(destinationID==userID) {
                     sql = "update messages set rFlag=0 where messageID="+messageID;
                 }
                  st.executeUpdate(sql);
            } catch(Exception ex) {
                System.out.println("You dun goofed!");
            }
        }
        
         /*
	 * Prepares sql statements and inserts into Animal and Posting tables.
	 */
        public void insertAnimalPost(String spec, String name, String age, String gend, 
                                    String color, String userEmail, String breed,
                                    String location, Double fee, String url){
                try {
                
                int userID = 0;
                int animalID = 0;
                Calendar insertDate = Calendar.getInstance();
                String date = insertDate.getTime().toString();
                
                con.setAutoCommit(false); // start transaction
                
                /*GET USERID FROM GIVEN EMAIL REPRESENTING THE INSERTING USER*/
                String sql = ("select userid from user where email = '" + userEmail +"'");
                rs = st.executeQuery(sql);
                if(rs.next()) { 
                    userID = rs.getInt("userID"); // get posting userID for FK in table posting                   
                }
                
                /*INSERT INTO ANIMAL TABLE*/
		sql = "insert into animal(species, animalName, age, gender, color, " 
                        + "breed_name) values(?,?,?,?,?,?)";  
		ps = con.prepareStatement(sql); 	//Executes the sql
		ps.setString(1, spec);		
		ps.setString(2, name);		
		ps.setString(3, age);		
		ps.setString(4, gend);		
		ps.setString(5, color);	
                ps.setString(6, breed);
		//use execute update when using insert, update, delete...
		ps.executeUpdate(); //Executes the sql
                
                /*STORE ANIMAL ID THAT WAS JUST INSERTED*/
                rs = st.executeQuery("select LAST_INSERT_ID() as animalID");                   
                if(rs.next()){         
                    animalID = rs.getInt("animalID");
                }
                
                /*INSERT INTO POSTING TABLE*/
                ps.clearBatch(); // clear last statement
                sql = "insert into posting(datePosted, userID, animalID, animalLocation, adoptionFee)" +
                      "values(?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, date);
                ps.setInt(2, userID);
                    System.err.println("Good to here");
                ps.setInt(3, animalID);
                ps.setString(4, location);
                ps.setDouble(5, fee);
                ps.executeUpdate();

                /*INSERT INTO IMAGES TABLE*/
                ps.clearBatch(); // clear last statement
                   
                if(url == null) {
                    sql = "insert into images(animalID) " +
                            "values(?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, animalID);
                    ps.executeUpdate();
                } else {
                    sql = "insert into images(url, animalID) " +
                            "values(?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, url);
                    ps.setInt(2, animalID);
                    ps.executeUpdate();
                }
             
                con.commit(); // end transaction
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		} 
        }
        /**
         * 
         * @return ResultSet containing a list of breed names for cats and dogs
         */
        public ResultSet getBreeds(){
            try{
                String sql = "select name from breed";
                rs = st.executeQuery(sql);               
                return rs;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        
        /** query an animal with given ID
         * 
         * @param id
         * @return 
         */
        public ResultSet getAnimal(int id){
            try{
                String sql = "select * from animal where animalID = " + id;
                rs = st.executeQuery(sql);
                return rs;
                
            }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
        }
        
        /** Inserts into wish list table
         * 
         * @param email - given email of inserting User
         * @param animalID - given animal to insert
         */
        public void insertWishList(int userID, int animalID){
            try{
                /*GET USERID FROM GIVEN EMAIL REPRESENTING THE INSERTING USER*/

                String sql = "insert into wishlist(userID,animalID,dateAdded)"
                        + " values (" + userID + ", " + animalID + ", " + "NOW())";
                System.out.println("User ID was: " + userID + "\nAnimal ID was : " + animalID);
                st.executeUpdate(sql);
                
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        
        /** Inserts into adoption request
         * 
         * @param email - given email of inserting user
         * @param animalID - given animal to insert
         */
        public void insertAdoptionRequest(int userID, int animalID){
            try{

                String sql = "insert into adoptionRequest(userID,animalID,requestDate)"
                        + " values (" + userID + ", " + animalID + ", " + "NOW())";
                st.executeUpdate(sql);
                
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        
       /** Queries DB using given search parameter representing species, gender, or
        * breed.
        * 
        * @param search - given search parameter
        * @return - ResultSet where given parameter matches to animals.
        */
        public ResultSet searchAnimalWithLink(String search){
           try{
               String sql = "";
               
               /*SEARCH BY SPECIES*/
               if(search.equalsIgnoreCase("dog") || search.equalsIgnoreCase("cat")){
                    sql = "select animal.animalID, animal.animalName, animal.species, animal.breed_name, "
                              + "posting.animalLocation, user.fname, user.lname, images.url "
                              + "from animal, user, posting, images "
                              + "where animal.animalID = posting.animalID and posting.searchableFlag = 1 "
                              + "and posting.userID = user.userID and images.animalID = animal.animalID "
                              + "and animal.species = '" + search + "'";
               /*SEARCH BY GENDER*/
               }else if (search.equalsIgnoreCase("male") || search.equalsIgnoreCase("female")){
                    String gender = "";
                    
                    /*USER IS MOST LIKELY TO SEARCH FOR MALE OR FEMALE, GENDER IS INSERTED AS M OR F IN DB*/
                    if(search.equalsIgnoreCase("male")){
                        gender = "m";
                    }else{
                        gender = "f";
                    }
                    
                    
                    sql = "select animal.animalID, animal.animalName, animal.species, animal.breed_name, "
                              + "posting.animalLocation, user.fname, user.lname, images.url "
                              + "from animal, user, posting, images "
                              + "where animal.animalID = posting.animalID and posting.searchableFlag = 1 "
                              + "and posting.userID = user.userID and images.animalID = animal.animalID "
                              + "and animal.gender = '" + gender + "'";
               /*SEARCH BY BREED*/
               }else{
                    sql = "select animal.animalID, animal.animalName, animal.species, animal.breed_name, "
                              + "posting.animalLocation, user.fname, user.lname, images.url "
                              + "from animal, user, posting, images "
                              + "where animal.animalID = posting.animalID and posting.searchableFlag = 1 "
                              + "and posting.userID = user.userID and images.animalID = animal.animalID "
                              + "and animal.breed_name = '" + search + "'";
               }
               
                rs = st.executeQuery(sql);
                return rs;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
          
        }
        
        public ResultSet searchAnimalWithLink(){
           try{
                String sql =   "select animal.animalID, animal.animalName, animal.species, animal.breed_name, "
                                     + "posting.animalLocation, user.fname, user.lname, images.url "
                                     + "from animal, user, posting, images "
                                     + "where animal.animalID = posting.animalID and images.animalID = animal.animalID "
                                     + "and posting.userID = user.userID";
            
                rs = st.executeQuery(sql);
                
                return rs;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
          
        }
        
      

	public void updateData(String i, String n) {
		try {
		String sql = "update person set age=? where name=?";
		PreparedStatement statement = con.prepareStatement(sql); 	//Executes the sql
		statement.setString(1, i);
		statement.setString(2, n);
		statement.executeUpdate();
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	
        
                public int getIdByEmail(String email)
        {
            int userID = 0;
            try
            {
                String sql = ("select userID from user where email = '" + email +"'");
                rs = st.executeQuery(sql);
                if(rs.next()) 
                { 
                    userID = rs.getInt("userID"); // get posting userID for FK in table posting                   
                }  
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                return -1;
            }
            return userID;
        }
        
        public ResultSet getPost(String email)
        {
            int userID = getIdByEmail(email);
            try
            {
                String sql = ("SELECT * from posting, animal WHERE posting.userID = " + userID + " AND posting.animalID = animal.animalID");
                
                rs = st.executeQuery(sql);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                return null;
            }
            
            return rs;
        }
        
        public ResultSet getWishlist(String email)
        {
            int userID = getIdByEmail(email);
            try
            {
                String sql = ("SELECT * from wishlist, animal WHERE wishlist.userID = " + userID + " AND wishlist.animalID = animal.animalID");
                
                rs = st.executeQuery(sql);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                return null;
            }
            
            return rs;
        }
        
        //Close the connection to the database
        public void closeConnection() {
		try { rs.close(); } catch(Exception e) { connectionGood = "Error";}
		try { st.close(); } catch(Exception e) { connectionGood = "Error";}
		try { ps.close(); } catch(Exception e) { connectionGood = "Error";}
		try { con.close(); } catch(Exception e) { connectionGood = "Error";}
                connectionGood = "Connection closed";
	}
}