package com.hokumus.course.management.ui;

import java.util.Date;
import java.util.List;

import com.hokumus.course.management.dao.kullanici.UsersDAO;
import com.hokumus.course.management.model.kullanici.Role;
import com.hokumus.course.management.model.kullanici.Users;
import com.hokumus.course.management.ui.kullanici.EnterUser;
import com.hokumus.course.management.ui.yonetim.ProductivityFrame;

public class Runner {
	
	public static void main(String[] args) {
		UsersDAO dao = new UsersDAO();
		List<Users> liste = null;
		try {
			liste = dao.tumKayitlariGetir(new Users());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(liste.size()<=0) {
			Users usr = new Users();
			usr.setUserName("hokumus");
			usr.setPassword("123");
			usr.setName("hüseyin");
			usr.setEmail("hsklþfdlþflþ@dsfkdlþfsdk.com");
			usr.setRdate(new Date());
			usr.setRole(Role.ADMIN);
			usr.setSurname("OKUMUŞ");
			
			try {
				dao.kaydet(usr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				new EnterUser().setVisible(true);
			}
		}else {
			new EnterUser().setVisible(true);
		}
		
		
	}

}
