package com.hokumus.course.management.ui.yonetim;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import com.hokumus.course.management.dao.yonetim.KursDAO;
import com.hokumus.course.management.dao.yonetim.ProductivityDAO;
import com.hokumus.course.management.model.yonetim.KarZarar;
import com.hokumus.course.management.model.yonetim.Kurs;
import com.hokumus.course.management.model.yonetim.Productivity;
import com.hokumus.course.management.ui.kullanici.EnterUser;
import com.hokumus.course.management.ui.mainscreen.MainFrame;
import com.hokumus.course.management.util.CourseUtils;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductivityFrame extends JFrame{
	private Container c =getContentPane();
	private JPanel panel_1;
	private JComboBox cmbCourse;
	private JComboBox cmbKScore;
	private JComboBox cmbTScore;
	private JComboBox cmbSScore;
	private Long courseId;
	private int productivityId;
	protected List<Kurs> liste = null;
	private Productivity productivity;
	private JButton btnUpdate;
	private JButton btnSave;
	private JTextArea txtOfferings ;
	private JTextArea txtCourseV ;
	private JTextArea txtTeacherV;
	private JTextArea txtStudentV;
	private JComboBox cmbProfitLoss;
	private String ekleyen;
	private Date   ekleyenDate;

	
	public ProductivityFrame() {
		intialize();
		getPLCombo() ;
		getCourse();
		getScore();
	}
	
	private void intialize() {
	setTitle("Verimlilik Formu ");
	setBounds(100, 100, 1200, 800);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	c.setLayout(null);
	
	panel_1 = new JPanel();
	panel_1.setBackground(Color.LIGHT_GRAY);
	panel_1.setBounds(12, 5, 1158, 53);
	getContentPane().add(panel_1);
	panel_1.setLayout(null);
	
	JLabel lblKursunAd = new JLabel("Kursun Adı :");
	lblKursunAd.setForeground(Color.RED);
	lblKursunAd.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblKursunAd.setBounds(12, 13, 119, 25);
	panel_1.add(lblKursunAd);
	
	cmbCourse = new JComboBox();
	cmbCourse.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			formuTemizle() ;
			courseId=Long.valueOf(cmbCourse.getSelectedItem().toString().substring(0,1));
			getProductivity(courseId);		
		}
	});
	
	
	cmbCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	cmbCourse.setBounds(170, 14, 350, 25);
	panel_1.add(cmbCourse);
	
	JLabel lblKarZarar = new JLabel("Kar / Zarar Durumu :");
	lblKarZarar.setForeground(Color.RED);
	lblKarZarar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblKarZarar.setBounds(582, 13, 148, 25);
	panel_1.add(lblKarZarar);
	
	cmbProfitLoss = new JComboBox();
	cmbProfitLoss.setFont(new Font("Tahoma", Font.PLAIN, 15));
	cmbProfitLoss.setBounds(742, 15, 166, 25);
	panel_1.add(cmbProfitLoss);
	
	JButton btnExcel = new JButton("TÜM KAYITLARIN RAPORU");
	btnExcel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ProductivityExcel m=new ProductivityExcel();
			JOptionPane.showMessageDialog(ProductivityFrame.this, "Excel Hazırlandı !");
		}
	});
	btnExcel.setBounds(948, 14, 186, 25);
	panel_1.add(btnExcel);
	
	JPanel panel_2 = new JPanel();
	panel_2.setBackground(Color.LIGHT_GRAY);
	panel_2.setBounds(12, 100, 1158, 624);
	getContentPane().add(panel_2);
	panel_2.setLayout(null);
	
	JLabel lblCourseV = new JLabel("Kursun Verimliliği");
	lblCourseV.setForeground(Color.RED);
	lblCourseV.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblCourseV.setBounds(12, 98, 146, 25);
	panel_2.add(lblCourseV);
	
	txtCourseV = new JTextArea();
	txtCourseV.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtCourseV.setBounds(170, 28, 350, 150);
	panel_2.add(txtCourseV);
	
	JLabel lbTeacherV = new JLabel("Öğretmen Verimliliği");
	lbTeacherV.setForeground(Color.RED);
	lbTeacherV.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lbTeacherV.setBounds(12, 261, 146, 25);
	panel_2.add(lbTeacherV);
	
	txtTeacherV = new JTextArea();
	txtTeacherV.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtTeacherV.setBounds(170, 209, 350, 150);
	panel_2.add(txtTeacherV);
	
	JLabel lblStudentV = new JLabel("Öğrencilerin Verimliliği");
	lblStudentV.setForeground(Color.RED);
	lblStudentV.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblStudentV.setBounds(12, 447, 146, 25);
	panel_2.add(lblStudentV);
	
	txtStudentV = new JTextArea();
	txtStudentV.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtStudentV.setBounds(170, 395, 350, 150);
	panel_2.add(txtStudentV);
	
	JLabel lblPuanlar = new JLabel("Puanları ");
	lblPuanlar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblPuanlar.setForeground(Color.RED);
	lblPuanlar.setBounds(571, 0, 56, 25);
	panel_2.add(lblPuanlar);
	
	JLabel lblneriler = new JLabel("Öneriler");
	lblneriler.setForeground(Color.RED);
	lblneriler.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblneriler.setBounds(835, 4, 56, 16);
	panel_2.add(lblneriler);
	
	txtOfferings = new JTextArea();
	txtOfferings.setFont(new Font("Tahoma", Font.PLAIN, 15));
	txtOfferings.setBounds(724, 28, 322, 447);
	panel_2.add(txtOfferings);
	
	JButton btnNewButton_1 = new JButton("Menüye Dön");
