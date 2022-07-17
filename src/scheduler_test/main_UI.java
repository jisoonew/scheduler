package scheduler_test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class main_UI {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calendarmain();

	}
}
class Calendarmain extends JFrame implements ActionListener{

	JLabel check_date = new JLabel("");  // 오른쪽 선택 일

	JLabel check_year = new JLabel("");
	
	JLabel check_month = new JLabel("");
	
	JLabel schedule2 = new JLabel("");

	JLabel schedule3 = new JLabel("");

	JLabel schedule4 = new JLabel("");

	JLabel schedule5 = new JLabel("");

	JLabel month2 = new JLabel("");
	
	
	String[] group_1={"그룹","일상", "가족", "친구", "여행"};

	

	JComboBox group = new JComboBox(group_1);
	
	
	Vector outer,title, noresult, msg ; //noresult :검색결과없을때 테이블때문에
	
	Vector outer1,title1, noresult1, msg1 ; //noresult :검색결과없을때 테이블때문에
	
	
	
	JTextArea ta = new JTextArea();
	
	DefaultTableModel model = new DefaultTableModel();
	
	JTable table = new JTable(model);

	JScrollPane sp;	
	
	JCheckBox finish = new JCheckBox("완료");
	




	
	
	String connect = new String("jdbc:mariadb://localhost:3305/javadb");

	String user = new String("root");

	String passwd = new String("1234");
	
	Connection conn;

	Statement stat;

	PreparedStatement prestat1, prestat2, prestat3, prestat4;

	ResultSet rs;
	

	boolean count;

	int a = -1;

	

	Container container = getContentPane();

    JLabel bean = new JLabel("");

    JLabel bean1 = new JLabel("");

    JLabel bean2 = new JLabel("");

    JLabel bean3 = new JLabel("    ");

    

    Button btn_delete = new Button("삭제");

    Button btn_save = new Button("저장");

	

	JPanel panel1 = new JPanel();

	JPanel panel2 = new JPanel();

	JPanel panel3 = new JPanel();

	JPanel panel4 = new JPanel();

	JPanel panel5 = new JPanel();

	JPanel panel6 = new JPanel();

	

	JTextArea textArea = new JTextArea(8,47);

	JTextArea textArea1 = new JTextArea(5,47);

	JTextField textfield = new JTextField(47);

	

	JRadioButton[] rb1 = new JRadioButton[5];


    ButtonGroup group1 = new ButtonGroup();

	
	

	JButton buttonBefore = new JButton("<");

	JButton buttonAfter = new JButton(">");

	JButton btn = new JButton("wpqkf");

	JTextField search = new JTextField(20);

	JButton btnsearch = new JButton("검색");

	

	String[] combo_search = {"Year","Month","Title","Content"};

	String[] year = {"Year","2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2012","2011",

			         "2010","2009","2008","2007","2006","2005","2004","2003","2002","2001", "2000", 

			         "1999", "1998", "1997", "1996" ,"1995" ,"1994", "1993", "1992", "1991", "1990"};

	

	JComboBox searchcombo = new JComboBox(combo_search);

	

	JComboBox yearcombo = new JComboBox(year);

	

	JLabel panel1_year = new JLabel("");
	JLabel panel1_month = new JLabel("");
	JLabel panel1_text_year = new JLabel("년");
	JLabel panel1_text_month = new JLabel("월");

	

	JButton[] buttons = new JButton[49];

