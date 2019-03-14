package com.hokumus.course.management.ui.kullanici;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.hokumus.course.management.dao.kullanici.UsersDAO;
import com.hokumus.course.management.model.kullanici.Role;
import com.hokumus.course.management.model.kullanici.Users;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class UserAdd extends JFrame {
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JTextField txtName;
	private JTextField txtSurName;
	private JComboBox cmbRole;

	public UserAdd() {
		initialize();
	}

	private void initialize() {
		setTitle("Dersane Projesi Kullan�c� Ekleme");
		setBounds(100, 100, 450, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblNewLabel.setBounds(33, 38, 75, 14);
		getContentPane().add(lblNewLabel);

		txtUserName = new JTextField();
		txtUserName.setBounds(130, 35, 169, 20);
		getContentPane().add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblifre = new JLabel("\u015Eifre");
		lblifre.setBounds(33, 69, 75, 14);
		getContentPane().add(lblifre);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(130, 66, 169, 20);
		getContentPane().add(txtPassword);

		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(33, 100, 75, 14);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(130, 97, 169, 20);
		getContentPane().add(txtEmail);

		JLabel lblAd = new JLabel("Ad\u0131");
		lblAd.setBounds(33, 141, 75, 14);
		getContentPane().add(lblAd);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(130, 138, 169, 20);
		getContentPane().add(txtName);

		JLabel lblSoyad = new JLabel("Soyad\u0131");
		lblSoyad.setBounds(33, 181, 75, 14);
		getContentPane().add(lblSoyad);

		txtSurName = new JTextField();
		txtSurName.setColumns(10);
		txtSurName.setBounds(130, 178, 169, 20);
		getContentPane().add(txtSurName);

		JLabel lblNewLabel_1 = new JLabel("Yetki");
		lblNewLabel_1.setBounds(33, 221, 75, 14);
		getContentPane().add(lblNewLabel_1);

		DefaultComboBoxModel model = new DefaultComboBoxModel(Role.values());
		cmbRole = new JComboBox<>(model);
		cmbRole.setBounds(130, 217, 169, 22);
		getContentPane().add(cmbRole);

		JButton btnCancel = new JButton("\u0130ptal");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAdd.this.dispose();
			}
		});
		btnCancel.setBounds(33, 273, 91, 23);
		getContentPane().add(btnCancel);

		JButton btnInsert = new JButton("Kaydet");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUserAdd_Action_Performed(e);
			}
		});
		btnInsert.setBounds(208, 273, 91, 23);
		getContentPane().add(btnInsert);

	}

	protected void btnUserAdd_Action_Performed(ActionEvent e) {

		UsersDAO dao = new UsersDAO();
		Users usr = new Users();
		usr.setUserName(txtUserName.getText());
		usr.setPassword(txtPassword.getText());
		usr.setName(txtName.getText());
		usr.setEmail(txtEmail.getText());
		usr.setRdate(new Date());
		usr.setRole((Role) cmbRole.getSelectedItem());
		usr.setSurname(txtSurName.getText());
		try {
			dao.kaydet(usr);
			JOptionPane.showMessageDialog(UserAdd.this, "Kayıt Başarılı...");

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(UserAdd.this, "Kayıt Başarısız oldu...!!!");
			e1.printStackTrace();
		}
	}
}
