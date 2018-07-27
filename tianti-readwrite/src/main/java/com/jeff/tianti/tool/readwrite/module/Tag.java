package com.jeff.tianti.tool.readwrite.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 标签
 * @author Jeff Xu
 * @since 2018-07-18
 */
@ApiModel(value="标签对象",description="标签（Tag）对象信息")
public class Tag {
	
	@ApiModelProperty(value="标签id",name="id")
	private String id;
	
	//名称
	@ApiModelProperty(value="名称",name="name")
	private String name;
	
	//类型
	@ApiModelProperty(value="类型",name="type")
	private Integer type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
