import java.sql.*;

class DBConnection
{
	String url = "sql.njit.edu";
	String ucid = "mh324";
	String dbpassword = "41jLJF9J9";

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public DBConnection()
	{
		//System.out.println("Loading driver . . .");
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		}
		catch (Exception e) {
			System.err.println("Unable to load driver.");
			e.printStackTrace();
		}
		//System.out.println("Driver loaded.");
		//System.out.println("Establishing connection . . . ");
		try{
			conn = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbpassword);
			//System.out.println("Connection established.");			
			//System.out.println("Creating a Statement object . . . ");
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//System.out.println("Statement object created.");
		}
		catch(Exception e){
			System.err.println("Returing Statement" + e.getMessage());	
		}
	}
	
	//Return result set
	public ResultSet getResult(String sql){
		rs = null;
		try{
			rs = stmt.executeQuery(sql);
			return rs;
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState:     " + e.getSQLState());
			System.out.println("VendorError:  " + e.getErrorCode());
			return null;
		}
	}
	
	//Execute Update
	public boolean executeMysql(String Mysql)
	{
		try{
			stmt.executeUpdate(Mysql);
			return true;
		}
		catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState:     " + e.getSQLState());
			System.out.println("VendorError:  " + e.getErrorCode());
			return false;
		}
	}
	public void Close()
	{
		try
		{
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e)
		{}
	}
}
