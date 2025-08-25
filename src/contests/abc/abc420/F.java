package contests.abc.abc420;

import java.io.*;
import java.util.*;

public class F {

    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] mat = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                mat[i][j] = line.charAt(j) == '.' ? 1 : 0;
            }
        }

        // N < M인 경우, 격자를 전치하여 N <= M이 되도록 함
        if (n < m) {
            int[][] tmp = new int[m][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    tmp[j][i] = mat[i][j];
                }
            }
            // 변수 스왑
            int temp = n;
            n = m;
            m = temp;
            mat = tmp;
        }

        // sum[h][j] = 높이가 h이고 너비가 1~j인 직사각형의 개수를 미리 계산
        long[][] sum = new long[n + 1][m + 1];
        for (int h = 1; h <= n; h++) {
            for (int j = 1; j <= m; j++) {
                sum[h][j] = sum[h][j - 1] + Math.min(h, k / j);
            }
        }

        int[] a = new int[m]; // 현재 행의 히스토그램 높이
        int[] stk = new int[m]; // 단조 스택
        long ans = 0;

        for (int i = 0; i < n; i++) {
            // 히스토그램 높이 갱신
            for (int j = 0; j < m; j++) {
                a[j] = mat[i][j] == 1 ? a[j] + 1 : 0;
            }

            int z = -1; // 스택 포인터
            // 스택을 이용해 각 기둥의 왼쪽/오른쪽 확장 범위 계산
            for (int j = 0; j < m; j++) {
                while (z != -1 && a[stk[z]] >= a[j]) {
                    int x = stk[z--];
                    int dl = x - (z == -1 ? -1 : stk[z]);
                    int dr = j - x;
                    int h = a[x];

                    if (dl > dr) {
                        int temp = dl;
                        dl = dr;
                        dr = temp;
                    }

                    for (int t = 1, t2 = dl + dr - 1; t < dl; t++, t2--) {
                        ans += (long) Math.min(h, k / t) * t;
                        ans += (long) Math.min(h, k / t2) * t;
                    }
                    ans += (sum[h][dr] - sum[h][dl - 1]) * dl;
                }
                stk[++z] = j;
            }

            // 스택에 남은 기둥 처리 (오른쪽 끝까지 확장)
            while (z != -1) {
                int x = stk[z--];
                int dl = x - (z == -1 ? -1 : stk[z]);
                int dr = m - x;
                int h = a[x];
                if (dl > dr) {
                    int temp = dl;
                    dl = dr;
                    dr = temp;
                }
                for (int t = 1, t2 = dl + dr - 1; t < dl; t++, t2--) {
                    ans += (long) Math.min(h, k / t) * t;
                    ans += (long) Math.min(h, k / t2) * t;
                }
                ans += (sum[h][dr] - sum[h][dl - 1]) * dl;
            }
        }

        pw.println(ans);
        pw.flush();
        br.close();
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        new F().solve();
    }
}