package stan.reuenthal.com.xsx.IMUI;

import android.content.Intent;

import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.io.File;

import stan.reuenthal.com.xsx.IMpanel.RequestCode;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.BaseAction;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class FileAction extends BaseAction {

    public FileAction() {
        super(R.drawable.message_plus_file_selector, R.string.input_panel_file);
    }

    /**
     * **********************文件************************
     */
    private void chooseFile() {
        FileBrowserActivity.startActivityForResult(getActivity(), makeRequestCode(RequestCode.GET_LOCAL_FILE));
    }

    @Override
    public void onClick() {
        chooseFile();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCode.GET_LOCAL_FILE) {
            String path = data.getStringExtra(FileBrowserActivity.EXTRA_DATA_PATH);
            File file = new File(path);
            IMMessage message = MessageBuilder.createFileMessage(getAccount(), getSessionType(), file, file.getName());
            sendMessage(message);
        }
    }
}
