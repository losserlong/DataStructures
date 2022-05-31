package com.atguigu.avl;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/08    20:51
 * @Version:1.0
 */
public class AvlTreeDemo {

    public static void main(String[] args) {

    }

    /**
     * 双旋转
     */
    @Test
    public void twoRotateTest() {
        int arr[] = {10, 11, 7, 6, 8, 9};


        // 创建一个AVL对象
        AVLTree root = new AVLTree();
        // 添加节点
        for (int value : arr) {
            root.add(new Node(value));
        }

        root.infixOrder();
        System.out.println();
        System.out.println("树的高度是");
        System.out.println(root.height());

        System.out.println("左子树的高度是");
        System.out.println(root.leftHeight());
        System.out.println("右子树的高度是");
        System.out.println(root.rightHeight());
        // 输出当前的根节点
        System.out.println(root.getRoot());

        System.out.println("根节点的左节点" + root.getRoot().left);
        System.out.println("根节点的右节点" + root.getRoot().right);


    }


    /**
     * 单旋转左旋
     */
    @Test
    public void testLeftRotate() {
        int arr[] = {4, 3, 6, 5, 7, 8};


        // 创建一个AVL对象
        AVLTree root = new AVLTree();
        // 添加节点
        for (int value : arr) {
            root.add(new Node(value));
        }

        root.infixOrder();
        System.out.println();
        System.out.println("树的高度是");
        System.out.println(root.height());

        System.out.println("左子树的高度是");
        System.out.println(root.leftHeight());
        System.out.println("右子树的高度是");
        System.out.println(root.rightHeight());


        // 输出当前的根节点
        System.out.println(root.getRoot());

    }

    /**
     * 单旋转右旋转
     */
    @Test
    public void rightRotateTest() {
        int arr[] = {10, 12, 8, 9, 7, 6};


        // 创建一个AVL对象
        AVLTree root = new AVLTree();
        // 添加节点
        for (int value : arr) {
            root.add(new Node(value));
        }

        root.infixOrder();
        System.out.println();
        System.out.println("树的高度是");
        System.out.println(root.height());

        System.out.println("左子树的高度是");
        System.out.println(root.leftHeight());
        System.out.println("右子树的高度是");
        System.out.println(root.rightHeight());


        // 输出当前的根节点
        System.out.println(root.getRoot());

    }


}

/**
 * 创建AVLTree
 */
class AVLTree {

    Node root;

    public Node getRoot() {
        return root;
    }


    /**
     * 获取右子树的高度
     * @return
     */
    public int rightHeight() {
        return root.rightHeight();
    }

    /**
     * 获取左子树的高度
     * @return
     */
    public int leftHeight() {
        return root.leftHeight();
    }


    /**
     * 获取整棵树的高度
     * @return
     */
    public int height() {
        return root.height();
    }


    /**
     * 寻找左子树最大的结点的值
     * @param node
     * @return 左子树中最大的结点的值
     */
    public int delLeftTreeMax(Node node) {
        Node target = node;
        while (target.right != null) {
            // 一直向左子树查找最大的值
            target = target.right;
        }

        removeNode(target.value);


        return target.value;
    }


