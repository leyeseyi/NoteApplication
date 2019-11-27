/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leye.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Leyeseyi
 */
@Entity
public class Note extends AuditModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable=false)
    private String title;
    
    @Column(columnDefinition="text", nullable=false)
    private String content;
    
    private String category;
    
    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    public Note() {
    }

    public Note(String title, String content, String category, User user) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.user = user;
    }

    public Note(int id, String title, String content, String category, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "note{" + "id=" + id + ", title=" + title + ", content=" + content + ", category=" + category + ", user=" + user + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Note other = (Note) obj;
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }
    
    
    
}
