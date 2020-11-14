package info.sinamon.mapsort.services;

import info.sinamon.mapsort.ColorCode;
import info.sinamon.mapsort.data.PlaceDistance;
import info.sinamon.mapsort.enums.MapType;

import java.util.List;

public class Graph {
    private final int R; // 척도
    private final int[][] graph; // 지도 데이터

    public Graph(int R) {
        this.R = R;
        this.graph = new int[360 / R][360 / R];
    }

    // 그래프 전처리 과정
    public void preprocessGraphData(List<PlaceDistance> distanceList) {
        // 가장 가까운 곳을 알려주기 위한 플래그 변수
        boolean isNearBy = false;

        // 거리를 담고 있는 모든 장소를 돌면서
        for (PlaceDistance distance : distanceList) {
            // 척도를 기반으로 각 장소의 x, y를 구합니다.
            int x = (int) (distance.getLatitude() / R);
            int y = (int) (distance.getLongitude() / R);

            // 아직 가장 가까운 곳을 못찾았다면
            if (!isNearBy) {
                // x y 좌표에 가장 가까운 곳이라고 표시해줍니다.
                setMark(x, y, MapType.NEARBY.getValue());
                // 가장 가까운 곳을 찾았다고 플래그를 변경합니다.
                isNearBy = true;
                continue;
            }

            // 가장 가까운 곳을 찾았다면,
            // 그냥 위치로 표시해줍니다.
            graph[y][x] += 1;
        }
    }

    public void printMap() {
        // 모든 그래프를 돌면서
        for (int i = 0; i < 360 / R; i++) {
            for (int j = 0; j < 360 / R; j++) {
                // 지도를 출력합니다.
                System.out.print(getMark(graph[i][j]));
            }
            System.out.println();
        }
    }

    public void setMark(int x, int y, int value) {
        this.graph[y][x] = value;
    }

    // 지도 값을 보고 범례가 존재한다면 그것을 출력하고, 아니면 빈 곳 또는 위치로 표시합니다.
    private String getMark(int value) {
        if (value == MapType.NEARBY.getValue()) {
            return ColorCode.GREEN + "■" + ColorCode.RESET;
        }
        if (value == MapType.MY.getValue()) {
            return ColorCode.RED + "●" + ColorCode.RESET;
        }
        if (value == 0) {
            return "□";
        }
        if (value == 1) {
            return "■";
        }
        if (value > 1) {
            return "▣";
        }

        return null;
    }

    public int[][] getGraph() {
        return this.graph;
    }

    public int getR() {
        return R;
    }
}
