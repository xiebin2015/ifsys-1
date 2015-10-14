package com.gigold.pay.ifsys.dao;

import java.util.List;

import com.gigold.pay.ifsys.bo.InterFaceInfo;

public interface InterFaceDao {
	/**
	 * 根据Id获得接口信息
	 * 
	 * @param id
	 * @return
	 */
	public InterFaceInfo getInterFaceById(InterFaceInfo interFaceInfo);

	/**
	 * 根据产品Id获得接口信息
	 * 
	 * @param id
	 * @return
	 */
	public List<InterFaceInfo> getInterFaceByProId(int id);

	/**
	 * 
	 * @param interFaceInfo
	 * @return
	 */
	public List<InterFaceInfo> getInterFace(InterFaceInfo interFaceInfo);

	/**
	 * 获得所有接口信息－－分页
	 * 
	 * @return
	 */
	public List<InterFaceInfo> getAllInterFace();


	/**
	 * 修改接口信息
	 * 
	 * @param interFaceInfo
	 * @return
	 */
	public boolean updateInterFace(InterFaceInfo interFaceInfo);

	/**
	 * 新增接口信息
	 * 
	 * @param interFaceInfo
	 * @return
	 */
	public boolean addInterFace(InterFaceInfo interFaceInfo);

	/**
	 * 根据Id删除接口信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteInterFaceById(InterFaceInfo interFaceInfo);
}
