package com.atguigu.kmp;

/**
 * Date 09/22/2014
 * @author tusroy
 *
 * 使用 KMP 算法进行模式匹配
 *
 * 运行时复杂度 —— O(m + n) 其中 m 是文本长度，n 是模式长度
 * 空间复杂度 —— O(n)
 */
public class SubstringSearch {

    /**
     * Slow method of pattern matching（暴力破解）
     */
    /**
     *
     * @param text 文本串
     * @param pattern 模式串
     * @return
     */
    public static boolean hasSubstring(char[] text, char[] pattern) {
        int i = 0;
        int j = 0;
        int k = 0;
            while (i < text.length && j < pattern.length) {
            if (text[i] == pattern[j]) {
                i++;
                j++;
            } else {
                j = 0;
                k++;
                i = k;
            }
        }
        if (j == pattern.length) {
            return true;
        }
        return false;
    }

    /**
     * 计算临时数组以保持与前缀相同的后缀大小时间/空间复杂度为 O（模式大小）
     *
     *
     */
    private int[] computeTemporaryArray(char pattern[]) {
        int[] lps = new int[pattern.length];
        int index = 0;
        for (int i = 1; i < pattern.length; ) {
            if (pattern[i] == pattern[index]) {
                lps[i] = index + 1;
                index++;
                i++;
            } else {
                if (index != 0) {
                    index = lps[index - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * KMP algorithm of pattern matching.
     */
    public boolean KMP(char[] text, char[] pattern) {

        int lps[] = computeTemporaryArray(pattern);
        int i = 0;
        int j = 0;
        while (i < text.length && j < pattern.length) {
            if (text[i] == pattern[j]) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        if (j == pattern.length) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {

        String str = "abcxabcdabcdabcy";
        String subString = "abcdabcy";
        SubstringSearch ss = new SubstringSearch();
        boolean b = hasSubstring(str.toCharArray(), subString.toCharArray());
        System.out.println(b);
        boolean result = ss.KMP(str.toCharArray(), subString.toCharArray());
        System.out.print(result);

    }
}