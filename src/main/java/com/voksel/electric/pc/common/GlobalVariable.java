package com.voksel.electric.pc.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edsarp on 8/17/16.
 */
public class GlobalVariable {

    private static GlobalVariable instanze = null;
    private Map<String, Object> variable = new HashMap<String, Object>(0);

    public GlobalVariable() {
    }
    public static GlobalVariable getInstance()
    {
        if (instanze == null)
            instanze = new GlobalVariable();
        return instanze;
    }
    public void put(String key, Object obj)
    {
        this.variable.put(key, obj);
    }

    public Object get(String key)
    {
        return this.variable.get(key);
    }
    public void clear()
    {
        this.variable.clear();
    }

}
