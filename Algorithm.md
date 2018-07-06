## Trie/Prefix Tree

- Variable length keys 
- The decision on what path to follow is taken based on potion of the key
- Static environment, fast retrieval but large space overhead

**Applications:**

- Dictionaries
- Text searching
- Spell checker
- Autocomplete 
- IP routing
- T9 predictive text
- solving word games

**Comparison:**

M: Maximum string length

N: Number of keys in tree

- Well balanced BST

  Key search: O(M * logN)

-  Hash Table

  Key search: O(1) But when size increasing, hash collision happens. O(N) instead.

- Trie

  Key search: O(M)

**Trie node structure**

Rooted tree

------



