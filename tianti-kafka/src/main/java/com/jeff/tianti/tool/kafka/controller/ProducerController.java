package com.jeff.tianti.tool.kafka.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jeff.tianti.tool.kafka.dto.LogMessageDTO;
import com.jeff.tianti.tool.kafka.dto.NoticeMessageDTO;
import com.jeff.tianti.tool.kafka.enums.TopicEnum;
import com.jeff.tianti.tool.kafka.service.KafkaService;
import com.jeff.tianti.tool.kafka.util.JsonUtils;
/**
 * kafkad的Producer端控制器
 * @author Jeff Xu
 * @since 2018-07-18
 */
@RestController
@RequestMapping("/api/kafka/send")
public class ProducerController {
	
	@Autowired
	private KafkaService<String> kafkaService;
	
	/**
	 * 发送kafka消息(日志消息)
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("/log")
	public Map<String,Object> sendLog(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			LogMessageDTO logMessageDTO = new LogMessageDTO();
			logMessageDTO.setCreateDate(new Date());
			logMessageDTO.setIp("127.0.0.1");
			logMessageDTO.setUrl("http://localhost:8000/api/kafka/send/log");
			String logMessageJson = JsonUtils.toJson(logMessageDTO);
			this.kafkaService.send(TopicEnum.LOG_TOPIC.getCode(),logMessageJson);
			resultMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
		}
		return resultMap;
	}
	
	/**
	 * 发送kafka消息(通知消息)
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("/notice")
	public Map<String,Object> sendNotice(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			NoticeMessageDTO noticeMessageDTO = new NoticeMessageDTO();
			noticeMessageDTO.setTitle("kafka发送通知啦。");
			noticeMessageDTO.setContent("您好，kafka给NOTICE_TOPIC主题发送消息啦，记得接收哈。");
			noticeMessageDTO.setPublisher("tianti");
			noticeMessageDTO.setNoticeDate(new Date());
			String noticeMessageJson = JsonUtils.toJson(noticeMessageDTO);
			this.kafkaService.send(TopicEnum.NOTICE_TOPIC.getCode(),noticeMessageJson);
			resultMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put("success", false);
		}
		return resultMap;
	}

}
