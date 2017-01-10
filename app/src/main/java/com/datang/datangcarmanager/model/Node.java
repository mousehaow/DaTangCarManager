package com.datang.datangcarmanager.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toby on 16/11/13.
 */
public class Node {
    private int id;
    private int pId = 0;      //节点
    private String name;
    private int levle;      //树的层级
    private boolean isExpand = false;
    private int icon;

    private Node partent;
    private List<Node> children = new ArrayList<Node>();

    public Node(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevle() {
        return partent == null ? 0 : partent.getLevle() + 1;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
        if (!isExpand) {
            for (Node node : children) {
                node.setExpand(false);
            }
        }
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Node getPartent() {
        return partent;
    }

    public void setPartent(Node partent) {
        this.partent = partent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public boolean isRoot() {
        return partent == null;
    }

    public boolean isParentExpand() {
        if (partent == null) {
            return false;
        }
        return partent.isExpand;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }
}
