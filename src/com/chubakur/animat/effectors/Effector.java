package com.chubakur.animat.effectors;

import com.chubakur.util.PhysicalBody;

/**
 * Created by User on 30.06.2014.
 */
public interface Effector {
    String getType();
    Integer effect(PhysicalBody e);
}
