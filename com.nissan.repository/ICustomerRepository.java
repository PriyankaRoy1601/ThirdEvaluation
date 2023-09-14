package com.nissan.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.Customer;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {

	@Query("UPDATE com.nissan.model.Customer SET balance=balance+?2 WHERE accountNo=?1")
	void deposit(long accno, double money);
	
	@Query("UPDATE com.nissan.model.Customer SET balance=?2 WHERE accountNo=?1")
	void withdraw(long accno,double money);


}
