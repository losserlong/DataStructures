package com.atguigu.tree.threadedbinarytree;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/27    21:20
 * @Version:1.0
 * 线索化二叉树：
 * 线索化二叉树，其实就叶子节点的left指针和right指针充分利用起来，将前序遍历、中序遍历或者后序遍历遍历完之后
 * 将某种遍历次序之后，将它的左指针指向前驱节点，右指针指向后继节点
 * (如果已经指向一棵子树了就不用指了,或者是第一个节点或者是最后的节点就不用指了)
 *
 */
public class ThreadedBinaryTree {


    public static void main(String[] args) {
        // 测试中序线索二叉树
        HeroNode root = new HeroNode(1, "tom");
        HeroNode n2 = new HeroNode(3, "jack");
        HeroNode n3 = new HeroNode(6, "smith");
        HeroNode n4 = new HeroNode(8, "marry");
        HeroNode n5 = new HeroNode(10, "ami");
        HeroNode n6 = new HeroNode(14, "dfi");

        // 以后会递归创建二叉数
        // 现在手动创建二叉树
        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;

        ThreadTree threadTree = new ThreadTree();
        threadTree.setRoot(root);
        // 进行线索化二叉树
        threadTree.threadedNodes();
        // 测试10号节点的前驱和后继节点是不是3和1
        System.out.println("10号节点的前驱节点" + n5.left);
        System.out.println("10号节点的后继节点" + n5.right);

        // 当线索化二叉树之后，
        System.out.println("用线索化的方法遍历");
//        threadTree.infixThreadedList(); // 8,3,10,1,6,14

        System.out.println("前序遍历");
        threadTree.preThreadedList();


    }


}

/**
 * 定义了ThreadedBinaryTree 实现了线索化功能的二叉树
 */
class ThreadTree {
    // 定义一个根节点
    private HeroNode root;

    // 为了实现线索化,需要创建要给当前节点的前驱节点的引用
    // 在递归的时候，用pre总是保存当前节点的前一个节点
    private HeroNode pre = null;


    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历线索二叉树的遍历
     * 先打印自己，然后再是左，再是右
     */
    public void preThreadedList() {
        HeroNode node = root;
        while (node != null) {

            // 前序先输出自己
            System.out.println(node);

            // 再输出左边的节点
            while (node.leftType == 0) {
                node = node.left;
                System.out.println(node);
            }

            while (node.rightType == 1) {
                node = node.right;
                System.out.println(node);
            }
            node = node.right;


        }

    }


    /**
     * 中序线索二叉树
     * 遍历线索线索化二叉树的方法
     */
    public void infixThreadedList() {
        // 定义一个变量,存储当前遍历的节点,从root开始
        HeroNode node = root;
        while (node != null) {
            // 循环找到leftType == 1的结点，第一个找到的就是8这个结点
            // 后面随着node的变化，left Type == 1时，说明该节点是按照线索化处理的有效节点
            // 处理后的有效节点
            while (node.leftType == 0) {
                node = node.left;
            }
            // 打印当前这个节点
            System.out.println(node);
            // 如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.rightType == 1) {
                node = node.right;
                System.out.println(node);
            }
            // 替换这个遍历的节点
            node = node.right;
        }
    }


    /**
     * 将方法进行重载
     */
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     *  编写对二叉树进行中序遍历线索化的方法，其实就是将树转换成线索化树
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        // 如果当前节点为空就不能进行线索化
        if (node == null) {
//            System.out.println("当前节点为空,不能进行线索化");
            return;
        }

        // 不为空，就按照中序线索化的方式来处理
        // ========================(一)线索化左子树
        threadedNodes(node.left);

        // ========================(二)线索化当前节点
        // 先处理当前节点的左指针
        // 以8号节点为例    8节点.left = null , 8节点的.leftType = 1(说明8的left还是有前驱节点但不过是指向空)
        if (node.left == null) {

            // 如果当前节点的前驱节点为空,就让当前节点的左指针,指向前驱节点
            node.left = pre;
            // 修改当前节点的左指针的类型,1表示指向的是前驱节点,0表示指向的是后继节点
            node.leftType = 1;
        }

        // 处理后继节点，(个人理解也就是第二次进来的时候pre就到了8的位置,node就退出到了下一个栈)
        if (pre != null && pre.right == null) {
            // 让前驱节点的右指针，指向当前节点
            pre.right = node;
            // 修改前驱节点的右指针类型
            pre.rightType = 1;
        }

        // !!!!!!!!!!每处理一个节点后，要让当前节点是下一个节点的前驱节点.
        pre = node;


        // ========================(三)线索化右子树
        threadedNodes(node.right);


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

class HeroNode {
    public int no;
    public String name;
    // 指向左边的索引
    public HeroNode left; // 一开始left和right不知道指向左边还是指向右边,默认都为null
    // 指向右边的索引
    public HeroNode right;

    //  说明：
    //  1. 如果leftType == 0 表明指向的是左子树，如果 1 表示指向的是前驱节点
    //  1. 如果rightType == 0 表明指向的是右子树，如果 1 表示指向的是后继驱节点
    public int leftType;
    public int rightType;


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
                } else if (this.left.right != null) {
                    this.left = this.left.right;
                } else {
                    this.left = null;

                }
            }

            if (this.right != null && this.right.no == no) {
                if (this.right.right != null) {
                    this.right = this.right.right;
                } else if (this.right.left != null) {
                    this.right = this.right.left;
                } else {
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
     * 复习删除节点
     * @param no
     */
    public void remove3(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
        }

        // 向左边递归查找
        if (this.left != null) {
            this.left.remove3(no);
        }
        // 向右边递归查找
        if (this.right != null) {
            this.right.remove3(no);
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

