import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JTextField;

public class TakingExam extends JFrame implements WindowListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserInformation uinfo = new UserInformation();
	private JPanel QuestionPane;
	private JPanel OperationPane;
	private CardLayout cl_QuestionPane; 
	private JButton previous;
	private JButton next;
	private JButton page2;
	private JButton page1;
	private JButton btnSubmmit;
	private JPanel Question2;
	private JPanel Question1;
	private JLabel question1;
	private JLabel question2;
	private JLabel lbchoice2A;
	private JLabel lbchoice2B;
	private JLabel lbchoice2C;
	private JTextField answer1;
	private JTextField answer2;
	
	public TakingExam(UserInformation u)
	{
		setBounds(new Rectangle(375, 225, 368, 300));
		uinfo = u;
		addWindowListener(this); 
		
		Vector<String> teaiditem = new Vector<String>();
		String command = "SELECT teaid from studentcourses WHERE stuid = \""+uinfo.GetStuID()+"\" and coursenumber = \""+uinfo.GetCourseNumber()+"\"";
		teaiditem = Query(command);
		u.SetTeaID(teaiditem.get(0));
		
		Vector<String> questionsitem = new Vector<String>();
		command = "SELECT question from teacherquestions WHERE teaid = \""+uinfo.GetTeaID()
					+"\" and coursenumber = \""+uinfo.GetCourseNumber()
					+"\" and choosen = \"1\"";
		questionsitem = Query(command);
		
		Vector<String> choice1Aitem = new Vector<String>();
		command = "SELECT choiceA from choices WHERE teaid = \""+uinfo.GetTeaID()
					+"\" and coursenumber = \""+uinfo.GetCourseNumber()
					+"\" and question = \""+questionsitem.get(0)+"\"";
		choice1Aitem = Query(command);
		
		Vector<String> choice1Bitem = new Vector<String>();
		command = "SELECT choiceB from choices WHERE teaid = \""+uinfo.GetTeaID()
					+"\" and coursenumber = \""+uinfo.GetCourseNumber()
					+"\" and question = \""+questionsitem.get(0)+"\"";
		choice1Bitem = Query(command);
		
		Vector<String> choice1Citem = new Vector<String>();
		command = "SELECT choiceC from choices WHERE teaid = \""+uinfo.GetTeaID()
					+"\" and coursenumber = \""+uinfo.GetCourseNumber()
					+"\" and question = \""+questionsitem.get(0)+"\"";
		choice1Citem = Query(command);
		
		Vector<String> choice2Aitem = new Vector<String>();
		command = "SELECT choiceA from choices WHERE teaid = \""+uinfo.GetTeaID()
					+"\" and coursenumber = \""+uinfo.GetCourseNumber()
					+"\" and question = \""+questionsitem.get(1)+"\"";
		choice2Aitem = Query(command);
		
		Vector<String> choice2Bitem = new Vector<String>();
		command = "SELECT choiceB from choices WHERE teaid = \""+uinfo.GetTeaID()
					+"\" and coursenumber = \""+uinfo.GetCourseNumber()
					+"\" and question = \""+questionsitem.get(1)+"\"";
		choice2Bitem = Query(command);
		
		Vector<String> choice2Citem = new Vector<String>();
		command = "SELECT choiceC from choices WHERE teaid = \""+uinfo.GetTeaID()
					+"\" and coursenumber = \""+uinfo.GetCourseNumber()
					+"\" and question = \""+questionsitem.get(1)+"\"";
		choice2Citem = Query(command);
		
		setTitle("Welcom to the Exam System");
		cl_QuestionPane = new CardLayout(8, 8);
		QuestionPane = new JPanel(cl_QuestionPane);
		QuestionPane.setBounds(new Rectangle(0, 0, 300, 125));
		OperationPane = new JPanel(); 
		OperationPane.setBounds(new Rectangle(0, 0, 300, 75));
		previous = new JButton("< Previous");
		next = new JButton("Next >");
		page1 = new JButton("1");
		page2 = new JButton("2");
		btnSubmmit = new JButton("Submmit");
		page1.setMargin(new Insets(2,2,2,2));
		page2.setMargin(new Insets(2,2,2,2));
		OperationPane.add(previous);
		OperationPane.add(page1);
		OperationPane.add(page2);
		OperationPane.add(next);
		OperationPane.add(btnSubmmit);
		Question1 = new JPanel();
		Question2 = new JPanel();
		Question1.setBackground(Color.WHITE);
		Question2.setBackground(Color.WHITE);
		QuestionPane.add(Question1, "q1");
		QuestionPane.add(Question2, "q2");
		Question1.setLayout(null);
		Question2.setLayout(null);
		
		question1 = new JLabel(questionsitem.get(0).toString());
		question1.setBounds(10, 10, 248, 42);
		Question1.add(question1);
		
		JLabel lb1A = new JLabel("A");
		lb1A.setBounds(10, 70, 12, 15);
		Question1.add(lb1A);
		
		JLabel lb1B = new JLabel("B");
		lb1B.setBounds(10, 95, 12, 15);
		Question1.add(lb1B);
		
		JLabel lb1C = new JLabel("C");
		lb1C.setBounds(10, 120, 12, 15);
		Question1.add(lb1C);
		
		JLabel lbchoice1A = new JLabel(choice1Aitem.get(0).toString());
		lbchoice1A.setBounds(26, 70, 232, 15);
		Question1.add(lbchoice1A);
		
		JLabel lbchoice1B = new JLabel(choice1Bitem.get(0).toString());
		lbchoice1B.setBounds(26, 95, 232, 15);
		Question1.add(lbchoice1B);
		
		JLabel lbchoice1C = new JLabel(choice1Citem.get(0).toString());
		lbchoice1C.setBounds(26, 120, 232, 15);
		Question1.add(lbchoice1C);
		
		answer1 = new JTextField();
		answer1.setBounds(250, 160, 66, 21);
		Question1.add(answer1);
		answer1.setColumns(10);
		
		question2 = new JLabel(questionsitem.get(1).toString());
		question2.setBounds(10, 10, 248, 42);
		Question2.add(question2);
		
		JLabel lb2A = new JLabel("A");
		lb2A.setBounds(10, 70, 12, 15);
		Question2.add(lb2A);
		
		JLabel lb2B = new JLabel("B");
		lb2B.setBounds(10, 95, 12, 15);
		Question2.add(lb2B);
		
		JLabel lb2C = new JLabel("C");
		lb2C.setBounds(10, 120, 12, 15);
		Question2.add(lb2C);
		
		lbchoice2A = new JLabel(choice2Aitem.get(0).toString());
		lbchoice2A.setBounds(26, 70, 232, 15);
		Question2.add(lbchoice2A);
		
		lbchoice2B = new JLabel(choice2Bitem.get(0).toString());
		lbchoice2B.setBounds(26, 95, 232, 15);
		Question2.add(lbchoice2B);
		
		lbchoice2C = new JLabel(choice2Citem.get(0).toString());
		lbchoice2C.setBounds(26, 120, 242, 15);
		Question2.add(lbchoice2C);
		
		answer2 = new JTextField();
		answer2.setBounds(250, 160, 66, 21);
		Question2.add(answer2);
		answer2.setColumns(10);
		
		
		previous.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				cl_QuestionPane.previous(QuestionPane);
			}
		});
		next.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				cl_QuestionPane.next(QuestionPane);
			}
		});
		page1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				cl_QuestionPane.show(QuestionPane, "q1");
			}
		});
		page2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				cl_QuestionPane.show(QuestionPane, "q2");
			}
		});
		btnSubmmit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				Submmit(question1.getText(),question2.getText());
			}
		});
		this.getContentPane().add(QuestionPane);
		this.getContentPane().add(OperationPane, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public Vector<String> Query(String Command){
		Vector<String> item = new Vector<String>();
		item = ClientServerProtol.Protol("Query", Command);
		if(item.get(0).equals("Protol Error!") ||
				item.get(0).equals("Query Failed!") ||
				item.get(0).equals("Update Failed!") ||
				item.get(0).equals("Begin Failed!")){
			JOptionPane.showMessageDialog(null, "Get Teaid Failed!\n"+item.get(0), "Error!", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
		return item;
	}
	
	public Vector<String> Update(String Command){
		Vector<String> item = new Vector<String>();
		item = ClientServerProtol.Protol("Update", Command);
		if(item.get(0).equals("Protol Error!") ||
				item.get(0).equals("Query Failed!") ||
				item.get(0).equals("Update Failed!") ||
				item.get(0).equals("Begin Failed!")){
			JOptionPane.showMessageDialog(null, "Get Teaid Failed!\n"+item.get(0), "Error!", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
		return item;
	}
	
	public void Submmit(String q1, String q2) {

		if(answer1.getText().length()>1||answer1.getText().length()==0||
				answer2.getText().length()>1||answer2.getText().length()==0)
			JOptionPane.showMessageDialog(null, "Answer Format Error!", "Error!", JOptionPane.PLAIN_MESSAGE);
		else{
			String command = "UPDATE studentanswers SET question = \""+q1+"\""
					+ " WHERE stuid = \""+uinfo.GetStuID()
					+ "\" and coursenumber = \""+uinfo.GetCourseNumber()+"\"";
			Update(command);
			command = "UPDATE studentanswers SET answer = \""+answer1.getText()+"\""
					+ " WHERE stuid = \""+uinfo.GetStuID()
					+ "\" and coursenumber = \""+uinfo.GetCourseNumber()+"\"";
			Update(command);
			
			command = "UPDATE studentanswers SET question = \""+q2+"\""
					+ " WHERE stuid = \""+uinfo.GetStuID()
					+ "\" and coursenumber = \""+uinfo.GetCourseNumber()+"\"";
			Update(command);
			command = "UPDATE studentanswers SET answer = \""+answer2.getText()+"\""
					+ " WHERE stuid = \""+uinfo.GetStuID()
					+ "\" and coursenumber = \""+uinfo.GetCourseNumber()+"\"";
			Update(command);
		}
		GradeQuit(q1,q2,answer1.getText(),answer2.getText());
	}
	
	public void GradeQuit(String Q1,String Q2,String A1,String A2) {
		
		Vector<String> answer1item = new Vector<String>();
		String command = "SELECT answer from teacherquestions WHERE teaid = \""+uinfo.GetTeaID()
					+"\" and coursenumber = \""+uinfo.GetCourseNumber()
					+"\" and question = \""+Q1+"\"";
		answer1item = Query(command);
		
		Vector<String> answer2item = new Vector<String>();
		command = "SELECT answer from teacherquestions WHERE teaid = \""+uinfo.GetTeaID()
				+"\" and coursenumber = \""+uinfo.GetCourseNumber()
				+"\" and question = \""+Q2+"\"";
		answer2item = Query(command);

		int grade = 100;
		int right = 0;
		if(answer1item.get(0).equals(A1))
			right++;
		if(answer2item.get(0).equals(A2))
			right++;
		grade = grade * right/2;
		System.out.println(right);
		System.out.println(grade);
		
		command = "UPDATE studentcourses SET grades = \""+grade+"\""
				+ " WHERE stuid = \""+uinfo.GetStuID()
				+"\" and coursenumber = \""+uinfo.GetCourseNumber()+"\"";
		Update(command);
		
		this.setVisible(false);
		StudentEntrance sEntrance = new StudentEntrance(uinfo);
		sEntrance.setVisible(true);
		this.dispose();
	}
	
	public void windowClosing(WindowEvent e) {
		this.setVisible(false);
		StudentEntrance sEntrance = new StudentEntrance(uinfo);
		sEntrance.setVisible(true);
		this.dispose();
	}
	public void windowOpened(WindowEvent e) { 
	} 
	public void windowClosed(WindowEvent e) {
	} 
	public void windowActivated(WindowEvent e) {
	} 
	public void windowDeactivated(WindowEvent e) { 
	}
	public void windowIconified(WindowEvent e) {
	} 
	public void windowDeiconified(WindowEvent e) { 
	} 
}
