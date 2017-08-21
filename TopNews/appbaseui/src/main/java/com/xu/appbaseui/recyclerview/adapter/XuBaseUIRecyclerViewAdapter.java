package com.xu.appbaseui.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xu.appbaseui.R;
import com.xu.appbaseui.recyclerview.listener.OnItemClickListener;
import com.xu.appbaseui.recyclerview.listener.OnItemLongClickListener;
import com.xu.appbaseui.recyclerview.listener.OnLoadMoreDataListener;
import com.xu.appbaseui.recyclerview.viewholder.XuBaseUIViewHolder;
import com.xu.appcommonutils.util.LogUtils;

import java.util.List;

/**
 * @author xuhang
 * @version V1.0
 * @ClassName: XuBaseUIRecyclerViewAdapter
 * @Title:
 * @Description: recyclerview baseadapter
 * @Package com.xu.appbaseui.recyclerview.adapter
 * @date 2017/5/23 14:25
 */
public abstract class XuBaseUIRecyclerViewAdapter<T> extends RecyclerView.Adapter<XuBaseUIViewHolder> {
    /**
     * 类型
     */
    public static final int TYPE_HEADER = 1; //头部
    public static final int TYPE_ITEM = 2;//item
    public static final int TYPE_MORE = 3;//加载更多
    public static final int TYPE_EMPTY = 4;//空
    private static final int TYPE_MORE_FAIL = 5;//加载更多失败
    /**
     * 数据
     */
    public List<T> mDataLists;
    /**
     *
     */
    private Context mContext;
    /**
     * 布局管理
     */
    private RecyclerView.LayoutManager mLayoutManager;
    /**
     * 布局加载器
     */
    private LayoutInflater mLayoutInflater;
    /**
     * 是否显示加载更多
     */
    public boolean mIsShowMoreView;
    /**
     * 是否显示空
     */
    public boolean mIsShowEmptyView;
    /**
     * 加载更多监听
     */
    public OnLoadMoreDataListener mLoadmoreDataListener;
    /**
     * item点击监听
     */
    public OnItemClickListener mItemClickListener;
    /**
     * item长按监听
     */
    public OnItemLongClickListener mItemLongClickListener;
    /**
     * 是否加载更多
     */
    public boolean mEnableLoadMore;

    public XuBaseUIRecyclerViewAdapter(List<T> dataLists, Context context) {
        this(dataLists,context,null);
    }

