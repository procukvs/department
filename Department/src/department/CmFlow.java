package department;

public class CmFlow extends Command {
	private int id, year;
	private String spec, term, type;
	CmFlow() {
		this.type = "s";
	}
	CmFlow(String spec, int year, String term) {
	    this.type = "a";
	    this.spec = spec; this.year = year;
	    this.term = term;
	}
	CmFlow(int id, String spec, int year, String term) {
	    this.type = "e"; this.id = id; 
	    this.spec = spec; this.year = year;
	    this.term = term;
	}
	public String toString(){
		String str;
		str = "f"+type;
		if (type == "e") str = str + " id:" + id;
		if (type != "s") {
	       if (!spec.equals("")) str = str + " spec:" + spec; 
	       if (!(year!=0)) str = str + " jear:" + year; 
	       if (!term.equals("")) str = str + " term:" + term;
		}
		return str;
	}
	
	public boolean iswf(DataBase db){
		boolean r = true; 
		if (type.charAt(0) == 'e'){
			r = db.isInDb("Flow",id);
			if (!r)	this.setMsg("Flow with number " + id +" not found !");
		}
		return r;
	}
	
	public void eval(DataBase db){
		String sql, res;
		int id;
		switch (type.charAt(0)) {
			case 'a': sql = "select max(idFl) from Flow";
					  id = db.newId(sql);
			          if (id >= 0) {
			           	  sql = "insert into Flow values(" + id + fmSql1() + ")";
			        	  System.out.println(sql);
			        	  if (db.execSQL(sql)) this.setMsg("Add new flow: " + sql);
			          }	  
				      break;
			case 'e': sql = "update Flow set " + fmSql2() + " where idFl = " + this.id ;
      	              System.out.println(sql);
      	              if (db.execSQL(sql)) this.setMsg("Edit flow with number " + this.id + " : " + sql);
				      break;
			case 's': sql = "select * from Flow";
			          res = db.show("Flow", sql);
			          this.setMsg(res);
			          break;
			default:  this.setMsg("Command f" + type +" not correct!");
	    } 
	}
	
	private String fmSql1() {
		String str = "";
		if (!spec.equals("")) str = str + ",'" + spec + "'";  else str = str + ",null";
	    if (year != 0) str = str + "," + year; else str = str + ",null";
	    if (!term.equals("")) str = str + ",'" + term + "'";  else str = str + ",null";
	    return str;
	}
		
	private String fmSql2(){
		String str = "";
		if (!spec.equals("")) str = str + ", speciality = '" + spec + "'";  
	    if (year != 0) str = str + ", years = '" + year + "'"; 
	    if (!term.equals("")) str = str + ",term = '" + term + "'";  
	    return str.substring(1);
	}
}
