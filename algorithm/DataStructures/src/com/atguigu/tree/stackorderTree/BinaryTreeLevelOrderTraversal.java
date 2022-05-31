package com.atguigu.tree.stackorderTree;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/28    17:49
 * @Version:1.0
 * =====================================二叉树的层次遍历===================================================
 * 二叉树的BFS广度优先遍历
 */
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> resList = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        //checkFun01(root,0);
        checkFun02(root);

        return resList;
    }


    public static void main(String[] args) {


    }

    /**
     * 用队列实现
     * @param root
     */
    private List<List<Integer>> checkFun03(TreeNode root) {

        ArrayList<Integer> list = null;
        if (root == null) {
            return resList;
        }

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            list = new ArrayList<>();
            int size = deque.size();
            while (size > 0) {
                TreeNode node = deque.pop();
                list.add(node.val);
                if (node.left != null) {
                    deque.offer(node.left);
                }
                if (node.right != null) {
                    deque.offer(node.right);
                }
            }
            resList.add(list);
        }

        return resList;


    }


    /**
     * 使用队列对二叉树进行层次遍历
     * @param root
     */
    private void checkFun01(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // 将头节点加入到队列中
        queue.offer(root);
        while (!queue.isEmpty()) {
            // size就是每次记录上一次的值
            int size = queue.size();

            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                // 然后遍历的时候每次将上一次放入到队列中的值全部弹出
                TreeNode tempNode = queue.poll();
                list.add(tempNode.val);


                if (tempNode.left != null) {
                    queue.offer(tempNode.left);
                }

                if (tempNode.right != null) {
                    queue.offer(tempNode.right);
                }
                size--;
            }
            resList.add(list);

        }


    }

    /**
     * 使用队列实现层次遍历
     * @param root
     */
    private void checkFun02(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            List<Integer> itemList = new ArrayList<Integer>();
            int len = que.size();
            while (len > 0) {
                // poll：用于查看元素，并且把那个元素删除,并将删除的元素返回
                TreeNode tempNode = que.poll();
                itemList.add(tempNode.val);
                if (tempNode.left != null) {
                    que.offer(tempNode.left);
                }
                if (tempNode.right != null) {
                    que.offer(tempNode.right);
                }
                len--;
            }
            resList.add(itemList);


        }
    }


    /**
     * 复习
     * @param root
     */
    private void checkFun04(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> deque = new LinkedList<>();
        // 将根结点加入到队列中
        deque.offer(root);

        while (!deque.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = deque.size();
            while (size > 0) {
                TreeNode tempNode = deque.pop();
                list.add(tempNode.val);

                if (tempNode.left != null) {
                    deque.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    deque.add(tempNode.right);
                }
                size--;
            }
            resList.add(list);


        }


    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
