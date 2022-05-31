package com.atguigu.test.spare_array.linkedlist;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/12    9:41
 * @Version:1.0
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode h2 = new HeroNode(2, "吴用", "智多星");

        HeroNode h1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode h3 = new HeroNode(3, "李逵", "黑旋风");
        HeroNode h4 = new HeroNode(4, "林冲", "豹子头");


        SingleLined singleLined = new SingleLined();
        singleLined.addByNo(h2);
        singleLined.addByNo(h1);

        singleLined.addByNo(h3);
        singleLined.addByNo(h4);
        singleLined.showLinkedList();
        //移除链表中的指定位置的节点
        singleLined.remove(singleLined.headNode, 10);
        singleLined.showLinkedList();
        System.out.println("链表开始进行反转");
        singleLined.reverseLinkedList(singleLined.headNode);
        singleLined.showLinkedList();

        // 获取单链表的长度
        int length = singleLined.getLength(singleLined.headNode);
        System.out.println("单链表的长度length = " + length);
        HeroNode node = singleLined.getINode(singleLined.headNode, 4);
        System.out.println(node);


    }

}

/**
 * 创建英雄的类
 */
class HeroNode {

    // 英雄编号
    public int no;

    // 英雄姓名
    public String name;

    // 英雄昵称
    public String nicName;

    // 指向下个一英雄
    public HeroNode next;

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

    public String getNicName() {
        return nicName;
    }

    public void setNicName(String nicName) {
        this.nicName = nicName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    public HeroNode() {
    }

    public HeroNode(int no, String name, String nicName) {
        this.no = no;
        this.name = name;
        this.nicName = nicName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nicName='" + nicName + '\'' +
                '}';
    }
}


/**
 *
 */
class SingleLined {
    //这个是头节点，带头节点的单向链表
    HeroNode headNode = new HeroNode(0, "", "");


    /**
     * 链表反转
     * @param headNode 传入的是要反转的链表的头节点
     */
    public HeroNode reverseLinkedList(HeroNode headNode) {
        if (headNode.next == null) {
            // 表明只有一个元素
            return headNode.next;
        }
        // 用一个临时变量去接收头节点
        HeroNode cur = headNode.next;
        // 再用一个变量去记录cur的下一个结点，防止链表断裂
        HeroNode cur_next = null;

        // 创建一个要反转的结点
        HeroNode reverseNode = new HeroNode(0, "", "");

        // 对链表进行遍历
        while (true) {
            if (cur == null) {
                break;
            }
            // cur_next去接收cur的下一个结点,防止链表断裂
            cur_next = cur.next;
            // 永远要指向反转链表的首部
            cur.next = reverseNode.next;
            reverseNode.next = cur;
            cur = cur_next;
        }

        // 退出循环之后，要将headNode的下一个结点指向reverse的下一个结点
        headNode.next = reverseNode.next;
        return headNode;

    }


    /**
     *
     * @param headNode 传入链表的头节点
     * @param i  传入倒数的第i
     * @return 返回倒数第i个节点
     */
    public HeroNode getINode(HeroNode headNode, int i) {
        int length = getLength(headNode);

        // 检验i的合法性
        if (i < 0 || i > length) {
            System.out.println("您输入的数据不合法");
            return null;
        }

        // 这里表示链表已经到达最后一个节点了，直接返回空即可
        if (headNode.next == null) {
            return null;
        }
        // 用临时变量去接收除头节点之后的第一个节点
        HeroNode tempNode = headNode.next;

        int index = length - i + 1;
        // 用于判断循环的次数
        int flag = 0;
        while (tempNode.next != null) {
            flag++;
            if (flag == index) {
                break;
            }
            tempNode = tempNode.next;

        }

        return tempNode;
    }


    /**
     * 获取链表的长度
     * @param heroNode
     * @return
     */
    public static int getLength(HeroNode heroNode) {
        // 用临时变量进行接收
        HeroNode tempNode = heroNode;
        int size = 0;
        // 获取链表的长度
        if (tempNode.next == null) {
            return 0;
        }

        while (tempNode.next != null) {
            // 当链表不为空的时候，对链表进行遍历
            size++;
            tempNode = tempNode.next;
        }

        return size;
    }


    /**
     * 单链表的移除操作
     * @param headNode 单链表的头节点
     * @param no  要删除的节点的编号
     */
    public void remove(HeroNode headNode, int no) {
        // 这里用一个临时变量去接受headNode
        HeroNode tempNode = headNode;

        // 因为我们这里是带头结点的单链表，但是我们这里默认没有把头节点算进去
        if (tempNode.next == null) {
            System.out.println("你输入的当前的单链表为空！！！");
            return;
        }

        // 校验no的值是否正确
        if (no > getLength(headNode) || no < 0) {
            System.out.println("您输入的no值有误，请确认后在输入！！！");
            return;
        }


        // 遍历单链表
        while (tempNode.next != null) {
            if (tempNode.next.no == no) {
                System.out.println("被移除的节点为" + tempNode.next);
                tempNode.next = tempNode.next.next;
                break;
            }
            tempNode = tempNode.next;
        }


    }


    /**
     * 添加节点的方法
     * @param heroNode
     */
    public void addNode(HeroNode heroNode) {
        // 头节点不能动，将头节点的值给一个临时变量temp
        HeroNode temp = headNode;

        // 对单链表进行遍历
        while (true) {
            if (temp.getNext() == null) {
                // 最后一个节点的next是为null的，到了这一步，就说明已经是最后一个节点了
                temp.setNext(heroNode);
                break;
            }
            // temp进行遍历
            temp = temp.getNext();
        }
    }

    /**
     * 按照顺序进行添加
     */
    public void addByNo(HeroNode heroNode) {
        // 这里还是用临时变量对头节点进行存储
        HeroNode temp = headNode;
        // 作为标志位，检验那个位置是否存在节点
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                // 这里表示的是temp是头节点的时候，然后就退出，可以直接添加
                break;
            }
            if (temp.getNext().getNo() > heroNode.getNo()) {
                break;
            } else if (temp.getNext().getNo() == heroNode.getNo()) {
                System.out.println("这个编号已经有英雄了，不能进行重复添加");
                break;
            } else {
                // 将temp进行遍历
                temp = temp.getNext();
            }
        }
        heroNode.setNext(temp.getNext());
        temp.setNext(heroNode);

    }


    /**
     * 显示所有的单向链表
     */
    public void showLinkedList() {
        // 这里还是用一个临时变量接收
        HeroNode temp = headNode;
        if (temp.next == null) {
            // 这表明只有一个空的头节点，直接退出
            return;
        }


        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;

        }


    }


}