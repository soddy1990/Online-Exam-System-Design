import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientServerProtol {
	public static Vector<String> Protol(String CT, String C){
		Vector<String> result = new	Vector<String>();
		result.add("Protol Error!");
		try
		{
			DataObject myObject = new DataObject();

			Socket socketToServer = new Socket("afsconnect1.njit.edu", 3241);
			ObjectOutputStream myOutputStream =
			new ObjectOutputStream(socketToServer.getOutputStream());
			ObjectInputStream myInputStream =
			new ObjectInputStream(socketToServer.getInputStream());

			myObject.setMessage("Begin");
			myOutputStream.writeObject(myObject);
			//System.out.println("Message sent : " + myObject.getMessage());
			
			myObject = (DataObject)myInputStream.readObject();
			//System.out.println("Messaged received : " + myObject.getMessage());
			if(myObject.getMessage().equals("Begin Success!"))
			{
				result.remove(0);
				if(CT.equals("Update")){
					
					myObject.setMessage("Update");
					myOutputStream.writeObject(myObject);
					//System.out.println("Message sent : " + myObject.getMessage());
					
					myObject = (DataObject)myInputStream.readObject();
					//System.out.println("Messaged received : " + myObject.getMessage());
					if(myObject.getMessage().equals("Update Start!")){
						
						myObject.setMessage(C);
						myOutputStream.writeObject(myObject);
						//System.out.println("Message sent : " + myObject.getMessage());
						
						myObject = (DataObject)myInputStream.readObject();
						//System.out.println("Messaged received : " + myObject.getMessage());
						result.add(myObject.getMessage());
						
						myObject.setMessage("Finished");
						myOutputStream.writeObject(myObject);
						//System.out.println("Message sent : " + myObject.getMessage());
					}
					else
						result.add("Update Failed!");
				}
				else if(CT.equals("Query")){
					
					myObject.setMessage("Query");
					myOutputStream.writeObject(myObject);
					//System.out.println("Message sent : " + myObject.getMessage());
					
					myObject = (DataObject)myInputStream.readObject();
					//System.out.println("Messaged received : " + myObject.getMessage());
					if(myObject.getMessage().equals("Query Start!")){
						
						myObject.setMessage(C);
						myOutputStream.writeObject(myObject);
						//System.out.println("Message sent : " + myObject.getMessage());
						
						myObject = (DataObject)myInputStream.readObject();
						//System.out.println("Messaged received : " + myObject.getMessage());
						
						while(!myObject.getMessage().equals("Query Finished")){
							
							result.add(myObject.getMessage());
							
							myObject.setMessage("Continue");
							myOutputStream.writeObject(myObject);
							//System.out.println("Message sent : " + myObject.getMessage());
							
							myObject = (DataObject)myInputStream.readObject();
							//System.out.println("Messaged received : " + myObject.getMessage());
						}
						myObject.setMessage("Finished");
						myOutputStream.writeObject(myObject);
						//System.out.println("Message sent : " + myObject.getMessage());
					}
					else
						result.add("Query Failed!");
				}
				else{
					result.add("Begin Failed!");
				}
			}
			myOutputStream.close();
			myInputStream.close();
			socketToServer.close();
			return result;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return result;
		}
	}
}
