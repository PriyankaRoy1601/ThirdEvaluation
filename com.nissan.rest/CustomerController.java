package com.nissan.rest;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.model.Customer;
import com.nissan.service.ICustomerService;
import com.nissan.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private APIResponse apiResponse;
	
	@Autowired
	private JwtUtil jwtUtil;

	// list
	@GetMapping("/customer/{accno}/{money}")
	public Customer Deposit(@PathVariable long accno,@PathVariable double money,@RequestHeader(value="authorization",defaultValue="")String auth) throws AccessDeniedException {
		jwtUtil.verifyC(auth);
		return (Customer) customerService.deposit(accno,money);
	}


	
	//transferring money
	@GetMapping("/customer/{accno1}/{accno2}/{amount}")
	public ResponseEntity<APIResponse> transferAmount(@PathVariable long accno1,@PathVariable long accno2,@PathVariable double amount,@RequestHeader(value="authorization",defaultValue="")String auth) throws AccessDeniedException{
		jwtUtil.verifyC(auth);
		customerService.transfer(accno1,accno2,amount);
		apiResponse.setData("Customer ADDED SUCCESSFULLY");
		apiResponse.setStatus(200);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}

