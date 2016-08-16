package stan.reuenthal.com.xsx.IMUI;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */
final class ServerConfig {

    public enum ServerEnv {
        TEST("t"),
        PRE_REL("p"),
        REL("r"),

        ;
        String tag;

        ServerEnv(String tag) {
            this.tag = tag;
        }
    }

    public static boolean testServer() {
        return ServerEnvs.SERVER == ServerEnv.TEST;
    }
}
