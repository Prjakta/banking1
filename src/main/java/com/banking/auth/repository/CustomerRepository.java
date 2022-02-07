package com.banking.auth.repository;

//import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.banking.auth.entities.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {

	@Query("SELECT c FROM Customers c WHERE c.email=?1")
	Customers findCustomerByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE Customers c SET c.firstName=?1,c.middleName=?2,c.lastName=?3,c.address=?4 WHERE c.customerId=?5")
	int updateCustomer(String firstName, String middleName, String lastName, String address, long customerId);

	@Query("SELECT c FROM Customers c WHERE c.customerId=?1")
	Customers findById(long customerId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Customers c SET c.password=?1 WHERE c.email=?2")
	int updatePassword(String confirmPassword, String email);


}
