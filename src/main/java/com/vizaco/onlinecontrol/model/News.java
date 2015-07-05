package com.vizaco.onlinecontrol.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *  Simple business object representing a news.
 *
 */
@Entity
@Table(name = "news")
public class News extends BaseEntity{

    @NotNull
    @Column(name = "date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;

    @NotEmpty
    @Column(name = "topic")
    private String topic;

    @NotEmpty
    @Column(name = "text")
    private String text;

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

}
