package com.example.web1.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.common.controller.HtmlBaseController;
import com.example.common.entity.demo.Sample;
import com.example.common.enumeration.Flg;
import com.example.common.enumeration.OrderDirection;
import com.example.common.search.demo.SampleSearch;
import com.example.common.service.demo.SampleService;
import com.example.common.util.DeviceUtil;
import com.example.web1.validator.sample.SearchValidator;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("html/sample")
public class SampleHtmlController extends HtmlBaseController {

    @PersistenceContext(unitName = "demoEntityManager")
    private EntityManager entityManager;
    
    @Autowired
    SampleService sampleService;
    
	public SampleHtmlController() {
	}

	@PostConstruct
	public void init() {
		this.sampleService.init(this.entityManager);
	}

	@InitBinder
	public void initBinder(HttpServletRequest request, WebDataBinder binder) {

		String path = request.getServletPath();
		
		if ("/html/sample/find".equals(path)) {
			binder.addValidators((SearchValidator) context.getBean(SearchValidator.class));
		}
		
	}

    @Transactional(readOnly = true)
	@RequestMapping(path={"", "/"})
	public String indexAction(HttpServletRequest request, @ModelAttribute("form") SampleSearch form, Model model, Locale locale) throws Exception {
		
    	Pageable paging = PageRequest.of(0, 50);
    	
		Page<Sample> page = this.sampleService.getPage(paging);
		model.addAttribute("form", form);
		model.addAttribute("page", page);
		model.addAttribute("flgClass", Flg.class);
		model.addAttribute("flgList", Flg.values());
		model.addAttribute("orderList", OrderDirection.values());
		return DeviceUtil.getTplName(this.appName, "sample", "index");
		
	}

    @Transactional(readOnly = true)
	@RequestMapping(path={"/create", "/create/"})
	public String createAction(HttpServletRequest request, @ModelAttribute("form") Sample form, Model model, Locale locale) throws Exception {

		model.addAttribute("flgList", Flg.values());
		return DeviceUtil.getTplName(this.appName, "sample", "create");
		
	}

    @Transactional(readOnly = true)
	@RequestMapping(path={"/edit/{id}", "/edit/{id}/"})
	public String editAction(HttpServletRequest request, @PathVariable("id") long id, @ModelAttribute("form") Sample form, Model model, Locale locale) throws Exception {

		
		Sample entity = this.sampleService.findById(id);
		model.addAttribute("form", entity);
		model.addAttribute("flgList", Flg.values());
		return DeviceUtil.getTplName(this.appName, "sample", "edit");
		
	}

    @Transactional(readOnly = true)
	@RequestMapping(path={"/show/{id}", "/show/{id}/"})
	public String showAction(HttpServletRequest request, @PathVariable("id") long id, @ModelAttribute("form") Sample form, Model model, Locale locale) throws Exception {

		
		Sample entity = this.sampleService.findById(id);
		model.addAttribute("form", entity);
		model.addAttribute("flgClass", Flg.class);
		return DeviceUtil.getTplName(this.appName, "sample", "show");
		
	}

    @Transactional(readOnly = true)
	@RequestMapping(path={"/find", "/find/"})
	public String findAction(HttpServletRequest request, Pageable pageable, @ModelAttribute("form") @Valid SampleSearch form, BindingResult result, Model model, Locale locale) throws Exception {

    	Page<Sample> page = null;
		if (result.hasErrors()) {
	    	Pageable paging = PageRequest.of(0, 50);
			page = this.sampleService.getPage(paging);
		} else {
	    	page = this.sampleService.searchSample(form);
		}
    	
		model.addAttribute("form", form);
		model.addAttribute("page", page);
		model.addAttribute("flgClass", Flg.class);
		model.addAttribute("flgList", Flg.values());
		model.addAttribute("orderList", OrderDirection.values());
		return DeviceUtil.getTplName(this.appName, "sample", "index");
		
	}

}