    public XuBaseUIRecyclerViewAdapter(List<T> dataLists, Context context, RecyclerView.LayoutManager layoutManager) {
        mDataLists = dataLists;
        mContext = context;
        mLayoutManager = layoutManager;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public XuBaseUIViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final XuBaseUIViewHolder xuBaseUIViewHolder;
        if (viewType == TYPE_MORE){
            xuBaseUIViewHolder = new XuBaseUIViewHolder(mLayoutInflater.inflate(R.layout.base_load_more_layout,null),mContext);
        }else if (viewType == TYPE_MORE_FAIL){
            xuBaseUIViewHolder = new XuBaseUIViewHolder(mLayoutInflater.inflate(R.layout.base_load_more_fail_layout,null),mContext);
            if (mLoadmoreDataListener != null){
                xuBaseUIViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEnableLoadMore = true;
                        mIsShowMoreView = true;
                        //刷新局部
                        notifyItemChanged(getItemCount() - 1);
                        xuBaseUIViewHolder.itemView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mLoadmoreDataListener.loadMoreData();
                            }
                        },300);
                    }
                });
            }
        }else {
            xuBaseUIViewHolder = new XuBaseUIViewHolder(mLayoutInflater.inflate(getItemLayoutId(),null),mContext);
            xuBaseUIViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (xuBaseUIViewHolder.getLayoutPosition() != RecyclerView.NO_POSITION){
                        try {
                            mItemClickListener.itemClickLinstener(v,xuBaseUIViewHolder.getLayoutPosition());
                        }catch (IndexOutOfBoundsException e){
                            LogUtils.e(e.toString());
                        }
                    }
                }
            });

            xuBaseUIViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (xuBaseUIViewHolder.getLayoutPosition() != RecyclerView.NO_POSITION){
                        try {
                            mItemLongClickListener.itemLongClickLinstener(v,xuBaseUIViewHolder.getLayoutPosition());
                        }catch (IndexOutOfBoundsException e){
                            LogUtils.e(e.toString());
                        }
                    }
                    return false;
                }
            });

        }
        return xuBaseUIViewHolder;
    }

    /**
     * 设置占全列
     * @param xuBaseUIViewHolder
     * @param type
     */
    private void fullSpan(XuBaseUIViewHolder xuBaseUIViewHolder, final int type){
        //span size表示一个item的跨度，跨度了多少个span。
        //layoutmanager为StaggeredGridLayoutManager 和 gridlayoutmanager 当type为TYPE_MORE和TYPE_MORE_FAIL时,设置span size为全屏
        if (mLayoutManager != null){
            if (mLayoutManager instanceof StaggeredGridLayoutManager){
                //获取当前列数
                if (((StaggeredGridLayoutManager)mLayoutManager).getSpanCount() != 1){
                    StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) xuBaseUIViewHolder.itemView.getLayoutParams();
                    layoutParams.setFullSpan(true);
                }
            }else if (mLayoutManager instanceof GridLayoutManager){
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) mLayoutManager;
                final GridLayoutManager.SpanSizeLookup oldSpanSizeLookup = gridLayoutManager.getSpanSizeLookup();
                //setSpanSizeLookup可以让你根据position来设置 span size
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (getItemViewType(position) == type){
                            return gridLayoutManager.getSpanCount();
                        }
                        if (oldSpanSizeLookup != null){
                            return oldSpanSizeLookup.getSpanSize(position);
                        }
                        return 1;
                    }
                });
            }
        }

    }

    @Override
    public void onBindViewHolder(XuBaseUIViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_MORE){
            fullSpan(holder,viewType);
        }else if (viewType == TYPE_MORE_FAIL){
            fullSpan(holder,viewType);
        }else {
            bindView(holder,position,mDataLists.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int i = (mLoadmoreDataListener == null) ? 0 : (mIsShowMoreView && mEnableLoadMore) || (!mIsShowMoreView && !mEnableLoadMore) ? 1 : 0;
        if (mDataLists != null){
            return mDataLists.size() + i;
        }else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsShowMoreView && mEnableLoadMore && mLoadmoreDataListener != null && position == getItemCount() - 1){
            return TYPE_MORE;
        }else if (!mIsShowEmptyView && !mEnableLoadMore && mLoadmoreDataListener != null && position == getItemCount() - 1){
            return TYPE_MORE_FAIL;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public abstract int getItemLayoutId();

    public abstract void bindView(XuBaseUIViewHolder holder,int position, T item);

    /**
     * 刷新
     * @param dataLists
     */
    public abstract void refreshData(List<T> dataLists);

    /**
     * 设置加载更多监听
     * @param loadmoreDataListener
     */
    public void setOnLoadmoreDataListener(OnLoadMoreDataListener loadmoreDataListener) {
        mLoadmoreDataListener = loadmoreDataListener;
        mEnableLoadMore = true;
        mIsShowMoreView = true;

    }

    /**
     * 设置item点击事件
     * @param itemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    /**
     * 设置item长按点击事件
     * @param itemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener){
        mItemLongClickListener = itemLongClickListener;
    }


    public void loadSuccess(List<T> dataLists){
        mEnableLoadMore = true;
        mIsShowMoreView = true;
        mDataLists = dataLists;
        notifyDataSetChanged();
    }

    public void loadFailer(){
        mEnableLoadMore = false;
        mIsShowMoreView = false;
        notifyItemChanged(getItemCount() - 1);
    }

    /**
     * 设置是否底部加载
     * @param enableLoadMore
     */
    public void setmEnableLoadMore(boolean enableLoadMore) {
        mEnableLoadMore = enableLoadMore;
    }
}
