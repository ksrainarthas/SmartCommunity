package com.lee.smartcommunity.model;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * 通知公告 请求回调
 * 文件名: AnnouncementResult
 * 创建者: WangYu
 * 创建日期: 2021/4/16 9:02
 */
public class GetNewsResult {
    private int error_code;
    private String msg;
    private List<DataBean> data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String title;
        private String content;
        private String add_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        @NonNull
        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", add_time='" + add_time + '\'' +
                    '}';
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "AnnouncementResult{" +
                "error_code=" + error_code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}