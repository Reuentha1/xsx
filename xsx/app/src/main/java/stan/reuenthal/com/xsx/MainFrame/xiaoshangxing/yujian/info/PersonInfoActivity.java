package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.yujian.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.RoundedImageView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;

/**
 * Created by 15828 on 2016/7/25.
 */
public class PersonInfoActivity extends BaseActivity {
    private TextView name, xueyuan, hometown, tag;
    private RoundedImageView head, img1, img2, img3, img4;
    private String tagContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yujian_personinfo);
        name = (TextView) findViewById(R.id.personinfo_name);
        xueyuan = (TextView) findViewById(R.id.personinfo_xueyaun);
        hometown = (TextView) findViewById(R.id.personifo_hometown_content);
        tag = (TextView) findViewById(R.id.personinfo_tag_content);
        head = (RoundedImageView) findViewById(R.id.personinfo_headimg);
        img1 = (RoundedImageView) findViewById(R.id.dynamic_image1);
        img2 = (RoundedImageView) findViewById(R.id.dynamic_image2);
        img3 = (RoundedImageView) findViewById(R.id.dynamic_image3);
        img4 = (RoundedImageView) findViewById(R.id.dynamic_image4);

        tagContent = "标签1  标签2  标签3  标签4  标签5";
        if (tagContent != null) {
            String s = tagContent.substring(0, 21) + "...";
            tag.setText(s);
        } else tag.setText("");


    }

    public void Back(View view) {
        finish();
    }

    public void Next(View view) {
        Intent intent = new Intent(this, SetInfoActivity.class);
        startActivity(intent);
    }

    public void More(View view) {
        Intent intent = new Intent(this, MoreInfoActivity.class);
        startActivity(intent);
    }

    //打个招呼
    public void SayHello(View view) {

    }
}
