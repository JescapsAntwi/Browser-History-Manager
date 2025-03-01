// Main class to manage my browser history using a doubly linked list

import java.io.*;

class BrowserHistory {
    private HistoryNode head;
    private HistoryNode tail;
    private int size;

    // Starting with an empty history
    public BrowserHistory() {
        head = null;
        tail = null;
        size = 0;
    }

    // Adding a new page to the browser history
    public void addPage(String url, String timestamp) {
        // Creating the new history entry
        HistoryNode newNode = new HistoryNode(url, timestamp);

        // Edge case handling if this is the first entry
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            // Add to the end of the history
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    // Removing a page from history based on when it was visited
    public void removePage(String timestamp) {
        // Nothing to remove if history is empty
        if (head == null) {
            System.out.println("History is empty!");
            return;
        }

        HistoryNode current = head;

        // Looking for the page we want to remove
        while (current != null) {
            if (current.timestamp.equals(timestamp)) {
                // If we're removing the first page
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        // If this was the only page
                        tail = null;
                    }
                }
                // If we're removing the last page
                else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                }
                // If we're removing a page from the middle
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }

                size--;
                System.out.println("Page removed from history.");
                return;
            }
            current = current.next;
        }

        System.out.println("Page not found in history!");
    }

    // Showing history from oldest to newest
    public void displayHistoryForward() {
        if (head == null) {
            System.out.println("No history to display!");
            return;
        }

        System.out.println("\nBrowsing history (oldest to newest):");
        System.out.println("-----------------------------------");

        HistoryNode current = head;
        while (current != null) {
            System.out.println("Time: " + current.timestamp + " | URL: " + current.url);
            current = current.next;
        }
        System.out.println("-----------------------------------\n");
    }

    // Showing history from newest to oldest
    public void displayHistoryBackward() {
        if (tail == null) {
            System.out.println("No history to display!");
            return;
        }

        System.out.println("\nBrowsing history (newest to oldest):");
        System.out.println("-----------------------------------");

        HistoryNode current = tail;
        while (current != null) {
            System.out.println("Time: " + current.timestamp + " | URL: " + current.url);
            current = current.prev;
        }
        System.out.println("-----------------------------------\n");
    }

    // Saving history to a file (Bonus Feature)
    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            HistoryNode current = head;
            while (current != null) {
                // Write each entry as "url,timestamp"
                writer.write(current.url + "," + current.timestamp + "\n");
                current = current.next;
            }
            System.out.println("History saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving history: " + e.getMessage());
        }
    }

    // Loading history from a file (Bonus Feature)
    public void loadFromFile(String filename) {
        // Clear current history
        head = null;
        tail = null;
        size = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Splitting each line into url and timestamp
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    addPage(parts[0], parts[1]);
                }
            }
            System.out.println("History loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading history: " + e.getMessage());
        }
    }
}