package com.yangxf.demoActiveMQ.component;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装类〉
 *
 * @author linwd
 * @create 2021/4/11
 * @since 1.0.0
 */
public class Message implements Serializable {
    private String content;
    private Date data;

    public Message() {
    }

    public Message(String content, Date data) {
        this.content = content;
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", data=" + data +
                '}';
    }
}
