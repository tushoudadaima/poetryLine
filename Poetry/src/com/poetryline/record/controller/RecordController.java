package com.poetryline.record.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetryline.entity.Record;
import com.poetryline.record.service.RecordService;

@Controller
@RequestMapping("/record")
public class RecordController {
	@Resource
	private RecordService recordService;
	@RequestMapping(value="/insert/{date}/{userName}/{phone}/{type}/{score}")
	@ResponseBody
	public void insert(@PathVariable(value="date")String date, @PathVariable(value="userName")String userName, @PathVariable(value="phone")String phone, 
			@PathVariable(value="type")String type, @PathVariable(value="score")int score) {
		this.recordService.insertRecord(date, userName, phone, type, score);
	}
	
	@RequestMapping(value="/show/{phone}")
	@ResponseBody
	public List<Record> showRecord(@PathVariable(value="phone")String phone){
		return this.recordService.showRecords(phone);
	}
}
