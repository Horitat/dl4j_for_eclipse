package deeplearning4j_scaleout.spark.dl4j_spark.src.main.java.org.deeplearning4j.spark.util.serde;
//package org.deeplearning4j.spark.util.serde;

import java.io.IOException;

import org.apache.spark.storage.StorageLevel;
import org.nd4j.shade.jackson.core.JsonParser;
import org.nd4j.shade.jackson.core.JsonProcessingException;
import org.nd4j.shade.jackson.databind.DeserializationContext;
import org.nd4j.shade.jackson.databind.JsonDeserializer;
import org.nd4j.shade.jackson.databind.JsonNode;

/**
 * By default: Spark storage levels don't serialize/deserialize cleanly with Jackson (i.e., we can get different results out).
 * So we'll manually control the serialization/deserialization for StorageLevel objects
 *
 * @author Alex Black
 */
public class StorageLevelDeserializer extends JsonDeserializer<StorageLevel> {
    @Override
    public StorageLevel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.textValue();
        if(value == null || "null".equals(value)){
            return null;
        }
        return StorageLevel.fromString(value);
    }
}
