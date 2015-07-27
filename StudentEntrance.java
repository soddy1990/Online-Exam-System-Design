import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class StudentEntrance extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInformation uinfo;
	
	public StudentEntrance(UserInformation u){
		uinfo = u;
		try {
			StudentEntranceInit(uinfo);
	    }
	    catch(Exception exception) {
	      exception.printStackTrace();
	    }
	    this.setVisible(true);
	}
	
	public void StudentEntranceInit(final UserInformation uinfo) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("StudentEntrance");
		setBounds(new Rectangle(420, 270, 400, 300));
		getContentPane().setLayout(null);
				
		Vector<String> courseIDitem = new Vector<String>();
		String command = "SELECT coursenumber from studentcourses WHERE stuid = \""+uinfo.GetStuID()+"\"";
		courseIDitem = ClientServerProtol.Protol("Query", command);
		if(courseIDitem.get(0).equals("Protol Error!") ||
				courseIDitem.get(0).equals("Query Failed!") ||
				courseIDitem.get(0).equals("Update Failed!") ||
				courseIDitem.get(0).equals("Begin Failed!")){
			JOptionPane.showMessageDialog(null, "Get Courses Failed!\n"+courseIDitem.get(0), "Error!", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
		
		final JComboBox courseComboBox = new JComboBox(courseIDitem);
		courseComboBox.setEditable(true);
		getContentPane().add(courseComboBox);
		courseComboBox.setBounds(134, 110, 115, 21);
		
		JButton btnEnterExam = new JButton("Enter Exam");
		btnEnterExam.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
			{
				uinfo.SetCourseNumber(courseComboBox.getSelectedItem().toString());
				enterExam_actionPerformed(e);
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnEnterExam.setBounds(47, 184, 115, 23);
		getContentPane().add(btnEnterExam);
		
		JButton btnCheckPoints = new JButton("Check Grades");
		btnCheckPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCheckPoints.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Vector<String> gradesitem = new Vector<String>();
				String command = "SELECT grades from studentcourses WHERE stuid = \""+uinfo.GetStuID()+"\" and coursenumber = \""+courseComboBox.getSelectedItem().toString()+"\"";
				gradesitem = ClientServerProtol.Protol("Query", command);
				if(gradesitem.get(0).equals("Protol Error!") ||
						gradesitem.get(0).equals("Query Failed!") ||
						gradesitem.get(0).equals("Update Failed!") ||
						gradesitem.get(0).equals("Begin Failed!")){
					JOptionPane.showMessageDialog(null, "Get Grades Failed!\n"+gradesitem.get(0), "Error!", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}
				JOptionPane.showMessageDialog(null, "Get Grades Success!\nYour Grades for "+courseComboBox.getSelectedItem().toString()+" is "+gradesitem.get(0), "Success!", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnCheckPoints.setBounds(215, 184, 115, 23);
		getContentPane().add(btnCheckPoints);
		
		JLabel lbChooseCourse = new JLabel("Choose Course");
		lbChooseCourse.setBounds(134, 69, 115, 15);
		getContentPane().add(lbChooseCourse);
		
		JLabel lbWelcome = new JLabel("Welcome");
		lbWelcome.setBounds(10, 10, 54, 15);
		getContentPane().add(lbWelcome);
		
		JLabel lbStuName = new JLabel(uinfo.GetFullName());
		lbStuName.setBounds(10, 35, 54, 15);
		getContentPane().add(lbStuName);
	}
	
	
	public void enterExam_actionPerformed (ActionEvent e)
	{
		this.setVisible(false);
		TakingExam texam = new TakingExam(uinfo);
		texam.setVisible(true);
		this.dispose();
	}
}
