package deeplearning4j_core.src.test.java.org.deeplearning4j.util;

//import org.deeplearning4j.util.UIDProvider;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import deeplearning4j_nn.src.main.java.org.deeplearning4j.util.UIDProvider;

/**
 * Created by Alex on 26/06/2016.
 */
public class TestUIDProvider {

    @Test
    public void testUIDProvider(){
        String jvmUID = UIDProvider.getJVMUID();
        String hardwareUID = UIDProvider.getHardwareUID();

        assertNotNull(jvmUID);
        assertNotNull(hardwareUID);

        assertTrue(!jvmUID.isEmpty());
        assertTrue(!hardwareUID.isEmpty());

        assertEquals(jvmUID, UIDProvider.getJVMUID());
        assertEquals(hardwareUID, UIDProvider.getHardwareUID());

        System.out.println("JVM uid:      " + jvmUID);
        System.out.println("Hardware uid: " + hardwareUID);
    }

}
