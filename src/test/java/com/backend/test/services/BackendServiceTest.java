package com.backend.test.services;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.backend.test.entities.Customer;
import com.backend.test.model.GetCustomerDetailRawRequest;
import com.backend.test.model.RegisterRawRequest;
import com.backend.test.model.TemplateResponseJson;
import com.backend.test.repository.BackendRepository;

@ExtendWith(MockitoExtension.class)
public class BackendServiceTest {

	@Mock
	BackendRepository backendRepository;
	@Mock
	ValidateService validateService;

	@InjectMocks
	BackendService backendService;

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(backendService, "key", "aesPrivateKey123");
	}

	@Test
	public void testAddUserNewAndDuplicate() throws IOException {
		when(backendRepository.findByUsernameAndActive("aaa5", 1)).thenReturn(null).thenReturn(new Customer());
		RegisterRawRequest registerRequest = new RegisterRawRequest();
		registerRequest.setData("dUk+u/PyKO1gbdQemL2CeeWIHDgnbSkEmUYZghFdV3WSAjsWNHcL/Ucy7seZBOgxaYaHWkbNuAD2Dd5jPUvlr+46L+k8wXhjtyioD1SAUcYAbAQqALVg+UaFFw8hKrvRl/4Y83+0NUaW4ZrmW9u87isXx8k6oNE2aSM3RBVS1BQ=");
		TemplateResponseJson res;
		res = backendService.addCustomer(registerRequest);
		assertEquals(1, res.getApiStatus());
		System.out.println(res.toString());
		res = backendService.addCustomer(registerRequest);
		assertEquals(0, res.getApiStatus());
		System.out.println(res.toString());
	}

	@Test
	public void testAddUserLowerSalary() throws IOException {
		when(backendRepository.findByUsernameAndActive("aaa6", 1)).thenReturn(null);
		RegisterRawRequest registerRequest = new RegisterRawRequest();
		registerRequest.setData("dUk+u/PyKO1gbdQemL2CeYeRqPh5Tm1vn2BFbZvqyY2SAjsWNHcL/Ucy7seZBOgxaYaHWkbNuAD2Dd5jPUvlr+46L+k8wXhjtyioD1SAUcYAbAQqALVg+UaFFw8hKrvRl/4Y83+0NUaW4ZrmW9u87h4MkTE90gpKfYIS+3LjJjw=");
		TemplateResponseJson res;
		res = backendService.addCustomer(registerRequest);
		assertEquals(0, res.getApiStatus());
		System.out.println(res.toString());
	}

	@Test
	public void testGetUserDetail() throws IOException {
		Customer custDetail = new Customer();
		custDetail.setPassword("$2a$12$G9SRgH2xw4QkKOB1MUEq3eCYtF3A6SuNmYDsRg/d8Z8WwOlRzywfy");
		when(backendRepository.findByUsernameAndActive("aaa5", 1)).thenReturn(custDetail);
		GetCustomerDetailRawRequest getCustomerDetailRequest = new GetCustomerDetailRawRequest();
		getCustomerDetailRequest.setData("dUk+u/PyKO1gbdQemL2CeeWIHDgnbSkEmUYZghFdV3XY0UiWtEdiDOSRLAC1VOU12gwQDoOZaLstTPtdso499w==");
		TemplateResponseJson res;
		res = backendService.getCustomerDetail(getCustomerDetailRequest);
		assertEquals(1, res.getApiStatus());
		System.out.println(res.toString());
	}

	@Test
	public void testGetUserDetailWrongPassword() throws IOException {
		Customer custDetail = new Customer();
		custDetail.setPassword("$2a$12$G9SRgH2xw4QkKOB1MUEq3eCYtF3A6SuNmYDsRg/d8Z8WwOlRzywfy123");
		when(backendRepository.findByUsernameAndActive("aaa5", 1)).thenReturn(custDetail);
		GetCustomerDetailRawRequest getCustomerDetailRequest = new GetCustomerDetailRawRequest();
		getCustomerDetailRequest.setData("dUk+u/PyKO1gbdQemL2CeeWIHDgnbSkEmUYZghFdV3XY0UiWtEdiDOSRLAC1VOU12gwQDoOZaLstTPtdso499w==");
		TemplateResponseJson res;
		res = backendService.getCustomerDetail(getCustomerDetailRequest);
		assertEquals(0, res.getApiStatus());
		System.out.println(res.toString());
	}

}
