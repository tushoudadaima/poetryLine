package com.poetryline.poetry.service;

import org.springframework.transaction.annotation.Transactional;

import com.poetryline.entity.Poetry;
import com.poetryline.poetry.dao.PoetryDao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly=false)
public class PoetryService {
	@Autowired
	private PoetryDao poetryDao;
	
	public Poetry find(String content) {
		return this.poetryDao.findName(content);
	}
	
	public Poetry findOneRadom(String key) {
		return this.poetryDao.findOneByRadom(key);
	}
	
	public List<Poetry> findBySome(String start){
		List<Poetry> lists = new ArrayList<Poetry>();
		for(int i = Integer.parseInt(start); i <= 200010; i += 10000) {
			Poetry poetry = this.poetryDao.selectSome(i);
			lists.add(poetry);
		}
		return lists;
	}
}
