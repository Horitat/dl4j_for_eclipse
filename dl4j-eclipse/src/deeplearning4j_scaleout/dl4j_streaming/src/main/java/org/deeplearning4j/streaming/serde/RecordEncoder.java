package deeplearning4j_scaleout.dl4j_streaming.src.main.java.org.deeplearning4j.streaming.serde;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

import org.nd4j.linalg.util.SerializationUtils;

/**
 * Created by agibsonccc on 6/7/16.
 */
public class RecordEncoder implements Encoder<Object> {
   public RecordEncoder(VerifiableProperties verifiableProperties) {

   }
    @Override
    public byte[] toBytes(Object writables) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        SerializationUtils.writeObject((Serializable) writables,dataOutputStream);
        byte[] ret = byteArrayOutputStream.toByteArray();
        return ret;
    }
}
