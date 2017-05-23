package com.doive.nameless.litter_hydra.ui.news.top;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doive.nameless.litter_hydra.R;
import com.doive.nameless.litter_hydra.model.bean.TopNewsBean;
import com.doive.nameless.litter_hydra.recyclerview.BaseViewHolder;
import com.doive.nameless.litter_hydra.utils.GlideManager;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 *
 */
public class TopSliderVH
        extends BaseViewHolder<TopNewsBean.BodyBean.SubjectsBean> {
    private ViewPager mViewPager;

    public TopSliderVH(View inflate) {
        super(inflate);
        mViewPager = (ViewPager) inflate.findViewById(R.id.vp_slide_top);
        //设置切换效果
        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {

            private static final float MIN_SCALE = 0.85f;
            private static final float MIN_ALPHA = 0.5f;
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    view.setAlpha(0);

                } else if (position <= 1) { // [-1,1]
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationX(horzMargin - vertMargin / 2);
                    } else {
                        view.setTranslationX(-horzMargin + vertMargin / 2);
                    }

                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    view.setAlpha(MIN_ALPHA +
                                          (scaleFactor - MIN_SCALE) /
                                                  (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else {
                    view.setAlpha(0);
                }
            }
        });
    }

    @Override
    public void bindData(TopNewsBean.BodyBean.SubjectsBean subjectsBean) {
        super.bindData(subjectsBean);
        List<TopNewsBean.BodyBean.SubjectsBean.Poditems> podItems = subjectsBean.getPodItems();
        mViewPager.setAdapter(new SliderViewPagerAdapter(podItems));
    }

    private static class SliderViewPagerAdapter
            extends PagerAdapter {

        private List<TopNewsBean.BodyBean.SubjectsBean.Poditems> mTList;//数据源

        public SliderViewPagerAdapter(List<TopNewsBean.BodyBean.SubjectsBean.Poditems> podItems) {
            mTList = podItems;
        }

        @Override
        public int getCount() {
            return mTList == null
                   ? 0
                   : mTList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //取出数据
            TopNewsBean.BodyBean.SubjectsBean.Poditems poditems = mTList.get(position);
            Log.e("///", "instantiateItem: "+poditems );
            View inflate = LayoutInflater.from(container.getContext())
                                         .inflate(R.layout.item_slide_vp, container, false);
            ImageView thumbnail= (ImageView) inflate.findViewById(R.id.iv_slider_vp_thumbnail);
            TextView title = (TextView) inflate.findViewById(R.id.tv_slide_vp_title);
            TextView positon = (TextView) inflate.findViewById(R.id.tv_slide_vp_position);

            GlideManager.getInstance().setImage(thumbnail,poditems.getThumbnail());
            title.setText(poditems.getTitle());
            positon.setText(Html.fromHtml("<font size=\"9\"  color=\"#FF0000\">"+(position+1)+"</font> <font size=\"3\" color=\"#CFD8DC\">/"+getCount()+"</font> "));
            container.addView(inflate);
            return inflate;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
