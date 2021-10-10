/**
 * 
 */
package com.backend.test.controller;


import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.test.entities.Customer;
import com.backend.test.model.GetCustomerDetailRawRequest;
import com.backend.test.model.GetCustomerDetailRequest;
import com.backend.test.model.RegisterRawRequest;
import com.backend.test.model.RegisterRequest;
import com.backend.test.model.TemplateResponseJson;
import com.backend.test.services.BackendService;
import com.backend.test.services.ValidateService;
import com.backend.test.utility.ErrorMapping;


@RestController
@RequestMapping("/api")
public class BackendController {
	
	private final Logger log = LoggerFactory.getLogger(BackendController.class);
	
	@Autowired
	private BackendService backendService;

	@Autowired
	private ValidateService validateService;
	
	@PostMapping("/register")
	public TemplateResponseJson register(@RequestBody RegisterRawRequest registerRequest) throws IOException{
		TemplateResponseJson response = new TemplateResponseJson();
		log.info(registerRequest.toString());
		try {
			List<String> validateMessages = validateService.validate(registerRequest);
			if (validateMessages.size() > 0) {
				String messageError = "";
				for (String validateMessage : validateMessages) {
					messageError += validateMessage + " ";
				}
				response.setApiStatus(0);
				response.setApiMessage(messageError);
				log.info(response.toString());
				return response;
			}
			response = backendService.addCustomer(registerRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setApiStatus(0);
			response.setApiMessage(ErrorMapping.getErrorMapping("M000"));
		}
		log.info(response.toString());
		return response;
		
	}
	
	@PostMapping("/getCustomerDetail")
	public TemplateResponseJson getCustomerDetail(@RequestBody GetCustomerDetailRawRequest getCustomerDetailRequest) throws IOException{
		TemplateResponseJson response = new TemplateResponseJson();
		log.info(getCustomerDetailRequest.toString());
		try {
			List<String> validateMessages = validateService.validate(getCustomerDetailRequest);
			if (validateMessages.size() > 0) {
				String messageError = "";
				for (String validateMessage : validateMessages) {
					messageError += validateMessage + " ";
				}
				response.setApiStatus(0);
				response.setApiMessage(messageError);
				log.info(response.toString());
				return response;
			}
			response = backendService.getCustomerDetail(getCustomerDetailRequest);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setApiStatus(0);
			response.setApiMessage(ErrorMapping.getErrorMapping("M000"));
		}
		log.info(response.toString());
		return response;
		
	}
}
