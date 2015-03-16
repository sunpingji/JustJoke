package mouse.justjoke.business.bean;


import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by storm on 14-3-25.
 */
public class Feed implements Serializable {

    public String id;
    public String caption;
    public String link;
    public Image images;
    public Vote votes;

    public class Image implements Serializable {
        public String small;
        public String normal;
        public String large;
    }

    public class Vote implements Serializable {
        public String count;
    }


    public static class FeedRequestData implements Serializable {
        public ArrayList<Feed> data;
        public Paging paging;

        public String getPage() {
            return paging.next;
        }
    }

    private class Paging implements Serializable {
        public String next;
    }
}
