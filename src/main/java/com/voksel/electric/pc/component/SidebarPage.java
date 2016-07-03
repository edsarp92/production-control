package com.voksel.electric.pc.component;

import java.io.Serializable;

/**
 * Created by Edsarp on 7/3/2016.
 */
public class SidebarPage implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    String label;
    String iconUri;
    String uri;

    public SidebarPage(String name, String label, String iconUri, String uri) {
        this.name = name;
        this.label = label;
        this.iconUri = iconUri;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getIconUri() {
        return iconUri;
    }

    public String getUri() {
        return uri;
    }
}
