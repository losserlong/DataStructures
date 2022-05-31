package com.atguigu.graph;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/10    17:38
 * @Version:1.0
 * ====================================图的相关学习==========================
 * 双层for循环外层循环代表的是行数,内层循环代表的是列数
 *
 *
 * 用邻接矩阵来表示图
 *  *     A B C D E
 *  * A   0 1 1 0 0
 *  * B   1 1 0 0 0
 *  * C   0 1 0 0 0
 *  * D   0 1 0 0 0
 *  * E   0 1 0 0 0
 * 说明
 * (1) 1表示能够直接连接
 * (2) 2表示不能够直接连接
 *
 *
 */
public class Graph {


    // 存储顶点集合
    private List<String> vertexLists;
    private int[][] edges; // 存储图对应的邻接矩阵
    // 边的数量
    private int numOfEdges;
    // 定义给定的数组boolean[] , 记录某个结点是否被访问过
    private boolean[] isVisited;


    // 构造器
    public Graph(int n) {
        // 初始化矩阵
        edges = new int[n][n];
        // 初始化顶点的集合
        vertexLists = new ArrayList<>(n);
        // 初始化边为0
        numOfEdges = 0;

        // 放到构造器中进行初始化
        isVisited = new boolean[n];

    }


    /**
     * 遍历所有的节点都进行广度优先
     */
    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }


    }


    /**
     * 对一个节点进行广度优先遍历的方法
     * @param isVisited
     * @param i
     */
    private void bfs(boolean[] isVisited, int i) {
        int u; // 表示队列的头节点对应的下标
        int w; // 代表的是邻接节点
        // 队列,记录节点访问的一个顺序
        Queue<Integer> queue = new LinkedList<>();

        // 访问这个节点,也就是输出节点的信息
        System.out.print(getValueByIndex(i) + "==>");
        // 访问之后,要标记为已访问
        isVisited[i] = true;
        // 将节点加入到队列
        queue.add(i);

        // 只要队列非空就继续执行
        while (!queue.isEmpty()) {
            // 取出队列的头节点的下标
            u = queue.poll();
            // 获取第一个邻接节点的下标
            w = getFirstNeighbour(u);
            while (w != -1) {
                // 找到,是否访问过
                if (!isVisited[w]) {
                    // 没访问
                    System.out.print(getValueByIndex(w) + "==>");
                    isVisited[w] = true;

                    // 加入队列
                    queue.add(w);
                }
                // 如果已经访问过就去找当前顶点的下下个顶点是否被访问
                // 也就是以u为前驱节点,找w后面的第一个节点
                w = getNextNeighbour(u, w);

            }
        }
    }


    /**
     * 深度优先遍历算法
     *
     * 1. 访问初始节点v，并标记结点v为已访问
     * 2. 查找结点v的第一个邻接结点w
     * 3. 若w存在，则继续执行4，如果w不存在，则回到第1步，将从v的下一个节点继续
     * 其实就是递归的时候回退到上一个栈，也就是回溯)
     * 4. 若w未被访问，对w进行深度优先遍历递归(即把w当作另一个v，然后进行步骤123)
     * 5. 查找结点v的w邻接结点的下一个邻接结点，转到步骤3 (其实就是w已经被访问了)
     *
     * @param isVisited 表示该节点是否已经被访问过
     * @param i 第一次就是0
     */
    private void dfs(boolean[] isVisited, int i) {
        // 首先我们访问该节点
        System.out.print(getValueByIndex(i) + "->");
        // 1. 将这个节点设置成已经访问
        isVisited[i] = true;

        // 2. 访问完当前节点之后，查找当前节点的第一个邻接节点w
        int w = getFirstNeighbour(i);
        while (w != -1) { // 说明有邻接节点
            // 还得判断是否被访问
            if (!isVisited[w]) {
                // 没有被访问,就递归进行遍历,就是对w进行访问
                dfs(isVisited, w);
            } else {
                // 如果w节点已经被访问,就去查找邻接节点的下一个邻接节点
                w = getNextNeighbour(i, w);
            }
        }
    }

    /**
     * 对dfs进行一个重载,遍历我说们所有的节点,并进行dfs
     */
    public void dfs() {
        isVisited = new boolean[getNumOfVertex()];
        // 遍历所有的节点,进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }


    }


    /**
     * 得到第一个邻接结点的下标 w
     * @param index
     * @return 如果存在就返回对应的下标, 否则返回 -1
     */
    public int getFirstNeighbour(int index) {
        for (int j = 0; j < vertexLists.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;

    }

    /**
     * 根据前一个邻接结点的下标来获取下一个邻接结点
     * 如果v2节点被访问过,则查找v1节点的下一个邻接节点(就不是第一个节点了)
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbour(int v1, int v2) {
        for (int j = v2 + 1; j < vertexLists.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }


    /**
     * 显示图所对应的矩阵
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }


    /**
     * 返回v1和v2的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWight(int v1, int v2) {
        return edges[v1][v2];
    }


    /**
     * 返回i(下标)对应的数据比如 0--> "A" , 1 --> "B"  2--> "C"
     * @param i
     * @return
     */
    public String getValueByIndex(int i) {
        return vertexLists.get(i);
    }


    /**
     * 返回节点中的个数
     * @return
     */
    public int getNumOfVertex() {
        return vertexLists.size();
    }

    /**
     * 得到边的数目
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }


    /**
     * 插入顶点
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexLists.add(vertex);
    }

    /**
     * 添加边
     * @param v1 表示点的下标,即第几个顶点, "A" - "B" "A" -> 0 "B" -> 1 ,这里的A对应的下标就是"0",B对应的下标就是"1"
     * @param v2 第二个顶点的下标
     * @param weight 两个顶点之间的边的权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        // 这里是无向图,所以v1到v2要设置权重为weight,v2到v1也要设置权重
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        // 将边的数目++
        numOfEdges++;
    }


}
