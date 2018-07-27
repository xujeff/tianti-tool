package com.jeff.tianti.tool.redis.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jeff.tianti.tool.redis.dto.UserInfoDTO;
import com.jeff.tianti.tool.redis.service.RedisService;
import com.jeff.tianti.tool.redis.util.JsonUtils;

/**
 * redis控制器
 * @author Jeff Xu
 * @since 2018-07-18
 */
@RestController
@RequestMapping("/api/redis")
public class RedisController {
	
	@Autowired
	private RedisService<String,UserInfoDTO> redisService;
	
	/**
	 * 设置值
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("/user/set")
	public Map<String,Object> setUserInfo(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			userInfoDTO.setId("10000"); 
			userInfoDTO.setSex(1);
			userInfoDTO.setUserName("tianti-redis");
			userInfoDTO.setBirthDate(new Date());
			String key = UserInfoDTO.class.getSimpleName()+":"+userInfoDTO.getId()+":id";
			UserInfoDTO redisDTO = this.redisService.get(key, UserInfoDTO.class);
			if(redisDTO != null){
				resultMap.put("success", true);
				resultMap.put("message", "缓存中已经存在该数据。");
			}else{
				boolean flag = this.redisService.set(key, userInfoDTO);
				if(flag){
					resultMap.put("success", true);
					resultMap.put("message", "已经将该数据加入缓存。");
				}else{
					resultMap.put("success", false);
					resultMap.put("message", "加入缓存失败。");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
		}
		return resultMap;
	}
	
	/**
	 * 获取值
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("/user/get")
	@ResponseBody
	public Map<String,Object> getUserInfo(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			String id = request.getParameter("id");
			String key = UserInfoDTO.class.getSimpleName()+":"+(id == null ?"":id) +":id";
			UserInfoDTO redisDTO = this.redisService.get(key, UserInfoDTO.class);
			if(redisDTO != null){
				resultMap.put("success", true);
				resultMap.put("userInfoDTO", redisDTO);
				resultMap.put("message", "缓存中已经存在该数据。");
			}else{
				resultMap.put("success", false);
				resultMap.put("message", "缓存中不存在该数据。");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
		}
		return resultMap;
	}
	
	/**
	 * 删除值
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("/user/remove")
	@ResponseBody
	public Map<String,Object> removeUserInfo(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			String id = request.getParameter("id");
			String key = UserInfoDTO.class.getSimpleName()+":"+(id == null ?"":id) +":id";
			UserInfoDTO redisDTO = this.redisService.get(key, UserInfoDTO.class);
			if(redisDTO != null){
				boolean flag = this.redisService.remove(key);
				if(flag){
					resultMap.put("success", true);
					resultMap.put("message", "缓存中已经删除该数据。");
				}else{
					resultMap.put("success", false);
					resultMap.put("message", "缓存删除失败。");
				}
			}else{
				resultMap.put("success", false);
				resultMap.put("message", "缓存中不存在该数据。");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
		}
		return resultMap;
	}

}