//	btnNewButton_1.addActionListener(new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			MainFrame mf = new MainFrame(ProductivityFrame.this);
//			mf.setVisible(true);
//			ProductivityFrame.this.setVisible(false);
//		}
//	});
	btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnNewButton_1.setBounds(906, 520, 140, 25);
	panel_2.add(btnNewButton_1);
	
	cmbKScore = new JComboBox();
	cmbKScore.setFont(new Font("Tahoma", Font.PLAIN, 15));
	cmbKScore.setBounds(571, 100, 80, 25);
	panel_2.add(cmbKScore);
	
	cmbTScore = new JComboBox();
	cmbTScore.setFont(new Font("Tahoma", Font.PLAIN, 15));
	cmbTScore.setBounds(571, 263, 80, 25);
	panel_2.add(cmbTScore);
	
	cmbSScore = new JComboBox();
	cmbSScore.setFont(new Font("Tahoma", Font.PLAIN, 15));
	cmbSScore.setBounds(571, 450, 80, 25);
	panel_2.add(cmbSScore);
	
	btnSave = new JButton("Kaydet");
	btnSave.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				saveProductivity();
				JOptionPane.showMessageDialog(ProductivityFrame.this,"Kayıt İşlemi Gerçekleşti ");
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(ProductivityFrame.this,"Kayıt İşleminde HATA var !!!! ");
			}
		}
	});
	btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnSave.setBounds(724, 520, 140, 25);
	panel_2.add(btnSave);
	
	btnUpdate = new JButton("Güncelle");
	btnUpdate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				updateProductivity();
				JOptionPane.showMessageDialog(ProductivityFrame.this,"Güncelleme İşlemi Gerçekleşti ");
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(ProductivityFrame.this,"Güncelleme İşleminde HATA var !!!! ");
			}
			 
		}
	});
	btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnUpdate.setBounds(724, 520, 140, 25);
	btnUpdate.setVisible(false);
	panel_2.add(btnUpdate);
	
	}
	
	protected void getCourse() {
		try {
			KursDAO kdao = new KursDAO();
			liste = kdao.tumKayitlariGetir(new Kurs());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int s=liste.size();
		String[] listeKurs=new String[s+1];
		if(liste.size()>0) {
			listeKurs[0]="KURS SEÇİNİZ ";
			for (int i = 0; i <liste.size(); i++) {
				listeKurs[i+1]=liste.get(i).getId()+";"+liste.get(i).getAdi();
			}	
		}
		
	    cmbCourse.setModel(new DefaultComboBoxModel(listeKurs));
		}
	
	protected void getScore() {
		Integer[] score= {0,10,9,8,7,6,5,4,3,2,1};
		cmbKScore.setModel(new DefaultComboBoxModel(score));
		cmbTScore.setModel(new DefaultComboBoxModel(score));
		cmbSScore.setModel(new DefaultComboBoxModel(score));
	}
	
	
	private void getPLCombo() {
		cmbProfitLoss.setModel(new DefaultComboBoxModel(KarZarar.values()));
	 }
	 
	protected List<Productivity> getProductivity(Long courseId) {
		List<Productivity> productivityList = null;
		try {
			productivity=new Productivity();
		    Kurs kurs =new Kurs();
		    kurs.setId(courseId);
			productivity.setKurs(kurs);
			ProductivityDAO pdao =new ProductivityDAO();
			productivityList = pdao.kayitIdAra(productivity);
			if(productivityList.size()>0) {
				productivityId=productivityList.get(0).getId();
				btnUpdate.setVisible(true);
				btnSave.setVisible(false);
				cmbProfitLoss.setSelectedItem(productivityList.get(0).getKarZarar());
				txtCourseV.setText(productivityList.get(0).getKursVerimlilik());
				txtTeacherV.setText(productivityList.get(0).getOrgetmenVerimlilik());
				txtStudentV.setText(productivityList.get(0).getOgrenciVerimlilik());
				cmbKScore.setSelectedItem(productivityList.get(0).getKursVNotu());
				cmbTScore.setSelectedItem(productivityList.get(0).getOrgetmenVNotu());
				cmbSScore.setSelectedItem(productivityList.get(0).getOgrenciVNotu());
				txtOfferings.setText(productivityList.get(0).getOneriler());
				ekleyen=productivityList.get(0).getEkleyen();
				ekleyenDate=productivityList.get(0).getEklemeTarihi();
				
			}
			else if(productivityList.size()==0) 
			{
				btnUpdate.setVisible(false);
				btnSave.setVisible(true);
			}
		} catch (Exception e) {
			
			btnUpdate.setVisible(false);
			btnSave.setVisible(true);
			e.printStackTrace();
			
		}
		return productivityList;
	}
	
	private void saveProductivity() throws Exception {
		ProductivityDAO pdao =new ProductivityDAO();
		productivity=new Productivity();
		Kurs kurs=new Kurs(); 
		kurs.setId(courseId);
		productivity.setKurs(kurs);
		productivity.setEklemeTarihi(new Date());
		productivity.setEkleyen(CourseUtils.userName);
		productivity.setKarZarar((KarZarar)cmbProfitLoss.getSelectedItem());
		productivity.setKursVerimlilik(txtCourseV.getText());
		productivity.setOrgetmenVerimlilik(txtTeacherV.getText());
		productivity.setOgrenciVerimlilik(txtStudentV.getText());
		productivity.setKursVNotu((Integer)cmbKScore.getSelectedItem());
		productivity.setOrgetmenVNotu((Integer)cmbTScore.getSelectedItem());
		productivity.setOgrenciVNotu((Integer)cmbSScore.getSelectedItem());
		productivity.setOneriler(txtOfferings.getText());
		productivity.setKayitDurumu(true);
		pdao.kaydet(productivity);
		
	}
	
	private void updateProductivity() throws Exception {
		ProductivityDAO pdao =new ProductivityDAO();
		productivity=new Productivity();
		Kurs kurs=new Kurs(); 
		kurs.setId(courseId);
		productivity.setId(productivityId);
		productivity.setKurs(kurs);
		productivity.setGuncellemeTarihi(new Date());
		productivity.setGuncelleyen(CourseUtils.userName);
		productivity.setKarZarar((KarZarar)cmbProfitLoss.getSelectedItem());
		productivity.setKursVerimlilik(txtCourseV.getText());
		productivity.setOrgetmenVerimlilik(txtTeacherV.getText());
		productivity.setOgrenciVerimlilik(txtStudentV.getText());
		productivity.setKursVNotu((Integer)cmbKScore.getSelectedItem());
		productivity.setOrgetmenVNotu((Integer)cmbTScore.getSelectedItem());
		productivity.setOgrenciVNotu((Integer)cmbSScore.getSelectedItem());
		productivity.setOneriler(txtOfferings.getText());
		productivity.setKayitDurumu(true);
		productivity.setEkleyen(ekleyen);
		productivity.setEklemeTarihi(ekleyenDate);
		pdao.guncelle(productivity);
		
	}
	private void formuTemizle() 
	{   
		getPLCombo();
	    getScore() ;
		txtCourseV.setText("");
		txtTeacherV.setText("");
		txtStudentV.setText("");
		txtOfferings.setText("");
	}
}
