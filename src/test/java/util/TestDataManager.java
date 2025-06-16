package util;

import java.util.HashMap;
import java.util.Map;

public class TestDataManager {
    
    // Valid test users for consistent testing between register and login
    public static final Map<String, Map<String, String>> VALID_USERS = new HashMap<>();
    
    static {
        // Primary test user
        Map<String, String> user1 = new HashMap<>();
        user1.put("name", "John Doe");
        user1.put("email", "john.doe@test.com");
        user1.put("password", "Password123!");
        user1.put("confirmPass", "Password123!");
        user1.put("age", "25");
        user1.put("height", "175");
        user1.put("weight", "70");
        user1.put("gender", "male");
        user1.put("goal", "maintain");
        user1.put("activity", "moderate");
        VALID_USERS.put("primary", user1);
        
        // Secondary test user
        Map<String, String> user2 = new HashMap<>();
        user2.put("name", "Jane Smith");
        user2.put("email", "jane.smith@test.com");
        user2.put("password", "SecurePass456!");
        user2.put("confirmPass", "SecurePass456!");
        user2.put("age", "30");
        user2.put("height", "165");
        user2.put("weight", "60");
        user2.put("gender", "female");
        user2.put("goal", "lose");
        user2.put("activity", "active");
        VALID_USERS.put("secondary", user2);
        
        // User for boundary testing
        Map<String, String> boundaryUser = new HashMap<>();
        boundaryUser.put("name", "Test User");
        boundaryUser.put("email", "test.boundary@test.com");
        boundaryUser.put("password", "TestPass789!");
        boundaryUser.put("confirmPass", "TestPass789!");
        boundaryUser.put("age", "18");
        boundaryUser.put("height", "170");
        boundaryUser.put("weight", "65");
        boundaryUser.put("gender", "female");
        boundaryUser.put("goal", "gain");
        boundaryUser.put("activity", "light");
        VALID_USERS.put("boundary", boundaryUser);
    }
    
    /**
     * Get user data by type and generate unique email if needed
     */
    public static Map<String, String> getUserData(String userType, boolean uniqueEmail) {
        Map<String, String> userData = new HashMap<>(VALID_USERS.get(userType));
        
        if (uniqueEmail && userData.containsKey("email")) {
            String originalEmail = userData.get("email");
            String uniqueEmailAddress = originalEmail.replace("@", "." + System.currentTimeMillis() + "@");
            userData.put("email", uniqueEmailAddress);
        }
        
        return userData;
    }
    
    /**
     * Get login credentials for existing user
     */
    public static Map<String, String> getLoginCredentials(String userType) {
        Map<String, String> userData = VALID_USERS.get(userType);
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", userData.get("email"));
        credentials.put("password", userData.get("password"));
        return credentials;
    }
    
    // Invalid test data for negative testing
    public static final Map<String, String> INVALID_EMAILS = new HashMap<>();
    static {
        INVALID_EMAILS.put("missing_at", "invalid.email.com");
        INVALID_EMAILS.put("missing_domain", "invalid@");
        INVALID_EMAILS.put("missing_local", "@domain.com");
        INVALID_EMAILS.put("double_at", "invalid@@domain.com");
        INVALID_EMAILS.put("special_chars", "invalid<>@domain.com");
    }
    
    public static final Map<String, String> INVALID_PASSWORDS = new HashMap<>();
    static {
        INVALID_PASSWORDS.put("too_short", "123");
        INVALID_PASSWORDS.put("no_numbers", "password");
        INVALID_PASSWORDS.put("no_uppercase", "password123");
        INVALID_PASSWORDS.put("no_special", "Password123");
    }
    
    public static final Map<String, String> INVALID_NUMERIC_VALUES = new HashMap<>();
    static {
        INVALID_NUMERIC_VALUES.put("negative_age", "-5");
        INVALID_NUMERIC_VALUES.put("zero_age", "0");
        INVALID_NUMERIC_VALUES.put("non_numeric_age", "abc");
        INVALID_NUMERIC_VALUES.put("negative_height", "-150");
        INVALID_NUMERIC_VALUES.put("zero_height", "0");
        INVALID_NUMERIC_VALUES.put("non_numeric_height", "xyz");
        INVALID_NUMERIC_VALUES.put("negative_weight", "-60");
        INVALID_NUMERIC_VALUES.put("zero_weight", "0");
        INVALID_NUMERIC_VALUES.put("non_numeric_weight", "def");
    }
}
