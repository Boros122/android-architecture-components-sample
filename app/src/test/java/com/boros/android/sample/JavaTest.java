package com.boros.android.sample;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JavaTest {

    private double epsilon = 1e-6;

    private static boolean isPalindrome(String word) {
        String reverseWord = "";
        word = word.toLowerCase();
        for (int i = word.length() - 1; i >= 0; i--) {
            Character actualCharacter = word.charAt(i);
            String actualString = actualCharacter.toString();
            reverseWord = reverseWord.concat(actualString);
        }
        return reverseWord.equals(word);
    }

    public static boolean contains(Node root, int value) {
        if (root.value == value) {
            return true;
        } else if (value < root.value && root.left != null) {
            return contains(root.left, value);
        } else if (value > root.value && root.right != null) {
            return contains(root.right, value);
        } else {
            return false;
        }
    }

    public static boolean isBetterAcronym(String s1, String s2) {
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        Arrays.sort(s1Chars);
        Arrays.sort(s2Chars);
        return Arrays.equals(s1Chars, s2Chars);
    }

    public static boolean isAcronym(String s1, String s2) {

        if (s1.length() != s2.length()) return false;

        HashMap<Character, Integer> charCounts = new HashMap<>();

        // Calculate chracter counts

        for (int i = 0; i < s1.length(); i++) {
            if (charCounts.containsKey(s1.charAt(i))) {
                charCounts.put(s1.charAt(i), charCounts.get(s1.charAt(i)) + 1);
            } else {
                charCounts.put(s1.charAt(i), 1);
            }
        }

        // Compare counts with characters in s2

        for (int i = 0; i < s2.length(); i++) {
            if (charCounts.containsKey(s2.charAt(i))) {
                charCounts.put(s2.charAt(i), charCounts.get(s2.charAt(i)) - 1);
            } else {
                return false;
            }
        }

        // Check all letters matched
        for (int count : charCounts.values()) {
            if (count != 0) return false;
        }

        return true;
    }

    @Test
    public void testPalindrome() {
        int a = 1;
        int b = a++;
        int c = ++b;

        System.out.println("a=" + a + " b=" + b + " c=" + c);

        Map<Integer, String> hashMap = new HashMap<Integer, String>(5);
        hashMap.put(1, "apple");
        hashMap.put(2, null);
        hashMap.put(new Integer(3), "peach");
        hashMap.put(3, "orange");
        hashMap.put(4, "peach");

        for (String v : hashMap.values()) {

            if ("orange".equals(v)) {
                hashMap.put(5, "banana");
            }
        }
        System.out.println(hashMap.get(5));
    }

/*    @Test
    public void accountCannotHaveNegativeOverdraftLimit() {
        Account account = new Account(-20);

        Assert.assertEquals(0d, account.getOverdraftLimit(), epsilon);
    }

    @Test
    public void accountCannotAcceptNegativeDeposit() {
        Account account = new Account(20);

        Assert.assertEquals(false, account.deposit(-20));
    }

    @Test
    public void accountCannotAcceptNegativeWithdraw() {
        Account account = new Account(20);

        Assert.assertEquals(false, account.withdraw(-20));
    }

    //Account cannot overstep its overdraft limit.
    @Test
    public void accountCannotOverstepOverdraftLimit() {
        Account account = new Account(20);

        Assert.assertEquals(false, account.withdraw(22));
    }

    @Test
    public void depositWorksFine() {
        Account account = new Account(20);

        account.deposit(20);
        Assert.assertEquals(20d, account.getBalance(), epsilon);
    }

    @Test
    public void withdrawWorksFine() {
        Account account = new Account(20);

        account.deposit(20);
        account.withdraw(18);
        Assert.assertEquals(2d, account.getBalance(), epsilon);
    }

    @Test
    public void pathTest() {
        Path path = new Path("/a/b/c/d");
        path.cd("../x");
        System.out.println(path.getPath());
    }*/

    @Test
    public void testSearchTree() {
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);

        System.out.println(contains(n2, 3));
    }

    @Test
    public void testAcronym() {
        Assert.assertEquals(true, isAcronym("ALMA", "AMLA"));
        Assert.assertEquals(true, isBetterAcronym("ALMA", "AMLA"));
    }

    public String numerify(String numberString) {
        if (numberString == null) {
            return null;
        }
        for (int i = 0; i < numberString.length(); i++) {
            Character ch = numberString.charAt(i);
            if (ch == '#') {
                Random rand = new Random();
                Integer randomNum = rand.nextInt(9);
                numberString = numberString.replace(ch.toString(), randomNum.toString());
            }
        }
        return numberString;
    }

    public String bothify(String string) {
        if (string == null) {
            return null;
        }
        for (int i = 0; i < string.length(); i++) {
            Character ch = string.charAt(i);
            if (ch == '#') {
                Random rand = new Random();
                Integer randomNum = rand.nextInt(9);
                string = string.replace(ch.toString(), randomNum.toString());
            } else if (ch == '?') {
                string = string.replace(ch.toString(), "a");
            }
        }
        return string;
    }

    public String letterify(String string) {
        if (string == null) {
            return null;
        }
        for (int i = 0; i < string.length(); i++) {
            Character ch = string.charAt(i);
            if (ch == '?') {
                Random r = new Random();
                Character c = (char) (r.nextInt(26) + 'a');
                string = string.replace(ch.toString(), c.toString());
            }
        }
        return string;
    }

    @Test
    public void testNumerify() {
        //given


        //when
        String result = numerify("###");

        //then
        Assert.assertTrue(result.matches("[0-9][0-9][0-9]"));

        String result2 = numerify("Test#");

        Assert.assertTrue(result2.matches("Test[0-9]"));

        String result3 = bothify("Test?#");

        //then
        Assert.assertTrue(result3.matches("Test[a-z][0-9]"));

        String result4 = letterify("???");

        //then
        Assert.assertTrue(result4.matches("[a-z][a-z][a-z]"));
    }

    class Node {
        public int value;
        public Node left, right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


}
