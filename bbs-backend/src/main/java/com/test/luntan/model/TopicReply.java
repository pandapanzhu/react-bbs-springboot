package com.test.luntan.model;

import javax.persistence.*;

/**
 * 回复实体--关于主题的回复
 */
@Entity
@Table(name = "topic_reply")
public class TopicReply extends BaseModel{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 回复内容
    @Column(name = "text")
    private String text;

    //回复时间-->根据回复时间排序判断几楼
    @Column(name = "reply_date")
    private String replyDate;

    //回复人-->一对一
    @OneToOne
    @JoinColumn(name = "user_id")
    private LoginUser user;

   //对应的主题-->多对一
    @Column(name = "topic_id")
    private String topicId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }

    public String getTopic() {
        return topicId;
    }

    public void setTopic(String topicId) {
        this.topicId = topicId;
    }
}
