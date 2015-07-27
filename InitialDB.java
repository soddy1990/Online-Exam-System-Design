import java.sql.*;

public class InitialDB {
	public static void main(String[] Args) 
	{
		try
		{
			DBConnection DBConn = new DBConnection();
			//DBConn.stmt = DBConn.CreatStat();

			String command = "";
			
			//////////////11111111111111111111111111/////////////////////////////////
			//Initialize the user table
			command = "DROP TABLE IF EXISTS user";
			DBConn.executeMysql(command);
			System.out.println("Old user table dropped (if it existed).");

			System.out.println("Creating user table . . .");
			command = "CREATE TABLE user("+
					"userid VARCHAR(100) UNIQUE NOT NULL, "+
					"fullname VARCHAR(100) UNIQUE NOT NULL, "+
					"identity VARCHAR(10) NOT NULL, "+
					"password VARCHAR(20) NOT NULL)";
					DBConn.executeMysql(command);
			DBConn.executeMysql(command);
			System.out.println("Table user created.");
			
			System.out.println("Inserting data in user table . . .");
			command = "INSERT INTO user (userid, fullname, identity, password) "+
			"VALUES(\"john\", \"john smith\", \"teacher\", \"johnsmith1978\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO user (userid, fullname, identity, password) "+
			"VALUES(\"jane\", \"jane doe\", \"teacher\", \"janedoe1978\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO user (userid, fullname, identity, password) "+
			"VALUES(\"zip\", \"zip zippy\", \"student\", \"zipzippy1990\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO user (userid, fullname, identity, password) "+
			"VALUES(\"ali\", \"ali ella\", \"student\", \"aliella1989\")";
			DBConn.executeMysql(command);
			System.out.println("Data Inserted.");

			System.out.println("Querying user table  . . . ");
			command = "SELECT * from user";
			ResultSet rs = DBConn.getResult(command);
			while (rs.next()) {
				System.out.print(rs.getString("userid")+"\t"
						+rs.getString("fullname")+"\t"
						+rs.getString("identity")+"\t"
						+rs.getString("password"));
				System.out.println();
			}
			rs.close();
			System.out.println();
			
//////////////22222222222222222222222222222222/////////////////////////////////
			//Initialize the courses table
			command = "DROP TABLE IF EXISTS courses";
			DBConn.executeMysql(command);
			System.out.println("Old table courses dropped (if it existed).");

			System.out.println("Creating courses table . . .");
			command = "CREATE TABLE courses("+
					"coursenumber VARCHAR(10) NOT NULL, "+
					"teaid VARCHAR(100) NOT NULL, "+
					"coursename VARCHAR(100)  NOT NULL)";
					DBConn.executeMysql(command);
			DBConn.executeMysql(command);
			System.out.println("Table courses created.");
			
			System.out.println("Inserting data in courses table . . .");
			command = "INSERT INTO courses (coursenumber, teaid, coursename) "+
			"VALUES(\"cs602\", \"john\", \"Java Programming\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO courses (coursenumber, teaid, coursename) "+
			"VALUES(\"cs610\", \"john\", \"Datastructure And Algorithm\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO courses (coursenumber, teaid, coursename) "+
			"VALUES(\"cs610\", \"jane\", \"Datastructure And Algorithm\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO courses (coursenumber, teaid, coursename) "+
			"VALUES(\"cs630\", \"jane\", \"Computer Operating System\")";
			DBConn.executeMysql(command);
			System.out.println("Data Inserted.");

			System.out.println("Querying courses table  . . . ");
			command = "SELECT * from courses";
			rs = DBConn.getResult(command);
			while (rs.next()) {
				System.out.print(rs.getString("coursenumber")+"\t"
						+rs.getString("coursename")+"\t"
						+rs.getString("teaid"));
				System.out.println();
			}
			rs.close();
			System.out.println();
			
//////////////333333333333333333333333333333/////////////////////////////////
			//Initialize the teacherquestions table
			command = "DROP TABLE IF EXISTS teacherquestions";
			DBConn.executeMysql(command);
			System.out.println("Old table teacherquestions dropped (if it existed).");

			System.out.println("Creating teacherquestions table . . .");
			command = "CREATE TABLE teacherquestions("+
					"teaid VARCHAR(100) NOT NULL, "+
					"coursenumber VARCHAR(10) NOT NULL, "+
					"question TEXT NOT NULL, "+
					"answer VARCHAR(1) NOT NULL, "+
					"choosen TINYINT(1) UNSIGNED NOT NULL)";
			DBConn.executeMysql(command);
			System.out.println("Table teacherquestions created.");

			System.out.println("Inserting data in teacherquestions table . . .");
			
			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"john\", \"cs602\", \"Qestion 1 by john smith for cs602\", \"A\", \"0\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"john\", \"cs602\", \"Qestion 2 by john smith for cs602\", \"A\", \"1\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"john\", \"cs602\", \"Qestion 3 by john smith for cs602\", \"A\", \"1\")";
			DBConn.executeMysql(command);

			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"john\", \"cs610\", \"Qestion 1 by john smith for cs610\", \"A\", \"1\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"john\", \"cs610\", \"Qestion 2 by john smith for cs610\", \"A\", \"0\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"john\", \"cs610\", \"Qestion 3 by john smith for cs610\", \"A\", \"1\")";
			DBConn.executeMysql(command);

			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"jane\", \"cs610\", \"Qestion 1 by jane doe for cs610\", \"A\", \"1\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"jane\", \"cs610\", \"Qestion 2 by jane doe for cs610\", \"A\", \"0\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"jane\", \"cs610\", \"Qestion 3 by jane doe for cs610\", \"A\", \"1\")";
			DBConn.executeMysql(command);

			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"jane\", \"cs630\", \"Qestion 1 by jane doe for cs630\", \"A\", \"1\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"jane\", \"cs630\", \"Qestion 2 by jane doe for cs630\", \"A\", \"1\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
					"VALUES(\"jane\", \"cs630\", \"Qestion 3 by jane doe for cs630\", \"A\", \"0\")";
			DBConn.executeMysql(command);
			
			System.out.println("Data Inserted.");

			System.out.println("Querying teacherquestions table  . . . ");
			command = "SELECT * from teacherquestions";
			rs = DBConn.getResult(command);
			while (rs.next()) {
				System.out.print(rs.getString("coursenumber")+"\t"
						+rs.getString("teaid")+"\t"
						+rs.getString("question")+"\t"
						+rs.getString("answer")+"\t"
						+rs.getString("choosen"));
				System.out.println();
			}
			rs.close();
			System.out.println();

//////////////44444444444444444444444444444444444/////////////////////////////////
			//Initialize the choices table
			command = "DROP TABLE IF EXISTS choices";
			DBConn.executeMysql(command);
			System.out.println("Old table choices dropped (if it existed).");

			System.out.println("Creating choices table . . .");
			command = "CREATE TABLE choices("+
					"coursenumber VARCHAR(10) NOT NULL, "+
					"teaid VARCHAR(100) NOT NULL, "+
					"question TEXT NOT NULL, "+
					"choiceA TEXT NOT NULL, "+
					"choiceB TEXT NOT NULL, "+
					"choiceC TEXT NOT NULL)";
			DBConn.executeMysql(command);
			System.out.println("Table choices created.");

			System.out.println("Inserting data in choices table . . .");
			
			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs602\", \"john\", \"Qestion 1 by john smith for cs602\", \"Choice A of question1\", \"Choice B of question1\", \"Choice C of question1\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs602\", \"john\", \"Qestion 2 by john smith for cs602\", \"Choice A of question2\", \"Choice B of question2\", \"Choice C of question2\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs602\", \"john\", \"Qestion 3 by john smith for cs602\", \"Choice A of question3\", \"Choice B of question3\", \"Choice C of question3\")";
			DBConn.executeMysql(command);

			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs610\", \"jane\", \"Qestion 1 by jane doe for cs610\", \"Choice A of question1\", \"Choice B of question1\", \"Choice C of question1\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs610\", \"jane\", \"Qestion 2 by jane doe for cs610\", \"Choice A of question2\", \"Choice B of question2\", \"Choice C of question2\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs610\", \"jane\", \"Qestion 3 by jane doe for cs610\", \"Choice A of question3\", \"Choice B of question3\", \"Choice C of question3\")";
			DBConn.executeMysql(command);

			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs610\", \"john\", \"Qestion 1 by john smith for cs610\", \"Choice A of question1\", \"Choice B of question1\", \"Choice C of question1\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs610\", \"john\", \"Qestion 2 by john smith for cs610\", \"Choice A of question2\", \"Choice B of question2\", \"Choice C of question2\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs610\", \"john\", \"Qestion 3 by john smith for cs610\", \"Choice A of question3\", \"Choice B of question3\", \"Choice C of question3\")";
			DBConn.executeMysql(command);

			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs630\", \"jane\", \"Qestion 1 by jane doe for cs630\", \"Choice A of question1\", \"Choice B of question1\", \"Choice C of question1\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs630\", \"jane\", \"Qestion 2 by jane doe for cs630\", \"Choice A of question2\", \"Choice B of question2\", \"Choice C of question2\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
					"VALUES(\"cs630\", \"jane\", \"3Qestion 3 by jane doe for cs630\", \"Choice A of question3\", \"Choice B of question3\", \"Choice C of question3\")";
			DBConn.executeMysql(command);
			System.out.println("Data Inserted.");

			System.out.println("Querying choices table  . . . ");
			command = "SELECT * from choices";
			rs = DBConn.getResult(command);
			while (rs.next()) {
				System.out.print(rs.getString("coursenumber")+"\t"
						+rs.getString("teaid")+"\t"
						+rs.getString("question")+"\t"
						+rs.getString("choiceA")+"\t"
						+rs.getString("choiceB")+"\t"
						+rs.getString("choiceC"));
				System.out.println();
			}
			rs.close();
			System.out.println();

//////////////555555555555555555555555555555555555/////////////////////////////////
			//Initialize the studentcourses table
			command = "DROP TABLE IF EXISTS studentcourses";
			DBConn.executeMysql(command);
			System.out.println("Old table studentcourses dropped (if it existed).");

			System.out.println("Creating studentcourses table . . .");
			command = "CREATE TABLE studentcourses("+
					"stuid VARCHAR(100) NOT NULL, "+
					"teaid VARCHAR(100) NOT NULL, "+
					"coursenumber VARCHAR(10) NOT NULL, "+
					"grades TINYINT UNSIGNED)";
			DBConn.executeMysql(command);
			System.out.println("Table studentcourses created.");

			System.out.println("Inserting data in studentcourses table . . .");
			command = "INSERT INTO studentcourses (stuid, teaid, coursenumber, grades) "+
					"VALUES(\"zip\", \"john\", \"cs602\", \"0\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO studentcourses (stuid, teaid, coursenumber, grades) "+
					"VALUES(\"zip\", \"john\", \"cs610\", \"0\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO studentcourses (stuid, teaid, coursenumber, grades) "+
					"VALUES(\"zip\", \"jane\", \"cs630\", \"0\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO studentcourses (stuid, teaid, coursenumber, grades) "+
					"VALUES(\"ali\", \"john\", \"cs602\", \"0\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO studentcourses (stuid, teaid, coursenumber, grades) "+
					"VALUES(\"ali\", \"jane\", \"cs610\", \"0\")";
			DBConn.executeMysql(command);
			System.out.println("Data Inserted.");

			System.out.println("Querying studentcourses table  . . . ");
			command = "SELECT * from studentcourses";
			rs = DBConn.getResult(command);
			while (rs.next()) {
				System.out.print(rs.getString("stuid")+"\t"
						+rs.getString("coursenumber")+"\t"
						+rs.getString("teaid")+"\t"
						+rs.getString("grades"));
				System.out.println();
			}
			rs.close();
			System.out.println();

//////////////6666666666666666666666666666666666666/////////////////////////////////
			//Initialize the studentanswers table
			command = "DROP TABLE IF EXISTS studentanswers";
			DBConn.executeMysql(command);
			System.out.println("Old table studentanswers dropped (if it existed).");

			System.out.println("Creating studentanswers table . . .");
			command = "CREATE TABLE studentanswers("+
					"stuid VARCHAR(100) NOT NULL, "+
					"coursenumber VARCHAR(10) NOT NULL, "+
					"question TEXT, "+
					"answer VARCHAR(1))";
			DBConn.executeMysql(command);
			System.out.println("Table studentanswers created.");

			System.out.println("Inserting data in studentanswers table . . .");
			command = "INSERT INTO studentanswers (stuid, coursenumber, question) "+
					"VALUES(\"zip\", \"cs602\", \"Qestion 1 by john smith for cs602\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO studentanswers (stuid, coursenumber, question) "+
					"VALUES(\"zip\", \"cs602\", \"Qestion 2 by john smith for cs602\")";
			DBConn.executeMysql(command);

			command = "INSERT INTO studentanswers (stuid, coursenumber, question) "+
					"VALUES(\"ali\", \"cs630\", \"Qestion 1 by jane doe for cs630\")";
			DBConn.executeMysql(command);
			command = "INSERT INTO studentanswers (stuid, coursenumber, question) "+
					"VALUES(\"ali\", \"cs630\", \"Qestion 2 by jane doe for cs630\")";
			DBConn.executeMysql(command);
			
			System.out.println("Data Inserted.");

			System.out.println("Querying studentanswers table  . . . ");
			command = "SELECT * from studentanswers";
			rs = DBConn.getResult(command);
			while (rs.next()) {
				System.out.print(rs.getString("stuid")+"\t"
						+rs.getString("coursenumber")+"\t"
						+rs.getString("question"));
				System.out.println();
			}
			rs.close();
			System.out.println();
		}
		catch (SQLException E)
		{
			System.out.println("SQLException: " + E.getMessage());
			System.out.println("SQLState:     " + E.getSQLState());
			System.out.println("VendorError:  " + E.getErrorCode());
		}
	}
}
