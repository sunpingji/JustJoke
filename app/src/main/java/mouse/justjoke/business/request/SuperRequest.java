package mouse.justjoke.business.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by mouse on 15/3/12.
 */
public class SuperRequest<T> extends Request<T> {

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Response.Listener<T> listener;
    private Map<String, String> params;

    public SuperRequest(String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(url, clazz, Method.GET, null, listener, errorListener);
    }

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url   URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param
     */
    public SuperRequest(String url, Class<T> clazz, int method, Map<String, String> params,
                        Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.params = params;
        this.clazz = clazz;
        this.listener = listener;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return null == params ? super.getParams() : params;
    }


    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
