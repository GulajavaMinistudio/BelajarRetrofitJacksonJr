package gk.retrofittest;

import com.fasterxml.jackson.jr.ob.JSON;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Converter;

/**
 * Created by Gulajava Ministudio on 11/18/15.
 */
public class JacksonJrResponseConverter<T> implements Converter<ResponseBody, T> {


    private Class<T> clazz;

    public JacksonJrResponseConverter(Class<T> tClass) {
        this.clazz = tClass;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        return JSON.std.beanFrom(clazz, value.bytes());
    }
}
