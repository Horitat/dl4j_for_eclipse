package deeplearning4j_ui_parent.deeplearning4j_ui_model.src.main.java.org.deeplearning4j.ui.storage;
//package org.deeplearning4j.ui.storage;

import org.agrona.DirectBuffer;
import org.agrona.MutableDirectBuffer;
//import org.deeplearning4j.api.storage.Persistable;

import deeplearning4j_core.src.main.java.org.deeplearning4j.api.storage.Persistable;

/**
 * Created by Alex on 07/10/2016.
 */
public interface AgronaPersistable extends Persistable {

    void encode(MutableDirectBuffer buffer);

    void decode(DirectBuffer buffer);

}
