/**
 * 
 */
package com.backend.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.test.entities.Customer;


@Repository
public interface BackendRepository extends JpaRepository<Customer, Integer>{
	
	Customer findByUsernameAndActive(String username,Integer active);
}
