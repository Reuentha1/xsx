package stan.reuenthal.com.xsx.IMUI;

import java.util.HashMap;
import java.util.Map;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.FileUtil;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class FileIcons {

    private static final Map<String, Integer> smallIconMap = new HashMap<String, Integer>();
    static {
        smallIconMap.put("xls", R.mipmap.file_ic_session_excel);
        smallIconMap.put("ppt", R.mipmap.file_ic_session_ppt);
        smallIconMap.put("doc", R.mipmap.file_ic_session_word);
        smallIconMap.put("xlsx", R.mipmap.file_ic_session_excel);
        smallIconMap.put("pptx", R.mipmap.file_ic_session_ppt);
        smallIconMap.put("docx", R.mipmap.file_ic_session_word);
        smallIconMap.put("pdf", R.mipmap.file_ic_session_pdf);
        smallIconMap.put("html", R.mipmap.file_ic_session_html);
        smallIconMap.put("htm", R.mipmap.file_ic_session_html);
        smallIconMap.put("txt", R.mipmap.file_ic_session_txt);
        smallIconMap.put("rar", R.mipmap.file_ic_session_rar);
        smallIconMap.put("zip", R.mipmap.file_ic_session_zip);
        smallIconMap.put("7z", R.mipmap.file_ic_session_zip);
        smallIconMap.put("mp4", R.mipmap.file_ic_session_mp4);
        smallIconMap.put("mp3", R.mipmap.file_ic_session_mp3);
        smallIconMap.put("png", R.mipmap.file_ic_session_png);
        smallIconMap.put("gif", R.mipmap.file_ic_session_gif);
        smallIconMap.put("jpg", R.mipmap.file_ic_session_jpg);
        smallIconMap.put("jpeg", R.mipmap.file_ic_session_jpg);
    }

    private static final Map<String, Integer> bigIconMap = new HashMap<String, Integer>();
    static {
        bigIconMap.put("xls", R.mipmap.file_ic_detail_excel);
        bigIconMap.put("ppt", R.mipmap.file_ic_detail_ppt);
        bigIconMap.put("doc", R.mipmap.file_ic_detail_word);
        bigIconMap.put("xlsx", R.mipmap.file_ic_detail_excel);
        bigIconMap.put("pptx", R.mipmap.file_ic_detail_ppt);
        bigIconMap.put("docx", R.mipmap.file_ic_detail_word);
        bigIconMap.put("pdf", R.mipmap.file_ic_detail_pdf);
        bigIconMap.put("html", R.mipmap.file_ic_detail_html);
        bigIconMap.put("htm", R.mipmap.file_ic_detail_html);
        bigIconMap.put("txt", R.mipmap.file_ic_detail_txt);
        bigIconMap.put("rar", R.mipmap.file_ic_detail_rar);
        bigIconMap.put("zip", R.mipmap.file_ic_detail_zip);
        bigIconMap.put("7z", R.mipmap.file_ic_detail_zip);
        bigIconMap.put("mp4", R.mipmap.file_ic_detail_mp4);
        bigIconMap.put("mp3", R.mipmap.file_ic_detail_mp3);
        bigIconMap.put("png", R.mipmap.file_ic_detail_png);
        bigIconMap.put("gif", R.mipmap.file_ic_detail_gif);
        bigIconMap.put("jpg", R.mipmap.file_ic_detail_jpg);
        bigIconMap.put("jpeg", R.mipmap.file_ic_detail_jpg);
    }

    public static int smallIcon(String fileName) {
        String ext = FileUtil.getExtensionName(fileName).toLowerCase();
        Integer resId = smallIconMap.get(ext);
        if (resId == null) {
            return R.mipmap.file_ic_session_unknow;
        }

        return resId.intValue();
    }

    public static int bigIcon(String fileName) {
        String ext = FileUtil.getExtensionName(fileName).toLowerCase();
        Integer resId = bigIconMap.get(ext);
        if (resId == null) {
            return R.mipmap.file_ic_detail_unknow;
        }

        return resId.intValue();
    }
}
