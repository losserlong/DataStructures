package com.atguigu.tree.stackorderTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/28    16:42
 * @Version:1.0
 *  用栈的方式实现对树的深度优先遍历(DFS)
 *
 *
 *
 */
public class OrderTree {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(6);
        TreeNode n3 = new TreeNode(8);
        TreeNode n4 = new TreeNode(10);
        TreeNode n5 = new TreeNode(14);
        root.left = n1;
        root.right = n2;
        n2.left = n5;
        n1.left = n3;
        n1.right = n4;

        List<Integer> list = preOrder(root);
        System.out.println("先序遍历");
        System.out.println("list = " + list);
        System.out.println("中序遍历");
        List<Integer> res = infixOrder(root);
        System.out.println(res);
        System.out.println("后续遍历");
        List<Integer> postOrder = postOrder(root);
        System.out.println("postOrder = " + postOrder);

    }


    /**
     * 前序遍历
     * @param root 根节点
     *             前序遍历的顺序：中-左-右   入栈顺序：中-右-左
     *             其实主要思路就是先存放根节点，再存放右子节点，再存放左子节点
     */
    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }


        return list;
    }

    /**
     * 用栈来实现中序遍历 左-中-右    如栈顺序： 左-右
     * @param root
     * @return
     */
    public static List<Integer> infixOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                // cur变成cur的右孩子
                cur = cur.left;
            } else {
                // 第一次进来的时候说明遍历到了最左的节点
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * 前序遍历用栈的顺序是中-右-左
     * 后续遍历的顺序是左-右-中 ， 只需要调整一下前序遍历代码就行了
     * 前序遍历是中-左-右，变成中-右-左边==> 左-右-中
     * @param root
     * @return
     */
    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        Collections.reverse(list);


        return list;
    }
}
