## 28. Implement strStr()

**Requirement:**

Return the index of <u>*the first occurrence*</u> of needle in haystack, or **-1** if needle is not part of haystack.

**Input**

haystack(string), needle(string)

**Output**

Index of the first occurrence of needle in haystack

/

-1 : neddle is not part of haystack

int

**Solution**

`Test Case`

haystack="hello", neddle="ll"

*Traverse*:

h e <u>l</u><u>l</u> 

return 2

haystack="aaaaa", neddle="bba"

a a a a a 

return -1

haystack="godmodmorning", neddle="odmor"

g o d m o   XXX

haystack="godmo", neddle="odmor"

g o d m o

**<u>Two Pointer</u>**

```java
class Solution {
    public int strStr(String haystack, String needle) {      
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        
        if (j == needle.length()) {
            return i - j;
        }
        
        return -1;       
    }
}
```

```java
class Solution {
    public int strStr(String haystack, String needle) {
        int i, j;
        for (i = 0; i < haystack.length() - needle.length() + 1; i++) {
            for (j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }          
        }        
        return -1;
    }
}
```

Time Complexity: O(m*n).   m = haystack.length()  n = needle.length()

Space Complexity: O(1)

What should we return when `needle` is an empty string? 

What should we return if `needle.length()`>`haystack.length()`?

------

## 78. Subsets

**Requirement:**

Given a set of **distinct** integers, *nums*, return all possible subsets (the power set).

**Note:** The solution set must not contain duplicate subsets.

 **Input**

```java
int[] nums
```

**Output**

```java
List<List<Integer>>
```

**Solution**

`Test Case`

```
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

​                                            {}

​        {1}                              {2}                      {3}

{1,2}       {1,3}                   {2,3}

{1,2,3}

Better in order

**DFS**

*Recursive&Callback*

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        
        if (nums == null) {
            return results;
        }
        
        if (nums.length == 0) {
            results.add(new ArrayList<>());
            return results;
        }
        
        Arrays.sort(nums);
        subsetsHelper(new ArrayList<>(), nums, 0, results);
        return results;
    }
    
    private void subsetsHelper(ArrayList<Integer> subset, 
                               int[] nums, 
                               int start, 
                               List<List<Integer>> results) {
        results.add(new ArrayList<Integer>(subset));
        
        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            subsetsHelper(subset, nums, i + 1, results);
            subset.remove(subset.size() - 1);
        }
    }
}
```



------

## 208 Implement Trie (Prefix Tree)

**Requirement**

Implement a trie with `insert`, `search`, and `startsWith` methods.

**Example:**

```
Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true
```

**Note:**

- You may assume that all inputs are consist of lowercase letters `a-z`.
- All inputs are guaranteed to be non-empty strings.

 **Solution**

*Method signature*

assume rarely updated

***insert:*** 

Input: String word

Output: void

***search:***

Input: String word

Output: boolean 

***startWith:***

Input: String prefix

Output: boolean







