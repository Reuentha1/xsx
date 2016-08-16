package stan.reuenthal.com.xsx.keyboard;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public interface Playable {
    long getDuration();
    String getPath();
    boolean isAudioEqual(Playable audio);
}