package deeplearning4j_ui_parent.deeplearning4j_ui.src.main.java.org.deeplearning4j.ui.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Adam Gibson
 */
@Provider
public class JsonExceptionMapper implements ExceptionMapper<JsonProcessingException> {
    @Override
    public Response toResponse(JsonProcessingException exception) {
        return Response.status(500)
                .entity("WTF")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
