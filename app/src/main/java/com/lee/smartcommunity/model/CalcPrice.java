package com.lee.smartcommunity.model;

/**
 * 文件名: CalcPrice
 * 创建者: WangYu
 * 创建日期: 2021/4/28 14:28
 */
public class CalcPrice {
    private String project;
    private String content;

    public CalcPrice(String project, String content) {
        this.project = project;
        this.content = content;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}