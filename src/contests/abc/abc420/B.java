package contests.abc.abc420;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {

    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);

        var st  = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] score = new int[n + 1];
        int[][] arr = new int[n + 1][m + 1];
        for(int i = 0; i < n; i++){
            String s = in.readLine();
            for(int j = 0; j < s.length(); j++){
                arr[i+1][j+1] = s.charAt(j)-'0';
            }
        }
        for(int i = 1; i <= m; i++){
            int[] vis = new int[n + 1];
            int cnt0 = 0, cnt1 = 0;
            for(int j = 1; j <= n; j++){
                if(arr[j][i] == 1) {
                    cnt1++; vis[j] = 1;
                } else {
                    cnt0++; vis[j] = 0;
                }
            }
            for(int j = 1; j <= n; j++){
                if(cnt1 < cnt0 && vis[j] == 1) score[j]++;
                else if(cnt1 > cnt0 && vis[j] == 0) score[j]++;
                else if(cnt1 == cnt0) score[j]++;
            }
        }
        int mx = -1;

        for(int i = 1; i <= n; i++) mx = Math.max(score[i], mx);
        for(int i = 1; i <= n; i++) if(score[i] == mx) out.printf("%d ", i);

        out.flush();
        out.close();
    }
}



