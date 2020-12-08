package info.sinamon.mapsort;

import info.sinamon.mapsort.data.PlaceData;
import info.sinamon.mapsort.data.PlaceDistance;
import info.sinamon.mapsort.data.UserInput;
import info.sinamon.mapsort.enums.Category;
import info.sinamon.mapsort.enums.MapType;
import info.sinamon.mapsort.services.Distance;
import info.sinamon.mapsort.services.Graph;
import info.sinamon.mapsort.services.SortPlace;
import info.sinamon.mapsort.store.MapInfo;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MapSort {
    // 사용자 입력을 받기 위해 스캐너 인스턴스를 만듭니다.
    private static final Scanner scan = new Scanner(System.in);
    // 지도 출력을 위한 그래프 인스턴스를 만듭니다.
    private static final Graph graph = new Graph(10);
    // 각 장소의 거리를 구하기 위해 거리 인스턴스를 만듭니다.
    private static final Distance sp = new Distance();

    // 초기 장소 데이터를 등록합니다.
    static {
        MapInfo.putMap("테스트1", new PlaceData("테스트1", 10.10, 10.10, Category.직업));
        MapInfo.putMap("테스트2", new PlaceData("테스트2", 11.10, 11.10, Category.직업));
        MapInfo.putMap("테스트3", new PlaceData("테스트3", 30.10, 50.10, Category.직업));
        MapInfo.putMap("테스트4", new PlaceData("테스트4", 40.10, 40.10, Category.직업));
        MapInfo.putMap("테스트4-1", new PlaceData("테스트8", 37.10, 70.10, Category.의료));
        MapInfo.putMap("테스트5", new PlaceData("테스트5", 20.10, 50.10, Category.직업));
        MapInfo.putMap("테스트6", new PlaceData("테스트6", 60.10, 50.10, Category.직업));
        MapInfo.putMap("테스트6-1", new PlaceData("테스트6", 60.10, 50.10, Category.직업));
        MapInfo.putMap("테스트7", new PlaceData("테스트7", 90.10, 70.10, Category.직업));
        MapInfo.putMap("테스트8", new PlaceData("테스트8", 80.10, 40.10, Category.생산품판매));
        MapInfo.putMap("테스트8", new PlaceData("테스트8", 110.10, 80.10, Category.의료));
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
        // 지도의 척도를 이용하여 내 X, Y 위치를 구합니다.
        int myX = (int) (input.lat / graph.getR());
        int myY = (int) (input.longg / graph.getR());
        // 그래프에 내 위치를 기록합니다.
        graph.setMark(myX, myY, MapType.MY.getValue());

        // 내 위치를 기준으로 장소들의 거리를 구합니다.
        List<PlaceDistance> list = sp.getDistanceByMySelf(myLocation);
        // 구한 거리에서 내가 원하는 카테고리만 걸러냅니다.
        List<PlaceDistance> filteredList = list.stream()
                .filter(distance -> distance.getCategory() == myLocation.getCategory())
                // 걸러낸 데이터를 List 타입으로 바꿉니다.
                .collect(Collectors.toList());

        // 정렬한 거리 데이터를 저장합니다.
        List<PlaceDistance> sortedList = new SortPlace().sort(filteredList);

        // 거리별로 정렬된 첫번째 장소, 즉 가장 가까운 장소를 가져옵니다.
        PlaceDistance near = sortedList.get(0);
        // 가장 가까운 곳의 정보를 출력합니다.
        System.out.println();
        System.out.println(ColorCode.YELLOW  + near.getCategory() + " 카테고리의 최단거리 장소는 " + near.getName() + " 이며 거리는 " + (int) near.getDistanceToOther() + "입니다." + ColorCode.RESET);
        // 지도 범례를 출력합니다.
        System.out.println(ColorCode.RED + "● : 현위치, " + ColorCode.GREEN + "■ : 최단 거리" + ColorCode.RESET);

        // 정렬된 데이터로 그래프 전처리 과정을 합니다.
        graph.preprocessGraphData(sortedList);
        // 그래프를 출력합니다.
        graph.printMap();
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
