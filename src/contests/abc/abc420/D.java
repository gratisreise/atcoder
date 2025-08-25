package contests.abc.abc420;

import java.io.*;
import java.util.*;

public class D {
    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] hw = in.readLine().trim().split("\\s+");
        int H = Integer.parseInt(hw[0]);
        int W = Integer.parseInt(hw[1]);

        char[][] g = new char[H][W];
        int sr = -1, sc = -1, gr = -1, gc = -1;

        for (int i = 0; i < H; i++) {
            String s = in.readLine();
            g[i] = s.toCharArray();
            for (int j = 0; j < W; j++) {
                if (g[i][j] == 'S') { sr = i; sc = j; }
                if (g[i][j] == 'G') { gr = i; gc = j; }
            }
        }

        // dist[r][c][p] = min steps to reach (r,c) with parity p
        int[][][] dist = new int[H][W][2];
        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                Arrays.fill(dist[i][j], -1);

        ArrayDeque<int[]> q = new ArrayDeque<>();
        dist[sr][sc][0] = 0;
        q.add(new int[]{sr, sc, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], p = cur[2];

            // 목표 지점 도달 시 즉시 종료
            if (r == gr && c == gc) {
                System.out.println(dist[r][c][p]);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d], nc = c + DC[d];

                // 경계선 및 장애물 체크
                if (nr < 0 || nr >= H || nc < 0 || nc >= W || g[nr][nc] == '#') {
                    continue;
                }

                char ch = g[nr][nc];

                // 현재 문의 상태에 따른 이동 가능 여부 체크
                if ((ch == 'x' && p == 0) || (ch == 'o' && p == 1)) {
                    continue;
                }

                // 스위치를 밟았을 때 문의 상태 토글
                int np = (ch == '?') ? (p ^ 1) : p;

                // 방문하지 않은 상태일 때만 갱신하고 큐에 추가
                if (dist[nr][nc][np] == -1) {
                    dist[nr][nc][np] = dist[r][c][p] + 1;
                    q.add(new int[]{nr, nc, np});
                }
            }
        }

        // 목표 지점에 도달할 수 없는 경우
        System.out.println(-1);
    }
}

