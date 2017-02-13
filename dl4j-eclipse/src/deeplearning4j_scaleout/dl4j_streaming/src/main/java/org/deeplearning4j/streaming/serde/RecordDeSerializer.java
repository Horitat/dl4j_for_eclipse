package deeplearning4j_scaleout.dl4j_streaming.src.main.java.org.deeplearning4j.streaming.serde;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.kafka.common.serialization.Deserializer;
import org.datavec.api.writable.Writable;
//import org.deeplearning4j.util.SerializationUtils;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.util.SerializationUtils;

/**
 * Record de serializer for datavec
 * @author Adam Gibson
 */
public class RecordDeSerializer implements Deserializer<Collection<Collection<Writable>>> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Collection<Collection<Writable>> deserialize(String s, byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Collection<Collection<Writable>>  ret;
        try {
            ret = SerializationUtils.readObject(bis);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
        IOUtils.closeQuietly(bis);
        return ret;
    }

    @Override
    public void close() {

    }
}
