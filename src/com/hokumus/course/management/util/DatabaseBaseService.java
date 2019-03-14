/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hokumus.course.management.util;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vektorel
 */
public class DatabaseBaseService<T> implements IDatabase<T> {

	private Session ss;
	private Transaction tt;

	private void baglantiAc() {
		ss = HBUtil.getSessionFactory().openSession();
		tt = ss.beginTransaction();
	}

	private void baglantiKapat() {
		tt.commit();
		ss.close();
	}

	@Override
	public boolean kaydet(T temp) throws Exception {
		baglantiAc();
		ss.save(temp);
		baglantiKapat();
		return true;

	}

	@Override
	public boolean guncelle(T temp) throws Exception {
		baglantiAc();
		ss.update(temp);
		baglantiKapat();
		return true;
	}

	@Override
	public boolean sil(T temp) throws Exception {
		baglantiAc();
		ss.delete(temp);
		baglantiKapat();
		return true;
	}

	@Override
	public List<T> tumKayitlariGetir(T temp) throws Exception {
		baglantiAc();
		Criteria cr = ss.createCriteria(temp.getClass());
		// cr.addOrder(Order.asc("id"));
		List<T> liste = cr.list();
		baglantiKapat();
		return liste;

	}

	@Override
	public T kayitBul(int id, T temp) {
		baglantiAc();
		Criteria cr = ss.createCriteria(temp.getClass());
		cr.add(Restrictions.eq("id", id));
		T instance = (T) cr.uniqueResult();
		baglantiKapat();
		return instance;
	}

	@Override
	public List<T> kayitAra(String kolonadi, String aranan, T temp) {
		baglantiAc();
		Criteria cr = ss.createCriteria(temp.getClass());
		cr.add(Restrictions.ilike(kolonadi, "'%" + aranan + "%'"));
		List<T> liste = cr.list();
		baglantiKapat();
		return liste;
	}

	@Override
	public List<T> kayitAra(T temp) throws Exception {
		List<T> listem = null;

		Class cl = temp.getClass();
		Field[] fl = cl.getDeclaredFields();

		baglantiAc();
		Criteria cr = ss.createCriteria(temp.getClass());
		for (int i = 0; i < fl.length; i++) {
			fl[i].setAccessible(true);
			if (fl[i].get(temp) != null && !fl[i].get(temp).toString().equals("0")) {

				cr.add(Restrictions.ilike(fl[i].getName(), "%" + fl[i].get(temp) + "%"));

			}
		}
		listem = cr.list();
		return listem;
	}
	
	@Override
	public List<T> kayitIdAra(T temp) throws Exception {
		List<T> listem = null;

		Class cl = temp.getClass();
		Field[] fl = cl.getDeclaredFields();

		baglantiAc();
		Criteria cr = ss.createCriteria(temp.getClass());
		for (int i = 0; i < fl.length; i++) {
			fl[i].setAccessible(true);
				if (fl[i].get(temp) != null && !fl[i].get(temp).toString().equals("0")) {
				cr.add(Restrictions.eq(fl[i].getName(), fl[i].get(temp)));

			}
		}
		listem = cr.list();
		return listem;
	}

	@Override
	public T kullaniciBul(T temp) throws Exception {
		
		Class cl = temp.getClass();
		if (cl.getName().equals("com.hokumus.course.management.model.kullanici.Users")) {
			Field[] fl = cl.getDeclaredFields();
			baglantiAc();
			Criteria cr = ss.createCriteria(temp.getClass());
			for (int i = 0; i < fl.length; i++) {
				fl[i].setAccessible(true);
				if (fl[i].getName().equals("userName") || fl[i].getName().equals("password")) {
					if (fl[i].get(temp) != null && !fl[i].get(temp).toString().equals("0")) {
						cr.add(Restrictions.eq(fl[i].getName(),  fl[i].get(temp) ));

					}
				}

			}
			return (T)cr.uniqueResult();
			
		}
		else {
			return null;
		}

		
	}

}
