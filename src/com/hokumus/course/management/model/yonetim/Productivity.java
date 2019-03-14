package com.hokumus.course.management.model.yonetim;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.hokumus.course.management.model.base.BaseEntity;


@Entity
@Table(name = "productivity")
public class Productivity extends BaseEntity{
	private int    id;
   	private Kurs    kurs;
    private String  kursVerimlilik;
    private String  orgetmenVerimlilik;
    private String  ogrenciVerimlilik;
    private Integer kursVNotu;
    private Integer orgetmenVNotu;
    private Integer ogrenciVNotu;
    private KarZarar karZarar;
    private String  oneriler;

    @Id
    @SequenceGenerator(name = "seq_productivity", allocationSize = 1, sequenceName = "seq_productivity")
    @GeneratedValue(generator = "seq_productivity", strategy = GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @OneToOne
	@JoinColumn(name = "course_id")
	public Kurs getKurs() {
		return kurs;
	}

	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
	}
	
	@Column(name = "courseProductivity", length = 1000)
	public String getKursVerimlilik() {
		return kursVerimlilik;
	}

	public void setKursVerimlilik(String kursVerimlilik) {
		this.kursVerimlilik = kursVerimlilik;
	}
	
	@Column(name = "teacherProductivity", length = 1000)
	public String getOrgetmenVerimlilik() {
		return orgetmenVerimlilik;
	}

	public void setOrgetmenVerimlilik(String orgetmenVerimlilik) {
		this.orgetmenVerimlilik = orgetmenVerimlilik;
	}
	
	@Column(name = "studentProductivity", length = 1000)
	public String getOgrenciVerimlilik() {
		return ogrenciVerimlilik;
	}

	public void setOgrenciVerimlilik(String ogrenciVerimlilik) {
		this.ogrenciVerimlilik = ogrenciVerimlilik;
	}
	@Column(name = "courseScore")
	public Integer getKursVNotu() {
		return kursVNotu;
	}
	public void setKursVNotu(Integer kursVNotu) {
		this.kursVNotu = kursVNotu;
	}
	@Column(name = "teacherScore")
	public Integer getOrgetmenVNotu() {
		return orgetmenVNotu;
	}
	public void setOrgetmenVNotu(Integer orgetmenVNotu) {
		this.orgetmenVNotu = orgetmenVNotu;
	}
	@Column(name = "studentScore")
	public Integer getOgrenciVNotu() {
		return ogrenciVNotu;
	}
	public void setOgrenciVNotu(Integer ogrenciVNotu) {
		this.ogrenciVNotu = ogrenciVNotu;
	}
	
	@Column(name = "profitLoss")
	public KarZarar getKarZarar() {
		return karZarar;
	}
	public void setKarZarar(KarZarar karZarar) {
		this.karZarar = karZarar;
	}
	
	@Column(name = "offerings", length = 1000)
	public String getOneriler() {
		return oneriler;
	}
	public void setOneriler(String oneriler) {
		this.oneriler = oneriler;
	}

	

}
