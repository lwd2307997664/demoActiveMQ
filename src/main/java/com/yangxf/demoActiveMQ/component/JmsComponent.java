/**
 * FileName: JmsComponent
 * Author:   linwd
 * Date:     2021/4/11 12:50
 * Description: JMS组件发送和接收消息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yangxf.demoActiveMQ.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 〈一句话功能简述〉<br> 
 * 〈JMS组件发送和接收消息〉
 *
 * @author linwd
 * @create 2021/4/11
 * @since 1.0.0
 */
@Component
public class JmsComponent {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    /**
     * 发送消息
     */
    public void send(Message message){
        jmsMessagingTemplate.convertAndSend(this.queue,message.toString());
    }

    /**
     * 接收消息
     */
    @JmsListener(destination= "${queueName}",containerFactory = "queueListenerFactory")    //用这个注解去监听 监听的队列
    public void receive(String messageString){
        System.out.println("receive:"+messageString);
    }


    /**
     * 广播发送消息
     */
    public void sendForTopic(Message message){
        jmsMessagingTemplate.convertAndSend(this.topic,message.toString());
    }

    /**
     * 接收消息
     */
    @JmsListener(destination= "${topicName}",containerFactory = "topicListenerFactory")
    public void receiveForTopic1(String messageString){
        System.out.println("receive1:"+messageString);
    }

    /**
     * 接收消息
     */
    @JmsListener(destination= "${topicName}")
    public void receiveForTopic2(String messageString){
        System.out.println("receive2:"+messageString);
    }

}
