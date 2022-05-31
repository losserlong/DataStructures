package com.atguigu.binarysorttree;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/08    16:29
 * @Version:1.0
 * 复习之前的二叉搜索树
 *
 */
public class BinarySortTree1 {


    @Test
    public void testDel() {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree1 binarySortTree = new BinarySortTree1();
        // 循环将arr数组的值创建节点
        for (int value : arr) {
            binarySortTree.add(new Node1(value));
        }
        // 中序遍历二叉排序树
        binarySortTree.infixOrder();
        System.out.println();

        binarySortTree.removeNode(2);
//        binarySortTree.removeNode(5);
//        binarySortTree.removeNode(9);
//        binarySortTree.removeNode(12);
//        binarySortTree.removeNode(7);
//        binarySortTree.removeNode(3);
//        binarySortTree.removeNode(10);
        System.out.println("root=   " + binarySortTree.getRoot());
        binarySortTree.removeNode(1);


        System.out.println("\n中序遍历之后的结果是");
        System.out.println(binarySortTree.getRoot());

        binarySortTree.infixOrder();


    }


    Node1 root;

    public Node1 getRoot() {
        return root;
    }


    /**
     * 二叉排序树的添加
     * @param node
     */
    public void add(Node1 node) {
        if (root == null) {
            // 如果root为空,就直接将root指向node
            root = node;
        } else {
            // 不为空直接将加到root的后面即可
            root.add(node);
        }
    }


    /**
     * 中序遍历二叉排序树
     */
    public void infixOrder() {

        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("当前的二叉排序树为空！不能为空！");
        }

    }


    /**
     * 删除结点的操作
     *
     *                       7
     *                     /    \
     *                     3    10
     *                   /  \   / \
     *                  1   5  9   12
     *                   \
     *                   2
     *
     * 注意：这里有三种特殊情况
     * 1. 删除叶子节点 比如(2 , 5 , 9 , 12)
     * 2. 删除两颗子树的子节点 (7 , 3 , 10 )
     * 3. 删除一棵子树的结点 ( 1 )
     *
     *
     *
     * @param value
     */
    public void removeNode(int value) {

        if (this == null) {
            return;
            // 提前结束
        }

        // 先找到待删除的结点
        Node1 targetNode = search(value);
        if (targetNode == null) {
            // 如果找到的是null说明不存在,直接提前结束
            return;
        }


        // 第一种删除叶子节点
        // 这说明只有root一个结点
        if (root.left == null && root.right == null) {
            // 说明是叶子节点
            root = null;
            return;
        }
        // 找到target的父节点
        Node1 parentNode = searchParentNode(value);
        if (targetNode.left == null && targetNode.right == null) {
            // 1. 说明是叶子节点,并且是parentNode的左子节点
            if (parentNode != null && parentNode.left != null && parentNode.left.value == value) {
                // 直接将parentNode.left置空
                parentNode.left = null;
                return;
            }
            if (parentNode != null && parentNode.right != null && parentNode.right.value == value) {
                parentNode.right = null;
                return;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            // 2. 说明待删除的结点含有左右两颗子树
            int minValue = delRightTreeMin(targetNode.right);
            // 将targetNode的value值用右子树的最小值代替
            targetNode.value = minValue;


        } else {
            // 3. 说明只有一棵子树,直接将target删除
            // 要删除的有左子节点,并且是parentNode的左子节点
            if (targetNode.left != null) {
                if (parentNode == null) {
                    // 就说明是targetNode是根节点
                    root.left = targetNode.left;
                    return;
                }
                if (parentNode.left.value == value) {
                    // 当targetNode是parentNode的左子节点
                    // 并且当前节点的左子节点不为空
                    parentNode.left = targetNode.right;
                } else {
                    // 当targetNode是当前节点的右子节点
                    // 反正不是左边就是右边有值,如果targetNode不是parentNode的左子节点就是右子节点
                    parentNode.right = targetNode.right;
                }
            }

            if (targetNode.right != null) {
                if (parentNode == null) {
                    // 就说明是targetNode是根节点
                    root.left = targetNode.right;
                    return;
                }
                // 如果当前节点的右子节点不为空
                if (parentNode.left.value == value) {
                    // 当targetNode是parentNode的左子节点
                    // 并且当前节点的左子节点不为空
                    parentNode.left = targetNode.right;
                } else {
                    // 当targetNode是当前节点的右子节点
                    parentNode.right = targetNode.right;
                }


            }


        }


    }


    /**
     * 找到当前待删除的结点的父节点
     * @param value
     * @return
     */
    public Node1 searchParentNode(int value) {
        return this.root.searchParentNode(value);
    }


    /**
     * 将Node1里面的search方法封装到BinarySortTree1中
     * @param value
     * @return
     */
    public Node1 search(int value) {
        if (root == null) return null;

        return this.root.search(value);
    }


    /**
     * 寻找当前节点的左子树中的最小值,将它删除替换成待删除的结点
     * @param targetNode 待删除的结点
     * @return
     */
    public int delRightTreeMin(Node1 targetNode) {

        while (targetNode.left != null) {
            targetNode = targetNode.left;
        }

        removeNode(targetNode.value);
        // 说明找到了右子树中的最小的结点的值
        return targetNode.value;
    }


}

/**
 * 树的每一个结点
 */
class Node1 {

    // 存放数据的值
    int value;
    // 左子节点
    Node1 left;
    // 右子节点
    Node1 right;

    public Node1() {
    }


    public Node1(int value) {
        this.value = value;
    }


    /**
     * 查找待删除的结点
     * @param value
     * @return
     */
    public Node1 search(int value) {

        if (this == null) {
            return null;
        }
        if (this.value == value) {
            return this;
        }


        if (this.left != null && this.value > value) {
            // 向左递归查找
            return this.left.search(value);
        }

        if (this.right != null && value > this.value) {
            // 向右递归查找
            return this.right.search(value);
        }


        return null;
    }


    /**
     * 查找当前节点的父节点
     * @param value
     * @return
     */
    public Node1 searchParentNode(int value) {
        if (this == null) {
            //  表明当前待移除的结点是根节点,直接将其移除即可
            return null;
        }

        // 如果当前结点的左边或者右边的值等于value就直接返回当前结点
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        }


        if (this.left != null && value < this.value) {
            // 向左递归查找
            return this.left.searchParentNode(value);
        }
        if (this.right != null && value >= this.value) {
            // 向右边递归查找
            return this.right.searchParentNode(value);
        }

        // 其他情况默认为空
        return null;

    }

    /**
     * 二叉树的中序遍历
     */
    public void infixOrder() {
        if (this == null) {
            return;
        }

        if (this.left != null) {
            this.left.infixOrder();
        }

        // 中间打印出来
        System.out.print(this.value + "\t");
        if (this.right != null) {
            this.right.infixOrder();
        }


    }


    /**
     * 添加结点的方法
     * 注意需要满足二叉排序树的要求
     * @param node 待添加的结点
     */
    public void add(Node1 node) {
        if (node == null) {
            return;
        }

        // 判断传入的结点的值和当前结点的值比较
        if (node.value < this.value) {
            if (this.left == null) {
                // 当前结点的左子结点为空,就直接将当前结点的left指向node
                this.left = node;
            } else {
                // 如果this的左边不为空,然后递归到左子树添加
                this.left.add(node);
            }


        } else {
            if (this.right == null) {

                this.right = node;
            } else {
                // 如果this的右边不为空,然后递归到右子树添加
                this.right.add(node);
            }
        }


    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


}