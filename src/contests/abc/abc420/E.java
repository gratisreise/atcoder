package contests.abc.abc420;

import java.io.*;
import java.util.*;

public class E {
    static int N, Q;
    static int[] parent;
    static boolean[] isBlack;
    static int[] blackCount;

    // 유니온-파인드: 루트를 찾는 find 연산 (경로 압축 적용)
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // 유니온-파인드: 두 집합을 합치는 union 연산
    static void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU != rootV) {
            // blackCount를 합쳐준다
            parent[rootU] = rootV;
            blackCount[rootV] += blackCount[rootU];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        isBlack = new boolean[N + 1];
        blackCount = new int[N + 1];

        // 초기화: 모든 노드는 자기 자신을 부모로 가리킴
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) { // 간선 추가
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                union(u, v);
            } else if (type == 2) { // 색상 변경
                int v = Integer.parseInt(st.nextToken());
                int rootV = find(v);

                if (isBlack[v]) {
                    isBlack[v] = false;
                    blackCount[rootV]--;
                } else {
                    isBlack[v] = true;
                    blackCount[rootV]++;
                }
            } else { // 도달 가능성 확인
                int v = Integer.parseInt(st.nextToken());
                int rootV = find(v);

                if (blackCount[rootV] > 0) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
        }
    }
}