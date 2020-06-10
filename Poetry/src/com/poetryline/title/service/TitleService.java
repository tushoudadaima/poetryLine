package com.poetryline.title.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poetryline.entity.Title;
import com.poetryline.title.dao.TitleDao;

@Service
@Transactional(readOnly = false)
public class TitleService {
	@Autowired
	private TitleDao titleDao;
	public List<Title> getList(){
		return this.titleDao.findList();
	}
}
