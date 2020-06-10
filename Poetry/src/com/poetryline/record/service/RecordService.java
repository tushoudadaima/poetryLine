package com.poetryline.record.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.poetryline.entity.Record;
import com.poetryline.record.dao.RecordDao;

@Service
public class RecordService {
	@Resource
	private RecordDao recordDao;
	public void insertRecord(String date, String userName, String phone, String type, int score) {
		this.recordDao.insertRecord(date, userName, phone, type, score);
	}
	
	public List<Record> showRecords(String phone){
		return this.recordDao.showRecord(phone);
	}

}
