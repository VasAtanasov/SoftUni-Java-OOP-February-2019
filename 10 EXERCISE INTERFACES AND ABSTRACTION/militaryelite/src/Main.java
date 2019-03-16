import contracts.Mission;
import contracts.Private;
import contracts.Repair;
import contracts.Soldier;
import impl.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader reader;
    private static Map<String, Private> privates;
    private static Map<String, Soldier> soldiers;

    static {
        soldiers = new LinkedHashMap<>();
        privates = new HashMap<>();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException {

        String input;
        while (! "End".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");

            String soldierType = tokens[0];
            String id = tokens[1];
            String firstName = tokens[2];
            String lastName = tokens[3];
            double salary;

            switch (soldierType) {
                case "Private":
                    salary = Double.parseDouble(tokens[4]);
                    Private privateSoldier = new PrivateImpl(id, firstName, lastName, salary);
                    privates.put(id, privateSoldier);
                    soldiers.put(id, privateSoldier);
                    break;
                case "LeutenantGeneral":
                    salary = Double.parseDouble(tokens[4]);
                    List<String> privatesIds = Arrays.stream(tokens).skip(5).collect(Collectors.toCollection(ArrayList::new));
                    Set<Private> privatesToAdd = getPrivates(privatesIds, privates);
                    Soldier LSoldier = new LeutenantGeneralImpl(id, firstName, lastName, salary, privatesToAdd);
                    soldiers.put(id, LSoldier);
                    break;
                case "Engineer":
                    try {
                        salary = Double.parseDouble(tokens[4]);
                        String corps = tokens[5];
                        List<String> repairsTokens = Arrays.stream(tokens).skip(6).collect(Collectors.toCollection(ArrayList::new));
                        Set<Repair> repairs = createRepairs(repairsTokens);
                        Soldier engineerSoldier = new EngineerImpl(id, firstName, lastName, salary, corps, repairs);
                        soldiers.put(id, engineerSoldier);
                    } catch (IllegalArgumentException ignore) {
                        ;
                    }
                    break;
                case "Commando":
                    salary = Double.parseDouble(tokens[4]);
                    String corps = tokens[5];
                    List<String> missionsTokens = Arrays.stream(tokens).skip(6).collect(Collectors.toCollection(ArrayList::new));
                    Set<Mission> missions = createMissions(missionsTokens);
                    Soldier commando = new CommandoImpl(id, firstName, lastName, salary, corps, missions);
                    soldiers.put(id, commando);
                    break;
                case "Spy":
                    String codeNumber = tokens[4];
                    Soldier spySoldier = new SpyImpl(id, firstName, lastName, codeNumber);
                    soldiers.put(id, spySoldier);
                    break;
            }

        }

        soldiers.values().forEach(System.out::println);
    }

    private static Set<Mission> createMissions(List<String> missionsTokens) {
        Set<Mission> missions = new LinkedHashSet<>();
        for (int i = 0; i < missionsTokens.size(); i += 2) {
            String codeName = missionsTokens.get(i);
            String state = missionsTokens.get(i + 1);
            Mission mission;
            try {
                mission = new MissionImpl(codeName, state);
                missions.add(mission);
            } catch (IllegalArgumentException ignore) {
                ;
            }
        }
        return missions;
    }

    private static Set<Repair> createRepairs(List<String> repairsTokens) {
        Set<Repair> repairs = new LinkedHashSet<>();
        for (int i = 0; i < repairsTokens.size(); i += 2) {
            String partName = repairsTokens.get(i);
            int hours = Integer.parseInt(repairsTokens.get(i + 1));
            repairs.add(new RepairImpl(partName, hours));
        }
        return repairs;
    }

    private static Set<Private> getPrivates(List<String> privatesIds, Map<String, Private> privates) {
        return privates
                .values()
                .stream()
                .filter(p -> privatesIds.contains(p.getId()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
