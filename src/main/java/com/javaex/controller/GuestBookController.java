package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestBookController {
	
	@Autowired
	private GuestBookDao gbDao;

	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("controller>list");
		
		List<GuestBookVo> gbList = gbDao.showList();
		
		model.addAttribute("gbList", gbList);
		
		return "addList";
	}
	
	
	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestBookVo gbVo) {
		System.out.println("controller>add");
		
		gbDao.add(gbVo);
		
		return "redirect:/list";
	}
	
	
	@RequestMapping(value="/deleteForm/{no}", method={RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable("no") int no, Model model) {
		System.out.println("controller>deleteform");
		
		model.addAttribute("no", no);		
		return "deleteForm";
	}
	
	
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestBookVo gbVo) {
		System.out.println("controller>delete");
		
		int no = gbDao.find(gbVo);
		if (no != -1) gbDao.delete(gbVo.no);
				
		return "redirect:/list";
	}

}
