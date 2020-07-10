package com.xxl.timer.schedule;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * 汽车租赁方面微信，QQ数据爬取
 */
@Component
public class GrabbingCarSchedule {
  private static final Logger log = LoggerFactory.getLogger(GrabbingCarSchedule.class);
    @Value("${robot.ips}")
    private String ips;


    /**
     * todo 源始数据采集信息
     * 表示每隔3分钟获取数据一次
     * 至少大于1分钟
     */
   //@Scheduled(cron = "0 0/2 * * * ?")
    public void collectSource(){
        log.info("********************collectSource()方法源始数据采集信定时器启动**************************");

    }


//***********************************************业务性操作*******************************************************

    /**
     * todo qq采集信息
     * 表示每隔3分钟获取数据一次
     * 至少大于1分钟
     */
  //@Scheduled(cron = "0 0/1 * * * ?")
  @Scheduled(cron = "0/30 * * * * ?")
   public void qqCrawl() throws JsonProcessingException {
       log.info("********************qqProces定时器启动**************************");
        RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();
      MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
      headers.setContentType(type);
      headers.add("Accept", MediaType.APPLICATION_JSON.toString());

      JSONObject param = new JSONObject();
      param.put("username", "123");
      ObjectMapper mapper = new ObjectMapper();
      String value = mapper.writeValueAsString(param);
      HttpEntity<String> formEntity = new HttpEntity<String>(value, headers);

      restTemplate.postForObject(ips+"/robot/qq/collectQQ", formEntity, String.class);

   }



}

