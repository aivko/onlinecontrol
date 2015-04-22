package com.vizaco.onlinecontrol.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 *  Simple business object representing a news.
 *
 */
@Entity
@Table(name = "news")
public class News {

    @Id
    @Column(name = "news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(name = "date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Column(name = "topic")
    private String topic;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="school_id")
    private School school;

    public News() {
    }

    public News(Date date, String topic, String text, School school) {
        this.date = date;
        this.topic = topic;
        this.text = text;
        this.school = school;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
