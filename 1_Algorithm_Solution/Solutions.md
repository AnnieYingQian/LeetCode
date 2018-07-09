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

## 90. Subsets II

**Requirement**

Given a collection of integers that might contain duplicates, **nums**, return all possible subsets (the power set).

**Note:** The solution set must not contain duplicate subsets.

**Example:**

```
Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```

**Solution**

**Input**

```java
int[] nums
```

**Output**

```java
List<List<Integer>>
```

**DFS**

*Recursive&Callback*

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        
        if (nums == null) {
            return results;
        }
        
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        
        Arrays.sort(nums);
        helperWithDup(new ArrayList<Integer>(), nums, 0, results);
        return results;
    }
    
    private void helperWithDup(ArrayList<Integer> subset, 
                               int[] nums, 
                               int position, 
                               List<List<Integer>> results) {
        results.add(new ArrayList<Integer>(subset));
        
        for (int i = position; i < nums.length; i++) {
            if (i != position && nums[i] == nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            helperWithDup(subset, nums, i + 1, results);
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

**Realize a Trie/ Prefix Tree**

1. *Boolean*

   Array

   ```java
   class TrieNode {
       boolean isWord;
       TrieNode[] children;
       
       public TrieNode() {
           this.children = new TrieNode[26];
       }
   }
   
   class Trie {
       private TrieNode root;
       public Trie() {
           this.root = new TrieNode();
       }
   }
   ```

   Map --- Recommend(space improvement: boolean)

   ```java
   class Trie {
       class TrieNode {
           Map<Character, TrieNode> children = new HashMap<>();
           boolean isEnd = false;
       }
   
       TrieNode root;
       
       /** Initialize your data structure here. */
       public Trie() {
           this.root = new TrieNode();
       }
       
       /** Inserts a word into the trie. */
       public void insert(String word) {
           TrieNode cur = this.root;
           for (int i = 0; i < word.length(); i++) {
               char c = word.charAt(i);
               cur = cur.children.computeIfAbsent(c, k -> new TrieNode());
           }
           cur.isEnd = true;
       }
       
       /** Returns if the word is in the trie. */
       public boolean search(String word) {
           TrieNode cur = this.root;
           for (int i = 0; i < word.length(); i++) {
               char c = word.charAt(i);
               TrieNode child = cur.children.get(c);
               if (child == null) {
                   return false;
               } else {
                   cur = child;
               }
           }
           return cur.isEnd;
       }
       
       /** Returns if there is any word in the trie that starts with the given prefix. */
       public boolean startsWith(String prefix) {
           TrieNode cur = this.root;
           for (int i = 0; i < prefix.length(); i++) {
               char c = prefix.charAt(i);
               TrieNode child = cur.children.get(c);
               if (child == null) {
                   return false;
               } else {
                   cur = child;
               }
           }
           return true;
       }
   }
   
   /**
    * Your Trie object will be instantiated and called as such:
    * Trie obj = new Trie();
    * obj.insert(word);
    * boolean param_2 = obj.search(word);
    * boolean param_3 = obj.startsWith(prefix);
    */
   ```

   

2. *Pointer to word*

   ```java
   class Trie {
       class TrieNode {
           Map<Character, TrieNode> children = new HashMap<>();
           String word = null;
       }
       
       TrieNode root;
   
       /** Initialize your data structure here. */
       public Trie() {
           this.root = new TrieNode();
       }
       
       /** Inserts a word into the trie. */
       public void insert(String word) {
           TrieNode cur = this.root;
           for (int i = 0; i < word.length(); i++) {
               char c = word.charAt(i);
               cur = cur.children.computeIfAbsent(c, k -> new TrieNode());
           }
           cur.word = word;
       }
       
       /** Returns if the word is in the trie. */
       public boolean search(String word) {
           TrieNode cur = this.root;
           for (int i = 0; i < word.length(); i++) {
               char c = word.charAt(i);
               TrieNode child = cur.children.get(c);
               if (child == null) {
                   return false;
               } else {
                   cur = child;
               }
           }
           if (cur.word == null) 
               return false;
           return true;
       }
       
       /** Returns if there is any word in the trie that starts with the given prefix. */
       public boolean startsWith(String prefix) {
           TrieNode cur = this.root;
           for (int i = 0; i < prefix.length(); i++) {
               char c = prefix.charAt(i);
               TrieNode child = cur.children.get(c);
               if (child == null) {
                   return false;
               } else {
                   cur = child;
               }
           }        
           return true;
       }
   }
   
   /**
    * Your Trie object will be instantiated and called as such:
    * Trie obj = new Trie();
    * obj.insert(word);
    * boolean param_2 = obj.search(word);
    * boolean param_3 = obj.startsWith(prefix);
    */
   ```

   

3. *Every node contains pointers to passing word*

   ```java
   class Trie {
       class TrieNode {
           Map<Character, TrieNode> children = new HashMap<>();
           List<String> words = new ArrayList<>();
       }
       
       TrieNode root;
       
       /** Initialize your data structure here. */
       public Trie() {
           this.root = new TrieNode();
       }
       
       /** Inserts a word into the trie. */
       public void insert(String word) {
           if (root.words.contains(word)) {
               return;
           }
           TrieNode cur = this.root;
           for (int i = 0; i < word.length(); i++) {
               cur.words.add(word);
               char c = word.charAt(i);
               cur = cur.children.computeIfAbsent(c, k -> new TrieNode());
           }
           cur.words.add(word);
       }
       
       /** Returns if the word is in the trie. */
       public boolean search(String word) {
           TrieNode cur = this.root;
           for (int i = 0; i < word.length(); i++) {
               TrieNode child = cur.children.get(word.charAt(i));
               if (child == null) {
                   return false;
               } else {
                   cur = child;
               }
           }
           return cur.words.contains(word);
       }
       
       /** Returns if there is any word in the trie that starts with the given prefix. */
       public boolean startsWith(String prefix) {
           TrieNode cur = this.root;
           for (int i = 0; i < prefix.length(); i++) {
               TrieNode child = cur.children.get(prefix.charAt(i));
               if (child == null) {
                   return false;
               } else {
                   cur = child;
               }
           }
           return true;
       }
   }
   
   /**
    * Your Trie object will be instantiated and called as such:
    * Trie obj = new Trie();
    * obj.insert(word);
    * boolean param_2 = obj.search(word);
    * boolean param_3 = obj.startsWith(prefix);
    */
   ```



------

## 720. Longest Word in Dictionary

**Requirement**

Given a list of strings `words` representing an English Dictionary, find the longest word in `words` that can be built one character at a time by other words in `words`. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

**Example 1:**

```
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
```

**Example 2:**

```
Input: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: 
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
```

**Note:**

All the strings in the input will only contain lowercase letters.

The length of `words` will be in the range `[1, 1000]`.

The length of `words[i]` will be in the range `[1, 30]`.

**Solution**

*Goal*

Find the longest word in `words` that can be built one character at a time by other words in `words`

*How*

Input: String[] --- Array

Output: String

Prefix / dictionary ——> Trie 

```java
class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie(words);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            trie.insert(word, i);
        }
        return trie.dfs();
    }
    
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int end = -1;
    }
    
    class Trie {
        TrieNode root;
        String[] words;
        
        public Trie(String[] words) {
            this.root = new TrieNode();
            this.words = words;
        }
        
        public void insert(String word, int index) {
            TrieNode cur = this.root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                cur = cur.children.computeIfAbsent(c, k -> new TrieNode());
            }
            cur.end = index;
        }
        
        public String dfs() {
            String ans = "";
            Deque<TrieNode> stack = new ArrayDeque<>();
            stack.offerLast(this.root);
            while (!stack.isEmpty()) {
                TrieNode node = stack.pollLast();
                if (node.end >= 0 || node == this.root) {
                    if (node != root) {
                        String word = this.words[node.end];
                        if (word.length() > ans.length() || 
                           (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                            ans = word;
                        }
                    }
                    for (TrieNode n : node.children.values()) {
                        stack.offerLast(n);
                    }
                }
            }
            return ans;
        }
    }
}
```



------

## 745. Prefix and Suffix Search

Given many `words`, `words[i]` has weight `i`.

Design a class `WordFilter` that supports one function, `WordFilter.f(String prefix, String suffix)`. It will return the word with given `prefix` and `suffix` with maximum weight. If no word exists, return -1.

**Examples:**

```
Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1
```

**Note:**

1. `words` has length in range `[1, 15000]`.
2. For each test case, up to `words.length` queries `WordFilter.f` may be made.
3. `words[i]` has length in range `[1, 10]`.
4. `prefix, suffix` have lengths in range `[0, 10]`.
5. `words[i]` and `prefix, suffix` queries consist of lowercase letters only.

**Solution**









