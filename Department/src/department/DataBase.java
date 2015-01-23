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
            //ex.printStackTrace();
            System.out.println("ERROR: Could not form DataBase .");
			System.out.println(">>> " + ex.getMessage());
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
				System.out.println(">>> " + e.getMessage());
				//e.printStackTrace();
		    }  
	}
	// sql - âèáèğàº ç ïåâíî¿ òàáëèö³ ïåâíó ê³ëüê³ñòü åëåìåíò³â
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
	    		System.out.println("ERROR: cntRow :" + sql);
	    		System.out.println(">>> " + e.getMessage());
	    		return -1;
	    	}
    }
    // ôîğìóº íîâèé íîìåğ äëÿ ïåâíî¿ òàáëèö³ îïåğàòîğîì sql -- select max(idPer) from Person ...
    public int newId (String sql){
    	try{ int cnt = 0;
    		 s.execute(sql);
             rs = s.getResultSet();
             if((rs!=null) && (rs.next()))cnt = rs.getInt(1);
             return cnt+1;
    	}catch (Exception e){
    		System.out.println("ERROR: newId :" + sql);
    		System.out.println(">>> " + e.getMessage());
      		return -1;
    	}
    }
    public boolean execSQL(String sql){
    	try{ 
    		s.execute(sql);
    		return true;
        } catch (Exception e){
        	System.out.println("ERROR: execSQL :" + sql);
    		System.out.println(">>> " + e.getMessage());
        	return false;
        }	
    }
    public String show(String tbl, String sql){
    	String str = "";
    	try{ 
		 	s.execute(sql);
		 	rs = s.getResultSet();
        	while((rs!=null) && (rs.next())){
        		//System.out.println(">>> " + rs.next());
        		if (!str.equals("")) str = str + "\n";
        		if (tbl.equals("Person"))
        			str = str  + 
        		           rs.getInt(1) + " sur:" + rs.getString(2) + " nam:" + rs.getString(3)
        		                        + " tel:" + rs.getString(4) + " sex:" + rs.getString(5);
        		else if (tbl.equals("Directions") || tbl.equals("Scientifics"))
        			str = str  +  rs.getInt(1) + "  " + rs.getString(2);
        		else if (tbl.equals("Flow"))
        			str = str  +  rs.getInt(1) + " spe:" + rs.getString(2) 
        			           + " yea:" + rs.getInt(3) + " ter:" + rs.getString(4)  ;
          		else str = str  + rs.getInt(1);
        	}
        }catch (Exception e){
          	System.out.println("ERROR: show :" + tbl + ".." + sql);
        	System.out.println(">>> " + e.getMessage());
        }
        return str;
     }
    // ïåğåâ³ğÿº ùî â òàáëèö³ tbl º åëåìåíò ç íîìåğîì id 
    public boolean isInDb(String tbl, int id){
	    	try{ String sql = ""; //
	    	     if (tbl.equals("Person") || tbl.equals("Teacher") || 
	    	    	 tbl.equals("Graduate") || tbl.equals("Student")	 )
	    	    	 sql = "select * from "  + tbl + " where idPer = " + id;
	    	     else if (tbl.equals("Flow"))
	    	    	 sql = "select * from "  + tbl + " where idFl = " + id;
	    	     if (sql.equals("")) {
	    	    	 System.out.println("ERROR: isInDb : not realize for " + tbl);
	    	    	 return false;
	    	     }
	             s.execute(sql);
	             rs = s.getResultSet();
	             //if ((rs != null)) {
	             //  if (rs.next())cnt = rs.getInt(1);
	            // }  
	             return ((rs!=null) && (rs.next()));
	       	}catch (Exception e){
	    		System.out.println("ERROR: isInDb :" + tbl);
	    		System.out.println(">>> " + e.getMessage());
	    		return false;
	    	}
    }

}
