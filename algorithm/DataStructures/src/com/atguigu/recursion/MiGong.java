package com.atguigu.recursion;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/17    21:19
 * @Version:1.0
 * 二维数组int[i][j]前面的i代表的是列，后面的j代表队的是行
 *
 *
 * 迷宫问题
 */
public class MiGong {
    public static void main(String[] args) {
        // 先创建一个二维数组来模拟迷宫
        // 地图map
        int[][] map = new int[8][7];
        // 使用1表示=墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            // 表示将第0行和第7行所有的列都置为1
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 把左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置相应的挡板,1表示
        map[3][1] = 1;
        map[3][2] = 1;


        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "   ");
            }
            System.out.println();
        }

        // 使用递归回溯给小球找路
        // 调用不同策略的方法
        setWay2(map, 1, 1);

        // 输出新的路，小球走过，并且标识过的地图
        System.out.println(" 输出新的路，小球走过，并且标识过的地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "   ");
            }
            System.out.println();
        }


    }

    // 说明：
    // 1. map表示地图
    // 2. i,j表示从地图的哪个位置出发(1,1)
    // 3. 如果小球能到map[6][5]位置，则说明通路找到
    // 4. 约定：当map的[i][j]为0时表示该点没有走过，为 1 表示的是墙，2表示通路可以走；3.表示该位置已经走过，但是走不通（用于回溯）
    // 5. 再走迷宫的时候，需要确定一个策略(方法) 下->右->上->左,如果走不通，再回溯


    /**
     *使用递归回溯来给小球找路
     * 说明：
     * @param map 表示地图
     * @param i  i和j表示小球的坐标
     * @param j
     * @return 如果找到通路了就返回true，反之为false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 说明通路已经找到了
            return true;
        } else {
            // 分情况来讨论
            if (map[i][j] == 0) {
                // 如果当前这个位置还没有走过的时候
                // 按照策略 下->右->上->左 走
                map[i][j] = 2;  // 假定该点能走通
                if (setWay(map, i + 1, j)) {
                    // 表示向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    // 表示向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    // 表示向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    // 表示向左走
                    return true;
                } else {
                    // 说明根本走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j] !=0 , 可能是1，2，3
                return false;
            }
        }

    }

    /**
     * 修改走路的策略，改成上->右->下->左
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 说明通路已经找到了
            return true;
        } else {
            // 分情况来讨论
            if (map[i][j] == 0) {
                // 如果当前这个位置还没有走过的时候
                // 按照策略 上->右->下->左 走
                map[i][j] = 2;  // 假定该点能走通
                if (setWay2(map, i - 1, j)) {
                    // 表示向下走
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    // 表示向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) {
                    // 表示向上走
                    return true;
                } else if (setWay2(map, i, j - 1)) {
                    // 表示向左走
                    return true;
                } else {
                    // 说明根本走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j] !=0 , 可能是1，2，3
                return false;
            }
        }

    }


}
