package com.atguigu.graph;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/12    8:46
 * @Version:1.0
 * 图的复习
 * 图是由点和边组成的
 *  用临接矩阵来表示图
 *      A B C D E
 *  A   0 1 1 0 0
 *  B   1 1 0 0 0
 *  C   0 1 0 0 0
 *  D   0 1 0 0 0
 *  E   0 1 0 0 0
 *  说明
 *  (1) 1表示能够直接连接
 *  (2) 2表示不能够直接连接
 *
 */
public class GraphReview {

    public static void main(String[] args) {

        // 结点的个数
        int n = 5;
        // 结点的值
        String vertexs[] = {"A", "B", "C", "D", "E"};
        GraphReview graph = new GraphReview(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        // 添加边  A-B     A-C     C-B     B-D     B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(2, 1, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();
        graph.dfs();


    }



    // 存储点的集合
    private List<String> vertexLists;
    // 表示图的关系
    private int[][] edges;
    // 边的数量
    private int numsOfEdges;
    // 表明节点是否被访问过
    private boolean[] isVisited;

    public GraphReview() {
    }

    public GraphReview(int n) {
        this.vertexLists = new ArrayList<>(n);
        this.edges = new int[n][n];
        // 初始化边的数量为0
        this.numsOfEdges = 0;
        this.isVisited = new boolean[n];
    }





    /**
     * 对dfs进行重载
     * 遍历所有的节点,进行深度优先遍历
     */
    public void dfs() {
        // 遍历所有节点,当没有访问就调用dfs
        for (int i = 0; i < getVertexCount(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }


    /**
     * dfs深度优先遍历(纵向的),这还知识对一个节点进行dfs
     * @param isVisited 判断当前顶点是否已经被访问过
     * @param i 当前顶点的下标
     */
    public void dfs(boolean[] isVisited, int i) {
        // 1. 先当问当前节点,并将它设置为已经访问
        System.out.print(getVertexName(i) + "->");
        // 设置为已经访问
        isVisited[i] = true;
        // 2. 当前节点访问完成之后,访问当前节点的第一个邻接节点w
        int firstNeighbour = getFirstNeighbour(i);
        while (firstNeighbour != -1) {
            // 若存在,若w未被访问,对w进行深度优先遍历
            if (!isVisited[firstNeighbour]) {
                dfs(isVisited, firstNeighbour);
            } else {
                // 当firstNeighbour已经被访问了,就查找它的邻接节点,设置为firstNeighbour
                firstNeighbour = getNextNeighbour(i, firstNeighbour);

            }


        }

    }


    /**
     * 返回当前顶点的第一个邻接节点
     * @param index
     * @return 找到了就返回对应的下标, 若未找到就返回-1
     */
    public int getFirstNeighbour(int index) {
        for (int i = 0; i < vertexLists.size(); i++) {
            if (edges[index][i] > 0) return i;
        }
        return -1;

    }

    /**
     * 寻找当前vertex1的下一个邻接节点
     * @param vertex1
     * @param vertex2 和vertex1当前相邻的节点
     * @return 找到了就返回, 没找到就返回-1
     */
    public int getNextNeighbour(int vertex1, int vertex2) {
        for (int i = vertex2 + 1; i < vertexLists.size(); i++) {
            if (edges[vertex1][i] > 0) {
                // 因为我们设置权重值是1所以只要大于0即可
                return i;
            }
        }
        return -1;
    }


    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }

    }


    /**
     * 获取两个顶点的权重
     * @return
     */
    public int getWeight(int vertex1, int vertex2) {
        return edges[vertex1][vertex2];
    }


    /**
     * 返回顶点集合中第i个顶点的名字
     * 比如 下标0表示A ，下标1表示B ...
     *
     * @param i
     * @return
     */
    public String getVertexName(int i) {
        return vertexLists.get(i);
    }


    /**
     * 返回节点的数量
     * @return
     */
    public int getVertexCount() {
        return vertexLists.size();
    }

    /**
     * 获取边的数量
     * @return
     */
    public int getNumsOfEdges() {
        return numsOfEdges;
    }

    /**
     * 往顶点的集合中插入顶点
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexLists.add(vertex);
    }

    /**
     * 添加边
     * @param vertex1 顶点1
     * @param vertex2 顶点2
     * @param weight 权重
     */
    public void insertEdge(int vertex1, int vertex2, int weight) {
        // 因为是无向图所以两边都要设置成相同的权值
        edges[vertex1][vertex2] = weight;
        edges[vertex2][vertex1] = weight;
        // 边的数量+1
        numsOfEdges++;
    }


}
