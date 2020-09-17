import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qixiaotian
 * @date 2020/7/3 13:47
 * @description
 */
public class NQueens {
    List<List<String>> res = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    List<List<String>> solveNQueens(int n) {
        // 记录「路径」
        String[][] strings = new String[n][n];
        for (String[] string : strings) {
            Arrays.fill(string, ".");
        }
        backtrack(strings, 0);
        return res;
    }

    void backtrack(String[][] strings, int row) {
        //结束
        if (row == strings.length) {
            List<String> temp = new LinkedList<>();
            // string:.,.,.,Q
            StringBuilder sb = new StringBuilder();
            for (String[] string : strings) {
                for (String s : string) {
                    sb.append(s);
                }
                temp.add(sb.toString());
                sb.setLength(0);
                // res.add(Arrays.asList(string));
            }
            res.add(temp);
            return;
        }
        int n = strings[row].length;// 该行的长度
        for (int col = 0; col < n; col++) {
            if (!isValid(strings, row, col))
                continue;
            // 选择
            strings[row][col] = "Q";
            // 进入
            backtrack(strings, row + 1);
            // 取消选择
            strings[row][col] = ".";
        }
    }

    private boolean isValid(String[][] strings, int row, int col) {
        // 检查该行

        // 检查该列
        for (String[] string : strings) {
            if (string[col].equalsIgnoreCase("Q"))
                return false;
        }
        // 检查斜方向
        // 左上
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (strings[i][j].equalsIgnoreCase("Q")) {
                return false;
            }
        }
        // 右上
        for (int i = row, j = col; i >= 0 && j < strings.length; i--, j++) {
            if (strings[i][j].equalsIgnoreCase("Q")) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<String>> lists = new NQueens().solveNQueens(4);
        System.out.println(lists);
    }
}
