import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<String, String> criteria) {
        Set<Notebook> filteredNotebooks = new HashSet<>();
        for (Notebook notebook : notebooks) {
            boolean criteriaMatch = true;
            for (Map.Entry<String, String> entry : criteria.entrySet()) {
                switch (entry.getKey()) {
                    case "ram":
                        if (notebook.getRam() < Integer.parseInt(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                    case "hdd":
                        if (notebook.getHdd() < Integer.parseInt(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                    case "os":
                        if (!notebook.getOs().equals(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                    case "color":
                        if (!notebook.getColor().equals(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                }
                if (!criteriaMatch) {
                    break;
                }
            }
            if (criteriaMatch) {
                filteredNotebooks.add(notebook);
            }
        }
        return filteredNotebooks;
    }

    public static Map<String, String> queryCriteria() {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> filterCriteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ (4-6-8-16)");
        System.out.println("2 - Объем ЖД (128-256-512-1024)");
        System.out.println("3 - Операционная система (Linux, MacOS, Windows)");
        System.out.println("4 - Цвет (black, white, silver, gray)");

        int criteria = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите значение:");
        String Value = scanner.nextLine();
        scanner.close();

        switch (criteria) {
            case 1:
                filterCriteria.put("ram", Value);
                break;
            case 2:
                filterCriteria.put("hdd", Value);
                break;
            case 3:
                filterCriteria.put("os", Value);
                break;
            case 4:
                filterCriteria.put("color", Value);
                break;
        }

        return filterCriteria;
    }

    public static void main(String[] args) {
        Set<Notebook> notebooks = new HashSet<>();

        notebooks.add(new Notebook(4, 128, "Linux", "gray"));
        notebooks.add(new Notebook(16, 512, "MacOS", "silver"));
        notebooks.add(new Notebook(8, 256, "Windows", "black"));
        notebooks.add(new Notebook(6, 256, "Windows", "gray"));
        notebooks.add(new Notebook(8, 512, "Linux", "silver"));
        notebooks.add(new Notebook(16, 1024, "MacOS", "white"));
        notebooks.add(new Notebook(4, 512, "Windows", "silver"));
        notebooks.add(new Notebook(8, 256, "Linux", "gray"));

        System.out.println("Количество ноутбуков: " + notebooks.size());

        var filterCriteria = queryCriteria();
        var filteredNotebooks = filterNotebooks(notebooks, filterCriteria);
        if (filteredNotebooks.size() == 0)
        {
            System.out.println("Нет ноутбуков, удовлетворяющих заданному критерию");
        }
        else for (Notebook notebook : filteredNotebooks) {
            System.out.println(notebook);
        }
    }
}