package com.poetryline.record.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.poetryline.entity.Record;

@Repository
public class RecordDao {
	@Resource
	private SessionFactory sessionFactory;
	
	public void insertRecord(String date,String userName,String phone,String type,int score) {
		Session session = this.sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		Record record = new Record(date,userName,phone,type,score);
		session.save(record);
		tc.commit();
	}
	
	public List<Record> showRecord(String phone){
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Record where phone = ?");
		query.setParameter(0, phone);
		List<Record> records = query.list();
		return records;
	}

}
