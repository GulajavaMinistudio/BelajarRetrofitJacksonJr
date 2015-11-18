package gk.retrofittest;

import com.fasterxml.jackson.jr.ob.JSON;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
//import java.lang.reflect.Field;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.Converter;

/**
 * Created by Gulajava Ministudio on 11/18/15.
 */
public class JacksonJrResponseArrayConverter<T> implements Converter<ResponseBody, List<T>> {

    private Class<T> clazz;
//    private List<T> mTList;

    public JacksonJrResponseArrayConverter(Class<T> tClass) {
        this.clazz = tClass;
//        mTList = new ArrayList<>();
    }

    @Override
    public List<T> convert(ResponseBody value) throws IOException {


        try {
            return JSON.std.listOfFrom(clazz, value.bytes());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
//        try {
//            Field listField = JacksonJrResponseArrayConverter.class.getDeclaredField("mTList");
//            ParameterizedType listType = (ParameterizedType) listField.getGenericType();
//            Type typelist = listType.getRawType();
//
//            if (typelist == java.util.List.class) {
//
//                return JSON.std.listOfFrom(clazz, value.bytes());
//
//            } else {
//                return new ArrayList<>();
//            }
//
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
    }
}
