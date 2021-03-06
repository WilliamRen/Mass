package xyz.geminiwen.mass.dagger.modules;

import android.content.Context;
import android.support.compat.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import xyz.geminiwen.mass.net.ServiceInterceptor;

/**
 * Created by geminiwen on 16/8/25.
 */
@Singleton
@Module
public class AppModule {
    Context mContext;

    public AppModule(Context context){
        this.mContext = context;
    }

    @Provides
    public Context provideContext(){
        return mContext;
    }

    @Provides static OkHttpClient provideHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return builder.addInterceptor(new ServiceInterceptor())
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
