package com.jeff.tianti.tool.readwrite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.tianti.tool.readwrite.annotation.ReadDataSource;
import com.jeff.tianti.tool.readwrite.annotation.WriteDataSource;
import com.jeff.tianti.tool.readwrite.dao.TagMapper;
import com.jeff.tianti.tool.readwrite.module.Tag;
import com.jeff.tianti.tool.readwrite.util.SpringContextUtil;

/**
 * 标签Service
 * 我们在service这一层通过使用标签来实现读写分离
 * 使用@WriteDataSource代表该方法写入到主库
 * 使用@ReadDataSource代表该方法随机从丛库中读取数据
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Service
@Transactional
public class TagService {
		
	@Autowired
	private TagMapper tagMapper;
	
	/**
	 * 新增
	 * @param tag
	 */
	@WriteDataSource
	public void insert(Tag tag) {
		this.tagMapper.insert(tag);
	}
	
	/**
	 * 修改
	 * @param tag
	 * @return
	 */
	@WriteDataSource
	public void update(Tag tag){
		this.tagMapper.update(tag);
	}
	
	
	/**
	 * 读取后修改，读从丛库里面读，写在主库里面写。
	 * @param tag
	 * @return
	 */
	public Tag readAndUpdate(Tag tag){
		Tag t = this.getService().findById(tag.getId());
		if(t != null){
			t.setName("jeff");
			this.tagMapper.update(tag);
		}
		
		return t;
	}

	/**
	 * 根据id查询标签
	 * @param id
	 * @return
	 */
	@ReadDataSource
	public Tag findById(String id) {
		Tag tag = this.tagMapper.findById(id);
		return tag;
	}
	
	/**
	 * 根据名称查找标签列表
	 * @param name
	 * @return
	 */
	@ReadDataSource
	public List<Tag> query(String name){
		return this.tagMapper.query(name);
	}
	
	private TagService getService(){
		return SpringContextUtil.getBean(this.getClass());
	}

}
