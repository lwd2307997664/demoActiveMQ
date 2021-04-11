/**
 * FileName: JmsController
 * Author:   linwd
 * Date:     2021/4/11 13:19
 * Description: 发送信息控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yangxf.demoActiveMQ.controller;

import com.yangxf.demoActiveMQ.component.JmsComponent;
import com.yangxf.demoActiveMQ.component.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈发送信息控制器〉
 *
 * @author linwd
 * @create 2021/4/11
 * @since 1.0.0
 */
@RestController
public class JmsController {
    private final Logger logger=LoggerFactory.getLogger(JmsController.class);
    @Autowired
    private JmsComponent jmsComponent;
    @GetMapping("/pushMessage")
    public void send(){
        logger.info("pushMessage推送消息");
        Message message=new Message();
        message.setContent("推送消息");
        message.setData(new Date());
        jmsComponent.send(message);
    }

    @GetMapping("/pushMessageForTopic")
    public void pushMessageForTopic(){
        logger.info("pushMessageForTopic广播消息");
        Message message=new Message();
        message.setContent("广播消息");
        message.setData(new Date());
        jmsComponent.sendForTopic(message);
    }
}
