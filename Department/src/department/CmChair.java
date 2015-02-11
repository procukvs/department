package department;

public class CmChair extends Command {
	private int ic, ih;
	private String title, type;
	public CmChair(String title) {
		this.type = "a"; this.title = title;
		this.ih =0;
	}
	public CmChair(int ic, String title, int ih) {
		this.type = "e"; this.title = title;
		this.ic = ic; this.ih = ih;
	}
	public CmChair(int ic) {
		this.type = "d"; this.ic = ic;
	}
	public CmChair() {
		this.type = "s"; 
	}
	public String toString(){
		String str;
		str = "c"+type;
		if (type != "s"){
			if (type != "a") str = str + " idch:" + ic;
			if (type != "d") {
				if (!title.equals(""))	str = str + " title:" + title; 
				if (!(ih == 0)) str = str + " idLead:" + ih; 
			}
		}	
		return str;
	}
	public boolean iswf(DataBase db){
		boolean r = true; 
	    String sql = "";
		if ((type.charAt(0) == 'd') || (type.charAt(0) == 'e')){
			r = db.isInDb("Chair",ic);
			if (r) {
				if ((type.charAt(0) == 'e') && !(ih==0)){
					r= db.isInDb("Teacher",ih);
					if (r) {
						sql = "select count(*) from Teacher where idPer = " + ih + " and idCh = " + ic;
						if (!(db.cntRows(sql)==1)){
							this.setMsg("Teacher wih number " + ih +" not work on Chair " + ic + " !");
							r = false;
						}	   
					} else this.setMsg("Teacher number " + ih +" not found !");
				}
			} else this.setMsg("Chair with number " + ic +" not found !");
		}
		return r;
	}
	public void eval(DataBase db){
		String sql, res;
		int id;
		switch (type.charAt(0)) {
			case 'd': sql = "delete from Chair where idCh = " + this.ic;
					  //System.out.println(sql);
      	              if (db.execSQL(sql)) this.setMsg("Delete chair with number " + this.ic);	
				      break;
			case 'a': sql = "select max(idCh) from Chair";
					  id = db.newId(sql);
			          if (id >= 0) {
			           	  sql = "insert into Chair values(" + id + fmSql1() + ")";
			        	  System.out.println(sql);
			        	  if (db.execSQL(sql)) this.setMsg("Add new chair: " + sql);
			          }	  
				      break;
			case 'e': sql = "update Chair set " + fmSql2() + " where idChr = " + this.ic ;
      	              System.out.println(sql);
      	              if (db.execSQL(sql)) this.setMsg("Edit chair with number " + this.ic + " : " + sql);
				      break;
			case 's': sql = "select * from Chair ";
			          //if (!surname.equals("")) sql = sql + " where surname like '%" + surname + "%'";
			          //System.out.println(sql);
			          res = db.show("Chair", sql);
			          this.setMsg(res);
			          break;
			default:  this.setMsg("Command p" + type +" not correct!");
	    }  
	} 
	private String fmSql1(){
		String str = "";
		if (!title.equals("")) str = str + ",'" + title + "'";  else str = str + ",null";
	    if (!(ih==0)) str = str + "," + ih ; else str = str + ",null";
	    return str;
	}
	private String fmSql2(){
		String str = "";
		if (!title.equals("")) str = str + ", Title = '" + title + "'";  
	    if (!(ih==0)) str = str + ", idLead = " + ih; 
	    return str.substring(1);
	}
}
