package com.atguigu.tree;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/25    10:13
 * @Version:1.0
 * =======================================二叉排序树===========================================
 * =======================二叉排序树的前序、中序和后序遍历的相关实现===============================
 * 前序遍历就是根节点==>左子树==>右子树     根节点在前面遍历   中 -> 左 -> 右   然后递归
 * 中序遍历就是左子树==>根节点==>右子树     根节点在中间遍历   左 -> 中 -> 右   然后递归
 * 后序遍历就是左子树==>右子树==>根节点     根节点在后面遍历   左 -> 右 -> 中   然后递归
 *
 * ==============================删除指定节点====================================
 * 完成删除节点的操作：(这里的删除是将整个子树进行删除)
 * 规定：
 * 1）如果删除的节点是叶子节点，则删除该节点
 * 2）如果删除的节点是非叶子节点，则删除该子树
 *
 * 思路
 * 首先先处理：
 * 考虑如果树是空树root，如果只有一个root节点，则等价于将二叉树置空。
 *
 * 然后再进行下面的操作
 * 1. 因为我们的二叉树是单向的(像链表)，所以我们是判断当前节点的子节点是否是需要待删除的节点，
 * 而不能判断当前这个节点是不是待删除的节点
 * 2. 如果当前节点的左子树不为空，并且当前节点的左子节点的编号就是待删除的节点，
 * 就将this.left=null(就将当前节点的左子节点置空即可，有点类似链表)；并且提前返回(结束递归  )
 * 3. 如果当前节点的右子树不为空，并且当前节点的右子节点的编号就是待删除的节点，
 * 就将this.right=null(就将当前节点的右子节点置空即可，有点类似链表)
 * 4. 如果第2，3步都没删除该节点，那么我们就需要像左子树(也要判断左子树是否为空)进行递归删除
 * 5. 如果2，3，4步都没有删除掉该节点，就像右子树进行递归删除
 *
 *
 *
 */
public class BinaryTreeSortDemo {
    public static void main(String[] args) {

        // 先创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();

        // 创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");


        // 说明，我们先手动创建二叉树，后买你递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);
//        binaryTree.delNode(3);
        System.out.println("===================================前序遍历======================================");
        binaryTree.preOrder();
        System.out.println("删除前");
        binaryTree.delNode2(3);
        System.out.println("删除后");
        binaryTree.preOrder();
        System.out.println("aaaaaaaaaa~~~~~~~~~~~~~~~~~~~~~~~");




/*        System.out.println("前序遍历"); // 1,2,3,4 (没加关胜的时候)  ===> 加了之后 1,2,3,4,5
        root.preOrder();
        System.out.println("中序遍历"); // 2,1,4,3 (没加关胜的时候)  ===> 加了之后 2,1,5,3,4
        root.infixOrder();
        System.out.println("后序遍历"); // 4,3,1,2 (没加关胜的时候)  ===> 加了之后 2,5,4,3,1
        root.postOrder();*/

        // 前序遍历方式
        System.out.println("前序遍历方式~~~~~~~~~~~~");
        HeroNode heroNode = root.preOrderSearch(3);
        System.out.println(heroNode);

//        System.out.println("中序遍历");
//        HeroNode result = root.infixOrderSearch(3);
        HeroNode result1 = root.postOrderSearch(2);
        System.out.println("后续遍历查找" + result1);
        HeroNode result2 = root.infixOrderSearch(5);
        System.out.println("中序遍历查找" + result2);
        HeroNode result3 = root.preOrderSearch(5);
        System.out.println("前序遍历查找" + result3);


    }


}

class BinaryTree {
    // 定义一个根节点
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 删除节点,但如果这个节点的左子节点或者右子节点不为空的化,就将左子节点替换为待删除的节点
     * @param no
     */
    public void delNode2(int no) {

        if (this == null) {
            System.out.println("当前树是一颗空树");
            return;
        }

        this.root.removeNode1(no);


    }


    /**
     * 二叉树删除节点
     * @param no 传入待删除节点的no编号
     */
    public void delNode(int no) {

        if (root != null) {
            if (root.no == no) {
                // 如果root是待删除的节点,就将root置为空
                root = null;
            } else {
                this.root.removeNode(no);
            }
        } else {
            System.out.println("这是一个空树，无法输出！");
        }


    }


    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            // 当前二叉树为空无法遍历
            System.out.println("当前二叉树为空无法遍历");
        }
    }

    /**
     * 前序遍历查找
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no) {
        if (root == null) {
            return null;
        }
        return root.preOrderSearch(no);
    }


    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            // 当前二叉树为空无法遍历
            System.out.println("当前二叉树为空无法遍历");
        }
    }


    /**
     * 中序遍历查找
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no) {
        if (root == null) {
            return null;
        }
        return root.infixOrderSearch(no);

    }


    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            // 当前二叉树为空无法遍历
            System.out.println("当前二叉树为空无法遍历");
        }
    }


    /**
     * 后序遍历查找
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        if (root == null) {
            return null;
        }
        return root.postOrderSearch(no);

    }


}


/**
 * 先创建HeroNode节点
 */
