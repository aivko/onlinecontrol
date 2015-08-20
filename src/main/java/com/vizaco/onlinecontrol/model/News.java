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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (date != null ? !date.equals(news.date) : news.date != null) return false;
        if (topic != null ? !topic.equals(news.topic) : news.topic != null) return false;
        return !(text != null ? !text.equals(news.text) : news.text != null);

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
