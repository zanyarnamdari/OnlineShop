package com.modavagostar.order.app;


import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;


@GlideModule
public class CustomCachingGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//Log.e("run module","1");
        int diskCacheSizeBytes = 1024 * 1024 * 200; // 100 MB
      /*  builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));*/
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .disallowHardwareConfig());


        String downloadDirectoryPath = Environment.getDownloadCacheDirectory().getPath();


        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, "viseocache", diskCacheSizeBytes));


        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {

        // nothing to do here
    }
}