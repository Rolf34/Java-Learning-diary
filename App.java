import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    static final int MAX_ENTRIES = 50;
    static String[] dates = new String[MAX_ENTRIES];
    static String[] entries = new String[MAX_ENTRIES];
    static int entryCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("""
                Мій щоденник:
                1. Додати запис
                2. Видалити запис
                3. Переглянути всі записи
                4. Вийти    
                    """);
            System.out.print("Оберіть опцію (1-4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEntry(scanner);
                    break;
                case "2":
                    deleteEntry(scanner);
                    break;
                case "3":
                    viewAllEntries();
                    break;
                case "4":
                    System.out.println("До побачення!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void addEntry(Scanner scanner) {
        if (entryCount >= MAX_ENTRIES) {
            System.out.println("Щоденник повний! Видаліть старі записи.");
            return;
        }

        System.out.print("Введіть дату (формат ДД.ММ.РРРР): ");
        String date = scanner.nextLine();

        if (!isValidDate(date)) {
            System.out.println("Невірний формат дати!");
            return;
        }

        System.out.println("Введіть текст запису (для завершення введіть порожній рядок):");
        String entry = "";
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            entry += line + "\n";
        }

        if (!entry.isEmpty()) {
            dates[entryCount] = date;
            entries[entryCount] = entry;
            entryCount++;
            System.out.println("Запис додано успішно!");
        }
    }

    private static void deleteEntry(Scanner scanner) {
        if (entryCount == 0) {
            System.out.println("Щоденник порожній!");
            return;
        }

        System.out.print("Введіть дату запису для видалення (ДД.ММ.РРРР): ");
        String dateToDelete = scanner.nextLine();

        for (int i = 0; i < entryCount; i++) {
            if (dates[i].equals(dateToDelete)) {
                for (int j = i; j < entryCount - 1; j++) {
                    dates[j] = dates[j + 1];
                    entries[j] = entries[j + 1];
                }
                entryCount--;
                System.out.println("Запис видалено успішно!");
                return;
            }
        }
        System.out.println("Запис з такою датою не знайдено!");
    }

    private static void viewAllEntries() {
        if (entryCount == 0) {
            System.out.println("Щоденник порожній!");
            return;
        }

        System.out.println("\nВсі записи:");
        for (int i = 0; i < entryCount; i++) {
            System.out.println("\nДата: " + dates[i]);
            System.out.println("Запис:");
            System.out.println(entries[i]);
        }
    }

    private static boolean isValidDate(String date) {
        if (date.length() != 10) return false;
        
        try {
            int day = Integer.parseInt(date.substring(0, 2));
            int month = Integer.parseInt(date.substring(3, 5));
            int year = Integer.parseInt(date.substring(6, 10));
            
            if (date.charAt(2) != '.' || date.charAt(5) != '.') return false;
            if (day < 1 || day > 31) return false;
            if (month < 1 || month > 12) return false;
            if (year < 1900 || year > 2100) return false;
            
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

// hehe hello my friends
