package com.poetryline.poetry.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poetryline.entity.Poetry;
import com.poetryline.poetry.service.PoetryService;

@Controller
@RequestMapping("/poetry")
public class PoetryController {
	@Resource
	private PoetryService poetryService;
	@RequestMapping(value = "/getone/{content}")
	@ResponseBody
	public Poetry findOne(@PathVariable(value="content")String content) {
		Poetry poetry = this.poetryService.find(content);
		return poetry;
	}
	
	@RequestMapping(value = "/getradom/{key}")
	@ResponseBody
	public Poetry findRadom(@PathVariable(value="key")String key) {
		Poetry poetry = this.poetryService.findOneRadom(key);
		return poetry;
	}
}

