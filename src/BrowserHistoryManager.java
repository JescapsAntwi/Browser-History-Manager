// Main program to test my browser history system
public class BrowserHistoryManager {
    public static void main(String[] args) {
        // Creating a new browser history instance
        BrowserHistory history = new BrowserHistory();


        System.out.println("Adding pages to history...\n");

        history.addPage("www.example1.com", "10:00");
        history.addPage("www.example2.com", "10:05");
        history.addPage("www.example3.com", "10:10");
        history.addPage("www.example4.com", "10:15");

        // Displaying the browsing history both ways
        history.displayHistoryForward();

        // Saving the browser history to a file
        history.saveToFile("browser_history.txt");

        // Removing some pages
        history.removePage("10:05");
        history.removePage("10:10");

        System.out.println("\nAfter removing some pages:");
        history.displayHistoryForward();

        // Loading the browser history back from file
        System.out.println("\nLoading history from file...");
        history.loadFromFile("browser_history.txt");

        // Showing the restored history
        history.displayHistoryForward();

        // loading from a file that does not exist
        System.out.println("\nTrying to load from non-existent file:");
        history.loadFromFile("nonexistent.txt");
    }
}