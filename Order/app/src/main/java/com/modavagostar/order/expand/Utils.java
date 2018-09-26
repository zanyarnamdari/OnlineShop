package com.modavagostar.order.expand;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.modavagostar.order.app.AppController;
import com.modavagostar.order.model.globaluser;
import com.modavagostar.order.utils.Services;
import com.modavagostar.order.utils.SharedpreferenceHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janisharali on 21/08/16.
 */
public class Utils {

    private static final String TAG = "Utils";

    public static List<Feed> loadFeeds(final Context context, String m) throws UnsupportedEncodingException, JSONException {

        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();


            JSONArray array = new JSONArray(m);
            List<Feed> feedList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                Feed feed = gson.fromJson(array.getString(i), Feed.class);
                feedList.add(feed);
            }
            return feedList;
        } catch (Exception e) {
//            Log.d(TAG,"seedGames parseException " + e);
            e.printStackTrace();
            return null;
        }
    }


}
