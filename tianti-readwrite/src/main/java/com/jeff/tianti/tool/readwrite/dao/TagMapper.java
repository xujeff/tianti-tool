package com.jeff.tianti.tool.readwrite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.jeff.tianti.tool.readwrite.module.Tag;

/**
 * 标签Mapper
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Mapper
public interface TagMapper {
	
	/**
	 * 新增标签
	 * @param tag
	 */
	@Insert("insert into pre_tag(id,name,tag_type) values(#{id},#{name},#{type})")
	void insert(Tag tag);
	
	/**
	 * 修改标签
	 * @param tag
	 */
	@Update("update pre_tag set name = #{name} where id = #{id} ")
	void update(Tag tag);
	
	/**
	 * 跟进id查找标签
	 * @param id
	 * @return
	 */
	Tag findById(@Param("id")String id);
	
	/**
	 * 根据名称查找标签列表
	 * @param name
	 * @return
	 */
	List<Tag> query(@Param("name")String name);

}
