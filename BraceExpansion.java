/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(length of s + (groups ^ AvgGroupLength))
* 
* Space Complexity: O(output strings)
* 
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BraceExpansion {
    List<String> result;

    private void helper(List<List<Character>> groups, int index, int totalGroups, StringBuilder str) {
        if (index == totalGroups) {
            result.add(str.toString());
            return;
        }

        for (int i = 0; i < groups.get(index).size(); i++) {
            str.append(groups.get(index).get(i));
            helper(groups, index + 1, totalGroups, str);
            str.deleteCharAt(str.length() - 1);
        }
    }

    public String[] expand(String s) {
        List<List<Character>> groups = new ArrayList<>();

        int index = 0;

        while (index < s.length()) {
            List<Character> group = new ArrayList<>();
            char ch = s.charAt(index);
            if (ch == '{') {
                index++;
                while (s.charAt(index) != '}') {
                    if (s.charAt(index) != ',') {
                        group.add(s.charAt(index));
                    }
                    index++;
                }
            } else {
                group.add(s.charAt(index));
            }
            index++;
            Collections.sort(group);
            groups.add(group);
        }

        result = new ArrayList<>();

        helper(groups, 0, groups.size(), new StringBuilder());

        String[] strings = new String[result.size()];

        index = 0;

        for (String st : result) {
            strings[index++] = st;
        }

        return strings;
    }
}