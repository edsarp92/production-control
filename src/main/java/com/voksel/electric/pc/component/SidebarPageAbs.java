package com.voksel.electric.pc.component;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Edsarp on 7/3/2016.
 */
@Service
public class SidebarPageAbs {

    HashMap<String,SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
    public SidebarPageAbs(){
        pageMap.put("001",new SidebarPage("001","Users","/asset/image/fn.png","/coming-soon.zul"));
        pageMap.put("002",new SidebarPage("002","Roles","/asset/image/fn.png","/coming-soon.zul"));
        pageMap.put("003",new SidebarPage("003","User Role","/asset/image/fn.png","/coming-soon.zul"));
        pageMap.put("004",new SidebarPage("004","Register Form","/asset/image/fn.png","/coming-soon.zul"));
        pageMap.put("005",new SidebarPage("005","Role Menu","/asset/image/fn.png","/coming-soon.zul"));
        pageMap.put("005",new SidebarPage("005","Role Menu","/asset/image/fn.png","/coming-soon.zul"));
        pageMap.put("005",new SidebarPage("005","Production Control","/asset/image/fn.png","/page/transaction/production-control.zul"));

    }

    public List<SidebarPage> getPages(){

        return new ArrayList<SidebarPage>(pageMap.values())                ;
    }

    public SidebarPage getPage(String name){
        return pageMap.get(name);
    }
}
