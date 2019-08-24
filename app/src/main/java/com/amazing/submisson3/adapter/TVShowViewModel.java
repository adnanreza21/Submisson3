package com.amazing.submisson3.adapter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TVShowViewModel extends ViewModel {
    private static final String API_KEY = "6988242597b26f4e15202b4ed3e71751";
    private MutableLiveData<ArrayList<TVShow>> listTvShow = new MutableLiveData<>();

    public void setTvShow(final String tvShow) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TVShow> listItem = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" +API_KEY+ "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {

                        JSONObject tv = list.getJSONObject(i);
                        TVShow tvShowItems = new TVShow(tv);
                        listItem.add(tvShowItems);
                    }
                    listTvShow.postValue(listItem);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }

        });

    }
    public LiveData<ArrayList<TVShow>> getTvShow() {
        return listTvShow;
    }
}