    /**
     * 这里是寻找右子树的最小的结点,然后将其删除并返回,然后到下面删除结点调用的时候在赋值给target
     * 1. 返回以node为根节点的二叉排序树的最小结点的值
     * 2. 删除以node为根节点的二叉排序树的最小节点
     * @param node 传入的结点(当做二叉排序树的根节点)
     * @return 返回以node为根节点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {


        Node target = node;
        // 循环的查找左子节点,就会找到最小值
        while (target.left != null) {
            // 指向target的left不为空就一直找,直到找到target的最小的值
            target = target.left;
        }
        // target指向了左子树中最小的值
        removeNode(target.value);

        // 还要讲最小的节点值返回
        return target.value;
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
     *                    2
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
        if (root == null) {
            // 直接提前终止
            return;
        }

        // 1.先找到待删除的结点targetNode
        Node targetNode = search(value);
        if (targetNode == null) {
            // 说明不存在
            return;
        }


        if (root.left == null && root.left == null) {
            // 如果发现当前节点只有一个结点直接删除即可
            root = null;
            return;
        }
        // 2.找到targetNode的父节点
        Node parentNode = parentNode(value);
        // 如果待删除的targetNode是叶子节点,
        // 进入下面的判断说明已经找到targetNode了
        if (targetNode.left == null && targetNode.right == null) {
            // 判断targetNode是父节点的左子节点还是右子节点
            if (parentNode != null && parentNode.left != null && parentNode.left.value == value) {
                // 说明targetNode是parentNode的左子节点
                parentNode.left = null;
            } else if (parentNode != null && parentNode.right != null && parentNode.right.value == value) {
                // 说明targetNode是parentNode的右子节点
                parentNode.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            // 删除有两棵子树的结点
            // 获取右子树中的最小的值
            int minValue = delRightTreeMin(targetNode.right);
            // 将target的值用最小的值替换
            targetNode.value = minValue;

/*
            找左子树中最大的值
            int maxValue = delLeftTreeMax(targetNode.left);
            targetNode.value = maxValue;

*/

        } else { // 删除只有一颗子树的结点
            // 要删除的结点有左子节点
            if (targetNode.left != null) {

                if (parentNode == null) {
                    // 就说明待删除的结点是根节点,直接将root指向targetNode的left
                    root = targetNode.left;
                    return;

                }


                // 当targetNode是parentNode的左子节点
                if (parentNode.left.value == value) {
                    parentNode.left = targetNode.left;
                } else {

                    // 当targetNode是parentNode的右子节点
                    parentNode.right = targetNode.left;
                }
            }

            // 要删除的结点有右子节点
            if (targetNode.right != null) {

                if (parentNode == null) {
                    // 就说明待删除的结点是根节点,直接将root指向targetNode的right
                    root = targetNode.right;
                    return;

                }
                if (parentNode.left.value == value) {
                    parentNode.left = targetNode.right;
                } else {
                    parentNode.right = targetNode.right;
                }
            }
        }
    }


    /**
     * 查找要删除的结点
     * @param value
     * @return
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    /**
     * 查找待删除结点的父节点
     * @param value
     * @return
     */
    public Node parentNode(int value) {
        if (root == null) {
            return null;
        }

        return root.searchParentNode(value);
    }


    /**
     * 二叉排序树的添加
     * @param node
     */
    public void add(Node node) {
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


}


/**
 * 树的每一个结点
 */
class Node {

    // 存放数据的值
    int value;
    // 左子节点
    Node left;
    // 右子节点
    Node right;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        // 1. 将当前结点的值作为新节点
        Node newNode = new Node(this.value);

        // 2. 把新节点的右子树设置为当前节点的右子树
        newNode.right = this.right;

        // 3. 把新结点的右子树设置为当前节点的左子树的右子树
        newNode.left = this.left.right;


        // 4. 把当前系欸但的值替换成左子节点的值
        value = left.value;

