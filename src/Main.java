import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Person> students;
        students = CsvParser.parse("resources\\data_1.csv", "resources\\data_2.csv");
        Auditory.sort(students);

        CsvParser.saveData(students, "resources\\result.csv");
    }
}

class Auditory {
    private static Person[][][] auditory = new Person[6][3][2];
    private static List<Person> notPlanted;

    public static void sort(List<Person> students) {

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_MID).collect(Collectors.toList())) {
            if (auditory[0][1][0] == null) {
                plant(student, 0, 1, 0);
            }
            else {
                plant(student, 0, 1, 1);
            }
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_2 & p.height.equals("Низкий") & p.attention).collect(Collectors.toList())) {
            tryPlant(student, 2);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_2 & p.height.equals("Низкий") & !p.attention).collect(Collectors.toList())) {
            tryPlant(student, 2);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_2 & p.height.equals("Средний") & p.attention).collect(Collectors.toList())) {
            tryPlant(student, 2);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_2 & p.height.equals("Средний") & !p.attention).collect(Collectors.toList())) {
            tryPlant(student, 2);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_2 & p.height.equals("Высокий") & p.attention).collect(Collectors.toList())) {
            tryPlant(student, 2);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_2 & p.height.equals("Высокий") & !p.attention).collect(Collectors.toList())) {
            tryPlant(student, 2);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_3  & p.height.equals("Низкий") & p.attention).collect(Collectors.toList())) {
            tryPlant(student, 3);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_3  & p.height.equals("Низкий") & !p.attention).collect(Collectors.toList())) {
            tryPlant(student, 3);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_3  & p.height.equals("Средний") & p.attention).collect(Collectors.toList())) {
            tryPlant(student, 3);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_3  & p.height.equals("Средний") & !p.attention).collect(Collectors.toList())) {
            tryPlant(student, 3);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_3  & p.height.equals("Высокий") & p.attention).collect(Collectors.toList())) {
            tryPlant(student, 3);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.DESK_1_3  & p.height.equals("Высокий") & !p.attention).collect(Collectors.toList())) {
            tryPlant(student, 3);
        }


        for (Person student : students.stream().filter(p -> p.vision == visions.NOTHING & p.height.equals("Низкий") & p.attention).collect(Collectors.toList())) {
            tryPlant(student, auditory.length);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.NOTHING & p.height.equals("Низкий") & !p.attention).collect(Collectors.toList())) {
            tryPlant(student, auditory.length);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.NOTHING & p.height.equals("Средний") & p.attention).collect(Collectors.toList())) {
            tryPlant(student, auditory.length);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.NOTHING & p.height.equals("Средний") & !p.attention).collect(Collectors.toList())) {
            tryPlant(student, auditory.length);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.NOTHING & p.height.equals("Высокий") & p.attention).collect(Collectors.toList())) {
            tryPlant(student, auditory.length);
        }

        for (Person student : students.stream().filter(p -> p.vision == visions.NOTHING & p.height.equals("Высокий") & !p.attention).collect(Collectors.toList())) {
            tryPlant(student, auditory.length);
        }

        for (int x = 0; x < auditory.length; x++) {
            for (int y = 0; y < auditory[0].length; y++) {
                if (auditory[x][y][0] == null | auditory[x][y][1] == null ) continue;
                    if (auditory[x][y][0].hand & !auditory[x][y][1].hand) {
                        swap(x, y, 0, x, y, 1);
                    }
            }
        }

        for (Person student : students) {
            out.println(student + " " + student.height + " " + student.location);
        }
    }

    private static void tryPlant(Person student, int maxX) {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < auditory[0].length; y++) {
                if (auditory[x][y][0] == null) {
                    plant(student, x, y, 0);
                    return;
                }
                else if (auditory[x][y][1] == null) {
                    plant(student, x, y, 1);
                    return;
                }
            }
        }
    }

    private static void swap(int x1, int y1, int z1, int x2, int y2, int z2) {
        Person tmp = auditory[x1][y1][z1];
        plant(auditory[x2][y2][z2], x1, y1, z1);
        plant(tmp, x2, y2, z2);
    }

    private static void plant(Person student, int x, int y, int z) {
        auditory[x][y][z] = student;
        student.location = x * 6 + y * 2 + z + 1;
    }
}

class Person {
    public String name, height;
    public visions vision;
    public boolean hand, attention;
    public int location = -1;
    public HashMap<Person, Integer> preferences = new HashMap<>();
    public Person(String[] data) {
        this.name = data[0];
        this.hand = data[1].equals("Правая");
        this.vision = data[2].equals("1 парта средний ряд") ? visions.DESK_1_MID : data[2].equals("1-2 парты") ? visions.DESK_1_2 : data[2].equals("1-3 парты") ? visions.DESK_1_3 : visions.NOTHING;
        this.height = data[3];
        this.attention = data[4].equals("TRUE") ? true : false;
    }

    @Override
    public String toString() {
        return "Name: " + this.name;
    }
}

enum visions {
    DESK_1_MID, DESK_1_2, DESK_1_3, NOTHING;
}

class CsvParser {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    public static List<Person> parse(String resource_path_1, String resource_path_2) {
        HashMap<String, Person> result = new HashMap<>();
        String str;
        Person pers;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(resource_path_1), StandardCharsets.UTF_8));
            reader.readLine();
            while ((str = reader.readLine()) != null) {
                str = fix(str);
                String[] line = str.split(COMMA_DELIMITER);
                pers = new Person(line);
                result.put(pers.name, pers);
            }

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(resource_path_2), StandardCharsets.UTF_8));
            String[] header = reader.readLine().split(COMMA_DELIMITER);
            String[] row;
            while ((str = reader.readLine()) != null) {
                row = str.split(COMMA_DELIMITER);
                for (int i = 1; i < row.length; i++) {
                    if (!row[i].isEmpty()) {
                        result.get(row[0]).preferences.put(result.get(header[i]), Integer.parseInt(row[i]));
                    }
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result.values().stream().collect(Collectors.toList());
    }

    private static String fix(String str) {
        String errSymbol = "\"";
        if (str.contains(errSymbol)) {
            return str.substring(0, str.indexOf(errSymbol)) + str.substring(str.indexOf(errSymbol) + 1, str.lastIndexOf(errSymbol)).replace(",", "") + str.substring(str.lastIndexOf(errSymbol) + 1, str.length());
        }
        return str;
    }

    public static void saveData(List<Person> persons, String resource_path) throws IOException {
        FileWriter fw = new FileWriter(resource_path);
        fw.append("Место,Имя");
        for (Person p : persons) {
            fw.append(NEW_LINE_SEPARATOR);
            fw.append(Integer.toString(p.location));
            fw.append(COMMA_DELIMITER);
            fw.append(p.name);
        }
        fw.flush();
        fw.close();
    }
}
