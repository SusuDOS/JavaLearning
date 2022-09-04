package com.itheima.domain;

import org.springframework.stereotype.Repository;

// 下面的注解添加与否都能work，但是SpringBoot的却必须添加注解方可运行，有什么差异没？
//@Repository
public class Book {
    private Integer id;
    private String type;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
