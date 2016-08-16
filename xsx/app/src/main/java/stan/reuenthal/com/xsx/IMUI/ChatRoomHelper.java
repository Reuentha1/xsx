package stan.reuenthal.com.xsx.IMUI;

import java.util.HashMap;
import java.util.Map;

import stan.reuenthal.com.xsx.R;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class ChatRoomHelper {
    private static final int[] imageRes = {R.mipmap.room_cover_36, R.mipmap.room_cover_37, R.mipmap.room_cover_49,
            R.mipmap.room_cover_50, R.mipmap.room_cover_57, R.mipmap.room_cover_58, R.mipmap.room_cover_64,
            R.mipmap.room_cover_72};

    private static Map<String, Integer> roomCoverMap = new HashMap<>();
    private static int index = 0;

    public static void init() {
        ChatRoomMemberCache.getInstance().clear();
        ChatRoomMemberCache.getInstance().registerObservers(true);
    }

    public static void logout() {
        ChatRoomMemberCache.getInstance().registerObservers(false);
        ChatRoomMemberCache.getInstance().clear();
    }

    public static void setCoverImage(String roomId, ImageViewEx coverImage) {
        if (roomCoverMap.containsKey(roomId)) {
            coverImage.setImageResource(roomCoverMap.get(roomId));
        } else {
            if (index > imageRes.length) {
                index = 0;
            }
            roomCoverMap.put(roomId, imageRes[index]);
            coverImage.setImageResource(imageRes[index]);
            index++;
        }
    }
}
