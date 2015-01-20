package department;

import java.io.BufferedReader;
import java.util.*;
import java.io.InputStreamReader;
import java.io.IOException;

public class Dialog {
	// встановлюємо потік для введення даних
	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	private int i;
	private String str, err;
	public Command inputCommand(){
		Command cmd; 
		boolean noGood= true;
		try{
		   	 do {
		   		 str = stdin.readLine();
		   		 i = i+1;
		   		 cmd = analysCommand();
		   		 err = cmd.getMsg();
		   		 if (noGood = !err.equals(""))
		   			 System.out.println("Not correct commant: " + err);
			 } while (noGood); 
		}
		catch (IOException e){
			e.printStackTrace();
			cmd = new CmExit();  
		};

		return cmd; 
	}
	
	private Command analysCommand(){
		StringTokenizer cmst = new StringTokenizer(str);
		String [] token;
		int st= cmst.countTokens() ;
		Command cm;
		if (st > 0){
			token = new String[st+1];
			for (int i=0;i<st;i++) token[i] = cmst.nextToken();
			if (!token[0].equals("ex"))  
				switch (token[0].charAt(0)) {
					case 'l': cm = analysLead(token); break;
					case 'e': cm = new CmExit(); break;
					case 'p': cm = analysPerson(token); break;
					default:  cm = new CmExit(str + "..." + st + ".." + token[0].charAt(0));
				}
			else cm = new CmExit(); 
		}
		else cm = new CmExit("Empty command !");
	//	switch (i){
    //		   case 1: return new CmLead("Direction","Programming");
		//   case 2: return new CmExit("testing erros");
	//	}
		return cm;  
	}
	
	// ls txt ==> mk_AddScientif(txt):ld txt ==> mk_AddDirection(txt):
	private Command analysLead(String [] token){
		Command cm;
		String dir;
		//System.out.println(token.length);
        if ((token.length > 2) && (token[0].length()>1)) {
        	if (token[0].charAt(1)=='s') dir = "Scientific";
        	else dir = "Direction";
        	cm = new CmLead(dir,token[1]);
        }
        else cm = new CmExit("ls/ld text .."); // +token.length );	   
        return cm;
	}
	private Command analysPerson(String [] token){
		Command cm;
		String surname, name, telefon, sex;
		int id;
		surname = "Ivanov"; name = "Ivan"; telefon = "89789"; sex = "M";
		id = 0;
        if ((token.length > 2) && (token[0].length()>1)) {
        	cm = new CmPerson(surname,name,telefon,sex);
        }
        else cm = new CmExit("pa {text} .. pe id {field:text} .. pd id .."); // +token.length );	   
        return cm;
	}
	
}
