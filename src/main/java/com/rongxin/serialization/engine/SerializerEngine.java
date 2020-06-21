package com.rongxin.serialization.engine;

import com.rongxin.serialization.common.SerializeType;
import com.rongxin.serialization.serializer.ISerializer;
import com.rongxin.serialization.serializer.impl.DefaultJavaSerializer;
import com.rongxin.serialization.serializer.impl.HessianSerializer;
import com.rongxin.serialization.serializer.impl.JSONSerializer;
import com.rongxin.serialization.serializer.impl.XmlSerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author com.rongxin
 */
public class SerializerEngine {

    public static final Map<SerializeType, ISerializer> serializerMap = new ConcurrentHashMap<SerializeType,
        ISerializer>();

    static {
        serializerMap.put(SerializeType.DefaultJavaSerializer, new DefaultJavaSerializer());
        serializerMap.put(SerializeType.HessianSerializer, new HessianSerializer());
        serializerMap.put(SerializeType.JSONSerializer, new JSONSerializer());
        serializerMap.put(SerializeType.XmlSerializer, new XmlSerializer());
    }


    public static <T> byte[] serialize(T obj, SerializeType serializeType) {
        ISerializer serializer = serializerMap.get(serializeType);
        if (serializer == null) {
            throw new RuntimeException("serialize error");
        }

        try {
            return serializer.serialize(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T deserialize(byte[] data, Class<T> clazz, SerializeType serializeType) {
        ISerializer serializer = serializerMap.get(serializeType);
        if (serializer == null) {
            throw new RuntimeException("serialize error");
        }

        try {
            return serializer.deserialize(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