        // 5. 把当前节点的左子树设置成左子树的左子树
        left = left.left;
        // 6. 把当前节点的右子树设置为新节点
        right = newNode;


    }


    /**
     * 左旋转的操作
     *         怎么进行左旋(6这个结点旋转过后就会被垃圾回收)
     *        1. 创建一个新的结点newNode(以4这个值创建一个新的结点,它的值等于当前节点的值)
     *
     *         // 把新节点(newNode)的左子树设置当前节点的左子树
     *        2. newNode.left = this.left
     *
     *         // 把新节点(newNode)的右子树设置为当前节点的右子树的左子树
     *        3. newNode.right = this.right.left
     *
     *         // 把当前节点的值替换为右子节点的值
     *        4. value = right.value
     *
     *         // 吧当前节点的右子树设置为右子树的右子树
     *        5. right = right.right
     *        // 把当前节点的左子树设置为新结点(newNode)
     *        6. left = newLeft
     */
    private void leftRotate() {
        // 1.创建一个新的结点,当前节点的值等于根节点的值
        Node newNode = new Node(value);
        // 2. 将新节点的左子树指向当前节点的左子树
        newNode.left = this.left;

        // 3. 把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = this.right.left;

        // 4. 把当前节点的值替换为右子节点的值
        this.value = this.right.value;

        // 5.把当前节点的右子树设置为右子树的右子树
        right = right.right;

        // 6. 把当前节点的左子树设置为新的结点
        left = newNode;


    }


    /**
     * 返回左子树的高度
     * @return
     */
    public int leftHeight() {
        if (left == null) return 0;
        // 不为空就去查找左子树的高度
        return left.height();
    }


    /**
     * 返回右子树的高度
     * @return
     */
    public int rightHeight() {
        // 当当前节点的右子节点为空,直接返回空
        if (right == null) return 0;
        // 不为空直接查找左子树的高度
        return right.height();
    }


    /**
     * 返回当前节点的树的的高度
     * 也就是左子树和右子树中比较大的值
     * @return
     */
    public int height() {
        // +1是因为统计的是坐右子树的高度+1是加上自己
        return Math.max(this.left == null ? 0 : left.height(),
                this.right == null ? 0 : right.height()) + 1;
    }


    /**
     * 查找待删除结点的父节点
     * @param value 要删除的节点值
     * @return 待删除的结点的父节点, 没有就返回null
     */
    public Node searchParentNode(int value) {

/*
        // 当前节点的左子节点值等于value或者右子节点值等于value,就返回当前结点
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        }
        // 如果待查找的结点的值比当前结点的值小,并且当前节点的左子节点不为空,递归向左边查找
        if (value < this.value && this.left != null) {
            // 向左子树递归查找
            return this.left.searchParentNode(value);
        }
        if (value >= this.value && this.right != null) {
            // 这里 >= 因为是之前加的时候相等的值加在了右子树
            // 向右子树递归查找
            return this.right.searchParentNode(value);
        }
        return null;*/

/*

        // 当前节点的左子节点值等于value或者右子节点值等于value,就返回当前结点
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果待查找的结点的值比当前结点的值小,并且当前节点的左子节点不为空,递归向左边查找
            if (value < this.value && this.left != null) {
                // 向左子树递归查找
                return this.left.searchParentNode(value);
            } else if (value >= this.value && this.right != null) {
                // 这里 >= 因为是之前加的时候相等的值加在了右子树
                // 向右子树递归查找
                return this.right.searchParentNode(value);
            } else {
                // 当当前结点的左子节点为空或者,当前节点右子树为空
                // 就是除了上面那两种情况之外
                return null;
            }
        }
*/
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
     * 查找要删除的结点
     *
     * @param value 待删除的结点的值
     * @return 返回待删除的结点
     */
    public Node search(int value) {
        if (value == this.value) {
            // 找到直接返回
            return this;
        } else if (value < this.value) {
            if (this.left != null) {
                // 如果查找的值小于当前节点,向左子树递归查找
                return this.left.search(value);
            } else {
                // 左节点为null直接返回
                return null;
            }
        } else {
            // 查找的值不小于当前的值,就向右子树递归查找
            if (this.right != null) {
                return this.right.search(value);
            }
            // 当右子节点为空的时候
            return null;
        }
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
    public void add(Node node) {
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

        // 当添加完成之后如果(右子树的高度 - 左子树的高度) > 1, ====>  就左旋
        if (rightHeight() - leftHeight() > 1) {
            // 右子节点不为空,并且右子节点的右子树的高度小于左子树的高度
/*
            if (right != null && right.rightHeight() < right.leftHeight()) {
            // 先对右子树进行

            }
*/
            // 如果它的右子树的左子树的高度大于它的右子树的高度
            // 先要进行右旋转
            if (this.right != null && this.right.rightHeight() < this.right.leftHeight()) {
                // 先对当前节点的左节点进行右旋转
                this.right.rightRotate();
            }


            leftRotate(); // 左旋转

            // 提前终止，防止下面再旋转
            return;
        }

        // 当添加完一个结点之后,如果(左子树高度 - 右子树高度) > 1, 右旋转
        if (leftHeight() - rightHeight() > 1) {
            // 如果它的左子树的右子树的高度大于它的左子树的高度
            // 先要进行左旋转
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                // 先对当前节点的左节点进行左旋转
                this.left.leftRotate();
            }
            rightRotate();
        }


    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
