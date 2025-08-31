package contests.abc.abc421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {

    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);


        var st = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        long[] dp = new long[11];
        dp[1] = x;
        dp[2] = y;
        for(int i = 3; i <= 10; i++){
            long sum = (dp[i-1] + dp[i-2]);
            var sb = new StringBuilder(String.valueOf(sum)).reverse();
            dp[i] = Long.parseLong(sb.toString());
        }
        System.out.println(dp[10]);

        out.flush();
        out.close();
    }
}

