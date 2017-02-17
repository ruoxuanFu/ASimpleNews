package com.firstcode.asimplenews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firstcode.asimplenews.Bean.News;
import com.firstcode.asimplenews.NewsContentActivity;
import com.firstcode.asimplenews.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yue on 2017/2/17.
 * 　　　　　　　  ┏┓　 ┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　     ┃
 * 　　　　　　　┃　　　━　    ┃ ++ + + +
 * 　　　　　　 ████━████     ┃++  ++
 * 　　　　　　　┃　　　　　　 ┃ +
 * 　　　　　　　┃　　　┻　　　┃  +  +
 * 　　　　　　　┃　　　　　　 ┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 */

public class NewsTitleFragment extends Fragment {

    private boolean isTowPage;
    private RecyclerView news_title_recycler_view;
    private NewsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            // 可以找到news_content_layout布局时，为双页模式
            Log.e("NewsTitleFragment", "true");
            isTowPage = true;
        } else {
            // 找不到news_content_layout布局时，为单页模式
            Log.e("NewsTitleFragment", "false");
            isTowPage = false;
        }
    }

    private void initView(View view) {
        news_title_recycler_view = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
    }

    private void initData() {
        mAdapter = new NewsAdapter(getNews());
        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        news_title_recycler_view.setLayoutManager(linear);
        news_title_recycler_view.setAdapter(mAdapter);
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.setTitle("I am title " + i);
            news.setContent(getRandomLengthContent("I am content" + i));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String s) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        public NewsAdapter(List<News> mNewsList) {
            this.mNewsList = mNewsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_itme, parent, false);
            final ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(viewHolder.getAdapterPosition());
                    if (isTowPage) {
                        //如果是双页显示，则刷新NewsContentFragment中的内容
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager()
                                .findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refrushfrag(news.getTitle(), news.getContent());
                    } else {
                        //如果是单页模式，直接启动NewsContentActivity
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTitleText;

            public ViewHolder(View itemView) {
                super(itemView);
                newsTitleText = (TextView) itemView.findViewById(R.id.news_title);
            }
        }
    }
}
