package gk.retrofittest;

import com.fasterxml.jackson.jr.ob.JSON;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by Gulajava Ministudio on 11/13/15.
 */
public class JacksonJrConverters<T> extends Converter.Factory {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    protected static final String PROTOCOL_CHARSET = "utf-8";

    private Class<T> clazz;

    public JacksonJrConverters(Class<T> tClass) {
        super();
        this.clazz = tClass;
    }


    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        super.fromResponseBody(type, annotations);

        if (clazz.equals(type)) {

            return new Converter<ResponseBody, T>() {
                @Override
                public T convert(ResponseBody value) throws IOException {

                    return JSON.std.beanFrom(clazz, value.bytes());
                }
            };
        }

        return null;
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        super.toRequestBody(type, annotations);

        if (clazz.equals(type)) {
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
