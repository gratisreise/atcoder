package contests.abc.abc421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class C {

    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);

        int N = Integer.parseInt(in.readLine());
        String S = in.readLine();

        // 'A'의 위치를 저장할 리스트
        List<Integer> posA = new ArrayList<>();
        for (int i = 0; i < 2 * N; i++) {
            if (S.charAt(i) == 'A') {
                posA.add(i);
            }
        }

        // A로 시작하는 패턴 (A는 0, 2, 4, ...)에 대한 스왑 횟수 계산
        long sum1 = 0;
        for (int k = 0; k < N; k++) {
            int target = 2 * k; // A는 짝수 인덱스
            sum1 += Math.abs(posA.get(k) - target);
        }

        // B로 시작하는 패턴 (A는 1, 3, 5, ...)에 대한 스왑 횟수 계산
        long sum2 = 0;
        for (int k = 0; k < N; k++) {
            int target = 2 * k + 1; // A는 홀수 인덱스
            sum2 += Math.abs(posA.get(k) - target);
        }

        // 두 경우 중 최소 스왑 횟수 출력
        out.println(Math.min(sum1, sum2));

        out.flush();
        out.close();
    }
}

