package department;

public class CmPerson extends Command {
	private int id;
	private String surname, name, telefon, sex, type;
	CmPerson(int id){
		this.type = "d"; this.id = id;
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
		if (type != "n") str = str + " id:" + id;
		if (type != "d") {
	       if (!surname.equals("")) str = str + " surname:" + surname; 
	       if (!name.equals("")) str = str + " name:" + name; 
	       if (!telefon.equals("")) str = str + " telefon:" + telefon;
	       if (!sex.equals("")) str = str + " sex:" + sex; 
		}
		return str;
	}
	public void eval(DataBase db){
		this.setMsg(this.toString());
	}
}
