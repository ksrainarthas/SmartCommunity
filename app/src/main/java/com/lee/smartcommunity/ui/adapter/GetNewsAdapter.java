package com.lee.smartcommunity.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.model.GetNewsResult;
import com.tencent.smtt.sdk.WebView;

import java.util.List;

/**
 * 通知公告adapter
 * 文件名: GetNewsAdapter
 * 创建者: WangYu
 * 创建日期: 2021/4/13 14:47
 */
public class GetNewsAdapter extends RecyclerView.Adapter<GetNewsAdapter.NewsVH> {

    private Context context;
    private List<GetNewsResult.DataBean> list;

    public GetNewsAdapter(Context context, List<GetNewsResult.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NewsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsVH holder, int position) {
        GetNewsResult.DataBean dataBean = list.get(position);
        String title = dataBean.getTitle();
        holder.tv_title.setText(title);
        String content = dataBean.getContent();
        content = content.replace("&quot;", "\"");
        content = content.replace("&amp;", "&");
        content = content.replace("&lt;", "<");
        content = content.replace("&gt;", ">");
        content = content.replace("&nbsp;", " ");
        holder.wv_content.getSettings().setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
        //holder.wv_content.loadData(htmlData, "text/html", "UTF -8");//API提供的标准用法，无法解决乱码问题
        holder.wv_content.loadData(content, "text/html; charset=UTF-8", null);//这种写法可以正确解码
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NewsVH extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private WebView wv_content;

        public NewsVH(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            wv_content = itemView.findViewById(R.id.wv_content);
        }
    }
}