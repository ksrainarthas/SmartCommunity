package com.lee.smartcommunity.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lee.smartcommunity.R;
import com.lee.smartcommunity.model.AnnouncementResult;
import com.lee.smartcommunity.utils.ImageGetter;
import com.lee.smartcommunity.utils.MxgsaTagHandler;

import java.util.List;

/**
 * 文件名: AnnouncementAdapter
 * 创建者: WangYu
 * 创建日期: 2021/4/13 14:47
 */
public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementVH> {

    private Context context;
    private List<AnnouncementResult.DataBean> list;

    public AnnouncementAdapter(Context context, List<AnnouncementResult.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AnnouncementVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_announcement, parent, false);
        return new AnnouncementVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementVH holder, int position) {
        AnnouncementResult.DataBean dataBean = list.get(position);
        String title = dataBean.getTitle();
        holder.tv_title.setText(title);
        String content = dataBean.getContent();
        content = content.replace("&quot;", "\"");
        content = content.replace("&amp;", "&");
        content = content.replace("&lt;", "<");
        content = content.replace("&gt;", ">");
        content = content.replace("&nbsp;", " ");
        holder.tv_content.setText(Html.fromHtml(content, new ImageGetter(context, holder.tv_content), new MxgsaTagHandler(context)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AnnouncementVH extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_content;

        public AnnouncementVH(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }
}