class HeroNode {
    public int no;
    public String name;
    // 指向左边的索引
    public HeroNode left; // 一开始left和right不知道指向左边还是指向右边,默认都为null
    // 指向右边的索引
    public HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    /**
     * 删除no对应的节点,如果待删除的节点的做字节或者右子节点不为空,就选择一个补到当前节点的位置
     * @param no
     */
    public void removeNode1(int no) {
        if (this != null) {
            if (this.left != null && this.left.no == no) {
                if (this.left.left != null) {
                    this.left = this.left.left;
                }
               else if (this.left.right != null) {
                    this.left = this.left.right;
                }else {
                    this.left = null;

                }
            }

            if (this.right != null && this.right.no == no) {
                if (this.right.right != null) {
                    this.right = this.right.right;
                }
                else if (this.right.left != null) {
                    this.right = this.right.left;
                }else {
                    this.right = null;

                }
            }

            // 向左边递归,去查找
            if (this.left != null) {
                this.left.removeNode1(no);
            }

            // 向右边递归,去查找
            if (this.right != null) {
                this.right.removeNode1(no);
            }
        }


    }


    /**
     * 删除指定节点
     * // 具体的规则写在上面
     * 简单的来讲，如果是叶子节点就删除该节点，非叶子节点就删除该子树
     * @param no
     */
    public void removeNode(int no) {


        // 2. 如果当前节点的左子树不为空，并且当前节点的左子节点的编号就是待删除的节点，
        // 就将this.left=null(就将当前节点的左子节点置空即可，有点类似链表)；并且提前返回(结束递归)
        HeroNode target = null; // 存储目标的节点
        if (this.left != null && this.left.no == no) {
            target = this.left;
            this.left = null;
        }

        // 3. 如果当前节点的右子树不为空，并且当前节点的右子节点的编号就是待删除的节点，
        //就将this.right=null(就将当前节点的右子节点置空即可，有点类似链表)
        if (this.right != null && this.right.no == no) {
            target = this.right;
            this.right = null;
        }
        // 4. 然后向左边递归，继续查找
        if (this.left != null) {
            this.left.removeNode(no);
            // 这里不要提前返回,防止左子树没有删除成功
        }

        // 5. 向右边递归,继续查找
        if (this.right != null) {
            this.right.removeNode(no);
            return;
        }


    }


    /**
     * 前序遍历查找
     * @param no 雇员编号
     * @return 找到就雇员
     */
    public HeroNode preOrderSearch(int no) {
        // 用来统计查找了多少次
        System.out.println("前序遍历查找~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // 1. 先比较当前节点的id是否和传入的雇员的id是否一致
        if (this.no == no) {
            return this;
        }
        // 2. 则判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
        HeroNode resNode = null;  // 这个变量用来保存是否找到
        if (this.left != null) {
            // 左递归查找
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            // 说明已经找到了
            return resNode;
        }
        // 3. 没有找到就继续判断右子节点是否为空,继续右递归查找

        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;


    }


    /**
     * 中序遍历查找
     * @param no 查找的id
     * @return
     */
    public HeroNode infixOrderSearch(int no) {

        // 先判断当前节点的左子节点是否等于no
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        System.out.println("中序遍历查找~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // 父节点(当前节点)
        if (this.no == no) {
            return this;
        }

        // 右子节点
        if (this.right != null) {
            return this.right.infixOrderSearch(no);

        }
        return resNode;


    }


    /**
     * 后续遍历查找
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {

        HeroNode resNode = null;

        // 先判断当前节点的左子节点是否为空
        if (this.left != null) {
            // 开始向左边后序遍历查找
            resNode = this.left.postOrderSearch(no);

        }
        if (resNode != null) {
            return resNode;
        }

        // 再判断当前节点点的右子节点是否为空,若不为空,则向右递归遍历
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }
        System.out.println("后续遍历的次数~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // 最后再判断当前节点的no是否等于传入的no
        if (this.no == no) {
            return this;
        }


        return resNode;
    }


    /**
     * 前序遍历：
     * 1. 先输出当前节点（初始节点是 root 节点）
     * 2. 如果左子节点不为空,递归继续前序遍历
     * 3. 如果右子节点不为空,递归继续后续遍历
     *
     */
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            // 向左递归前序遍历
            this.left.preOrder();
        }

        if (this.right != null) {
            // 递归向右子树前序遍历
            this.right.preOrder();
        }

    }

    /**
     * 树的中序遍历：
     * 1. 先中序遍历左子树(递归)
     * 2. 再输出父节点
     * 3. 再递归中序遍历右子树
     *
     */
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        // 递归向右子树后续遍历
        if (this.right != null) {
            this.right.infixOrder();
        }


    }

    /**
     * 后续遍历：
     * 1. 先后序遍历右子树(递归)
     * 2. 再输出父节点
     * 3. 再后序遍历左子树(递归)
     *
     */
    public void postOrder() {

        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();

        }
        System.out.println(this);


    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name +
                '}';
    }
}






