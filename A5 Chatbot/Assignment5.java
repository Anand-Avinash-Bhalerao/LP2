import java.util.Scanner;

public class Assignment5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome! How can I assist you today?");
        
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            
            if (input.contains("hello") || input.contains("hi")) {
                System.out.println("Hello! How can I help you?");
            } else if (input.contains("goodbye") || input.contains("bye")) {
                System.out.println("Goodbye! Have a great day!");
                break;
            } else if (input.contains("time")) {
                String time = getCurrentTime();
                System.out.println("The current time is: " + time);
            } else if (input.contains("weather")) {
                String location = extractLocation(input);
                String weather = getWeather(location);
                System.out.println("The weather in " + location + " is " + weather);
            } else {
                System.out.println("I'm sorry, I didn't understand. Can you please rephrase?");
            }
        }
        
        scanner.close();
    }
    
    private static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }
    
    private static String extractLocation(String input) {
        // Code to extract the location from the input
        return "Pune";
    }
    
    private static String getWeather(String location) {
        // Code to retrieve the weather for the given location
        return "Sunny";
    }
}
