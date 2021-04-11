/**
 * FileName: ActiveMQConfig
 * Author:   linwd
 * Date:     2021/4/11 12:40
 * Description: ActiveMQConfig配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yangxf.demoActiveMQ.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 〈一句话功能简述〉<br>
 * 〈ActiveMQConfig配置〉
 *
 * @author linwd
 * @create 2021/4/11
 * @since 1.0.0
 */
@Configuration
public class ActiveMQConfig {
    @Value("${spring.activemq.user}")
    private String usrName;
    @Value("${spring.activemq.password}")
    private  String password;
    @Value("${spring.activemq.broker-url}")
    private  String brokerUrl;

    @Value("${queueName}")
    private String queueName;
    @Value("${topicName}")
    private String topicName;

    /**
     * 队列模式实例
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }

    /**
     * 订阅模式实例
     *
     * @return
     */
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(topicName);
    }

    /**
     * 配置以下两个bean，同时支持队列模式和广播模式，配置中spring.jms.pub-sub-domain=true将失效
     * @return
     */
    @Bean("connectionFactory")
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(usrName, password, brokerUrl);
    }

    @Bean("queueListenerFactory")
    public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean("topicListenerFactory")
    public JmsListenerContainerFactory <?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //设置为发布订阅方式, 默认情况下使用的生产消费者方式
        factory.setPubSubDomain(true);
        return factory;
    }


}
