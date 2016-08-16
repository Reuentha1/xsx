package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 打开或关闭软键盘
 * 
 * @author zhy
 * 
 */
public class KeyBoardUtils
{
	/**
	 * 打卡软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void openKeybord(EditText mEditText, Context mContext)
	{
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	public static void openKeybord(View view, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 * 
	 * @param mEditText
	 *            输入框
	 * @param mContext
	 *            上下文
	 */
	public static void closeKeybord(EditText mEditText, Context mContext)
	{
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}


	//	监听键盘高度变化
	public static void observeSoftKeyboard(Activity activity, final OnSoftKeyboardChangeListener listener) {
		final View decorView = activity.getWindow().getDecorView();
		decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			int previousKeyboardHeight = -1;

			@Override
			public void onGlobalLayout() {
				Rect rect = new Rect();
				decorView.getWindowVisibleDisplayFrame(rect);
				int displayHeight = rect.bottom - rect.top;
				int height = decorView.getHeight();
				int keyboardHeight = height - displayHeight;
				if (previousKeyboardHeight != keyboardHeight) {
					boolean hide = (double) displayHeight / height > 0.8;
					listener.onSoftKeyBoardChange(keyboardHeight, !hide);
				}

				previousKeyboardHeight = height;

			}
		});
	}

	public interface OnSoftKeyboardChangeListener {
		void onSoftKeyBoardChange(int softKeybardHeight, boolean visible);
	}

}
