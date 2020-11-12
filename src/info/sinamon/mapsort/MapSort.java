package info.sinamon.mapsort;

import info.sinamon.mapsort.data.PlaceData;
import info.sinamon.mapsort.data.PlaceDistance;
import info.sinamon.mapsort.data.UserInput;
import info.sinamon.mapsort.enums.Category;
import info.sinamon.mapsort.services.Distance;
import info.sinamon.mapsort.services.SortPlace;
import info.sinamon.mapsort.store.MapInfo;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MapSort {
    private static final int R = 10;
    private static final Scanner scan = new Scanner(System.in);
    private static final Distance sp = new Distance();
    private static final int[][] graph = new int[360 / R][360 / R];

    // 초기 장소 데이터를 등록합니다.
    static {
        MapInfo.putMap("테스트1", new PlaceData("테스트1", 10.10, 10.10, Category.직업));
        MapInfo.putMap("테스트2", new PlaceData("테스트2", 11.10, 11.10, Category.직업));
        MapInfo.putMap("테스트3", new PlaceData("테스트3", 30.10, 50.10, Category.직업));
        MapInfo.putMap("테스트4", new PlaceData("테스트4", 40.10, 40.10, Category.직업));
        MapInfo.putMap("테스트5", new PlaceData("테스트5", 20.10, 50.10, Category.직업));
        MapInfo.putMap("테스트6", new PlaceData("테스트6", 60.10, 50.10, Category.직업));
        MapInfo.putMap("테스트6-1", new PlaceData("테스트6", 60.10, 50.10, Category.직업));
        MapInfo.putMap("테스트7", new PlaceData("테스트7", 90.10, 70.10, Category.직업));
        MapInfo.putMap("테스트8", new PlaceData("테스트8", 80.10, 40.10, Category.생산품판매));
    }

    public static void main(String[] args) {
        // 유저 입력을 받기 위해 변수 선언
        UserInput input = null;

        try {
            // 유저 입력을 받습니다.
            input = scanInput();
        } catch (IllegalArgumentException ex) {
            // 입력 양식이 틀린경우 경고를 보내고 프로그램을 끝냅니다.
            System.out.println(ColorCode.RED + "카테고리는 직업, 외료, 생산품판매만 선택할 수 있습니다." + ColorCode.RESET);
            System.exit(0);
        }

        // 입력 받은 값으로 내 위치 객체를 만들어 냅니다.
        PlaceData myLocation = new PlaceData("LOCATION", input.lat, input.longg, input.category);
        int myX = (int) (input.lat / R);
        int myY = (int) (input.longg / R);
        graph[myY][myX] = -2;

        // 내 위치를 기준으로 장소들의 거리를 구합니다.
        List<PlaceDistance> list = sp.getDistanceByMySelf(myLocation);
        // 구한 거리에서 내가 원하는 카테고리만 걸러냅니다.
        List<PlaceDistance> filteredList = list.stream()
                .filter(distance -> distance.getCategory() == myLocation.getCategory())
                // 걸러낸 데이터를 List 타입으로 바꿉니다.
                .collect(Collectors.toList());

        // 정렬한 거리 데이터를 저장합니다.
        List<PlaceDistance> sortedList = new SortPlace().sort(filteredList);

        boolean isNearBy = false;
        for (int i = 0; i < sortedList.size(); i++) {
            PlaceDistance distance = sortedList.get(i);
            int value = (int) Math.round(distance.getDistanceToOther());
//            System.out.println(distance.getName() + " " + value);

            int x = (int) (distance.getLatitude() / R);
            int y = (int) (distance.getLongitude() / R);
            if (!isNearBy) {
                graph[y][x] = -1;
                isNearBy = true;
            } else {
                graph[y][x] += 1;
            }
        }

        for (int i = 0; i < 360 / R; i++) {
            for (int j = 0; j < 360 / R; j++) {
                if (graph[i][j] == -1) {
                    System.out.print(ColorCode.GREEN);
                    System.out.print("■");
                }
                if (graph[i][j] == -2) {
                    System.out.print(ColorCode.RED);
                    System.out.print("■");
                }
                if (graph[i][j] == 0) {
                    System.out.print("□");
                }
                if (graph[i][j] == 1) {
                    System.out.print("■");
                }
                if (graph[i][j] > 1) {
                    System.out.print("▣");
                }
                System.out.print(ColorCode.RESET);
            }
            System.out.println();
        }
    }

    private static UserInput scanInput() throws IllegalArgumentException {
        System.out.print(ColorCode.GREEN + "현재 내 위도 : ");
        double lat = scan.nextDouble();

        System.out.print("현재 내 경도 : ");
        double longg = scan.nextDouble();

        System.out.print("카테고리(직업, 의료, 생산품판매) : ");
        Category category = Category.valueOf(scan.next());

        System.out.print(ColorCode.RESET);

        // Scanner로 입력을 받아 UserInput 객체로 반환합니다.
        return new UserInput(lat, longg, category);
    }
}
