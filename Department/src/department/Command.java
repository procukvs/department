package department;

public class Command {
	private String msg = ""; 
	public String getMsg() {
		return msg;
	}
	protected void setMsg(String msg) {
		this.msg = msg;
	}

	private boolean isStop = false;
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
	public boolean isStop() {
		return isStop;
	}
	public boolean iswf(DataBase db){
		return true;
	}
	public void eval(DataBase db){
		System.out.println(this.toString());
	}

}
