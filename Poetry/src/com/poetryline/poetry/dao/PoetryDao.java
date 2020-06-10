package com.poetryline.poetry.dao;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.poetryline.entity.Poetry;


@Repository
public class PoetryDao {
	@Resource
	private SessionFactory sessionFactory;
	
	public Poetry findName(String content) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Poetry p where p.content like ?");
		query.setParameter(0, "%"+content+"%");
		Poetry poetry = (Poetry) query.uniqueResult();
		return poetry;
	}
	
	//随机取一条数据
	public Poetry findOneByRadom(String key) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Poetry p where p.content like ?");
		query.setParameter(0, "%"+key+"%");
//		Poetry poetry = (Poetry) query.uniqueResult();
		List<Poetry> list = query.list();
		Random random = new Random();
		int radomNum = random.nextInt(list.size());
		return list.get(radomNum);
	}
	
	public Poetry selectSome(int id){
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Poetry p where p.id = ?");
		query.setParameter(0, id);
		Poetry poetry = (Poetry)query.uniqueResult();
		return poetry;
	}
	
	
}
