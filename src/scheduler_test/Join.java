package scheduler_test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Join {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Joinmain();
	}
}

class Joinmain extends JFrame{
	// 프레임 생성
    JFrame frm = new JFrame();
	
	JLabel Label = new JLabel("To-Do List");
    JLabel Label1 = new JLabel("아이디");
    JLabel Label2 = new JLabel("비밀번호");
    JLabel Label3 = new JLabel("비밀번호 확인");
    
    JLabel Label7 = new JLabel("이메일");
    JLabel Label8 = new JLabel("@");
    
    JTextField text1 = new JTextField(25);
    JTextField text2 = new JTextField(25);
    JTextField text3 = new JTextField(25);
    JTextField text4 = new JTextField(20);
    
    
	String connect = new String("jdbc:mariadb://localhost:3305/javadb");

	String user = new String("root");

	String passwd = new String("1234");
	
	Connection conn;

	Statement stat;

	PreparedStatement prestat1, prestat2, prestat3, prestat4;

	ResultSet rs;
	
    String[] email = {"선택","naver.com", "daum.net", "gmail.com"};

    JComboBox combo4 = new JComboBox(email);
    
    
    
    JButton btn1 = new JButton("로그인");
    JButton btn2 = new JButton("회원가입");
    JButton btn3 = new JButton("취소");
    JButton btn4 = new JButton("확인");
	
	public void Join() { 
		 
        // 프레임 크기 설정
        frm.setSize(500, 500);
 
        // 프레임을 화면 가운데에 배치
        frm.setLocationRelativeTo(null);
 
        // 프레임을 닫았을 때 메모리에서 제거되도록 설정
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // 레이아웃 설정
        frm.getContentPane().setLayout(null);
        
        Label.setBounds(200, 30, 300, 40);
        Label.setFont(new Font("Serif", Font.BOLD, 30));
 
        Label1.setBounds(70, 100, 200, 30);
        Label2.setBounds(70, 150, 200, 30);
        Label3.setBounds(70, 200, 250, 30);  // 비밀번호 확인
        
        text1.setBounds(170, 100, 250, 30);  // 아이디
        text2.setBounds(170, 150, 250, 30);  // 비밀번호
        text3.setBounds(170, 200, 250, 30);  // 비밀번호 확인
        
 
        
        Label7.setBounds(70, 250, 60, 30);  // 비밀번호 확인
        text4.setBounds(170, 250, 100, 30);  // 비밀번호 확인
        Label8.setBounds(290, 250, 100, 30);  // 비밀번호 확인
        combo4.setBounds(320, 250, 100, 30);  // 비밀번호 확인
        
        btn3.setBounds(170, 320, 100, 45);  // 비밀번호 확인
        btn4.setBounds(320, 320, 100, 45);  // 비밀번호 확인

        
        frm.getContentPane().add(Label);
 
        frm.getContentPane().add(btn1);
        frm.getContentPane().add(btn2);
        
        frm.getContentPane().add(text1);
        frm.getContentPane().add(text2);
        frm.getContentPane().add(text3);
        
        frm.getContentPane().add(Label1);
        frm.getContentPane().add(Label2);
        frm.getContentPane().add(Label3);
        
        
        frm.getContentPane().add(Label7);
        frm.getContentPane().add(text4);
        frm.getContentPane().add(Label8);
        frm.getContentPane().add(combo4);
        
        frm.getContentPane().add(btn3);
        frm.getContentPane().add(btn4);
 
        // 프레임이 보이도록 설정
        frm.setVisible(true);
        
        
	}
	
	public void prepareDB(){

		 try {

				//driver 등록

				Class.forName("org.mariadb.jdbc.Driver");	//객체 생성


				//connection 얻기

				conn = DriverManager.getConnection(connect, user, passwd);
	

				//statement 얻기

				stat = conn.createStatement();


				prestat1 = conn.prepareStatement("insert into java_table_login(id, pw, email,email2) values(?,?,?,?)");

			} catch (Exception e) {

			}

	}
	
	public Joinmain() {
		Join();
		prepareDB();
		btn4.addActionListener(new ActionListener () {  
			public void actionPerformed(ActionEvent e){

		if(combo4.getSelectedItem().toString() == "선택") {
			JOptionPane.showMessageDialog(null, " 이메일을 확인해주세요");
		} else{
			add_join(e); // DB 저장
			JOptionPane.showMessageDialog(null, "가입 되었습니다!");
			new Login();
			frm.setVisible(false);
		}
		}
		});
		
		
		btn3.addActionListener(new ActionListener () {  
			public void actionPerformed(ActionEvent e){
				new Login();
				frm.setVisible(false);
			}
		});
	}
	
	
public void add_join(ActionEvent e){
		
		try {
			
		String id_up = text1.getText();
		
		String pw_up = text2.getText();

		String pw2_up = text3.getText();
	
		String email_up = text4.getText();
	
		String email2_up = combo4.getSelectedItem().toString();
			
				int index = 1;
				prestat1.setString(index++, id_up);

				prestat1.setString(index++, pw_up);

				prestat1.setString(index++, email_up);
				
				prestat1.setString(index++, email2_up);

			prestat1.executeUpdate();

			
		} catch (SQLException e7) {

			// TODO Auto-generated catch block

			e7.printStackTrace();

		}
	}

}

	

