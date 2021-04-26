package com.lee.smartcommunity.model;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * 获取快店 分类ID、分类名称、子分类ID、子分类名称 请求回调
 * 文件名: CommodityCategoryResult
 * 创建者: WangYu
 * 创建日期: 2021/4/25 10:11
 */
public class GetShopGoodsCategoryResult {
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
        private String sort_id;
        private String sort_name;
        private String fid;

        public String getSort_id() {
            return sort_id;
        }

        public void setSort_id(String sort_id) {
            this.sort_id = sort_id;
        }

        public String getSort_name() {
            return sort_name;
        }

        public void setSort_name(String sort_name) {
            this.sort_name = sort_name;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        @NonNull
        @Override
        public String toString() {
            return "DataBean{" +
                    "sort_id='" + sort_id + '\'' +
                    ", sort_name='" + sort_name + '\'' +
                    ", fid='" + fid + '\'' +
                    '}';
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "CommodityCategoryResult{" +
                "error_code=" + error_code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}