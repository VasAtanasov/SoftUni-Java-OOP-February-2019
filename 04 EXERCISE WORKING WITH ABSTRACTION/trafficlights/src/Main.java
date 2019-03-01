import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // read input
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        List<TrafficLightImpl> trafficLightList = new ArrayList<>();
        String[] input = scan.readLine().split("\\s+");
        for (String anInput : input) {
            TrafficLights currentLight = TrafficLights.valueOf(anInput.toUpperCase());
            TrafficLightImpl trafficLight = new TrafficLightImpl(currentLight);
            trafficLightList.add(trafficLight);
        }
        int number = Integer.parseInt(scan.readLine());
        //print output
        for (int i = 0; i < number; i++) {
            StringBuilder result = new StringBuilder();
            for (TrafficLightImpl aTrafficLightList : trafficLightList) {
                result.append(aTrafficLightList.next()).append(" ");
            }
            System.out.println(result.toString().trim());
        }
    }
}