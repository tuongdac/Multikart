package com.ecommerce.admin.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.admin.dao.UserDAO;
import com.ecommerce.admin.dto.UserDto;
import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.service.UserService;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO UserDao;
	//@Autowired
	//EntityManagerFactory entityManagerFactory;
	private UserDto userdto = null;
	
	
	@Override
	public List<UserDto> findAll() {
//		List<UserDto> userDtos = new ArrayList();
//		List<User> users = new ArrayList<User>();
//		users.add(UserDao.findById(2));
//		users.add(UserDao.findById(1));
//		users.add(UserDao.findByFirstname("dac"));
//		users.add(UserDao.findByFirstname("dao"));
		return convertEntityToDtoList(UserDao.findAll());
	}

	@Override
	public UserDto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(UserDto dto) {
		// Mã hóa mật khẩu
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashed);
		return UserDao.save(convertDtoToEntity(dto));
		
	}

	@Override
	public void update(UserDto dto) {
		UserDao.save(convertDtoToEntity(dto));
		
	}

	@Override
	public void delete(int id) {
		UserDao.deleteById(id);
		
	}
	
	public List<UserDto> convertEntityToDtoList(List<User> users){
		List<UserDto> dtos = new ArrayList<>();
		for(User user:users) {
			userdto = new UserDto();
			convertEntityToDto(user);
			dtos.add(userdto);
		}
		return dtos;
	}
	
	private int convertDateTime() {
		
		//get Date + Hourse (27062021_113300)
		LocalDateTime myDateFormat = LocalDateTime.now();
		
		//Format DDMMYYY
		DateTimeFormatter dateTimeFormat =  DateTimeFormatter.ofPattern("ddMMYYYY");
		String formatDate = myDateFormat.format(dateTimeFormat);
		
		//Parse to String to Int
		int joinDatetime = Integer.parseInt(formatDate);
		
		return joinDatetime;
	}
	
	public UserDto convertEntityToDto(User user) {
		userdto = new UserDto();
		userdto.setId(user.getId());
		userdto.setFirstname(user.getFirstname());
		userdto.setLastname(user.getLastname());
		userdto.setAddress(user.getAddress());
		userdto.setAvatar(user.getAvatar());
		userdto.setJoindate(user.getJoindate());
		userdto.setEmail(user.getEmail());
		userdto.setJoindate(user.getJoindate());
		userdto.setPassword(user.getPassword());
		userdto.setPhone(user.getPhone());
		userdto.setUseradd(user.getUseradd());
		userdto.setValidflag(user.getValidflag());
		return userdto;
	}
	
	public User convertDtoToEntity(UserDto dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setAddress(dto.getAddress());
		user.setAvatar(dto.getAvatar());
		user.setValidflag("1");
		user.setJoindate(convertDateTime());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setPhone(dto.getPhone());
		user.setUseradd(dto.getUseradd());
		user.setRoleId(dto.getRoleId());
		return user;
	}

	@Override
	public Page<User> findAllPaging(Pageable pageable) {
		return UserDao.findAll(pageable);
	}

	@Override
	public Page<User> pagingUserDto(int pageIndex, int pageSize) {
		//PageAble container vi tri trang duoc lay, va so phan tu duoc lay
		Pageable pageAble = PageRequest.of(pageIndex - 1, pageSize);
		
		return UserDao.findAll(pageAble);
		
	}


}
