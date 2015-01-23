package department;

public class CmLead extends Command {
	private String title;
	private String type = "Show";
    CmLead(String type, String title){
    	if (type.equals("Show")) {
    		this.title = title.substring(0,1);
    	} else {
    		this.title = title;
    		this.type = type;
    	}	
    }
	public String getTitle() {
		return title;
	}
	public String getType() {
		return type;
	}
	public String toString(){
		if (type == "Direction") 
			return "ld " + title;
		else if (type == "Scientific") 
		    return "ls " + title;
		else return "lv " + title;
	}
	public void eval(DataBase db){
		String sql, tbl, res;
		int cnt; 
		if (type == "Show") {
			tbl = "Directions";
			if ((title.equals("S")) || (title.equals("s") )) tbl = "Scientifics";
			sql = "select * from " + tbl;
	        res = db.show(tbl, sql);
	        this.setMsg(res);
		}
		else {
			sql = "select count(*) from " + type  + "s where " + type + " = '" + title +"'";
			//if (type == "Scientific")
			//	sql = "select count(*) from Scientifics where Scientific = '" + title +"'";
			//else sql = "select count(*) from Directions where Direction = '" + title +"'";
			cnt = db.cntRows(sql);
			if (cnt == 0) {
				sql = "select count(*) from " + type  + "s";
				cnt = db.cntRows(sql);
				if (cnt >= 0){
					sql = "insert into "+ type  + "s values (" + String.valueOf(cnt+1) + ", '" + title +"')";
					db.execSQL(sql);
					this.setMsg(type +":lead " + title + " is adds!");
				}  
				//   String addRow = "INSERT INTO " + tableName + " VALUES ( " + 
				//              String.valueOf((int) (Math.random() * 100000)) + ", 'Text Value " + 
				//              String.valueOf(Math.random()) + "')";  	  
			} else this.setMsg(type +":lead " + title + " is exists!");
		}	
	}
}
