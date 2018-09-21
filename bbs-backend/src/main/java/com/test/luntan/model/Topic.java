package com.test.luntan.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 主题实体--就是一个帖子
 */
@Entity
@Table(name = "topic")
public class Topic extends BaseModel{

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

    //所属板块
    @Column(name = "plate_id")
    private String plateId;

    //主题名称
    @Column(name = "topic_name")
    private String topicName;

    //发表人
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private LoginUser user;

    //发表时间
    @Column(name = "public_date")
    private Timestamp publicDate;

    //发表内容
    @Column(name = "text")
    private String text;


    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }

    public Timestamp getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Timestamp publicDate) {
        this.publicDate = publicDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
