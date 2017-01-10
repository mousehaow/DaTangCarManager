package com.datang.datangcarmanager.utils;

import com.datang.datangcarmanager.R;
import com.datang.datangcarmanager.model.Node;
import com.datang.datangcarmanager.utils.annotation.TreeNodeId;
import com.datang.datangcarmanager.utils.annotation.TreeNodeLable;
import com.datang.datangcarmanager.utils.annotation.TreeNodePid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by toby on 16/11/13.
 */
public class TreeHelper {

    public static <T> List<Node> converDatasToNodes(List<T> datas) throws IllegalAccessException {
        List<Node> nodes = new ArrayList<Node>();
        Node node = null;

        for (T t : datas) {
            int id = -1;
            int pid = -1;
            String label = null;
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(TreeNodeId.class) != null) {
                    field.setAccessible(true);
                    id = field.getInt(t);
                }
                if (field.getAnnotation(TreeNodePid.class) != null) {
                    field.setAccessible(true);
                    pid = field.getInt(t);
                }
                if (field.getAnnotation(TreeNodeLable.class) != null) {
                    field.setAccessible(true);
                    label = (String) field.get(t);
                }
            }
            node = new Node(id, pid, label);
            nodes.add(node);
        }

        for(int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                Node m = nodes.get(j);
                if (m.getpId() == n.getId()) {
                    n.getChildren().add(m);
                    m.setPartent(n);
                }
                if (m.getId() == n.getpId()) {
                    m.getChildren().add(n);
                    n.setPartent(m);
                }
            }
        }

        for (Node n : nodes) {
            setIcon(n);
        }

        return nodes;
    }

    public static <T> List<Node> getSortedNodes(List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        List<Node> result = new ArrayList<Node>();
        List<Node> nodes = converDatasToNodes(datas);

        List<Node> rootNodes = getRootNodes(nodes);

        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 0);
        }
        return result;
    }

    public static List<Node> filterVisableNodes(List<Node> nodes) {
        List<Node> result = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node.isRoot() || node.isParentExpand()) {
                setIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    private static void addNode(List<Node> result, Node node, int level, int currentLevel) {
        result.add(node);
        if (level > currentLevel) {
            node.setExpand(true);
        }
        if (node.isLeaf()) {
            return;
        }
        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(result,node.getChildren().get(i), level, currentLevel + 1);
        }
    }

    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<Node>();

        for (Node node : nodes) {
            if (node.isRoot()) {
                root.add(node);
            }
        }
        return root;
    }

    public static void setIcon(Node n){

        if (n.getChildren().size() > 0 && n.isExpand()) {
            n.setIcon(R.drawable.arrow_down);
        } else if (n.getChildren().size() > 0 && !n.isExpand()) {
            n.setIcon(R.drawable.arrow_right);
        } else {
            n.setIcon(-1);
        }

    }
}
