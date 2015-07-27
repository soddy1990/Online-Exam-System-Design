import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.CardLayout;

import javax.swing.JTextField;
import javax.swing.JTextPane;

public class TeacherEntrance extends JFrame
{  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInformation uinfo;
	private JPanel Pane;
	private CardLayout cl_Pane; 
	private JPanel MainPanel;
	private JPanel AddPanel;
	private JPanel SelectPanel;
	private JTextField textFieldQuestion;
	private JTextField textFieldChoiceA;
	private JTextField textFieldChoiceB;
	private JTextField textFieldChoiceC;
	private JTextField textFieldRightAnswer;
	private JTextField textFieldSelect1;
	private JTextField textFieldSelect2;
	private JTextField textField;
	
	public TeacherEntrance(UserInformation u){
		
		super("CardLayout Test");
		try 
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		uinfo = u;
		uinfo.SetTeaID(uinfo.GetUserID());
		
		cl_Pane = new CardLayout(8,8);
		Pane = new JPanel(cl_Pane);
		Pane.setBounds(new Rectangle(0, 0, 325, 335));
		
		MainPanel = new JPanel();
		MainPanel.setAutoscrolls(true);
		AddPanel = new JPanel();
		SelectPanel = new JPanel();
		
		setTitle("TeacherEntrance");
		setBounds(new Rectangle(23,43,341,432));
		getContentPane().setLayout(null);
		
		JLabel lbWelcome = new JLabel("Welcome");
		lbWelcome.setBounds(58, 33, 55, 18);
		MainPanel.add(lbWelcome);

		JLabel lbTeaName = new JLabel(uinfo.GetFullName());
		lbTeaName.setBounds(175,22,124,41);
		MainPanel.add(lbTeaName);
		
		Vector<String> courseIDitem = new Vector<String>();
		String command = "SELECT coursenumber from courses WHERE teaid = \""+uinfo.GetTeaID()+"\"";
		courseIDitem = ClientServerProtol.Protol("Query", command);
		if(courseIDitem.get(0).equals("Protol Error!") ||
				courseIDitem.get(0).equals("Query Failed!") ||
				courseIDitem.get(0).equals("Update Failed!") ||
				courseIDitem.get(0).equals("Begin Failed!")){
			JOptionPane.showMessageDialog(null, "Get Courses Failed!\n"+courseIDitem.get(0), "Error!", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}

		final JComboBox courseNumber = new JComboBox(courseIDitem);
		courseNumber.setBounds(175,145,74,21);
		MainPanel.add(courseNumber);
		uinfo.SetCourseNumber(courseIDitem.get(0).toString());
		
		JLabel lblPleaseChooseYour = new JLabel("Please Choose Your Operation");
		lblPleaseChooseYour.setBounds(38,78,248,51);
		lblPleaseChooseYour.setForeground(SystemColor.textHighlight);
		lblPleaseChooseYour.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		MainPanel.add(lblPleaseChooseYour);
		
		JButton btnSelectQuestion = new JButton("SELECT QUESTION");
		btnSelectQuestion.setBounds(85,257,138,30);
		MainPanel.add(btnSelectQuestion);
		
		JButton btnAddQuestion = new JButton("ADD QUESTION");
		btnAddQuestion.setBounds(85,203,138,30);
		MainPanel.add(btnAddQuestion);

		MainPanel.setLayout(null);
		AddPanel.setLayout(null);
		SelectPanel.setLayout(null);
		Pane.add(MainPanel, "main");
		
		JLabel lblNewLabel_1 = new JLabel("Choose Course");
		lblNewLabel_1.setBounds(58, 148, 90, 15);
		MainPanel.add(lblNewLabel_1);
		Pane.add(AddPanel, "add");
		Pane.add(SelectPanel, "select");
		
		textFieldQuestion = new JTextField();
		textFieldQuestion.setBounds(99, 10, 200, 78);
		AddPanel.add(textFieldQuestion);
		textFieldQuestion.setColumns(10);

		JLabel lbQuestionSelect = new JLabel("Questions");
		lbQuestionSelect.setBounds(10, 10, 73, 15);
		SelectPanel.add(lbQuestionSelect);
		uinfo.SetCourseNumber(courseNumber.getSelectedItem().toString());
		
		JLabel lbQuestion = new JLabel("Question");
		lbQuestion.setBounds(10, 36, 79, 27);
		AddPanel.add(lbQuestion);
		
		JLabel lbChoiceA = new JLabel("ChoiceA");
		lbChoiceA.setBounds(new Rectangle(10, 110, 0, 0));
		lbChoiceA.setBounds(10, 117, 54, 15);
		AddPanel.add(lbChoiceA);
		
		JLabel lbChoiceB = new JLabel("ChoiceB");
		lbChoiceB.setBounds(new Rectangle(10, 135, 0, 0));
		lbChoiceB.setBounds(10, 159, 54, 15);
		AddPanel.add(lbChoiceB);
		
		JLabel lbChoiceC = new JLabel("ChoiceC");
		lbChoiceC.setBounds(10, 198, 54, 15);
		AddPanel.add(lbChoiceC);
		
		textFieldChoiceA = new JTextField();
		textFieldChoiceA.setBounds(99, 114, 200, 21);
		AddPanel.add(textFieldChoiceA);
		textFieldChoiceA.setColumns(10);
		
		textFieldChoiceB = new JTextField();
		textFieldChoiceB.setBounds(99, 156, 200, 21);
		AddPanel.add(textFieldChoiceB);
		textFieldChoiceB.setColumns(10);
		
		textFieldChoiceC = new JTextField();
		textFieldChoiceC.setBounds(99, 195, 200, 21);
		AddPanel.add(textFieldChoiceC);
		textFieldChoiceC.setColumns(10);
		
		JButton btnAddButton = new JButton("A D D");
		btnAddButton.setBounds(43, 253, 227, 23);
		AddPanel.add(btnAddButton);
		
		JButton btnSelect = new JButton("SELECT");
		btnSelect.setBounds(43, 286, 93, 23);
		AddPanel.add(btnSelect);
		
		JButton btnManu = new JButton("MANU");
		btnManu.setBounds(177, 286, 93, 23);
		AddPanel.add(btnManu);
		
		JLabel lblRightAnswer = new JLabel("Right Answer");
		lblRightAnswer.setBounds(10, 228, 79, 15);
		AddPanel.add(lblRightAnswer);
		
		textFieldRightAnswer = new JTextField();
		textFieldRightAnswer.setBounds(233, 225, 66, 21);
		AddPanel.add(textFieldRightAnswer);
		textFieldRightAnswer.setColumns(10);
		
		
		
		textFieldSelect1 = new JTextField();
		textFieldSelect1.setBounds(105, 212, 66, 21);
		SelectPanel.add(textFieldSelect1);
		textFieldSelect1.setColumns(10);
		
		textFieldSelect2 = new JTextField();
		textFieldSelect2.setBounds(217, 212, 66, 21);
		SelectPanel.add(textFieldSelect2);
		textFieldSelect2.setColumns(10);
		
		JLabel lbSelectedQuestion = new JLabel("Selected");
		lbSelectedQuestion.setBounds(10, 212, 73, 21);
		SelectPanel.add(lbSelectedQuestion);
		
		JButton btnSubmmit = new JButton("SUBMMIT");
		btnSubmmit.setBounds(70, 245, 174, 23);
		SelectPanel.add(btnSubmmit);
		
		JButton btnSubmmitAdd = new JButton("A D D");
		btnSubmmitAdd.setBounds(39, 286, 116, 23);
		SelectPanel.add(btnSubmmitAdd);
		
		JButton btnSubmmitManu = new JButton("MANU");
		btnSubmmitManu.setBounds(165, 286, 118, 23);
		SelectPanel.add(btnSubmmitManu);
		

		btnAddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(textFieldRightAnswer.getText().length()==0||textFieldRightAnswer.getText().length()>1)
					JOptionPane.showMessageDialog(null, "Answer Format Error!", "Error!", JOptionPane.PLAIN_MESSAGE);
				else if(textFieldQuestion.getText().length()==0)
					JOptionPane.showMessageDialog(null, "Question Blank!", "Error!", JOptionPane.PLAIN_MESSAGE);
				else if(textFieldChoiceA.getText().length()==0||textFieldChoiceB.getText().length()==0||textFieldChoiceC.getText().length()==0)
					JOptionPane.showMessageDialog(null, "Choice Blank!", "Error!", JOptionPane.PLAIN_MESSAGE);
				else{
					addQuestion(textFieldQuestion.getText(),textFieldChoiceA.getText(),textFieldChoiceB.getText(),textFieldChoiceC.getText(),textFieldRightAnswer.getText());
				}
			}
		});

		btnAddQuestion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				cl_Pane.show(Pane, "add");
				uinfo.SetCourseNumber(courseNumber.getSelectedItem().toString());
			}
		});
		btnSelectQuestion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				Vector<String> questionsitem = new Vector<String>();
				String command = "SELECT question from teacherquestions WHERE teaid = \""+uinfo.GetTeaID()
							+"\" and coursenumber = \""+uinfo.GetCourseNumber()+"\"";
				questionsitem = Query(command);
				JComboBox comboBox = new JComboBox(questionsitem);
				comboBox.setBounds(10, 73, 289, 62);
				SelectPanel.add(comboBox);

				cl_Pane.show(Pane, "select");
			}
		});
		btnSelect.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Vector<String> questionsitem = new Vector<String>();
				String command = "SELECT question from teacherquestions WHERE teaid = \""+uinfo.GetTeaID()
							+"\" and coursenumber = \""+uinfo.GetCourseNumber()+"\"";
				questionsitem = Query(command);
				JComboBox comboBox = new JComboBox(questionsitem);
				comboBox.setBounds(10, 73, 289, 62);
				SelectPanel.add(comboBox);

				cl_Pane.show(Pane, "select");
			}
		});
		btnSubmmit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Vector<String> questionsitem = new Vector<String>();
				String command = "SELECT question from teacherquestions WHERE teaid = \""+uinfo.GetTeaID()
							+"\" and coursenumber = \""+uinfo.GetCourseNumber()+"\"";
				questionsitem = Query(command);
				for(int i = 0; i<questionsitem.size();i++){
					int a = Integer.parseInt(textFieldSelect1.getText());
					int b = Integer.parseInt(textFieldSelect2.getText());
					if(i == a || i == b){
						command = "UPDATE teacherquestions SET choosen = \"1\""
								+ " WHERE teaid = \""+uinfo.GetTeaID()
								+ "\" and coursenumber = \""+uinfo.GetCourseNumber()
								+ "\" and question = \""+questionsitem.get(i)+"\"";
						Update(command);
					}
					else{
						command = "UPDATE teacherquestions SET choosen = \"0\""
							+ " WHERE teaid = \""+uinfo.GetTeaID()
							+ "\" and coursenumber = \""+uinfo.GetCourseNumber()
							+ "\" and question = \""+questionsitem.get(i)+"\"";
						Update(command);
					}
				}
			}
		});
		btnManu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Manu_actionPerformed (e);
			}
		});
		btnSubmmitAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				cl_Pane.show(Pane, "add");
			}
		});
		btnSubmmitManu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Manu_actionPerformed (e);
			}
		});
		
		
		this.getContentPane().add(Pane);
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
			JOptionPane.showMessageDialog(null, "Query "+Command+" Failed!\n"+item.get(0), "Error!", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
		//JOptionPane.showMessageDialog(null, "Query "+Command+" Success!", "Success!", JOptionPane.PLAIN_MESSAGE);
		return item;
	}
	
	public Vector<String> Update(String Command){
		Vector<String> item = new Vector<String>();
		item = ClientServerProtol.Protol("Update", Command);
		if(item.get(0).equals("Protol Error!") ||
				item.get(0).equals("Query Failed!") ||
				item.get(0).equals("Update Failed!") ||
				item.get(0).equals("Begin Failed!")){
			JOptionPane.showMessageDialog(null, "Update "+Command+" Failed!\n"+item.get(0), "Error!", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
		//JOptionPane.showMessageDialog(null, "Update "+Command+" Success!", "Success!", JOptionPane.PLAIN_MESSAGE);
		return item;
	}
	
	public void addQuestion(String q, String c1, String c2, String c3, String a)
	{
		String command = "INSERT INTO teacherquestions (teaid, coursenumber, question, answer, choosen) "+
				"VALUES(\""+uinfo.GetTeaID()+"\", \""+uinfo.GetCourseNumber()+"\", \""
				+q+"\", \""+a+"\", \"0\")";
		Update(command);
		command = "INSERT INTO choices (coursenumber, teaid, question, choiceA, choiceB, choiceC) "+
				"VALUES(\""+uinfo.GetCourseNumber()+"\", \""+uinfo.GetTeaID()+"\", \""
				+q+"\", \""+c1+"\", \""+c2+"\", \""+c3+"\")";
		Update(command);
	}
	
	public void Manu_actionPerformed (ActionEvent e)
	{
		this.setVisible(false);
		TeacherEntrance te = new TeacherEntrance(uinfo);
		te.setVisible(true);
		this.dispose();
	}
}