package com.jeff.tianti.tool.readwrite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeff.tianti.tool.readwrite.module.Tag;
import com.jeff.tianti.tool.readwrite.service.TagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 标签控制层
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Api(value="标签信息管理",tags={"标签信息管理接口"})
@Controller
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	/**
	 * 新增
	 * @param id
	 * @param name
	 * @return
	 */
	@ApiOperation(value="新增标签", notes="add")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "标签id", required = true, dataType = "String"),
        @ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String")
    })
	@RequestMapping(value="/add",method=RequestMethod.POST )
	@ResponseBody
	public String add(String id,String name){
		Tag tag = new Tag();
		tag.setId(id);
		tag.setName(name);
		tag.setType(1);
		this.tagService.insert(tag);
		
		return tag.getId()+" " + tag.getName();
	}
	
	/**
	 * 查询后修改
	 * @param id
	 * @param name
	 * @return
	 */
	@ApiOperation(value="查询标签后修改标签", notes="queryandupdate")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "标签id", required = true, dataType = "String"),
        @ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String")
    })
	@RequestMapping(value="/queryandupdate",method=RequestMethod.PUT)
	@ResponseBody
	public String queryAndUpdate(String id,String name){
		String resultMsg = "";
		Tag tag = this.tagService.findById(id);
		if(tag != null){
			tag.setName(name);
			this.tagService.update(tag);
			resultMsg = tag.getId()+" " + tag.getName();
		}else{
			resultMsg = "丛库里面没有该数据";
		}
		return resultMsg;
	}
	
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	@ApiOperation(value="查询标签", notes="根据id查询标签")
	@ApiImplicitParam(name = "id", value = "标签id", required = true, dataType = "String")
	@RequestMapping(value="/get/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String findById(@PathVariable("id") String id){
		Tag tag = this.tagService.findById(id);
		return tag==null?"找不到该数据":tag.getId()+" " + tag.getName();
	}
	
	/**
	 * 根据名称查询标签列表
	 * @param name
	 * @return
	 */
	@ApiOperation(value="查询标签列表", notes="根据名称查询标签列表")
	@ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String")
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public List<Tag> query(String name){
		List<Tag> tagList = this.tagService.query(name);
		return tagList;
	}

}
