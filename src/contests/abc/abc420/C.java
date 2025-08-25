package contests.abc.abc420;

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);

        var st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] A = new int[n];
        int[] B = new int[n];
        int[] M = new int[n];
        String[] sa = in.readLine().split(" ");
        String[] sb = in.readLine().split(" ");
        long sum = 0;
        for(int i = 0; i < n; i++){
            A[i] = Integer.parseInt(sa[i]);
            B[i] = Integer.parseInt(sb[i]);
            M[i] = Math.min(A[i], B[i]);
            sum += M[i];
        }
        for(int i = 0; i < q; i++){
            String[] ip =  in.readLine().split(" ");
            char c = ip[0].charAt(0);
            int idx = Integer.parseInt(ip[1])-1;
            int temp = Integer.parseInt(ip[2]);
            if(c == 'A') A[idx] = temp;
            else B[idx] = temp;
            sum -= M[idx];
            M[idx] = Math.min(A[idx], B[idx]);
            sum += M[idx];
            out.println(sum);
        }
        out.flush();
        out.close();
    }
}
