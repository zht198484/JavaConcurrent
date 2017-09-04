package com.zht.netty.marshalling;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * Created by zht198484 on 2017/9/4.
 * marshalling factory to create decoder and encoder
 */
final class MarshallingCodeCFactory {
    static MarshallingDecoder buildMarshallingDecoder() {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        DefaultUnmarshallerProvider unmarshallerProvider = new DefaultUnmarshallerProvider(marshallerFactory, marshallingConfiguration);
        return new MarshallingDecoder(unmarshallerProvider, 1024);
    }

    static MarshallingEncoder buildMarshallingEncoder() {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        DefaultMarshallerProvider defaultMarshallerProvider = new DefaultMarshallerProvider(marshallerFactory, marshallingConfiguration);
        return new MarshallingEncoder(defaultMarshallerProvider);
    }
}
