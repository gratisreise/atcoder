package contests.abc.abc421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class D {

    // RLE 블록 정보를 저장할 클래스
    static class Move {
        char dir;  // 움직임 방향 (U, D, L, R)
        long count; // 해당 방향으로 움직이는 횟수

        public Move(char dir, long count) {
            this.dir = dir;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);

        // 1. 입력 받기
        var st = new StringTokenizer(in.readLine());
        long rt = Long.parseLong(st.nextToken()); // 타카하시 초기 행
        long ct = Long.parseLong(st.nextToken()); // 타카하시 초기 열
        long ra = Long.parseLong(st.nextToken()); // 아오키 초기 행
        long ca = Long.parseLong(st.nextToken()); // 아오키 초기 열

        st = new StringTokenizer(in.readLine());
        long n = Long.parseLong(st.nextToken()); // 총 움직임 횟수
        int m = Integer.parseInt(st.nextToken()); // 타카하시 RLE 블록 개수
        int l = Integer.parseInt(st.nextToken()); // 아오키 RLE 블록 개수

        var sMoves = new ArrayList<Move>(); // 타카하시 움직임 리스트
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            sMoves.add(new Move(st.nextToken().charAt(0), Long.parseLong(st.nextToken())));
        }

        List<Move> tMoves = new ArrayList<>(); // 아오키 움직임 리스트
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(in.readLine());
            tMoves.add(new Move(st.nextToken().charAt(0), Long.parseLong(st.nextToken())));
        }

        // 2. 상대적 위치 추적
        long dr = rt - ra; // 행 좌표 차이 (T - A)
        long dc = ct - ca; // 열 좌표 차이 (T - A)

        long totalCount = 0; // 총 만나는 횟수
        int sIdx = 0;        // 타카하시 RLE 블록 인덱스
        int tIdx = 0;        // 아오키 RLE 블록 인덱스

        // 3. RLE 블록을 순회하며 시뮬레이션
        while (sIdx < m && tIdx < l) {
            Move sMove = sMoves.get(sIdx);
            Move tMove = tMoves.get(tIdx);

            // 현재 두 RLE 블록 중 더 짧은 구간의 길이
            long steps = Math.min(sMove.count, tMove.count);

            // 한 스텝당 상대적 위치 변화량 계산
            long drChange = getDrChange(sMove.dir, tMove.dir);
            long dcChange = getDcChange(sMove.dir, tMove.dir);


            // 4. 현재 구간에서 만나는 횟수 계산 (수학적 접근)
            if (drChange == 0 && dcChange == 0) {
                // 변화가 없는 경우:
                // 이미 같은 위치라면, 이 구간 내내 계속 만남
                if (dr == 0 && dc == 0) {
                    totalCount += steps;
                }
            } else if (drChange == 0) {
                // 행 위치 차이는 고정, 열 위치 차이만 변하는 경우:
                // 행 차이가 0이고, 열 차이가 0이 되는 지점 찾기
                if (dr == 0 && dc % dcChange == 0) {
                    long k = -dc / dcChange;
                    if (k >= 1 && k <= steps) {
                        totalCount++;
                    }
                }
            } else if (dcChange == 0) {
                // 열 위치 차이는 고정, 행 위치 차이만 변하는 경우:
                // 열 차이가 0이고, 행 차이가 0이 되는 지점 찾기
                if (dc == 0 && dr % drChange == 0) {
                    long k = -dr / drChange;
                    if (k >= 1 && k <= steps) {
                        totalCount++;
                    }
                }
            } else { // drChange != 0 && dcChange != 0
                // 행, 열 위치 차이 모두 변하는 경우:
                // 두 방정식을 모두 만족하는 k 찾기
                if ((dr % drChange == 0) && (dc % dcChange == 0)) {
                    long k1 = -dr / drChange;
                    long k2 = -dc / dcChange;
                    if (k1 == k2 && k1 >= 1 && k1 <= steps) {
                        totalCount++;
                    }
                }
            }

            // 5. 다음 구간을 위한 위치 업데이트 및 인덱스 이동
            dr += steps * drChange;
            dc += steps * dcChange;

            sMove.count -= steps;
            tMove.count -= steps;

            if (sMove.count == 0)  sIdx++;
            if (tMove.count == 0)  tIdx++;
        }

        out.println(totalCount);
        out.flush();
        out.close();
    }

    // 타카하시와 아오키의 움직임 방향에 따른 dr 변화량 계산
    static long getDrChange(char sDir, char tDir) {
        long drChange = 0;
        if (sDir == 'U') drChange--;
        if (sDir == 'D') drChange++;
        if (tDir == 'U') drChange++;
        if (tDir == 'D') drChange--;
        return drChange;
    }

    // 타카하시와 아오키의 움직임 방향에 따른 dc 변화량 계산
    static long getDcChange(char sDir, char tDir) {
        long dcChange = 0;
        if (sDir == 'L') dcChange--;
        if (sDir == 'R') dcChange++;
        if (tDir == 'L') dcChange++;
        if (tDir == 'R') dcChange--;
        return dcChange;
    }
}

