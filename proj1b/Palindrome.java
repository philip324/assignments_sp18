public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            char c = word.charAt(i);
            deq.addLast(c);
        }
        return deq;
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    private boolean isPalindrome(Deque<Character> word) {
        if (word.size() <= 1) {
            return true;
        }
        char front = word.removeFirst();
        char back = word.removeLast();
        if (front != back) {
            return false;
        }
        return isPalindrome(word);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

    private boolean isPalindrome(Deque<Character> word, CharacterComparator cc) {
        if (word.size() <= 1) {
            return true;
        }
        char front = word.removeFirst();
        char back = word.removeLast();
        if (!cc.equalChars(front, back)) {
            return false;
        }
        return isPalindrome(word, cc);
    }
}
