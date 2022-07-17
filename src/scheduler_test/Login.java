package scheduler_test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JFrame{
	
	// 프레임 생성
    JFrame frm = new JFrame();
	
	JLabel Label = new JLabel("To-Do List");
    JLabel Label1 = new JLabel("아이디");
    JLabel Label2 = new JLabel("비밀번호");
    JTextField text1 = new JTextField(25);
    JTextField text2 = new JTextField(25);
    JButton btn1 = new JButton("로그인");
    JButton btn2 = new JButton("회원가입");
	
	public Login() { 
 
        // 프레임 크기 설정
        frm.setSize(500, 400);
 
        // 프레임을 화면 가운데에 배치
        frm.setLocationRelativeTo(null);
 
        // 프레임을 닫았을 때 메모리에서 제거되도록 설정
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // 레이아웃 설정
        frm.getContentPane().setLayout(null);
        
        Label.setBounds(200, 30, 300, 40);
        Label.setFont(new Font("Serif", Font.BOLD, 30));
 
        Label1.setBounds(100, 100, 200, 30);
        Label2.setBounds(100, 150, 200, 30);
        text1.setBounds(170, 100, 200, 30);
        text2.setBounds(170, 150, 200, 30);
        btn1.setBounds(170, 200, 200, 40);
        btn2.setBounds(170, 250, 200, 40);
        
        frm.getContentPane().add(Label);
 
        frm.getContentPane().add(btn1);
        frm.getContentPane().add(btn2);
        frm.getContentPane().add(text1);
        frm.getContentPane().add(text2);
        frm.getContentPane().add(Label1);
        frm.getContentPane().add(Label2);
 
        // 프레임이 보이도록 설정
        frm.setVisible(true);
        
        btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Calendarmain();
                frm.setVisible(false); // 창 안보이게 하기 
			}
	});
        
        btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Joinmain();
                frm.setVisible(false); // 창 안보이게 하기 
			}
	});
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

}
