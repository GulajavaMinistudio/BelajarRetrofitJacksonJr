package gk.retrofittest;

import android.util.Log;

import com.fasterxml.jackson.jr.ob.JSON;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import retrofit.Converter;

/**
 * Created by Gulajava Ministudio on 11/13/15.
 */
public class JacksonJrArrayConverters<T> extends Converter.Factory {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    protected static final String PROTOCOL_CHARSET = "utf-8";

    private Class<T> clazz;
    public List<T> mTList;

    public JacksonJrArrayConverters(Class<T> tClass, List<T> lists) {
        super();
        this.clazz = tClass;
        mTList = lists;
    }


    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        super.fromResponseBody(type, annotations);

        try {

            Field listField = JacksonJrArrayConverters.class.getDeclaredField("mTList");
            ParameterizedType listType = (ParameterizedType) listField.getGenericType();
            Type typelist = listType.getRawType();

            ParameterizedType listTypeConverter = (ParameterizedType) type;
            Type typelistconverter = listTypeConverter.getRawType();

            if (typelist == typelistconverter) {

                Log.w("SAME TYPE", "LIST IS THE TYPE");

                return new Converter<ResponseBody, List<T>>() {
                    @Override
                    public List<T> convert(ResponseBody value) throws IOException {

                        return JSON.std.listOfFrom(clazz, value.bytes());
                    }
                };
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        super.toRequestBody(type, annotations);

        if (clazz == type) {
            return new Converter<T, RequestBody>() {
                @Override
                public RequestBody convert(T value) throws IOException {

                    String strs = JSON.std.asString(value);

                    return RequestBody.create(MEDIA_TYPE, strs.getBytes(PROTOCOL_CHARSET));
                }
            };
        }
        return null;
    }
}
