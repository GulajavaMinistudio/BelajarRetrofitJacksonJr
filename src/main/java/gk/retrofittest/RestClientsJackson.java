package gk.retrofittest;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.Retrofit;

/**
 * Created by Gulajava Ministudio on 11/13/15.
 */
public class RestClientsJackson<T> {

    //use class T as generic for Jackson Jr parsing reference
    //kelas T sebagai kelas referensi untuk parsing Jackson Jr
    private Class<T> mClass;
    private OkHttpClient mOkHttpClient;
    public static final String ALAMAT_SERVERS = "https://api.github.com";


    public RestClientsJackson(Class<T> aClass) {
        mClass = aClass;

        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(15, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
    }

    public RestClientsJackson() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(15, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
    }


    //DENGAN CONVERTER JACKSON
    public Apis getApiServicesJackson() {

        Retrofit.Builder retrobuilder = new Retrofit.Builder();
        retrobuilder.baseUrl(ALAMAT_SERVERS);
        retrobuilder.client(mOkHttpClient);

        retrobuilder.addConverterFactory(new JacksonJrConverter<>(mClass));

        Retrofit retrofits = retrobuilder.build();

        return retrofits.create(Apis.class);
    }


    //DENGAN CONVERTER JACKSON JR LIST ARRAY
    public Apis getApiServicesJacksonArray() {

        Retrofit.Builder retrobuilder = new Retrofit.Builder();
        retrobuilder.baseUrl(ALAMAT_SERVERS);
        retrobuilder.client(mOkHttpClient);

        retrobuilder.addConverterFactory(new JacksonJrConverter<>(mClass));

        Retrofit retrofits = retrobuilder.build();

        return retrofits.create(Apis.class);
    }


    //DENGAN STRING RESPONSE
    public Apis getApiServicesString() {

        Log.w("API NULL", "API NULL, INIT LAGI");

        Retrofit.Builder retrobuilder = new Retrofit.Builder();
        retrobuilder.baseUrl(ALAMAT_SERVERS);
        retrobuilder.client(mOkHttpClient);

        retrobuilder.addConverterFactory(new StringConverters());

        Retrofit retrofits = retrobuilder.build();

        return retrofits.create(Apis.class);

    }


}
