package department;

public class CmLead extends Command {
	private String title;
	private String type = "Direction";
    CmLead(String type, String title){
    	this.title = title;
    	this.type = type;
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
		else return "ls " + title;
	}
	public void eval(DataBase db){
		String sql;
		int cnt; 
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
