package com.gigold.pay.ifsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigold.pay.ifsys.bo.UserInfo;
import com.gigold.pay.ifsys.dao.UserInfoDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserInfoService {
	@Autowired
	UserInfoDao userInfoDao;

	public UserInfo getUser(int id) {
		return userInfoDao.getUser(id);
	}

	public PageInfo<UserInfo> getList() {
		PageHelper.startPage(1, 10);
		List<UserInfo> uList = userInfoDao.getList();
		PageInfo<UserInfo> pi = new PageInfo<UserInfo>(uList);
		return pi;
	}

	public UserInfo login(UserInfo user) {
		return userInfoDao.login(user);
	}
	
	public boolean addUser(UserInfo userInfo){
		return userInfoDao.addUser(userInfo);
	}
}
