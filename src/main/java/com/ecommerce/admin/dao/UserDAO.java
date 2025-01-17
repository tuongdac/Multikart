package com.ecommerce.admin.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.admin.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
	
	@Query("SELECT u FROM User u WHERE u.id = :id AND u.validflag = 2")
	public User findById(@Param("id")int id);
	public User findByFirstname(String firstname);
	public User findByEmail(String email);
	//public Page<User> findAllPaging(Pageable pageAble);
//	public List<User> custom();
}
