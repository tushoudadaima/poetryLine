package com.poetryline.poetry.service;

import org.springframework.transaction.annotation.Transactional;

import com.poetryline.entity.Poetry;
import com.poetryline.poetry.dao.PoetryDao;

import javax.annotation.Resource;

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
}
