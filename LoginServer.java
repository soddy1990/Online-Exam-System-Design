import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServer
{
	public static void main(String[] arg)
	{
		try
		{
			@SuppressWarnings("resource")
			ServerSocket s = new ServerSocket(3242);
			for (;;)
			{
				Socket incoming = s.accept( );
				new LoginHandler(incoming).start();
			}
		}
		catch (Exception e)
		{  
			System.out.println(e);
		} 
	}
}

class LoginHandler extends Thread
{  
	DataObject myObject = null;
	private Socket incoming;

	public LoginHandler(Socket i) 
	{ 
		incoming = i;
	}

	public void run()
	{
		try
		{
			String UserName = "";
			String Password = "";
			
			ObjectOutputStream myOutputStream =
			new ObjectOutputStream(incoming.getOutputStream());
			ObjectInputStream myInputStream =
			new ObjectInputStream(incoming.getInputStream());

			myObject = (DataObject)myInputStream.readObject();
			UserName = myObject.getMessage();
			//System.out.println("Message received : " + myObject.getMessage());
			System.out.println(UserName);
			myObject.setMessage("Got UserName!");
			//System.out.println("Message sent : " + myObject.getMessage());
			myOutputStream.writeObject(myObject);
			
			myObject = (DataObject)myInputStream.readObject();
			Password = myObject.getMessage();
			//System.out.println("Message received : " + myObject.getMessage());
			System.out.println(Password);
			myObject.setMessage("Got Password!");
			//System.out.println("Message sent : " + myObject.getMessage());
			myOutputStream.writeObject(myObject);
			

			try
			{
				DBConnection DBConn = new DBConnection();
				
				//Initialize the user table
				String command = "";

				command = "SELECT * from user WHERE userid = \""+UserName+"\" ";
				ResultSet rs = DBConn.getResult(command);
				if(rs.next() == false){
					myObject = (DataObject)myInputStream.readObject();
					//System.out.println("Messaged received : " + myObject.getMessage());
					myObject.setMessage("No Such User!");
					myOutputStream.writeObject(myObject);
				}
				else{
					//System.out.println(rs.getString("password"));
					if(rs.getString("password").equals(Password)){
						myObject = (DataObject)myInputStream.readObject();
						//System.out.println("Messaged received : " + myObject.getMessage());
						myObject.setMessage("Affirmed!");
						myOutputStream.writeObject(myObject);

						myObject = (DataObject)myInputStream.readObject();
						//System.out.println("Messaged received : " + myObject.getMessage());
						myObject.setMessage(rs.getString("identity"));
						myOutputStream.writeObject(myObject);
						
						myObject = (DataObject)myInputStream.readObject();
						//System.out.println("Messaged received : " + myObject.getMessage());
						myObject.setMessage(rs.getString("fullname"));
						myOutputStream.writeObject(myObject);
					}
					else{
						myObject = (DataObject)myInputStream.readObject();
						//System.out.println("Messaged received : " + myObject.getMessage());
						myObject.setMessage("Password Not Match!");
						myOutputStream.writeObject(myObject);
					}
				}
				rs.close();
			}
			catch (SQLException E)
			{
				System.out.println("SQLException: " + E.getMessage());
				System.out.println("SQLState:     " + E.getSQLState());
				System.out.println("VendorError:  " + E.getErrorCode());
			}
			
			myOutputStream.close();
			myInputStream.close();
			incoming.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

