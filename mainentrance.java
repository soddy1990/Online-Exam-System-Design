
public class mainentrance {
	public static void main(String args[]){
		LoginFrame loginframe = new LoginFrame();
		while(loginframe.result == 0){
		}
		if(loginframe.result == 1){
			TeacherEntrance te = new TeacherEntrance(loginframe.uinfo);
			loginframe.dispose();
			te.setVisible(true);
		}
		else if(loginframe.result == 2){
			StudentEntrance se = new StudentEntrance(loginframe.uinfo);
			loginframe.dispose();
			se.setVisible(true);
		}
	}
}
