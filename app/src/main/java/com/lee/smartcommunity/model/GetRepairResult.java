package com.lee.smartcommunity.model;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * 获取小区报修列表 请求回调
 * 文件名: CommunityRepairResult
 * 创建者: WangYu
 * 创建日期: 2021/4/25 9:57
 */
public class GetRepairResult {
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
        private String content;
        private String pic;
        private String time;
        private String status;
        private Object name;
        private Object phone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        @NonNull
        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", content='" + content + '\'' +
                    ", pic='" + pic + '\'' +
                    ", time='" + time + '\'' +
                    ", status='" + status + '\'' +
                    ", name=" + name +
                    ", phone=" + phone +
                    '}';
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "CommunityRepairResult{" +
                "error_code=" + error_code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}