package constants;

public enum Command {
	ADD("add"), DEL("del"), SEARCH("search"), EDIT("edit"), SHOW_ALL("show all"), 
	CLEAR_ALL("clear all"), RECORD("record"), READ("read"), HELP("HELP"), STOP("stop"),
	ADD_SUBTASK("add subtask"), DELETE_SUBTASK("delete subtask"), EDIT_SUBTASK("edit subtask"),
	OTHER("Вы указали неверную команду");
	
	private final String command;
	
	private Command(String command){
		this.command = command;
	}
	
	public static Command valueParse(String arg){ 
		Command cmd;
		String tmp = "";
		try{
			char[] chArr = arg.trim().toCharArray();
			for(int i = 0; i<chArr.length; i++){
				if (chArr[i] == ' ') chArr[i] = '_';
				tmp += chArr[i];
			}
			tmp = tmp.toUpperCase();
			cmd = Command.valueOf(tmp);
		}catch (IllegalArgumentException | NullPointerException e) {
			cmd = Command.OTHER; 
		}
		return cmd;
	}
	
	@Override
	public String toString(){
		return command;
	}

}