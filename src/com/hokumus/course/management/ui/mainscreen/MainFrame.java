package com.hokumus.course.management.ui.mainscreen;

import javax.swing.JFrame;

import com.hokumus.course.management.model.kullanici.Role;
import com.hokumus.course.management.ui.kullanici.EnterUser;
import com.hokumus.course.management.ui.kullanici.UserAdd;
import com.hokumus.course.management.ui.yonetim.ProductivityFrame;
import com.hokumus.course.management.util.CourseUtils;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
	private EnterUser user;

	public MainFrame(EnterUser user) {
		this.user = user; // Para metre almayýp giriþ ekranýný ana ekran açýlýrken kapatýp
		// daha sonra ana ekrandan istendiðinde yeni istance yaratýlabilir.
		intialize();
		if (CourseUtils.userRole == Role.ADMIN)
			intialize();
		else if(CourseUtils.userRole == Role.STUDENT)
			intialize_Student();
	}

	private void intialize() {
		setTitle("Dersane Projesi Ana Ekran");
		setBounds(100, 100, 650, 700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDosya = new JMenu("Dosya");
		menuBar.add(mnDosya);

		JMenuItem mnItemStdList = new JMenuItem("\u00D6\u011Frenci Listesi");
		mnDosya.add(mnItemStdList);

		JMenuItem mnItemThrList = new JMenuItem("\u00D6\u011Fretmen Listesi");
		mnDosya.add(mnItemThrList);

		JMenuItem mnItemCrsList = new JMenuItem("Kurs Listesi");
		mnDosya.add(mnItemCrsList);

		JMenu mnSettings = new JMenu("\u0130\u015Flem");
		menuBar.add(mnSettings);

		JMenuItem mnItemStdAdd = new JMenuItem("\u00D6\u011Frenci Ekleme");
		mnSettings.add(mnItemStdAdd);

		JMenuItem mnItemCourseAdd = new JMenuItem("Kurs A\u00E7ma");
		mnSettings.add(mnItemCourseAdd);

		JMenuItem mnItemThrAdd = new JMenuItem("\u00D6\u011Fretmen Ekleme");
		mnSettings.add(mnItemThrAdd);
		
		JMenuItem mnItemUserAdd = new JMenuItem("Kullan\u0131c\u0131 Ekleme");
		mnItemUserAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnItemUserAdd_Action_Performed(e);
			}
		});
		mnSettings.add(mnItemUserAdd);
		
		JMenuItem mnItemProductivity = new JMenuItem("Kursların Verimliliği");
		mnItemProductivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnItemProductivity_Action_Performed(e);
			}
		});
		mnSettings.add(mnItemProductivity);
	}

	protected void mnItemUserAdd_Action_Performed(ActionEvent e) {
		UserAdd temp = new UserAdd();
		temp.setVisible(true);
		
	}
	protected void mnItemProductivity_Action_Performed(ActionEvent e) {
		ProductivityFrame temp = new ProductivityFrame();
		temp.setVisible(true);
	}

	private void intialize_Student() {
		setTitle("Dersane Projesi Ana Ekran");
		setBounds(100, 100, 650, 700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDosya = new JMenu("Dosya");
		menuBar.add(mnDosya);

		JMenuItem mnItemStdList = new JMenuItem("\u00D6\u011Frenci Listesi");
		mnDosya.add(mnItemStdList);

		JMenuItem mnItemThrList = new JMenuItem("\u00D6\u011Fretmen Listesi");
		mnDosya.add(mnItemThrList);

		JMenuItem mnItemCrsList = new JMenuItem("Kurs Listesi");
		mnDosya.add(mnItemCrsList);

		JMenu mnSettings = new JMenu("\u0130\u015Flem");
		menuBar.add(mnSettings);


		
	}
}
