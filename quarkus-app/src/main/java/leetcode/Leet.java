package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Leet {
    private Map<Character, Long> getCountsByCharacter(String text) {
        return text
            .chars()
            .mapToObj(intVal -> (char) intVal)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Long> magazineLetterCounts = getCountsByCharacter(magazine);
        Map<Character, Long> ransomNoteLetterCounts = getCountsByCharacter(ransomNote);

        return ransomNoteLetterCounts.entrySet().stream().allMatch(ransomNoteLetterWithCount ->
            magazineLetterCounts.containsKey(ransomNoteLetterWithCount.getKey()) && magazineLetterCounts.get(ransomNoteLetterWithCount.getKey()) >= ransomNoteLetterWithCount.getValue()
        );
    }

    public int[] twoSum(int[] nums, int target) {
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        numsList.remove(0);
        for (int idx = 0; idx < nums.length - 1; idx++) {
            int wanted = target - nums[idx];
            int indexOfWanted = numsList.indexOf(wanted);
            if (indexOfWanted > -1) return new int[] {idx, indexOfWanted + idx + 1};
            numsList.remove(0);
        }
        return new int[0];
    }

    public int convertToInt(ListNode listNode) {
        var num = 0;
        int pos = 1;
        var pointer = listNode;
        do {
            num += pos * pointer.val;
            pos *= 10;
            pointer = pointer.next;
        } while (pointer != null);
        return num;
    }

    public ListNode convertToListNode(int num) {
        var head = new ListNode(num % 10);
        var pointer = head;
        num /= 10;
        while (num > 0) {
            pointer.next = new ListNode(num % 10);
            pointer = pointer.next;
            num /= 10;
        }
        return head;
    }

    public ListNode addTwoNumbersWrong(ListNode l1, ListNode l2) {
        var num1 = convertToInt(l1);
        var num2 = convertToInt(l2);

        return convertToListNode(num1 + num2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var pointer1 = l1;
        var pointer2 = l2;
        ListNode resultHead = null;
        ListNode resultPointer = null;
        var prevOverflow = false;
        do {
            var pointer1PosVal = pointer1 == null ? 0 : pointer1.val;
            var pointer2PosVal = pointer2 == null ? 0 : pointer2.val;
            int posSum = pointer1PosVal + pointer2PosVal + (prevOverflow ? 1 : 0);
            var posValue = posSum % 10;
            var posListNode = new ListNode(posValue);
            prevOverflow = posSum > 9;
            if (resultPointer == null) {
                resultHead = resultPointer = posListNode;
            } else {
                resultPointer.next = posListNode;
                resultPointer = resultPointer.next;
            }
            pointer1 = pointer1 != null ? pointer1.next : null;
            pointer2 = pointer2 != null ? pointer2.next : null;
        } while (pointer1 != null || pointer2 != null);
        if (prevOverflow) {
            resultPointer.next = new ListNode(1);
        }
        return resultHead;
    }

    public static void main(String[] args) {
//        System.out.println(new Leet().addTwoNumbers(new ListNode(1, new ListNode(2)), new ListNode(3, new ListNode(6, new ListNode(2)))).val);
        System.out.println(new Leet().addTwoNumbers(new ListNode(1, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))))))), new ListNode(9)).val);
    }

}

