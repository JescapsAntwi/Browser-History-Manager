// this node class stores webpage visits in the browser history
class HistoryNode {
    String url;
    String timestamp;
    HistoryNode prev;
    HistoryNode next;

    // this constructor creates a new history entry
    public HistoryNode(String url, String timestamp) {
        this.url = url;
        this.timestamp = timestamp;
        this.prev = null;
        this.next = null;
    }
}