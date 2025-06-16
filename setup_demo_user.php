<?php
// Database configuration
$host = 'localhost';
$database = 'calories_trackerDB';
$username = 'root';
$password = '';

try {
    $pdo = new PDO("mysql:host=$host;dbname=$database", $username, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    // Check if demo user exists
    $stmt = $pdo->prepare("SELECT * FROM users WHERE email = ?");
    $stmt->execute(['demo@example.com']);
    $user = $stmt->fetch();
    
    if ($user) {
        echo "Demo user already exists!\n";
        // Update password to make sure it's correct
        $hashedPassword = password_hash('password123', PASSWORD_BCRYPT);
        $updateStmt = $pdo->prepare("UPDATE users SET password = ? WHERE email = ?");
        $updateStmt->execute([$hashedPassword, 'demo@example.com']);
        echo "Password updated successfully!\n";
    } else {
        echo "Creating demo user...\n";
        
        // Create demo user
        $hashedPassword = password_hash('password123', PASSWORD_BCRYPT);
        $stmt = $pdo->prepare("INSERT INTO users (name, email, password, age, gender, height, weight, goal, activity_level, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())");
        $stmt->execute([
            'Demo User',
            'demo@example.com',
            $hashedPassword,
            25,
            'male',
            175.0,
            70.0,
            'maintain',
            'moderate'
        ]);
        
        echo "Demo user created successfully!\n";
    }
    
    echo "Email: demo@example.com\n";
    echo "Password: password123\n";
    
} catch (PDOException $e) {
    echo "Database Error: " . $e->getMessage() . "\n";
} catch (Exception $e) {
    echo "Error: " . $e->getMessage() . "\n";
}
?>
