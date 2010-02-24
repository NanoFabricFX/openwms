/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.common.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A TreeNodeImpl.
 * 
 * @author <a href="mailto:openwms@googlemail.com">Heiko Scherrer</a>
 * @version $Revision: 877 $
 * @since 0.1
 */
public class TreeNodeImpl<T> implements TreeNode<T> {

    private static final long serialVersionUID = -5498990493803705085L;
    private T data;
    private TreeNode<T> parent;

    private Map<Object, TreeNode<T>> childrenMap = new LinkedHashMap<Object, TreeNode<T>>();

    public T getData() {
        return data;
    }

    public TreeNode<T> getChild(Object identifier) {
        return (TreeNode<T>) childrenMap.get(identifier);
    }

    public void addChild(Object identifier, TreeNode<T> child) {
        child.setParent(this);
        childrenMap.put(identifier, child);
    }

    public void removeChild(Object identifier) {
        TreeNode<T> treeNode = childrenMap.remove(identifier);
        if (treeNode != null) {
            treeNode.setParent(null);
        }
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public Iterator<Map.Entry<Object, TreeNode<T>>> getChildren() {
        return childrenMap.entrySet().iterator();
    }

    public boolean isLeaf() {
        return childrenMap.isEmpty();
    }

}