package com.example.web1.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.controller.RestBaseController;
import com.example.common.dto.demo.SampleForListDto;
import com.example.common.entity.demo.Sample;
import com.example.common.exception.DemoException;
import com.example.common.group.Delete;
import com.example.common.group.Insert;
import com.example.common.group.Update;
import com.example.common.msg.Message;
import com.example.common.service.demo.SampleService;
import com.example.web1.validator.sample.DeleteValidator;
import com.example.web1.validator.sample.InsertValidator;
import com.example.web1.validator.sample.UpdateValidator;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("rest/sample")
public class SampleRestController extends RestBaseController {

    @PersistenceContext(unitName = "demoEntityManager")
    private EntityManager entityManager;

    @Autowired
    SampleService sampleService;

	public SampleRestController() {
	}

	@PostConstruct
	public void init() {
		this.sampleService.init(this.entityManager);
		this.fieldMsgCodeHead = "table.sample.col.";
	}
	
	@InitBinder
	public void initBinder(HttpServletRequest request, WebDataBinder binder) {

		String path = request.getServletPath();
		
		if ("/rest/sample/insert".equals(path)) {
			binder.addValidators((InsertValidator) context.getBean(InsertValidator.class));
		} else if ("/rest/sample/update".equals(path)) {
			this.begin();
			binder.addValidators((UpdateValidator) context.getBean(UpdateValidator.class));
		} else if ("/rest/sample/delete".equals(path)) {
			this.begin();
			binder.addValidators((DeleteValidator) context.getBean(DeleteValidator.class));
		}
		
	}

    @Transactional(readOnly = true)
	@RequestMapping()
    @ResponseBody
	public List<SampleForListDto> indexAction(HttpServletRequest request, Model model, Locale locale) throws Exception {
		
    	List<SampleForListDto> list = this.sampleService.getList();
		return list;
	}

    @Transactional(readOnly = true)
	@RequestMapping(path="/create")
    @ResponseBody
	public String createAction(HttpServletRequest request, @ModelAttribute Sample form, Model model, Locale locale) throws Exception {

		return null;
	}

    @Transactional(readOnly = false, rollbackFor=Exception.class)
	@PostMapping(path="/insert")
    @ResponseBody
	public Message insertAction(HttpServletRequest request, @ModelAttribute @Validated(Insert.class) Sample entity, BindingResult result, Model model, Locale locale) throws Exception {

		if (result.hasErrors()) {
			throw new DemoException(result.getGlobalErrors(), result.getFieldErrors());
		}
    	
    	this.sampleService.insert(entity);
    	
		return Message.createMsg().addGlobalMsg(messageSource.getMessage("app.success.insert", null, "It was registered", request.getLocale()));
	}

    @Transactional(readOnly = true)
	@RequestMapping(path="/edit/{id}")
    @ResponseBody
	public SampleForListDto editAction(HttpServletRequest request, @PathVariable("id") long id, Model model, Locale locale) throws Exception {

    	SampleForListDto entity = this.sampleService.getDto(id);

		return entity;
	}

	@RequestMapping(path="/update")
    @ResponseBody
	public Message updateAction(HttpServletRequest request, @ModelAttribute("form") @Validated(Update.class) Sample entity, BindingResult result, Model model, Locale locale) throws Exception {

		if (result.hasErrors()) {
			throw new DemoException(result.getGlobalErrors(), result.getFieldErrors());
		}
    	
    	this.sampleService.update(entity);
    	
    	this.transactionManager.commit(this.transactionStatus);

		return Message.createMsg().addGlobalMsg(messageSource.getMessage("app.success.update", null, "It was updated", request.getLocale()));
	}

    @Transactional(readOnly = true)
	@RequestMapping(path="/show/{id}")
    @ResponseBody
	public SampleForListDto showAction(HttpServletRequest request, @PathVariable("id") long id, Model model, Locale locale) throws Exception {

    	SampleForListDto entity = this.sampleService.getDto(id);

		return entity;
	}

	@RequestMapping(path="/delete")
    @ResponseBody
	public Message deleteAction(HttpServletRequest request, @ModelAttribute("form") @Validated(Delete.class) Sample entity, BindingResult result, Model model, Locale locale) throws Exception {

		if (result.hasErrors()) {
			throw new DemoException(result.getGlobalErrors(), result.getFieldErrors());
		}
    	
    	this.sampleService.delete(entity.getId());
    	
    	this.transactionManager.commit(this.transactionStatus);

		return Message.createMsg().addGlobalMsg(messageSource.getMessage("app.success.delete", null, "It was deleted", request.getLocale()));
	}

}