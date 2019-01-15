public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            deq.addLast(word.charAt(i));
        }
        return deq;
    }

    public boolean isPalindrome(String word) {
        return helper1(wordToDeque(word));
    }

    private boolean helper1(Deque<Character> word) {
        if (word.size() <= 1) {
            return true;
        } else {
            char front = word.removeFirst();
            char back = word.removeLast();
            int diff = front - back;
            if (diff != 0) {
                return false;
            } else {
                return helper1(word);
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return helper2(wordToDeque(word), cc);
    }

    private boolean helper2(Deque<Character> word, CharacterComparator cc) {
        if (word.size() <= 1) {
            return true;
        } else {
            char front = word.removeFirst();
            char back = word.removeLast();
            if (!cc.equalChars(front, back)) {
                return false;
            } else {
                return helper2(word, cc);
            }
        }
    }
}
