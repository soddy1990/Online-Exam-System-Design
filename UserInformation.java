public class UserInformation {
	private String UserID;
	private String Identity;
	private String StuID;
	private String FullName;
	private String TeaID;
	private String CourseNumber;
	
	public void SetUserID(String u){
		UserID = u;
	}
	public void SetIdentity(String i){
		Identity = i;
	}
	public void SetStuID(String s){
		StuID = s;
	}
	public void SetFullName(String f){
		FullName = f;
	}
	public void SetTeaID(String t){
		TeaID = t;
	}
	public void SetCourseNumber(String c){
		CourseNumber = c;
	}
	
	public String GetUserID(){
		return UserID;
	}
	public String GetIdentity(){
		return Identity;
	}
	public String GetStuID(){
		return StuID;
	}
	public String GetFullName(){
		return FullName;
	}
	public String GetTeaID(){
		return TeaID;
	}
	public String GetCourseNumber(){
		return CourseNumber;
	}
}
