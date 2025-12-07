package isp2;

// Segregated Interfaces
interface TextScreen {
    void showText(String text);
}

interface InteractiveScreen {
    void handleUserInput(String input);
}

interface MediaScreen {
    void showImage(String path);
    void playVideo(String path);
}

// LoginScreen only implements relevant interfaces
class LoginScreen implements TextScreen, InteractiveScreen {
    public void showText(String text) {
        System.out.println("LOGIN: " + text);
    }
    public void handleUserInput(String input) {
        System.out.println("Handling login input: " + input);
    }
}

// TutorialScreen implements all
class TutorialScreen implements TextScreen, InteractiveScreen, MediaScreen {
    public void showText(String text) { System.out.println("TUTORIAL: " + text); }
    public void handleUserInput(String input) { System.out.println("Tutorial skipped."); }
    public void showImage(String path) { System.out.println("Showing image: " + path); }
    public void playVideo(String path) { System.out.println("Playing video: " + path); }
}

public class App {
    public static void main(String[] args) {
        LoginScreen login = new LoginScreen();
        login.showText("Welcome");
        login.handleUserInput("password123");
        // login.playVideo(...) // Compile error, which is good! ISP respected.
    }
}