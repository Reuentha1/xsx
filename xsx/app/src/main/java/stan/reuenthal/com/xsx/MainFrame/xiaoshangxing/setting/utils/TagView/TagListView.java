/**
 *
 */
package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.TagView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;

import java.util.ArrayList;
import java.util.List;


public class TagListView extends FlowLayout implements OnClickListener, View.OnLongClickListener {

    private boolean mIsDeleteMode;
    //private OnTagCheckedChangedListener mOnTagCheckedChangedListener;
    private OnTagClickListener mOnTagClickListener;
    private OnTagLongClickListener mOnTagLongClickListener;
    //	private int mTagViewBackgroundResId;
    private int mTagViewTextColorResId;
    private final List<Tag> mTags = new ArrayList<Tag>();

    /**
     * @param context
     */
    public TagListView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    /**
     * @param context
     * @param attributeSet
     */
    public TagListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        // TODO Auto-generated constructor stub
        init();
    }

    /**
     * @param context
     * @param attributeSet
     * @param defStyle
     */
    public TagListView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        // TODO Auto-generated constructor stub
        init();
    }


    private void init() {

    }

    private void inflateTagView(final Tag t, boolean b) {

        TextView localTagView = (TextView) View.inflate(getContext(),
                R.layout.tag, null);
        localTagView.setText(t.getTitle());
        localTagView.setTag(t);
//		localTagView.setWidth(20);

        if (mTagViewTextColorResId <= 0) {
            int c = getResources().getColor(R.color.w0);
            localTagView.setTextColor(c);

        }

        localTagView.setBackgroundResource(R.drawable.tag_normal);


//		localTagView.setChecked(t.isChecked());
//		localTagView.setCheckEnable(b);
        if (mIsDeleteMode) {
            int k = (int) TypedValue.applyDimension(1, 5.0F, getContext()
                    .getResources().getDisplayMetrics());
            localTagView.setPadding(localTagView.getPaddingLeft(),
                    localTagView.getPaddingTop(), (int) getResources().getDimension(R.dimen.x24),
                    localTagView.getPaddingBottom());
            localTagView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.mipmap.tag_close, 0);
        }

        localTagView.setOnClickListener(this);
        localTagView.setOnLongClickListener(this);
//		localTagView
//				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//					public void onCheckedChanged(
//							CompoundButton paramAnonymousCompoundButton,
//							boolean paramAnonymousBoolean) {
//						t.setChecked(paramAnonymousBoolean);
//						if (TagListView.this.mOnTagCheckedChangedListener != null) {
//							TagListView.this.mOnTagCheckedChangedListener
//									.onTagCheckedChanged(
//											(TagView) paramAnonymousCompoundButton,
//											t);
//						}
//					}
//				});
        addView(localTagView);
    }

    public void addTag(int i, String s) {
        addTag(i, s, false);
    }

    public void addTag(int i, String s, boolean b) {
        addTag(new Tag(i, s), b);
    }

    public void addTag(Tag tag) {
        addTag(tag, false);
    }

    public void addTag(Tag tag, boolean b) {
        mTags.add(tag);
        inflateTagView(tag, b);
    }

    public void addTags(List<Tag> lists) {
        addTags(lists, false);
    }

    public void addTags(List<Tag> lists, boolean b) {
        for (int i = 0; i < lists.size(); i++) {
            addTag((Tag) lists.get(i), b);
        }
    }

    public List<Tag> getTags() {
        return mTags;
    }

    public View getViewByTag(Tag tag) {
        return findViewWithTag(tag);
    }

    public void removeTag(Tag tag) {
        mTags.remove(tag);
        removeView(getViewByTag(tag));
    }

    public void setDeleteMode(boolean b) {
        mIsDeleteMode = b;
    }

//	public void setOnTagCheckedChangedListener(
//			OnTagCheckedChangedListener onTagCheckedChangedListener) {
//		mOnTagCheckedChangedListener = onTagCheckedChangedListener;
//	}

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        mOnTagClickListener = onTagClickListener;
    }

    public void setOnTagLongClickListener(OnTagLongClickListener onTagLongClickListener) {
        mOnTagLongClickListener = onTagLongClickListener;
    }


//	public void setTagViewBackgroundRes(int res) {
//		mTagViewBackgroundResId = res;
//	}

    public void setTagViewTextColorRes(int res) {
        mTagViewTextColorResId = res;
    }

    public void setTags(List<? extends Tag> lists) {
        setTags(lists, false);
    }

    public void setTags(List<? extends Tag> lists, boolean b) {
        removeAllViews();
        mTags.clear();
        for (int i = 0; i < lists.size(); i++) {
            addTag((Tag) lists.get(i), b);
        }
    }

    @Override
    public void onClick(View v) {
        if ((v instanceof TextView)) {
            Tag localTag = (Tag) v.getTag();
            if (this.mOnTagClickListener != null) {
                this.mOnTagClickListener.onTagClick((TextView) v, localTag);
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if ((v instanceof TextView)) {
            Tag localTag = (Tag) v.getTag();
            if (this.mOnTagLongClickListener != null) {
                this.mOnTagLongClickListener.onTagLongClick((TextView) v, localTag);
            }
        }
        return true;
    }

//	public static abstract interface OnTagCheckedChangedListener {
//		public abstract void onTagCheckedChanged(TextView tagView, Tag tag);
//	}

    public interface OnTagClickListener {
        void onTagClick(TextView tagView, Tag tag);
    }

    public interface OnTagLongClickListener {
        void onTagLongClick(TextView tagView, Tag tag);
    }

}
