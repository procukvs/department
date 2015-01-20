package department;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBase {
	private Connection conn = null;
	private Statement s; 
	//private String sql;
	private ResultSet rs;
//	private Substitution sub;
	private String nameDB ;
	DataBase(){ 
		try
	    {
		  Class.forName("com.mysql.jdbc.Driver");
	     }
		catch(Exception ex)
        {
            ex.printStackTrace();
        } 	
	}
	public boolean connectionDb(String nmDb){
	       try{ 
		      conn = DriverManager.getConnection("jdbc:mysql://localhost/" + nmDb + "?"
                                                  + "user=root&password=root");
		      s = conn.createStatement();
		      nameDB = nmDb;
		      return true;
	        } catch (SQLException e) {
				System.out.println("ERROR: Could not connect to the database " + nmDb + ".");
				System.out.println(">>> " + e.getMessage());
				//e.printStackTrace();
				return false;
		    }  
	}
	public void disConnect(){
		 try{
		        conn.close();	
	        } catch (SQLException e) {
				System.out.println("ERROR: Could not disconnect.");
				e.printStackTrace();
		    }  
	}
	// sql - вибирає з певної таблиці певну кількість елементів
    public int cntRows(String sql){
	    	try{ int cnt = 0;
			    // sql = "SELECT count(*) FROM Algorithm where Model = '" + nmAlg +"'";
	             s.execute(sql);
	             rs = s.getResultSet();
	             //if ((rs != null)) {
	             //  if (rs.next())cnt = rs.getInt(1);
	            // }  
	             if((rs!=null) && (rs.next()))cnt = rs.getInt(1);
	             return cnt;
	    	}catch (Exception e){
	    		System.out.println(e.getMessage() + "\n" + sql);
	    		return -1;
	    	}
    }
    public void execSQL(String sql){
    	try{ 
    		s.execute(sql);
        } catch (Exception e){
        	System.out.println(e.getMessage());
        }	
    }

}
