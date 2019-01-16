import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('a', 'c'));
    }

    static Palindrome palindrome = new Palindrome();

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertFalse(palindrome.isPalindrome("hannah", offByOne));
    }

    @Test
    public void testCornerCases() {
        assertTrue(palindrome.isPalindrome("%flake&", offByOne));
        assertFalse(palindrome.isPalindrome("aB", offByOne));
    }
}
