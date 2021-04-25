package com.lee.smartcommunity.model;

import java.util.List;

public class AreaListResult {

    /**
     * error_code : 0
     * msg : Success
     * data : [{"id":"1","content":"气温反复点点滴滴","pic":"","time":"1557128408","status":"0","name":null,"phone":null},{"id":"2","content":"我们的生活方式是什么意思","pic":"","time":"1557130495","status":"0","name":null,"phone":null}]
     */

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
        /**
         * id : 1
         * content : 气温反复点点滴滴
         * pic :
         * time : 1557128408
         * status : 0
         * name : null
         * phone : null
         */

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
    }
}