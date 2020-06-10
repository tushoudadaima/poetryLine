package com.poetryline.title.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.poetryline.entity.Title;

@Repository
public class TitleDao {
	@Resource
	private SessionFactory sessionFactory;
	
	public List<Title> findList(){
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Title");
		Random random = new Random();
		List<Title> list = new ArrayList<Title>();
		List<Title> titles = query.list();
		for (int i = 0;i < 10;i++) {
			list.add(titles.get(random.nextInt(titles.size())));
		}
		return list;
	}
}
