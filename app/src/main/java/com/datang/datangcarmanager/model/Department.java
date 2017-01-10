package com.datang.datangcarmanager.model;

import com.datang.datangcarmanager.utils.annotation.TreeNodeId;
import com.datang.datangcarmanager.utils.annotation.TreeNodeLable;
import com.datang.datangcarmanager.utils.annotation.TreeNodePid;

/**
 * Created by toby on 16/11/13.
 */
public class Department {
    @TreeNodeId
    private int id;
    @TreeNodePid
    private int pId;
    @TreeNodeLable
    private String departmentame;
    private String desc;

    public String getDepartmentame() {
        return departmentame;
    }

    public void setDepartmentame(String departmentame) {
        this.departmentame = departmentame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
