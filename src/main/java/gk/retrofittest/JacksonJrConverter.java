package gk.retrofittest;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by Gulajava Ministudio on 11/13/15.
 */
public class JacksonJrConverter<T> extends Converter.Factory {

    private Class<T> clazz;

    public JacksonJrConverter(Class<T> tClass) {
        super();
        this.clazz = tClass;
    }


    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        super.fromResponseBody(type, annotations);

        //if same as class type return as class, otherwise, return as list
        if (clazz == type) {

            return new JacksonJrResponseConverter<>(clazz);
        } else {

            return new JacksonJrResponseArrayConverter<>(clazz);
        }
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        super.toRequestBody(type, annotations);

        return new JacksonJrRequestBodyConverter<>();
    }
}
