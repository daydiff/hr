/*
Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

A Node is defined as: 
    class Node {
        int data;
        Node next;
    }
*/

boolean hasCycle(Node head) {
    if (head == null) {
        return false;
    }
    ArrayList<Node> nodes = new ArrayList<Node>();
    Node node = head;
    int moves = 0, i = 0;
    do {
        if (node == null) {
            return false;
        } else if (nodes.contains(node)) {
            return true;
        }
        nodes.add(node);
        node = node.next;
        moves++;
    } while (moves <= 100);
    
    if (moves > 100) {
        return true;
    }
    return false;
}
