package department;

public class CmPerson extends Command {
	private int id;
	private String surname, name, telefon, sex, type;
	CmPerson(int id){
		this.type = "d"; this.id = id;
	}
	CmPerson(String surname){
		this.type = "s"; this.surname = surname;
	}
	CmPerson(String surname, String name, String telefon, String sex) {
	    this.type = "a";
	    this.surname = surname; this.name = name;
	    this.telefon = telefon; this.sex = sex;
	}
	CmPerson(int id, String surname, String name, String telefon, String sex) {
	    this.type = "e"; this.id = id; 
	    this.surname = surname; this.name = name;
	    this.telefon = telefon; this.sex = sex;
	}
	
	public String toString(){
		String str;
		str = "p"+type;
		if (type != "a") str = str + " id:" + id;
		if (type != "d") {
	       if (!surname.equals("")) str = str + " surname:" + surname; 
	       if (!name.equals("")) str = str + " name:" + name; 
	       if (!telefon.equals("")) str = str + " telefon:" + telefon;
	       if (!sex.equals("")) str = str + " sex:" + sex; 
		}
		return str;
	}
	
	public boolean iswf(DataBase db){
		boolean r = true; 
		String sql = "";
		if ((type.charAt(0) == 'd') || (type.charAt(0) == 'e')){
			r = db.isInDb("Person",id);
			if (r) {
				if (type.charAt(0) == 'd') {
					if (db.isInDb("Teacher",id)) sql = "Teacher";
					if (db.isInDb("Graduate",id)) sql = "Graduate";
					if (db.isInDb("Student",id)) sql = "Student";
					if (!sql.equals("")) {
						this.setMsg(sql + " with number " + id +" must delete !");
						r = false;
					}  					
				}
			} else this.setMsg("Person with number " + id +" not found !");
		//	sql = "select count(*) from Person where idPer = " + id ;
		//	cnt = db.cntRows(sql);
		//	if (cnt == 0) {
		//		  this.setMsg("Person with number " + id +" not found !");	
		//		  r = false;
		//	}
		}
		return r;
	}
	
	public void eval(DataBase db){
		String sql, res;
		int id;
		switch (type.charAt(0)) {
			case 'd': sql = "delete from Person where idPer = " + this.id;
					  //System.out.println(sql);
      	              if (db.execSQL(sql)) this.setMsg("Delete person with number " + this.id);	
				      break;
			case 'a': sql = "select max(idPer) from Person";
					  id = db.newId(sql);
			          if (id >= 0) {
			           	  sql = "insert into Person values(" + id + fmSql1() + ")";
			        	  System.out.println(sql);
			        	  if (db.execSQL(sql)) this.setMsg("Add new person: " + sql);
			          }	  
				      break;
			case 'e': sql = "update Person set " + fmSql2() + " where idPer = " + this.id ;
      	              System.out.println(sql);
      	              if (db.execSQL(sql)) this.setMsg("Edit person with number " + this.id + " : " + sql);
				      break;
			case 's': sql = "select * from Person ";
			          if (!surname.equals("")) sql = sql + " where surname like '%" + surname + "%'";
			          //System.out.println(sql);
			          res = db.show("Person", sql);
			          this.setMsg(res);
			          break;
			default:  this.setMsg("Command p" + type +" not correct!");
	    }  
	}
	
	private String fmSql1(){
		String str = "";
		if (!surname.equals("")) str = str + ",'" + surname + "'";  else str = str + ",null";
	    if (!name.equals("")) str = str + ",'" + name + "'"; else str = str + ",null";
	    if (!telefon.equals("")) str = str + ",'" + telefon + "'";  else str = str + ",null";
	    if (!sex.equals("")) str = str + ",'" + sex + "'";  else str = str + ",null";
	    return str;
	}
	
	private String fmSql2(){
		String str = "";
		if (!surname.equals("")) str = str + ", surname = '" + surname + "'";  
	    if (!name.equals("")) str = str + ", name = '" + name + "'"; 
	    if (!telefon.equals("")) str = str + ",telefon = '" + telefon + "'";  
	    if (!sex.equals("")) str = str + ", sex = '" + sex + "'";  
	    return str.substring(1);
	}
}
