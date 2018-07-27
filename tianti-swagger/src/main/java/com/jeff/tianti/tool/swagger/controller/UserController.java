package com.jeff.tianti.tool.swagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeff.tianti.tool.swagger.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户控制器
 * @author Jeff Xu
 *
 */
@Api(value="用户信息管理",tags={"用户信息管理接口"})
@Controller
@RequestMapping("/user")
public class UserController {
	
	/**
	 * 新增
	 * @param id
	 * @param name
	 * @return
	 */
	@ApiOperation(value="新增用户", notes="add")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "标签id", required = true, dataType = "String"),
        @ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String")
    })
	@RequestMapping(value="/add",method=RequestMethod.POST )
	@ResponseBody
	public String add(String id,String name){
		User user = new User();
		user.setAccout("tianti");
		user.setPwd("swagger");
		return user.getAccout();
	}
	
	
	

}
