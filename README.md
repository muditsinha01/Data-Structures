# Data-Structures
What are DS? Why do we learn them?
A data structure is a particular way of organizing data in a computer so that it can be used effectively.
The idea is to reduce the space and time complexities of different tasks.
## My work in this Repository:
* Stack 

A stack or LIFO (last in, first out) is an abstract data type that serves as a collection of elements, with two principal operations: push, which adds an element to the collection, and pop, which removes the last element that was added. In stack both the operations of push and pop takes place at the same end that is top of the stack. Implemented here with a linked list and utilized in a file management system. Also used in DFS search algorithm to compare with BST and Queue data structure for search times.
Insertion : O(1) constant time complexity 
Deletion :  O(1) constant time complexity
Access Time : O(n) Linear time complexity 

* Queues

A queue or FIFO (first in, first out) is an abstract data type that serves as a collection of elements, with two principal operations: enqueue, the process of adding an element to the collection.(The element is added from the rear side) and dequeue, the process of removing the first element that was added. Implemented here with a linked list and utilized in a file management system. Used in A BFS search algorithm to compare search times against stack and BST data structures.
Insertion : O(1) constant time complexity 
Deletion :  O(1) constant time complexity
Access Time : O(n) Linear time complexity
* Heaps

A Binary Heap is a Binary Tree with following properties. 
1) It’s a complete tree (All levels are completely filled except possibly the last level and the last level has all keys as left as possible). This property of Binary Heap makes them suitable to be stored in an array. 
2) A Binary Heap is either Min Heap or Max Heap. In a Min Binary Heap, the key at root must be minimum among all keys present in Binary Heap. The same property must be recursively true for all nodes in Binary Tree. Max Binary Heap is similar to Min Heap. My Heap has both min heap and max heap versions. 
Get Minimum in Min Heap: O(1) [Or Get Max in Max Heap]
Extract Minimum Min Heap: O(Log n) [Or Extract Max in Max Heap]
Decrease Key in Min Heap: O(Log n)  [Or Decrease Key in Max Heap]
Insert: O(Log n) 
Delete: O(Log n)
The only issue is that heaps can't search for specific things, they can only give the smallest or largest elements in the array(Useful for sorting).
* Hashtable

Features of Hashtable
It is similar to HashMap, but is synchronized.
Hashtable stores key/value pair in hash table.
In Hashtable we specify an object that is used as a key, and the value we want to associate to that key. The key is then hashed, and the resulting hash code is used as the index at which the value is stored within the table.
HashMap doesn’t provide any Enumeration, while Hashtable provides not fail-fast Enumeration.
The major difference between the common versions of hashtable and my version is that my version employs several types of probes for insertion of key value into table. In some 
cases this increases the time complexity while in other cases it reduces the time complexity but gives worse performance. 

* Bst 

A binary tree is a tree data structure in which each node has at most two children, which are referred to as the left child and the right child. It is implemented mainly using Links. 
Binary Tree Representation: A tree is represented by a pointer to the topmost node in tree. If the tree is empty, then value of root is NULL. A Binary Tree node contains following parts. 
1. Data 
2. Pointer to left child 
3. Pointer to right child 
Our binary tree has a Depth First Traversal
Depth First Traversal: Inorder (Left-Root-Right), Preorder (Root-Left-Right) and Postorder (Left-Right-Root) Time Complexity of Tree Traversal: O(n)

Binary Search Trees have the following additional properties
1. The left subtree of a node contains only nodes with keys less than the node’s key. 
2. The right subtree of a node contains only nodes with keys greater than the node’s key. 
3. The left and right subtree each must also be a binary search tree. 
Our Bst is a self-balancing tree so the time complexity for insertion,deletion and search/access is O(log n) which means that the Bst is better at searching than a Queue or stack based algorithm

