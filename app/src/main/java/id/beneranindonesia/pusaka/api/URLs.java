package id.beneranindonesia.pusaka.api;

public class URLs {

    private static String SERVER = "http://dev-pusaka.beneranindonesia.id/";

    public static String TOKEN_URL   = SERVER + "token";
    public static String SIGN_UP_URL = SERVER + "auth/signup";
    public static String SIGN_IN_URL = SERVER + "auth/signin";

    public static String CONTENT_LIST_URL     = SERVER + "content/list";
    public static String CONTENT_DETAIL_URL   = SERVER + "content/get";
    public static String CONTENT_TAKE_MISSION = SERVER + "content/take";

    public static String ONGOING_MISSION_LIST = SERVER + "/users/mission/list";
    public static String ONGOING_MISSION_SIGNIN = SERVER + "/users/mission/signin";

}
