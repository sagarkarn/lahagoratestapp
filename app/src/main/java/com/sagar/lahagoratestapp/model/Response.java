package com.sagar.lahagoratestapp.model;

import com.android.volley.VolleyError;

public class Response<T> {
    public final T success;
    public final VolleyError error;
    public Response(T success, VolleyError error) {
        this.success = success;
        this.error = error;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", error=" + error +
                '}';
    }
}
