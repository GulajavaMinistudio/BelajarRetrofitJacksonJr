package gk.retrofittest;

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
public class StringConverters extends Converter.Factory {

    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

    public StringConverters() {
        super();
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        super.fromResponseBody(type, annotations);

        if (String.class == type) {

            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(ResponseBody value) throws IOException {

                    return value.string();
                }
            };
        }

        return null;
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        super.toRequestBody(type, annotations);

        if (String.class == type) {
            return new Converter<String, RequestBody>() {
                @Override
                public RequestBody convert(String value) throws IOException {
                    return RequestBody.create(MEDIA_TYPE, value);
                }
            };
        }
        return null;
    }
}
