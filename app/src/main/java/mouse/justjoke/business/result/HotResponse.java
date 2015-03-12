package mouse.justjoke.business.result;

import com.google.gson.JsonObject;

/**
 * Created by mouse on 15/3/12.
 */
public class HotResponse {

    private JsonObject data;

    private String paging;

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    public String getPaging() {
        return paging;
    }

    public void setPaging(String paging) {
        this.paging = paging;
    }
}
