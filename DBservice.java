import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBservice
{
	public static void main(String[] arg)
	{
		try
		{
			@SuppressWarnings("resource")
			ServerSocket s = new ServerSocket(3241);
			for (;;)
			{
				Socket incoming = s.accept( );
				new DBserviceHandler(incoming).start();
			}
		}
		catch (Exception e)
		{  
			System.out.println(e);
		} 
	}
}

class DBserviceHandler extends Thread
{  
	DataObject myObject = null;
	private Socket incoming;

	public DBserviceHandler(Socket i) 
	{ 
		incoming = i;
	}

	public void run()
	{
		try
		{			
			ObjectOutputStream myOutputStream =
			new ObjectOutputStream(incoming.getOutputStream());
			ObjectInputStream myInputStream =
			new ObjectInputStream(incoming.getInputStream());

			myObject = (DataObject)myInputStream.readObject();
			System.out.println("Messaged received : " + myObject.getMessage());
			if(myObject.getMessage().equals("Begin")){
				
				myObject.setMessage("Begin Success!");
				myOutputStream.writeObject(myObject);
				System.out.println("Message sent : " + myObject.getMessage());
				
				myObject = (DataObject)myInputStream.readObject();
				System.out.println("Messaged received : " + myObject.getMessage());
				if(myObject.getMessage().equals("Update")){
					
					myObject.setMessage("Update Start!");
					System.out.println("Message sent : " + myObject.getMessage());
					myOutputStream.writeObject(myObject);
					System.out.println("Message sent : " + myObject.getMessage());
					
					DBConnection DBConn = new DBConnection();
					
					String command = "";
					myObject = (DataObject)myInputStream.readObject();
					System.out.println("Messaged received : " + myObject.getMessage());
					command = myObject.getMessage();

					boolean result = DBConn.executeMysql(command);
					if(result){
						myObject.setMessage("Update Success!");
						myOutputStream.writeObject(myObject);
						System.out.println("Message sent : " + myObject.getMessage());

						myObject = (DataObject)myInputStream.readObject();
						System.out.println("Messaged received : " + myObject.getMessage());
					}
					else{
						myObject.setMessage("Update Failed!");
						myOutputStream.writeObject(myObject);
						System.out.println("Message sent : " + myObject.getMessage());

						myObject = (DataObject)myInputStream.readObject();
						System.out.println("Messaged received : " + myObject.getMessage());
					}
					myOutputStream.close();
					myInputStream.close();
					incoming.close();
				}
				else if(myObject.getMessage().equals("Query")){
					
					myObject.setMessage("Query Start!");
					myOutputStream.writeObject(myObject);
					System.out.println("Message sent : " + myObject.getMessage());
					
					try
					{
						DBConnection DBConn = new DBConnection();
						
						String command = "";
						myObject = (DataObject)myInputStream.readObject();
						System.out.println("Messaged received : " + myObject.getMessage());
						command = myObject.getMessage();
						
						ResultSet rs = DBConn.getResult(command);

						while(rs.next() != false){
							System.out.println(rs.getString(1));
							
							myObject.setMessage(rs.getString(1));
							myOutputStream.writeObject(myObject);
							System.out.println("Message sent : " + myObject.getMessage());
							
							myObject = (DataObject)myInputStream.readObject();
							System.out.println("Messaged received : " + myObject.getMessage());
						}
						
						myObject.setMessage("Query Finished");
						myOutputStream.writeObject(myObject);
						System.out.println("Message sent : " + myObject.getMessage());

						myObject = (DataObject)myInputStream.readObject();
						System.out.println("Messaged received : " + myObject.getMessage());
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
				else{
					myObject.setMessage("SQL Order Error!");
					//System.out.println("Message sent : " + myObject.getMessage());
					myOutputStream.writeObject(myObject);
					System.out.println("Message sent : " + myObject.getMessage());
				}				
			}
			else{
				myObject.setMessage("Begin Error!");
				//System.out.println("Message sent : " + myObject.getMessage());
				myOutputStream.writeObject(myObject);
				System.out.println("Message sent : " + myObject.getMessage());
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

