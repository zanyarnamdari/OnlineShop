package com.modavagostar.order;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

/**
 * Created by Xaniar on 4/18/2018.
 */

class Headers2 {


    private static final String AUTHORIZATION = "Android";

    static GlideUrl getUrlWithHeaders(String url, String z) {
        return new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Authorization", AUTHORIZATION)
                .addHeader("tokens", z)

                .build());
    }
}