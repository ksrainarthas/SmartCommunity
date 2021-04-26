package com.lee.smartcommunity.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * 根据 分类ID 获取商品列表 请求回调
 * 文件名: GetShopGoodsResult
 * 创建者: WangYu
 * 创建日期: 2021/4/25 10:13
 */
public class GetShopGoodsResult {
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

    public static class DataBean implements Serializable {
        private String goods_id;
        private String name;
        private String image;
        private String unit;
        private String price;
        private String nb_price;
        private String stock_num;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNb_price() {
            return nb_price;
        }

        public void setNb_price(String nb_price) {
            this.nb_price = nb_price;
        }

        public String getStock_num() {
            return stock_num;
        }

        public void setStock_num(String stock_num) {
            this.stock_num = stock_num;
        }

        @NonNull
        @Override
        public String toString() {
            return "DataBean{" +
                    "goods_id='" + goods_id + '\'' +
                    ", name='" + name + '\'' +
                    ", image='" + image + '\'' +
                    ", unit='" + unit + '\'' +
                    ", price='" + price + '\'' +
                    ", nb_price='" + nb_price + '\'' +
                    ", stock_num='" + stock_num + '\'' +
                    '}';
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "GetShopGoodsResult{" +
                "error_code=" + error_code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}