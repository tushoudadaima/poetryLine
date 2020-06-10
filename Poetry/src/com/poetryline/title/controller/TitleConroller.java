package com.poetryline.title.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetryline.entity.Title;
import com.poetryline.title.service.TitleService;

@Controller
@RequestMapping("/title")
public class TitleConroller {
	@Resource
	private TitleService titleService;
	@RequestMapping(value="/getitle")
	@ResponseBody
	public List<Title> findTitles(){
		return this.titleService.getList();
	}
}
