package ru.asaper.calendarpluscb.network;


import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import ru.asaper.calendarpluscb.BuildConfig;
import ru.asaper.calendarpluscb.network.models.base.ExtraDouble;
import ru.asaper.calendarpluscb.network.models.transformers.ExtraDoubleTransformer;

public class ServiceGenerator {

    private static final String API_BASE_URL = "http://www.cbr.ru/scripts/";

//    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
//    private static Retrofit.Builder builder = new Retrofit.Builder()
//            .baseUrl(API_BASE_URL)
//            .addConverterFactory(SimpleXmlConverterFactory.create());
//
//    static {
//
//        RegistryMatcher m = new RegistryMatcher();
//        m.bind(ExtraDouble.class, new ExtraDoubleTransformer());
//
//        Serializer ser = new Persister(m);
//
//
//        if (BuildConfig.DEBUG) {
//            okHttpClient.addInterceptor(
//                    new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//            );
//        }
//    }

    public static <T> T createService(Class<T> serviceClass) {
        try {
            // TODO: make all as static
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                okHttpClient.addInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                );
            }

            RegistryMatcher m = new RegistryMatcher();
            m.bind(ExtraDouble.class, new ExtraDoubleTransformer());
            Serializer ser = new Persister(m);

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create(ser));

            return builder
                    .client(okHttpClient.build())
                    .build()
                    .create(serviceClass);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
