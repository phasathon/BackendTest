/**
 * 
 */
package com.backend.test.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.backend.test.constant.Common;
import com.backend.test.entities.Customer;
import com.backend.test.model.GetCustomerDetailRawRequest;
import com.backend.test.model.GetCustomerDetailRequest;
import com.backend.test.model.GetCustomerDetailResponse;
import com.backend.test.model.RegisterRawRequest;
import com.backend.test.model.RegisterRequest;
import com.backend.test.model.RegisterResponse;
import com.backend.test.model.TemplateResponseJson;
import com.backend.test.repository.BackendRepository;
import com.backend.test.utility.Encryption;
import com.backend.test.utility.ErrorMapping;
import com.backend.test.utility.Validate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class BackendService {
	
	@Value("${application.key}")
	private String key;
	
	@Autowired
	private BackendRepository backendRepository;
	
	@Autowired
	private ValidateService validateService;
	
	private final Logger log = LoggerFactory.getLogger(BackendService.class);

	public TemplateResponseJson getCustomerDetail(GetCustomerDetailRawRequest getCustomerDetailRequest) throws IOException{
		TemplateResponseJson response = new TemplateResponseJson();
		String rawData = Encryption.decrypt(getCustomerDetailRequest.getData(), key);
		ObjectMapper mapper = new ObjectMapper();
		GetCustomerDetailRequest modelData = mapper.readValue(rawData, GetCustomerDetailRequest.class);
		List<String> validateMessages = validateService.validate(modelData);
		if (validateMessages.size() > 0) {
			String messageError = "";
			for (String validateMessage : validateMessages) {
				messageError += validateMessage + " ";
			}
			response.setApiStatus(0);
			response.setApiMessage(messageError);
			return response;
		}
		Customer customerDetail = backendRepository.findByUsernameAndActive(modelData.getUsername(),1);
		if(customerDetail == null) {
			response.setApiStatus(0);
			response.setApiMessage(ErrorMapping.getErrorMapping("M003"));
			return response;
		}
		log.info(customerDetail.toString());
		boolean matched = BCrypt.checkpw(modelData.getPassword(), customerDetail.getPassword());
		if(!matched) {
			response.setApiStatus(0);
			response.setApiMessage(ErrorMapping.getErrorMapping("M004"));
			return response;
		}
		GetCustomerDetailResponse getCustomerDetailResponse = new GetCustomerDetailResponse();
		getCustomerDetailResponse.setUsername(customerDetail.getUsername());
		getCustomerDetailResponse.setReference(customerDetail.getReference());
		getCustomerDetailResponse.setPhone(customerDetail.getPhone());
		getCustomerDetailResponse.setAddress(customerDetail.getAddress());
		getCustomerDetailResponse.setCustomerType(customerDetail.getCustomerType());
		getCustomerDetailResponse.setCreateDate(customerDetail.getCreateDate());
		response.setApiStatus(1);
		response.setApiMessage(Common.SUCESS);
		response.setData(getCustomerDetailResponse);
		return response;
	}
	
	public TemplateResponseJson addCustomer(RegisterRawRequest registerRequest) throws IOException {
		TemplateResponseJson response = new TemplateResponseJson();
		String rawData = Encryption.decrypt(registerRequest.getData(), key);
		ObjectMapper mapper = new ObjectMapper();
		RegisterRequest modelData = mapper.readValue(rawData, RegisterRequest.class);
		List<String> validateMessages = validateService.validate(modelData);
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
		
		Customer customerList = backendRepository.findByUsernameAndActive(modelData.getUsername(),1);
		if(customerList != null) {
			response.setApiStatus(0);
			response.setApiMessage(ErrorMapping.getErrorMapping("M001"));
			return response;
		}
		if(modelData.getSalary() < 15000) {
			response.setApiStatus(0);
			response.setApiMessage(ErrorMapping.getErrorMapping("M002"));
			return response;
		}
		if(!Validate.validateMobile(modelData.getPhone())) {
			response.setApiStatus(0);
			response.setApiMessage(ErrorMapping.getErrorMapping("M005"));
			return response;
		}
		Customer customer = new Customer();
		customer.setReference(genReference(modelData.getPhone()));
		customer.setUsername(modelData.getUsername());
		customer.setPassword(Encryption.bcrypt(modelData.getPassword()));
		customer.setAddress(modelData.getAddress());
		customer.setPhone(modelData.getPhone());
		customer.setSalary(modelData.getSalary());
		customer.setCustomerType(classifyCustomer(modelData.getSalary()));
		customer.setActive(1);
		customer.setCreateDate(Common.DATE_TIME_FORMAT.format(new Date()));
		backendRepository.save(customer);
		response.setApiStatus(1);
		response.setApiMessage(Common.SUCESS);
		RegisterResponse registerResponse = new RegisterResponse();
		registerResponse.setCustomerType(customer.getCustomerType());
		registerResponse.setReference(customer.getReference());
		response.setData(registerResponse);
		return response;
	}
	
	private String genReference(String phone) {
		return String.format("%s%s",Common.DATE_FORMAT.format(new Date()),phone.substring(6));
	}
	
	private String classifyCustomer(int salary) {
		String customerType;
		if(salary > 50000) {
			customerType = Common.PLATINUM;
		}else if(salary >= 30000 && salary <= 50000) {
			customerType = Common.GOLD;
		}else {
			customerType = Common.Silver;
		}
		return customerType;
	}

}