	String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};

	

	CalendarFunction cF = new CalendarFunction();

	

	public Calendarmain() {

		setTitle("schedule");

		setSize(1000, 800);

		setLocation(10, 10);

		GUI_init();
		
		prepareDB();
		
		init();
		
		data();
		
		model.setDataVector(outer, title);

		btn_save.addActionListener(new ActionListener () {  // 저장버튼
			public void actionPerformed(ActionEvent e){
				add(e);
				
			}
		});

		btn_delete.addActionListener(new ActionListener () {  // 삭제버튼
			public void actionPerformed(ActionEvent e){
		delete();
			}
		});
		
		checkList();  // 년월일 표시
		
		btnsearch.addActionListener(new ActionListener () {  // 검색 버튼
			public void actionPerformed(ActionEvent e){
				combo_select();
			}
		});

		schedule_Bottom();

		start();
		
		setVisible(true);

	}

	

	private void init() {

		 panel3.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0)); //상좌하우 10씩 띄우기

		 panel3.setPreferredSize(new Dimension(45, 100));

		 panel4.setPreferredSize(new Dimension(230, 250));

		 panel4.setBackground(new Color(201, 231, 248));

		 panel1.add(buttonBefore);

		 buttonBefore.setBorderPainted(false); // 버튼 테두리 설정

		 buttonBefore.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정

		 panel1.add(panel1_year); // 2022년
		 panel1.add(panel1_text_year);  // "년"
		 panel1.add(panel1_month); // 5월
		 panel1.add(panel1_text_month);  // "월"

		 panel1.add(buttonAfter);

		 buttonAfter.setBorderPainted(false); // 버튼 테두리 설정

		 buttonAfter.setContentAreaFilled(false); // 버튼 영역 배경 표시 설정

		 buttonAfter.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 20)); //상 좌 하 우


		 panel1.add(searchcombo);

		 panel1.add(search);

		 panel1.add(btnsearch);


		

		 // 월 before after 글씨 크기

		 Font font1 = new Font("SansSerif", Font.BOLD, 20);

		 buttonAfter.setFont(font1);

		 buttonBefore.setFont(font1);

		 panel1_year.setFont(font1);
		 
		 panel1_month.setFont(font1);
		 
		 panel1_text_year.setFont(font1);
		 
		 panel1_text_month.setFont(font1);


		 // 검색 텍스트 필드 글씨 크기

		 Font font2 = new Font("SansSerif", Font.ITALIC, 18);

		 search.setFont(font2);

		 

		 panel1_year.setText(cF.getCalText_year());
		 panel1_month.setText(cF.getCalText_month());


			 add(panel1, BorderLayout.NORTH);

			 add(panel2, BorderLayout.CENTER); 

			 add(panel3, BorderLayout.WEST);  // 왼쪽

			 add(panel4, BorderLayout.SOUTH);

			 add(panel5, BorderLayout.EAST);


		 panel2.setLayout(new GridLayout(7, 7, 5, 5));

		 for(int i = 0; i < buttons.length; i++) {

			 buttons[i] = new JButton();

			 panel2.add(buttons[i]);

			 buttons[i].setFont(new Font("SansSerif", Font.BOLD, 24));

			 buttons[i].setContentAreaFilled(false); // 버튼 영역 배경 표시 설정


			 if(i < 7) buttons[i].setText(dayNames[i]); 

			 if(i%7 == 0) buttons[i].setForeground(Color.RED);

			 if(i%7 == 6) buttons[i].setForeground(Color.BLUE);


				 buttons[i].addActionListener(new Calculate());

		 }
		 
		 cF.setButtons(buttons);

		 cF.calSet();

	}


	private void start() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		buttonAfter.addActionListener(this);

		buttonBefore.addActionListener(this);

	}

	

	@Override

	public void actionPerformed(ActionEvent e) {

		int gap = 0;

		if(e.getSource() == buttonAfter) {				// 1달 후

			gap = 1;

		} else if(e.getSource() == buttonBefore ) {		// 1달 전

			gap = -1;

		}

		cF.allInit(gap);

		panel1_year.setText(cF.getCalText_year());		// 년 글자 갱신		
		panel1_month.setText(cF.getCalText_month());		// 월 글자 갱신	

	}	
	

	
	public void data() {
			String query = null;
			
			String check_year_up = check_year.getText();

			String check_month_up = check_month.getText();
		
			String check_date_up = check_date.getText();
		
			try{

				if(query == null) {

					query = "select * from java_table where check_year='"+check_year.getText()+"' && check_month='"+check_month.getText()+"' && check_date='"+check_date.getText()+"'";

					
				rs = stat.executeQuery(query);

				// rs안에 데이터가 여부 체크 후 작업

				outer.clear();

				while (rs.next()) {

					msg = new Vector<String>();
					
						
					msg.add(rs.getString(2));

					msg.add(rs.getString(6));

					msg.add(rs.getString(7));


					model.addRow(msg);
					//outer.add(msg);
				}
				model.fireTableDataChanged();
				}
				
				else {
					table.setModel(model);
				}

			

		}catch(Exception e66){
			e66.printStackTrace();
		}
		
	}
	
	
	



	
	public void prepareDB(){


		 try {

				//driver 등록

				Class.forName("org.mariadb.jdbc.Driver");	//객체 생성


				//connection 얻기

				conn = DriverManager.getConnection(connect, user, passwd);
	

				//statement 얻기

				stat = conn.createStatement();


				prestat1 = conn.prepareStatement("insert into java_table(finish, check_year, check_month, check_date,title,textfield) values(?,?,?,?,?,?)");

				prestat2 = conn.prepareStatement("delete from java_table where check_year=? and check_month=? and check_date=? and title=? and textfield=?");
				
				prestat3 = conn.prepareStatement("UPDATE java_table SET title='"+textfield.getText()+"'");
				

				
			} catch (Exception e) {

			}

	}

	

	public void add(ActionEvent e){
		
		try {
		String finish_up = finish.getText();
		
		String check_year_up = check_year.getText();

		String check_month_up = check_month.getText();
	
		String check_date_up = check_date.getText();
	
		String textfield_up = textfield.getText();

		String textArea_up = textArea.getText();
		

			int index = 1;
			
			if(finish.isSelected()) { // 완료 체크 박스 체크 여부
				finish_up = finish.getText();
			} 
			else {
				finish_up = "";
			}
			

			prestat1.setString(index++, finish_up);

			prestat1.setString(index++, check_year_up);
			
			prestat1.setString(index++, check_month_up);

			prestat1.setString(index++, check_date_up);
			
			prestat1.setString(index++, textfield_up);

			prestat1.setString(index++, textArea_up);
			
			

			prestat1.executeUpdate();


			
		} catch (SQLException e7) {

			// TODO Auto-generated catch block

			e7.printStackTrace();

		}
	}
	
	
	
	public void delete(){
		int index = 1;
		
		String textfield_up1 = check_year.getText();
		String textfield_up2 = check_month.getText();
		String textfield_up3 = check_date.getText();
		String textfield_up4 = textfield.getText();
		String textfield_up5 = textArea.getText();

		
		try {

			prestat2.setString(index++, textfield_up1);
			prestat2.setString(index++, textfield_up2);
			prestat2.setString(index++, textfield_up3);
			prestat2.setString(index++, textfield_up4);
			prestat2.setString(index++, textfield_up5);

			prestat2.executeUpdate();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}
	
	
	
	
public void update(){
	
		String finish_update = finish.getText();
		
		String textfield_update = textfield.getText();

		String textArea_update = textArea.getText();

		//driver 등록

		
		if(finish.isSelected()) {
			finish_update = finish.getText();
		} 
		else {
			finish_update = "";
		}

		try {
//			prestat3.setString(2, finish_up);

			prestat3.setString(6, textfield_update);

//			prestat3.setString(7, textArea_up);

			prestat3.executeUpdate();

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}



public void combo_select() {

	if(searchcombo.getSelectedItem().toString() == "Month" && search.getText() != "") { // 월 검색 기능

		String query = null;
		
		String check_year_up = check_year.getText();

		String check_month_up = check_month.getText();
	
		String check_date_up = check_date.getText();
	
		try{

			if(query == null) {

				query = "select * from java_table where check_month='"+search.getText()+"'";
				
				
				
			rs = stat.executeQuery(query);

			// rs안에 데이터가 여부 체크 후 작업

			outer.clear();

			while (rs.next()) {

				msg = new Vector<String>();
				
					
				msg.add(rs.getString(2));

				msg.add(rs.getString(6));

				msg.add(rs.getString(7));
				

				model.addRow(msg);
				//outer.add(msg);
			}
			model.fireTableDataChanged();
			}
			
			else {
				table.setModel(model);
			}

	}catch(Exception e66){
		e66.printStackTrace();
	}
	}
		
		
		
		if(searchcombo.getSelectedItem().toString() == "Year" && search.getText() != "") { // 해당 년도 검색 기능

			String query = null;
			
			String check_year_up = check_year.getText();

			String check_month_up = check_month.getText();
		
			String check_date_up = check_date.getText();
		
			try{

				if(query == null) {

					query = "select * from java_table where check_year='"+search.getText()+"'";
					
				rs = stat.executeQuery(query);

				// rs안에 데이터가 여부 체크 후 작업

				outer.clear();

				while (rs.next()) {

					msg = new Vector<String>();
					
						
					msg.add(rs.getString(2));

					msg.add(rs.getString(6));

					msg.add(rs.getString(7));


					model.addRow(msg);
					//outer.add(msg);
				}
				model.fireTableDataChanged();
				}
				
				else {
					table.setModel(model);
				}

		}catch(Exception e66){
			e66.printStackTrace();
		}
		}
			
			
			if(searchcombo.getSelectedItem().toString() == "Content" && search.getText() != "") { // 내용 검색 기능

				String query = null;
				
				String check_year_up = check_year.getText();

				String check_month_up = check_month.getText();
			
				String check_date_up = check_date.getText();
			
				try{

					if(query == null) {

						query = "select * from java_table where textfield like '%"+search.getText()+"%'";
						
					rs = stat.executeQuery(query);

					// rs안에 데이터가 여부 체크 후 작업

					outer.clear();

					while (rs.next()) {

						msg = new Vector<String>();
						
							
						msg.add(rs.getString(2));

						msg.add(rs.getString(6));

						msg.add(rs.getString(7));


						model.addRow(msg);
						//outer.add(msg);
					}
					model.fireTableDataChanged();
					}
					
					else {
						table.setModel(model);
					}

			}catch(Exception e66){
				e66.printStackTrace();
			}
		
	}
	
	
		if(searchcombo.getSelectedItem().toString() == "Title" && search.getText() != ""){ // 제목 검색 기능

			String query = null;
			
			String check_year_up = check_year.getText();

			String check_month_up = check_month.getText();
		
			String check_date_up = check_date.getText();
		
			try{

				if(query == null) {

					query = "select * from java_table where title like '%"+search.getText()+"%'";
					
				rs = stat.executeQuery(query);

				// rs안에 데이터가 여부 체크 후 작업

				outer.clear();

				while (rs.next()) {

					msg = new Vector<String>();
					
						
					msg.add(rs.getString(2));

					msg.add(rs.getString(6));

					msg.add(rs.getString(7));


					model.addRow(msg);
					//outer.add(msg);
				}
				model.fireTableDataChanged();
				}
				
				else {
					table.setModel(model);
				}

		}catch(Exception e66){
			e66.printStackTrace();
		}
		}
	}


	
	
	public void GUI_init() {
		
		title = new Vector();

		outer = new Vector();		

		noresult = new Vector();		

		msg = new Vector();		
		
		title.add("Year");

		title.add("Month");

		title.add("Date");
		
		title.add("완료");

		title.add("제목");

		title.add("내용");



		noresult.add("실행 결과");
		
		addWindowListener(new WindowAdapter(){

			@Override

			public void windowClosing(WindowEvent w) {

				try {

					if(rs!=null) rs.close();
                    if(stat!=null) stat.close();
                    if(conn!=null) conn.close();          

					setVisible(false);

					dispose();

					System.exit(0);

				} catch (Exception e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}
				super.windowClosing(w);
			}

		});		
		
		// DefaultTableModel 생성

				model = new DefaultTableModel();

				//model 사용해서 JTable 생성

				table = new JTable(model);
		
		table.addMouseListener(new MouseAdapter(){  // 테이블 클릭시 텍스트필드와 텍스트아리아에 표시됨

			public void mouseClicked(MouseEvent me){

				//클릭한 행의 인덱스 알아내기-->JTable의 메소드

				   int index = table.getSelectedRow();


				  //인덱스 이용해서 out안의 작은 벡터 in 꺼내기

				   Vector msg = (Vector) outer.get(index);
				   
				   String title = (String) msg.get(1);

				   String content = (String) msg.get(2);
				   

				   textfield.setText(title);

				   textArea.setText(content);

				  //번호가 들어가는 텍스트 필드는 편집 불가 상태로 변경

				  // fid.setEditable(false);
			}

		});
		
		sp = new JScrollPane(table);

   
    }
	

	
	
	
	
	public void clear(){		

		textfield.setText("");

		textArea.setText("");
 
	}
	


	public void schedule_Bottom() {

		

		JLabel content = new JLabel("내용");

		JLabel title = new JLabel("제목");

		JLabel label3 = new JLabel("배경색");

		JLabel label4 = new JLabel("이모티콘");

		JLabel label5 = new JLabel("글자색");

		

		Box box1 = Box.createHorizontalBox();

		Box box2 = Box.createHorizontalBox();


    	

    	JLabel chb5 = new JLabel("dsdsdddd");

    	JLabel chb6 = new JLabel("");

 

    	panel4.setLayout(new BorderLayout());

		

		// 클릭한 년월일 표시

	    JPanel a1 = new JPanel();

	    a1.setBorder(BorderFactory.createEmptyBorder(10 , 0 , 0 , 0)); //상 좌 하 우

		a1.add(finish);

		a1.add(bean);

		

		bean.setBorder(BorderFactory.createEmptyBorder(0 , 800 , 0 , 0)); //상 좌 하 우

 

	    //a1.setBackground(Color.WHITE);

	    panel4.add(a1,BorderLayout.NORTH);



	    // 센터 입력한 당일 일정 모아보기

	    JPanel c2 = new JPanel();

	    c2.setLayout(new BorderLayout());

	    

		 JPanel c4 = new JPanel();

	    c4.add(title);

	    c4.add(textfield);

	    

	    c4.add(content);

	    c4.add(textArea);

	    c2.add(c4,BorderLayout.CENTER);


	    panel4.add(c2);


	 // 동쪽 borderLayout 모양 유지를 위해서

	    JPanel e4 = new JPanel();

	    e4.setLayout(new BorderLayout());

	    e4.setLocation(30,30);

	    e4.add(chb6);

	    panel4.add(e4, BorderLayout.SOUTH);


	    
		 // 동쪽 borderLayout 모양 유지를 위해서

	    JPanel a2 = new JPanel();

	    


	    JRadioButton[] rb2 = new JRadioButton[5];


	    ButtonGroup group2 = new ButtonGroup();

	  



	    btn_delete.setPreferredSize(new Dimension(80, 35));

		btn_save.setPreferredSize(new Dimension(80, 35));

		

		a2.add(btn_delete);

		a2.add(btn_save);

	    a2.setPreferredSize(new Dimension(400, 250));

	    panel4.add(a2, BorderLayout.EAST);

	    

	    setVisible(true);

	}


	

	public void checkList() {

		// panel5는 borderLayout으로 지정

				panel5.setLayout(new BorderLayout());

				// 클릭한 년월일 표시

			    JPanel a = new JPanel();
			    
			    JPanel b = new JPanel();
			    b.setLayout(new FlowLayout());

			    

			    JLabel D_day = new JLabel("D-day");

				check_date.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0)); //상 좌 하 우

				Font font3 = new Font("SansSerif", Font.BOLD, 25);

				check_date.setFont(font3);

				check_year.setFont(font3);
				
				check_month.setFont(font3);

//				JLabel o1 = new JLabel(" / ");
//
//				JLabel o2 = new JLabel(" / ");
//				
//				o1.setFont(font3);
//				
//				o2.setFont(font3);
//				
//				b.add(check_year);
//				
//				b.add(o1);
//				
//				b.add(check_month);
//				
//				b.add(o2);
//
//				b.add(check_date);
//
//				a.add(b);
				

			    panel5.add(a,BorderLayout.NORTH);
			    

			    

			    // 센터 입력한 당일 일정 모아보기

			    JPanel c = new JPanel();


				

				Font font4 = new Font("SansSerif", Font.PLAIN, 15);

				

				sp = new JScrollPane(table);
				
				sp.setPreferredSize(new Dimension(403,350));
				

			    c.add(sp);

			    panel5.add(c,BorderLayout.CENTER);



	}
	
	
	
	
	class Calculate implements ActionListener {

		public void actionPerformed(ActionEvent e) {


			 String operation =e.getActionCommand(); //어떤 버튼이 눌렸는지 확인


					if(operation.equals("1")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("1");data(); month2.setText(panel1_year.getText()); schedule2.setText("1"); data();  }
					
					if(operation.equals("2")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("2"); month2.setText(panel1_year.getText()); schedule2.setText("2"); data(); }

					if(operation.equals("3")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("3"); month2.setText(panel1_year.getText()); schedule2.setText("3"); data(); }

					if(operation.equals("4")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("4"); month2.setText(panel1_year.getText()); schedule2.setText("4"); data(); }

					if(operation.equals("5")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("5"); month2.setText(panel1_year.getText()); schedule2.setText("5"); data(); }

					if(operation.equals("6")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("6"); month2.setText(panel1_year.getText()); schedule2.setText("6"); data(); }

					if(operation.equals("7")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("7"); month2.setText(panel1_year.getText()); schedule2.setText("7"); data(); }

					if(operation.equals("8")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("8"); month2.setText(panel1_year.getText()); schedule2.setText("8"); data(); }

					if(operation.equals("9")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("9"); month2.setText(panel1_year.getText()); schedule2.setText("9"); data(); }

					if(operation.equals("10")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("10"); month2.setText(panel1_year.getText()); schedule2.setText("10"); data(); }

					if(operation.equals("11")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("11"); month2.setText(panel1_year.getText()); schedule2.setText("11"); data(); }

					if(operation.equals("12")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("12"); month2.setText(panel1_year.getText()); schedule2.setText("12"); data(); }

					if(operation.equals("13")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("13"); month2.setText(panel1_year.getText()); schedule2.setText("13"); data(); }

					if(operation.equals("14")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("14"); month2.setText(panel1_year.getText()); schedule2.setText("14"); data(); }

					if(operation.equals("15")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("15"); month2.setText(panel1_year.getText()); schedule2.setText("15"); data(); }

					if(operation.equals("16")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("16"); month2.setText(panel1_year.getText()); schedule2.setText("16"); data(); }

					if(operation.equals("17")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("17"); month2.setText(panel1_year.getText()); schedule2.setText("17"); data(); }

					if(operation.equals("18")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("18"); month2.setText(panel1_year.getText()); schedule2.setText("18"); data(); }

					if(operation.equals("19")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("19"); month2.setText(panel1_year.getText()); schedule2.setText("19"); data(); }

					if(operation.equals("20")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("20"); month2.setText(panel1_year.getText()); schedule2.setText("20"); data(); }

					if(operation.equals("21")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("21"); month2.setText(panel1_year.getText()); schedule2.setText("21"); data(); }

					if(operation.equals("22")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("22"); month2.setText(panel1_year.getText()); schedule2.setText("22"); data(); }

					if(operation.equals("23")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("23"); data(); month2.setText(panel1_year.getText()); schedule2.setText("23");  }

					if(operation.equals("24")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("24"); month2.setText(panel1_year.getText()); schedule2.setText("24"); data(); }

					if(operation.equals("25")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("25"); month2.setText(panel1_year.getText()); schedule2.setText("25"); data(); }

					if(operation.equals("26")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("26"); month2.setText(panel1_year.getText()); schedule2.setText("26"); data(); }

					if(operation.equals("27")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("27"); month2.setText(panel1_year.getText()); schedule2.setText("27"); data(); }

					if(operation.equals("28")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("28"); month2.setText(panel1_year.getText()); schedule2.setText("28"); data(); }

					if(operation.equals("29")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("29"); month2.setText(panel1_year.getText()); schedule2.setText("29"); data(); }

					if(operation.equals("30")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("30"); month2.setText(panel1_year.getText()); schedule2.setText("30"); data(); }

					if(operation.equals("31")) { check_year.setText(panel1_year.getText()); check_month.setText(panel1_month.getText()); check_date.setText("31"); month2.setText(panel1_year.getText()); schedule2.setText("31"); data(); }

			 }

			}

		}