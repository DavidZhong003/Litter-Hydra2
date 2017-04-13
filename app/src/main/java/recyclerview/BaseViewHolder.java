package recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.utils.GlideRoundTransform;


/**
 * Created by Administrator on 2017/4/5.
 *
 */

public class BaseViewHolder<T>
        extends RecyclerView.ViewHolder {

    private   View              mRootView;
    private   Context           mContext;
    protected T                 t;
    private   SparseArray<View> mViews;

    public BaseViewHolder(View rootView) {
        super(rootView);
        mRootView = rootView;
        mContext = rootView.getContext();
    }

    /**
     * 绑定数据
     * @param t
     */
    public void bindData(T t) {}

    ;

    /**
     * ===============以下为设置数据方法
     */

    public BaseViewHolder setImageWithPlaceHolder(ImageView iv, String url) {
        Glide.with(iv.getContext())
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.RESULT)
             .placeholder(R.mipmap.item_pic_loading)
             .transform(new GlideRoundTransform(iv.getContext(), 3))
             .into(iv);
        return this;
    }

    protected BaseViewHolder setImageBigPlaceholder(ImageView iv, String url) {
        Glide.with(iv.getContext())
             .load(url)
             .diskCacheStrategy(DiskCacheStrategy.RESULT)
             .placeholder(R.mipmap.item_pic_loading_big)
             .transform(new GlideRoundTransform(iv.getContext(),10))
             .into(iv);
        return this;
    }

    /**
     * 关于事件的
     */
    public BaseViewHolder setOnClickListener(View view, View.OnClickListener listener)
    {
        view.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnTouchListener(View view, View.OnTouchListener listener)
    {
        view.setOnTouchListener(listener);
        return this;
    }

    public BaseViewHolder setOnLongClickListener(View view, View.OnLongClickListener listener)
    {
        view.setOnLongClickListener(listener);
        return this;
    }


    protected String getFormatTime(String updateTime) {
        if (updateTime == null) {
            return "";
        }
        return updateTime.substring(updateTime.indexOf(" ") + 1);
    }

    protected String forMatVideoTime(int length) {
        int s   = length % 60;//秒
        int min = length / 60;//分钟
        return min < 10
               ? "0" + min + "'" + s + "'"
               : min + "'" + s + "'";
    }

}
