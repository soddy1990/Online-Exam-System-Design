import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Rectangle;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	private JTextField userNameTextField;
	int result = 0;
	int identitynum = 0;
	UserInformation uinfo = new UserInformation();
	
	public LoginFrame(){
		
		this.setLocationByPlatform(true);
		this.setBounds(new Rectangle(400, 200, 500, 300));
		this.setTitle("Welcome");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to the Online Exam System");
		lblWelcome.setBounds(98, 41, 269, 19);
		lblWelcome.setForeground(Color.RED);
		lblWelcome.setFont(new Font("Old English Text MT", Font.PLAIN, 18));
		this.getContentPane().add(lblWelcome);
		
		JLabel lblPleaseLoginFirst = new JLabel("Please Login First");
		lblPleaseLoginFirst.setBounds(168, 98, 128, 20);
		lblPleaseLoginFirst.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblPleaseLoginFirst.setForeground(Color.DARK_GRAY);
		lblPleaseLoginFirst.setBackground(Color.WHITE);
		this.getContentPane().add(lblPleaseLoginFirst);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(98, 152, 79, 15);
		this.getContentPane().add(lblUserName);

		userNameTextField = new JTextField();
		userNameTextField.setBounds(219, 149, 148, 21);
		this.getContentPane().add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(98, 177, 79, 15);
		this.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(219, 175, 148, 19);
		this.getContentPane().add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result = logintest(userNameTextField.getText(),new String(passwordField.getPassword()));
			}
		});
		loginButton.setBounds(98, 222, 93, 23);
		
		this.getContentPane().add(loginButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result = logintest(userNameTextField.getText(),new String(passwordField.getPassword()));
			}
		});
		cancelButton.setBounds(274, 222, 93, 23);
		this.getContentPane().add(cancelButton);
		
		this.setVisible(true);
	}
	public int logintest(String UserName,String Password)
	{
		uinfo.SetUserID(UserName);
		try
		{
			DataObject myObject = new DataObject();

			Socket socketToServer = new Socket("afsconnect1.njit.edu", 3242);
			ObjectOutputStream myOutputStream =
			new ObjectOutputStream(socketToServer.getOutputStream());
			ObjectInputStream myInputStream =
			new ObjectInputStream(socketToServer.getInputStream());

			myObject.setMessage(UserName);
			myOutputStream.writeObject(myObject);
			//System.out.println("Message sent : " + myObject.getMessage());
			
			myObject = (DataObject)myInputStream.readObject();
			//System.out.println("Messaged received : " + myObject.getMessage());
			if(myObject.getMessage().equals("Got UserName!"))
			{
				myObject.setMessage(Password);
				myOutputStream.writeObject(myObject);
				//System.out.println("Message sent : " + myObject.getMessage());
				
				myObject = (DataObject)myInputStream.readObject();
				//System.out.println("Messaged received : " + myObject.getMessage());
				if(myObject.getMessage().equals("Got Password!")){

					myObject.setMessage("Blah Blah!");
					myOutputStream.writeObject(myObject);

					myObject = (DataObject)myInputStream.readObject();
					//System.out.println("Messaged received : " + myObject.getMessage());
					

					if(myObject.getMessage().equals("Affirmed!")){
						
						myObject.setMessage("Blah Blah!");
						myOutputStream.writeObject(myObject);
						myObject = (DataObject)myInputStream.readObject();
						uinfo.SetIdentity(myObject.getMessage());
						//System.out.println("Messaged received : " + myObject.getMessage());
						
						myObject.setMessage("Blah Blah!");
						myOutputStream.writeObject(myObject);
						myObject = (DataObject)myInputStream.readObject();
						uinfo.SetFullName(myObject.getMessage());
						//System.out.println("Messaged received : " + myObject.getMessage());
						
						myOutputStream.close();
						myInputStream.close();
						socketToServer.close();
						JOptionPane.showMessageDialog(null, "Affirmed!\nWelcome\t"+uinfo.GetFullName()+" as a "+ uinfo.GetIdentity(), "Congratulation!", JOptionPane.PLAIN_MESSAGE);
						if(uinfo.GetIdentity().equals("teacher")){
							identitynum  = 1;
							uinfo.SetTeaID(uinfo.GetUserID());
						}
						else if(uinfo.GetIdentity().equals("student")){
							identitynum  = 2;
							uinfo.SetStuID(uinfo.GetUserID());
						}
						return identitynum;
					}
				}
			}
			myOutputStream.close();
			myInputStream.close();
			socketToServer.close();
			JOptionPane.showMessageDialog(null, myObject.getMessage(), "Error!", JOptionPane.PLAIN_MESSAGE);
			return identitynum;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return identitynum;
		}
	}
}
