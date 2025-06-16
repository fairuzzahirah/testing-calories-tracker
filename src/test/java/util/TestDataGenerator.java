package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TestDataGenerator {
    private static final Random random = new Random();
    private static final String[] FIRST_NAMES = {"John", "Jane", "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
    private static final String[] FOOD_NAMES = {"Apple", "Banana", "Chicken Breast", "Rice", "Broccoli", "Salmon", "Yogurt", "Oatmeal"};
    private static final String[] SERVING_UNITS = {"piece", "cup", "gram", "ounce", "slice", "bowl"};

    public static String generateRandomEmail() {
        return "test" + random.nextInt(10000) + "@example.com";
    }

    public static String generateRandomName() {
        return FIRST_NAMES[random.nextInt(FIRST_NAMES.length)] + " " + 
               LAST_NAMES[random.nextInt(LAST_NAMES.length)];
    }

    public static String generateRandomPassword() {
        return "password" + random.nextInt(1000);
    }

    public static int generateRandomAge() {
        return 18 + random.nextInt(62); // Age between 18-80
    }

    public static int generateRandomHeight() {
        return 150 + random.nextInt(50); // Height between 150-200 cm
    }

    public static int generateRandomWeight() {
        return 50 + random.nextInt(50); // Weight between 50-100 kg
    }

    public static String generateRandomGender() {
        return random.nextBoolean() ? "male" : "female";
    }

    public static String generateRandomGoal() {
        String[] goals = {"lose", "maintain", "gain"};
        return goals[random.nextInt(goals.length)];
    }

    public static String generateRandomActivityLevel() {
        String[] levels = {"sedentary", "light", "moderate", "active", "very_active"};
        return levels[random.nextInt(levels.length)];
    }

    public static String generateRandomFoodName() {
        return FOOD_NAMES[random.nextInt(FOOD_NAMES.length)];
    }

    public static int generateRandomCalories() {
        return 50 + random.nextInt(450); // Calories between 50-500
    }

    public static double generateRandomServingAmount() {
        return 0.5 + (random.nextDouble() * 4.5); // Serving between 0.5-5.0
    }

    public static String generateRandomServingUnit() {
        return SERVING_UNITS[random.nextInt(SERVING_UNITS.length)];
    }

    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static String getFutureDateTime() {
        return LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static String getPastDateTime() {
        return LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static String generateXSSPayload() {
        return "<script>alert('XSS Test')</script>";
    }

    public static String generateSQLInjectionPayload() {
        return "'; DROP TABLE users; --";
    }

    public static String generateLongString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append("a");
        }
        return sb.toString();
    }
}
