package contests.abc.abc421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine());
        String[] nums = new String[n + 1];
        for(int i = 1; i <= n; i++){
            nums[i] = in.readLine();
        }
        var st = new StringTokenizer(in.readLine());
        int idx = Integer.parseInt(st.nextToken());
        String s = st.nextToken();
        if(s.equals(nums[idx])){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        out.flush();
        out.close();
    }
}
