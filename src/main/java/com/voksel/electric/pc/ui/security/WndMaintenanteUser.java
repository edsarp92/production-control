package com.voksel.electric.pc.ui.security;

import com.voksel.electric.pc.common.ComponentUtil;
import com.voksel.electric.pc.common.MessageBox;
import com.voksel.electric.pc.domain.Privilege;
import com.voksel.electric.pc.domain.entity.Role;
import com.voksel.electric.pc.domain.entity.User;
import com.voksel.electric.pc.domain.entity.UserRole;
import com.voksel.electric.pc.service.ParameterService;
import com.voksel.electric.pc.service.UserAndPrivilegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Paginal;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Component
@Scope("desktop")
public class WndMaintenanteUser extends SelectorComposer<Component> {
    final Logger log = LoggerFactory.getLogger(WndMaintenanteUser.class);
    @Wire
    Window wnd;
    @Wire
    Intbox txtUserId;
    @Wire
    Textbox txtUserName;
    @Wire
    Textbox txtEmail;
    @Wire
    Radiogroup rgStatus;
    @Wire
    Listbox lsRole;
    @Wire
    Checkbox chkAll;
    @Wire
    Listbox list;
    @Wire
    Paginal paging;
    @Wire
    Button btnSave;
    @Wire
    Button btnDelete;
    @Wire
    Button btnReset;

    Listitem item;

