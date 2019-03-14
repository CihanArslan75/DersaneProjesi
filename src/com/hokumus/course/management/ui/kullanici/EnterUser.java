package com.hokumus.course.management.ui.kullanici;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.hokumus.course.management.dao.kullanici.UsersDAO;
import com.hokumus.course.management.model.kullanici.Users;
import com.hokumus.course.management.ui.mainscreen.MainFrame;
import com.hokumus.course.management.ui.yonetim.ProductivityFrame;
import com.hokumus.course.management.util.CourseUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class EnterUser extends JFrame {
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JButton btnEnter;
	private JButton btnCancel;
	private JLabel lblUyari;
	
	public EnterUser() {
		intialize();
	}

	private void intialize() {
		setTitle("Kullanıcı Giriş Ekranı");
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblNewLabel.setBounds(23, 42, 101, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u015Eifre");
		lblNewLabel_1.setBounds(23, 73, 101, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtUserName = new JTextField("hokumus");
		txtUserName.setBounds(146, 42, 110, 20);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JTextField("123");
		txtPassword.setBounds(146, 70, 110, 20);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		btnEnter = new JButton("Giri\u015F");
		btnEnter.setBounds(179, 111, 77, 23);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEnter_Action_Performed(e);
			}
		});
		getContentPane().add(btnEnter);
		
		btnCancel = new JButton("\u0130ptal");
		btnCancel.setBounds(48, 111, 86, 23);
		getContentPane().add(btnCancel);
		
		lblUyari = new JLabel("Uyari");		
		lblUyari.setBounds(0, 193, 292, 30);
		
		getContentPane().add(lblUyari);
		
		
	}

	protected void btnEnter_Action_Performed(ActionEvent e) {
		UsersDAO dao = new UsersDAO();
		Users usr = new Users();
		usr.setUserName(txtUserName.getText());
		usr.setPassword(txtPassword.getText());
		try {
			Users temp = dao.kullaniciBul(usr);
			if(temp==null) {
				lblUyari.setText("Girdi�iniz Kullan�c� Bulunamad�...!");
			}
			else  {
				/// yeni ekranı açtır;
				CourseUtils.userName=temp.getUserName();
				CourseUtils.userRole=temp.getRole();
				MainFrame mf = new MainFrame(EnterUser.this);
				mf.setVisible(true);
				EnterUser.this.setVisible(false);
				
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
