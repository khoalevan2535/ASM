package com.poly.demo.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.demo.beans.UserBean;
import com.poly.demo.enities.UserEntity;
import com.poly.demo.jpas.UserJPA;


@Service
public class UserService {
	@Autowired
	UserJPA userJPA;

	@Autowired
	ImageService imageService;

	public String insertUser(UserBean userBean){
	    try{

	      // Kiểm tra username và email có tồn tại chưa

	      List<UserEntity> userEntities = userJPA.findByUsernameAndEmail(userBean.getUsername(), userBean.getEmail());
	      if(userEntities.size() > 0){
	        return "Username và email đã tồn tại";
	      }

	      // Lưu file vào project => fileName

	      String fileName = imageService.saveImage(userBean.getAvatar());

	      if(fileName == null){
	        return "Error image";
	      }

	      // insert
	      // convert bean to entity
	      // Gson
	      UserEntity userEntity = new UserEntity();
	      userEntity.setUsername(userBean.getUsername());
	      userEntity.setPasswordHash(userBean.getPasswordHash());
	      userEntity.setName(userBean.getName());
	      userEntity.setEmail(userBean.getEmail());
	      userEntity.setAvatar(fileName);
	      userEntity.set_Active(true);
	      userEntity.setRole(1);
	      userJPA.save(userEntity);

	    }catch(Exception e){
	      return "Có lỗi trong quá trình thêm user";
	    }

	    return null;
	  }
}

