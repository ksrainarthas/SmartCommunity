package com.lee.smartcommunity.model;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * 物业缴费 请求回调
 * 文件名: PropertyPaymentResult
 * 创建者: WangYu
 * 创建日期: 2021/4/25 10:07
 */
public class GetPropertyResult {
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
        private String village_id;
        private int property_price;
        private String property_month_num;
        private String diy_content;
        private String property_year_name;

        public String getVillage_id() {
            return village_id;
        }

        public void setVillage_id(String village_id) {
            this.village_id = village_id;
        }

        public int getProperty_price() {
            return property_price;
        }

        public void setProperty_price(int property_price) {
            this.property_price = property_price;
        }

        public String getProperty_month_num() {
            return property_month_num;
        }

        public void setProperty_month_num(String property_month_num) {
            this.property_month_num = property_month_num;
        }

        public String getDiy_content() {
            return diy_content;
        }

        public void setDiy_content(String diy_content) {
            this.diy_content = diy_content;
        }

        public String getProperty_year_name() {
            return property_year_name;
        }

        public void setProperty_year_name(String property_year_name) {
            this.property_year_name = property_year_name;
        }

        @NonNull
        @Override
        public String toString() {
            return "DataBean{" +
                    "village_id='" + village_id + '\'' +
                    ", property_price=" + property_price +
                    ", property_month_num='" + property_month_num + '\'' +
                    ", diy_content='" + diy_content + '\'' +
                    ", property_year_name='" + property_year_name + '\'' +
                    '}';
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "PropertyPaymentResult{" +
                "error_code=" + error_code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}