    @Autowired
    UserAndPrivilegeService userAndPrivilegeService;
    Boolean onLoad = false;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        initWndRegistrationForm();
    }

    @Listen("onOk = #txtRoleId")
    public void onSearch(Event event){
        try{
            User user=userAndPrivilegeService.findOneUserByUserName((String)ComponentUtil.getValue(txtUserName));
            if(user !=null) {
                List<User> users=new ArrayList<>();
                users.add(user);
                paging.setTotalSize(users.size());
                doFillTable(users);
                List<Privilege> privileges= userAndPrivilegeService.findAllUserPrivilegeByUserId(user.getUserId());
                doFillTableRole(privileges);
            }
        }catch (Exception e)
        {
            log.error("Textbox Role Id on ok ", e);
        }
    }

    @Listen("onDoubleClick = #list")
    public void onEdit(Event event){
        try{
            doCheckAll(0);
            item = list.getSelectedItem();
            if (item != null) {
                User user = (User) item.getAttribute("DATA");
                doFillDetailForm(user);
                List<Privilege> privileges= userAndPrivilegeService.findAllUserPrivilegeByUserId(user.getUserId());
                doFillTableRole(privileges);
                onLoad = true;
                btnDelete.setDisabled(false);
            }
        }catch (Exception e){
            log.error("Listbox on double click ", e);
        }
    }

    @Listen("onClick =#btnSave")
    public void onSave(Event event){
        try{
            if (doValidation()) {
                if(onLoad==true){
                    doUpdate();
                }else{
                    doInsert();
                }
            }
        }catch (Exception e){
            log.error("Button save on click ", e);
        }
    }

    @Listen("onClick =#btnDelete")
    public void onDelete(Event event){
        try{
            if(ComponentUtil.getValue(txtUserId)!=null || !ComponentUtil.getValue(txtUserId).equals("")) {
                userAndPrivilegeService.deleteUser((Integer) ComponentUtil.getValue(txtUserId));
                MessageBox.showInformation("Data berhasil dihapus");
                doReset();
            }else{
                MessageBox.showInformation("Data tidak ada yang dipilih!");
            }
        }catch (Exception e){
            log.error("Button delete on clik ", e);
        }
    }

    @Listen("onClick =#btnReset")
    public void onReset(Event event){
        try{
            doReset();
        }catch (Exception e)
        {
            log.error("Event btnDelete on click ", e);
        }
    }

    @Listen("onPaging =#paging")
    public void onPaging(Event event){
        try {
            doLoadData();
        } catch (Exception e) {
            log.error("Event list on Paging  ", e);
        }
    }

    @Listen("onClick =#chkAll")
    public void onCheckAll(Event event){
        try{
            doCheckAll((Integer) ComponentUtil.getValue(chkAll));
        }catch (Exception e){

        }
    }

    private void initWndRegistrationForm(){
        try{
            doReset();
        }catch (Exception e){
            log.error("Initialization on WndRegistrationForm: ",e);
        }
    }

    private void doInsert() throws  Exception{
        if (!isExists()) {
            User user=populationData();
            BCryptPasswordEncoder ewdEndcode = new BCryptPasswordEncoder();
            user.setPassword(ewdEndcode.encode((String)ComponentUtil.getValue(txtUserName)));
            user= userAndPrivilegeService.saveUser(user);
            List<UserRole> userRole=populationDataRole(user.getUserId());
            userAndPrivilegeService.savePrivilege(userRole);
            MessageBox.showInformation("Data berhasil disimpan");
        }else{
            MessageBox.showError("Data sudah tersimpan sebelumnya!");
        }
        doReset();
    }

    private void doUpdate() throws  Exception{
        User user=populationData();
        user= userAndPrivilegeService.saveUser(user);
        List<UserRole> userRole=populationDataRole(user.getUserId());
        userAndPrivilegeService.savePrivilege(userRole);
        MessageBox.showInformation("Data berhasil diupdate");
        doReset();
    }

    private void doReset() throws Exception{
        ComponentUtil.clear(wnd);
        doLoadData();
        doLoadDataRole();
        onLoad = false;
        btnDelete.setDisabled(true);
        txtUserName.setFocus(true);
        doCheckAll(0);
    }

    private void doLoadData() throws Exception{
        Page<User> page= userAndPrivilegeService.findAllUser(createPageRequest());
        paging.setTotalSize((int)page.getTotalElements());
        doFillTable(page.getContent());
    }

    private  void doLoadDataRole() throws Exception{
        List<Privilege> page=userAndPrivilegeService.findAllUserPrivilege();
        doFillTableRole(page);
    }

    private void doFillTable(List<User> page) throws  Exception{
        list.getItems().clear();
        if (page != null){
            for(User user : page){
                Listitem item = new Listitem();
                item.setAttribute("DATA", user);
                item.appendChild(new Listcell(user.getUserName()));
                item.appendChild(new Listcell(user.getEmail()));
                String status=user.getEnabled()==1?"Aktif":"Tidak Aktif";
                item.appendChild(new Listcell(status));
                list.appendChild(item);
            }
        }
    }

    private void doFillTableRole(List<Privilege> page) throws  Exception{
        lsRole.getItems().clear();
        if (page != null){
            for(Privilege privilege : page){
                Listitem item = new Listitem();
                Checkbox checkbox = new Checkbox();
                Listcell listcell = new Listcell("");
                listcell.appendChild(checkbox);
                item.setAttribute("CHECKBOX", checkbox);
                item.setAttribute("DATA_ROLE", privilege);
                checkbox.setChecked(privilege.isStatus());
                item.appendChild(listcell);
                item.appendChild(new Listcell(privilege.getRoleName()));
                lsRole.appendChild(item);

            }
        }
    }

    private void doCheckAll(Integer check) {
        boolean checkAll = false;
        checkAll= (check == 1) ? true: false;
        for (Object object : lsRole.getItems()) {
            Listitem item = (Listitem) object;
            Checkbox checkBox = (Checkbox) item.getAttribute("CHECKBOX");
            checkBox.setChecked(checkAll);
        }
    }

    private boolean isExists() throws Exception{
        return userAndPrivilegeService.existsUserByUserName((String)ComponentUtil.getValue(txtUserName));
    }

    private void doFillDetailForm(User user){
        ComponentUtil.setValue(txtUserId,user.getUserId());
        ComponentUtil.setValue(txtUserName, user.getUserName());
        ComponentUtil.setValue(txtEmail, user.getEmail());
        ComponentUtil.setValue(rgStatus, user.getEnabled());
    }

    private User populationData(){
        User user=new User();
        user.setUserId((Integer)ComponentUtil.getValue(txtUserId));
        user.setUserName((String) ComponentUtil.getValue(txtUserName));
        user.setEmail((String)ComponentUtil.getValue(txtEmail));
        user.setEnabled((Integer)ComponentUtil.getValue(rgStatus));
        return user;
    }

    private List<UserRole> populationDataRole(Integer userId){
        List<UserRole> userRoles=new ArrayList<>();
        for (Object object : lsRole.getItems()) {
            UserRole userRole=new UserRole();
            Listitem item = (Listitem) object;
            Checkbox check = (Checkbox) item.getAttribute("CHECKBOX");
            Privilege privilege = (Privilege) item.getAttribute("DATA_ROLE");
            if (check.isChecked()) {
                userRole.setUserId(userId);
                userRole.setRoleId(privilege.getRoleId());
                userRoles.add(userRole);
            }
        }
        return userRoles;
    }

    private Pageable createPageRequest(){
        int pageNo = paging.getActivePage();
        int totalSize = paging.getTotalSize();
        int pageCount = paging.getPageCount();
        int pageSize = paging.getPageSize();
        log.debug("pageNo: {}, totalSize: {}, pageCount: {}, pageSize: {}", pageNo, totalSize, pageCount, pageSize);
        return new PageRequest(pageNo, pageSize, Sort.Direction.ASC, "userId");
    }

    private boolean doValidation(){
        if (ComponentUtil.getValue(txtUserName) == null || ComponentUtil.getValue(txtUserName).equals("")) {
            throw new WrongValueException(txtUserName, "User name harus diisi");
        }
        return true;
    }
}
