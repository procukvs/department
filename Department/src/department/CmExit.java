package department;

public class CmExit extends Command {
   CmExit(){
	  this.setStop(true);
   }
   CmExit(String msg){
	   this.setMsg(msg);
   }
   public String toString(){
	   return "Exit";
   }
}
