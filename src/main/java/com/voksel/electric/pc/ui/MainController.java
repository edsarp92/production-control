package com.voksel.electric.pc.ui;


import com.voksel.electric.pc.component.SidebarPage;
import com.voksel.electric.pc.component.SidebarPageAbs;
import com.voksel.electric.pc.component.MenuLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Edsarp on 7/3/2016.
 */
@org.springframework.stereotype.Component
@Scope("desktop")
public class MainController extends SelectorComposer<Borderlayout> {

    @WireVariable
    MenuLoader menuLoader;
    @Autowired
    Environment environment;
    @Wire
    Grid westInfoGrid;
    @Wire
    Label lblUser;
    @Wire
    Label lblDate;
    @Wire
    Label lblTime;
    @Wire
    Combobox cmbRole;

    @Wire
    Grid navList;

    @WireVariable
    SidebarPageAbs sidebarPageAbs;

    private ListModelList<GrantedAuthority> roleModels;

    public void doAfterCompose(Borderlayout comp) throws Exception {
        super.doAfterCompose(comp);
        this.westInfoGrid.setVisible(Boolean.parseBoolean(this.environment.getProperty("application.show-left-info-panel", "true")));
        SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");
        Clients.evalJavaScript("startClock(\'" + sf.format(new Date(System.currentTimeMillis())) + "\')");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.lblUser.setValue(userDetails.getUsername());
        this.lblDate.setValue((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
        this.roleModels = new ListModelList(new ArrayList(userDetails.getAuthorities()));
        Iterator i$ = this.roleModels.iterator();
        while (i$.hasNext()) {
            GrantedAuthority branch = (GrantedAuthority) i$.next();
            this.roleModels.setSelection(Collections.singleton(branch));
            break;
        }
        this.cmbRole.setModel(this.roleModels);
        Rows rows = navList.getRows();
        for(SidebarPage page:sidebarPageAbs.getPages()){
            Row row = constructSidebarRow(page.getName(),page.getLabel(),page.getIconUri(),page.getUri());
            rows.appendChild(row);
        }
    }

    private Row constructSidebarRow(final String name,String label, String imageSrc, final String locationUri) {
        Row row = new Row();
        Image image = new Image(imageSrc);
        Label lab = new Label(label);
        row.appendChild(image);
        row.appendChild(lab);
        row.setSclass("sidebar-fn");
        org.zkoss.zk.ui.event.EventListener<Event> onActionListener = new SerializableEventListener<Event>(){
            private static final long serialVersionUID = 1L;
            public void onEvent(Event event) throws Exception {
                if(locationUri.startsWith("http")){
                    Executions.getCurrent().sendRedirect(locationUri);
                }else{

                    Include include = (Include) Selectors.iterable(navList.getPage(), "#mainInclude")
                            .iterator().next();
                    include.setSrc(locationUri);
                    if(name!=null){
                        getPage().getDesktop().setBookmark("p_"+name);
                    }
                }
            }
        };
        row.addEventListener(Events.ON_DOUBLE_CLICK, onActionListener);
        return row;
    }

}
