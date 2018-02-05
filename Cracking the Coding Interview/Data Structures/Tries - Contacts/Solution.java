import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static Node root = new Node();

    private static class Node {
        private HashMap<Character, Node> children = new HashMap<Character, Node>();
        private int amountOfWords;

        public void add(String word) {
            add(word, 0);
        }

        private void add(String word, int index) {
            amountOfWords++;
            if (word.length() == index) {
                return;
            }

            char letter = word.charAt(index);
            Node child;
            if (!children.containsKey(letter)) {
                child = new Node();
                children.put(letter, child);
            } else {
                child = children.get(letter);
            }

            child.add(word, ++index);
        }

        public int find(String word) {
            return find(word, 0);
        }

        private int words() {
            return amountOfWords;
        }

        private int find(String word, int index) {
            char letter = word.charAt(index);
            if (!children.containsKey(letter)) {
                return 0;
            }

            Node child = children.get(letter);
            if (word.length() == (index + 1)) {
                return child.words();
            } else {
                return child.find(word, ++index);
            }
        }
    }

    private static void add(String word) {
        root.add(word);
    }

    private static int find(String word) {
        return root.find(word);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            switch (op) {
                case "add":
                    add(contact);
                    break;
                case "find":
                    System.out.println(find(contact));
                    break;
            }
        }
    }
}
