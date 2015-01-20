package department;

import java.io.BufferedReader;

public class WorkObject {
	private int cnt, obj;
	private String nmObject[];
	BufferedReader stdin;
	WorkObject(BufferedReader stdin){
		this.stdin = stdin;
		cnt = 5;
		nmObject = new String[cnt];
		nmObject[0] = "Lead";
		nmObject[1] = "Person";
		nmObject[2] = "Chair";
		nmObject[3] = "Teacher";
		nmObject[4] = "Exit";
	}	
    public String selectObject( String begin){
    	obj = cnt-1;
    	//System.in.
    	return nmObject[obj];
    }
    
    private void inputObject() {
    	//final String str;
//		try{
 //         	str = stdin.readLine();
        //  	return -1;
//		}
//		catch (IOException e){
//			e.printStackTrace();
		//	return -1;
//		}
    	
    }
}
