# DSA Revision — Java
> A structured DSA revision repository in Java, covering core data structures and algorithms.
> Built for backend interview prep as a Java Developer (Spring Boot, Kafka).

---

## Why This Exists
I'm systematically revising Data Structures & Algorithms in Java to align my problem-solving with my backend development stack and prepare for product-based company interviews.

Every solution is written with production-style clarity — commented with approach, intuition, edge cases, and complexity analysis.

---

## 🗂️ Master Index

### 1. Sorting Algorithms

| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Selection Sort | Sorting / Basics | Easy | `O(N^2)` | `O(1)` | [SelectionSort.java](./sorting/SelectionSort.java) |
| Bubble Sort | Sorting / Basics | Easy | `O(N^2)` | `O(1)` | [BubbleSort.java](./sorting/BubbleSort.java) |
| Insertion Sort | Sorting / Basics | Easy | `O(N^2)` | `O(1)` | [InsertionSort.java](./sorting/InsertionSort.java) |
| Merge Sort | Sorting / Divide & Conquer | Medium | `O(N log N)` | `O(N)` | [MergeSort.java](./sorting/MergeSort.java) |
| Quick Sort | Sorting / Divide & Conquer | Medium | `O(N log N)` | `O(log N)` | [QuickSort.java](./sorting/QuickSort.java) |

---

### 2. Array Challenges

| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC1] Two Sum | Arrays / Hashing | Easy | `O(N)` | `O(N)` | [LC1TwoSum.java](./arrays/LC1TwoSum.java) |
| [LC26] Remove Duplicates | Arrays / Two Pointers | Easy | `O(N)` | `O(1)` | [LC26RemoveDuplicates.java](./arrays/LC26RemoveDuplicates.java) |
| [LC88] Merge Sorted Array | Arrays / Two Pointers | Easy | `O(M+N)` | `O(1)` | [LC88MergeSortedArraysInOne.java](./arrays/LC88MergeSortedArraysInOne.java) |
| [LC118] Pascal's Triangle | Arrays / Math | Easy | `O(N^2)` | `O(N^2)` | [LC118PascalsTriangle.java](./arrays/LC118PascalsTriangle.java) |
| [LC136] Single Number | Arrays / Bit Manipulation | Easy | `O(N)` | `O(1)` | [LC136SingleNumber.java](./arrays/LC136SIngleNumber.java) |
| [LC169] Majority Element | Arrays / Boyer-Moore | Easy | `O(N)` | `O(1)` | [LC169MajorityElement.java](./arrays/LC169MajorityElement.java) |
| [LC268] Missing Number | Arrays / Math | Easy | `O(N)` | `O(1)` | [LC268MissingNumber.java](./arrays/LC268MissingNumber.java) |
| [LC283] Move Zeroes | Arrays / Two Pointers | Easy | `O(N)` | `O(1)` | [LC283MoveZeroes.java](./arrays/LC283MoveZeroes.java) |
| [LC485] Max Consecutive Ones | Arrays / Traversal | Easy | `O(N)` | `O(1)` | [LC485MaxConsecutiveOnes.java](./arrays/LC485MaxConsecutiveOnes.java) |
| [GFG] Leaders in an Array | Arrays / Traversal | Easy | `O(N)` | `O(N)` | [LeadersInArray.java](./arrays/LeadersInArray.java) |
| [GFG] Union of Two Sorted Arrays | Arrays / Two Pointers | Easy | `O(N+M)` | `O(N+M)` | [UnionOfTwoSortedArraysWithDuplicates.java](./arrays/UnionOfTwoSortedArraysWithDuplicates.java) |
| [LC15] 3Sum | Arrays / Two Pointers | Medium | `O(N^2)` | `O(1)` | [LC15ThreeSum.java](./arrays/LC15ThreeSum.java) |
| [LC18] 4Sum | Arrays / Two Pointers | Medium | `O(N^3)` | `O(1)` | [LC18FourSum.java](./arrays/LC18FourSum.java) |
| [LC48] Rotate Image | Arrays / Matrix | Medium | `O(N^2)` | `O(1)` | [LC48RotateMatrix.java](./arrays/LC48RotateMatrix.java) |
| [LC53] Maximum Subarray (Kadane) | Arrays / DP | Medium | `O(N)` | `O(1)` | [LC59MaximumSubarrayKadanesAlgo.java](./arrays/LC59MaximumSubarrayKadanesAlgo.java) |
| [LC54] Spiral Matrix | Arrays / Matrix | Medium | `O(M*N)` | `O(1)` | [LC54SpriralMatrix.java](./arrays/LC54SpriralMatrix.java) |
| [LC56] Merge Intervals | Arrays / Sorting | Medium | `O(N log N)` | `O(N)` | [LC56MergeIntervals.java](./arrays/LC56MergeIntervals.java) |
| [LC73] Set Matrix Zeroes | Arrays / Matrix | Medium | `O(M*N)` | `O(1)` | [LC73SetMatrixZeroes.java](./arrays/LC73SetMatrixZeroes.java) |
| [LC75] Sort Colors | Arrays / Dutch National Flag | Medium | `O(N)` | `O(1)` | [LC75SortColors.java](./arrays/LC75SortColors.java) |
| [LC128] Longest Consecutive Sequence | Arrays / Hashing | Medium | `O(N)` | `O(N)` | [LC128LongestConsecutiveSequence.java](./arrays/LC128LongestConsecutiveSequence.java) |
| [LC189] Rotate Array | Arrays / Math | Medium | `O(N)` | `O(1)` | [LC189RotateArrayByK.java](./arrays/LC189RotateArrayByK.java) |
| [LC2149] Rearrange Array by Sign | Arrays / Two Pointers | Medium | `O(N)` | `O(N)` | [LC2149RearrangeArrayBySign.java](./arrays/LC2149RearrangeArrayBySign.java) |
| [LC229] Majority Element II | Arrays / Boyer-Moore | Medium | `O(N)` | `O(1)` | [LC229MajorityElements2.java](./arrays/LC229MajorityElements2.java) |
| [IB/LC] Count Subarrays with XOR K | Arrays / Prefix XOR | Medium | `O(N)` | `O(N)` | [CountSubarrayXOREqualsK.java](./arrays/CountSubarrayXOREqualsK.java) |
| [GFG] Find Missing & Repeating | Arrays / Math | Medium | `O(N)` | `O(1)` | [GFG_FindMissingAndRepeatingNumber.java](./arrays/GFG_FindMissingAndRepeatingNumber.java) |
| [GFG] Longest Subarray with Sum K | Arrays / Prefix Sum | Medium | `O(N)` | `O(N)` | [GFG_LongestSubarrayWithSumK.java](./arrays/GFG_LongestSubarrayWithSumK.java) |
| [LC493] Reverse Pairs | Arrays / Divide & Conquer | Hard | `O(N log N)` | `O(N)` | [LC493ReversePairs.java](./arrays/LC493ReversePairs.java) |
| [GFG] Count Inversions | Arrays / Divide & Conquer | Hard | `O(N log N)` | `O(N)` | [GFG_CountInversion.java](./arrays/GFG_CountInversion.java) |

---

### 3. Binary Search

#### On 1D Arrays
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [Basic] Find Index of Target | BS on 1D | Easy | `O(log N)` | `O(1)` | [FindIndexOfTarget.java](./binarysearch/on1DArrays/FindIndexOfTarget.java) |
| [LC35] Search Insert Position | BS on 1D | Easy | `O(log N)` | `O(1)` | [LC35SearchInsertPosition.java](./binarysearch/on1DArrays/LC35SearchInsertPosition.java) |
| [GFG] Count Occurrences | BS on 1D | Easy | `O(log N)` | `O(1)` | [GFG_CountOccurrencesInSortedArray.java](./binarysearch/on1DArrays/GFG_CountOccurrencesInSortedArray.java) |
| [LC34] Find First & Last Position | BS on 1D | Medium | `O(log N)` | `O(1)` | [LC34FindFirstAndLastOccurrences.java](./binarysearch/on1DArrays/LC34FindFirstAndLastOccurrences.java) |
| [LC33] Search in Rotated Array | BS on 1D | Medium | `O(log N)` | `O(1)` | [LC33RotatedSortedArraySearch.java](./binarysearch/on1DArrays/LC33RotatedSortedArraySearch.java) |
| [LC81] Search in Rotated Array II | BS on 1D | Medium | `O(log N)*` | `O(1)` | [LC81RotatedSortedArraySearch2.java](./binarysearch/on1DArrays/LC81RotatedSortedArraySearch2.java) |
| [LC153] Min in Rotated Array | BS on 1D | Medium | `O(log N)` | `O(1)` | [LC153MinOfRotatedSortedArray.java](./binarysearch/on1DArrays/LC153MinOfRotatedSortedArray.java) |
| [GFG] Find Rotation Count | BS on 1D | Medium | `O(log N)` | `O(1)` | [GFG_FindRotationCountInSortedArray.java](./binarysearch/on1DArrays/GFG_FindRotationCountInSortedArray.java) |
| [LC162] Find Peak Element | BS on 1D | Medium | `O(log N)` | `O(1)` | [LC162PeakElementInSortedArray.java](./binarysearch/on1DArrays/LC162PeakElementInSortedArray.java) |
| [LC540] Single Element in Sorted Array | BS on 1D | Medium | `O(log N)` | `O(1)` | [LC540SingleElementInSortedArray.java](./binarysearch/on1DArrays/LC540SingleElementInSortedArray.java) |

<br>

#### On Answers
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC69] Sqrt(x) | BS on Answers | Easy | `O(log N)` | `O(1)` | [LC69SquareRoot.java](./binarysearch/onAnswers/LC69SquareRoot.java) |
| [GFG] Nth Root of M | BS on Answers | Easy | `O(log M)` | `O(1)` | [GFG_nthRoot.java](./binarysearch/onAnswers/GFG_nthRoot.java) |
| [LC875] Koko Eating Bananas | BS on Answers | Medium | `O(N log M)` | `O(1)` | [LC875BananaEating.java](./binarysearch/onAnswers/LC875BananaEating.java) |
| [SPOJ/GFG] Aggressive Cows | BS on Answers | Medium | `O(N log M)` | `O(1)` | [GFG_AggressiveCows.java](./binarysearch/onAnswers/GFG_AggressiveCows.java) |
| [GFG] Allocate Minimum Pages | BS on Answers | Hard | `O(N log M)` | `O(1)` | [GFG_BookAllocation.java](./binarysearch/onAnswers/GFG_BookAllocation.java) |
| [LC410] Split Array Largest Sum | BS on Answers | Hard | `O(N log M)` | `O(1)` | [LC410SplitArrayLargestSum.java](./binarysearch/onAnswers/LC410SplitArrayLargestSum.java) |

<br>

#### On 2D Matrix
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC74] Search a 2D Matrix | BS on 2D | Medium | `O(log(M*N))` | `O(1)` | [LC74SortedMatrixSearch.java](./binarysearch/on2DMatrix/LC74SortedMatrixSearch.java) |
| [LC240] Search a 2D Matrix II | BS on 2D | Medium | `O(M + N)` | `O(1)` | [LC240MatrixSearch2.java](./binarysearch/on2DMatrix/LC240MatrixSearch2.java) |
| [GFG] Row with Max 1s | BS on 2D | Medium | `O(M + N)` | `O(1)` | [GFG_RowWithMaxOne.java](./binarysearch/on2DMatrix/GFG_RowWithMaxOne.java) |
| [LC1901] Find a Peak Element II | BS on 2D | Medium | `O(M log N)` | `O(1)` | [LC1901PeakElement2.java](./binarysearch/on2DMatrix/LC1901PeakElement2.java) |
| [GFG] Median in a Row-Wise Sorted Matrix | BS on 2D | Hard | `O(M log(MAX) * log N)` | `O(1)` | [GFG_MatrixMedian.java](./binarysearch/on2DMatrix/GFG_MatrixMedian.java) |

---

### 4. Strings

#### Basics & Easy
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC151] Reverse Words in a String | Strings / Two Pointers | Medium* | `O(N)` | `O(N)` | [LC151ReverseWords.java](./strings/easy/LC151ReverseWords.java) |
| [LC242] Valid Anagram | Strings / Hashing | Easy | `O(N)` | `O(1)` | [LC242Anagram.java](./strings/easy/LC242Anagram.java) |
| [LC796] Rotate String | Strings / Matching | Easy | `O(N)` | `O(N)` | [LC796RotateString.java](./strings/easy/LC796RotateString.java) |
| [LC1021] Remove Outermost Parentheses | Strings / Stack | Easy | `O(N)` | `O(N)` | [LC1021RemoveOutermostParenthesis.java](./strings/easy/LC1021RemoveOutermostParenthesis.java) |
| [LC1903] Largest Odd Number in String | Strings / Math | Easy | `O(N)` | `O(1)` | [LC1903LargestOddNumInString.java](./strings/easy/LC1903LargestOddNumInString.java) |

<br>

#### Medium
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC5] Longest Palindromic Substring | Strings / Expand Center | Medium | `O(N^2)` | `O(1)` | [LC5LongestPalindromicSubstring.java](./strings/medium/LC5LongestPalindromicSubstring.java) |
| [LC1781] Sum of Beauty of All Substrings | Strings / Hashing | Medium | `O(N^2)` | `O(1)` | [LC1781BeautyOfString.java](./strings/medium/LC1781BeautyOfString.java) |

---

### 5. Linked List

#### Singly Linked List (Basics)
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Convert Array to Linked List | Linked List / Basics | Easy | `O(N)` | `O(N)` | [ArrayToLinkedList.java](./linkedlist/singlylinkedlist/ArrayToLinkedList.java) |
| Print Linked List | Linked List / Basics | Easy | `O(N)` | `O(1)` | [PrintLinkedList.java](./linkedlist/singlylinkedlist/PrintLinkedList.java) |
| Find Length of Linked List | Linked List / Basics | Easy | `O(N)` | `O(1)` | [LinkedListLength.java](./linkedlist/singlylinkedlist/LinkedListLength.java) |
| Search in Linked List | Linked List / Basics | Easy | `O(N)` | `O(1)` | [CheckIfTargetExistsInLL.java](./linkedlist/singlylinkedlist/CheckIfTargetExistsInLL.java) |
| Insert Node in Singly LL | Linked List / Basics | Easy | `O(N)`* | `O(1)` | [InsertionInSinglyLinkedList.java](./linkedlist/singlylinkedlist/InsertionInSinglyLinkedList.java) |
| Delete Node in Singly LL | Linked List / Basics | Easy | `O(N)`* | `O(1)` | [DeleteInSinglyLinkedList.java](./linkedlist/singlylinkedlist/DeleteInSinglyLinkedList.java) |

<br>

#### Singly Linked List (Medium)
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC876] Middle of the Linked List | Linked List / Fast & Slow | Easy* | `O(N)` | `O(1)` | [LC876MidOfLL.java](./linkedlist/mediumonsinglylinkedlist/LC876MidOfLL.java) |
| [LC141] Linked List Cycle | Linked List / Fast & Slow | Easy* | `O(N)` | `O(1)` | [LC141CycleInSinglyLL.java](./linkedlist/mediumonsinglylinkedlist/LC141CycleInSinglyLL.java) |
| [LC142] Linked List Cycle II | Linked List / Fast & Slow | Medium | `O(N)` | `O(1)` | [LC142StartOfCycleLengthSinglyLinkedList.java](./linkedlist/mediumonsinglylinkedlist/LC142StartOfCycleLengthSinglyLinkedList.java) |
| [GFG] Length of Loop in LL | Linked List / Fast & Slow | Medium | `O(N)` | `O(1)` | [GFG_LengthOfLoopOfLinkedList.java](./linkedlist/mediumonsinglylinkedlist/GFG_LengthOfLoopOfLinkedList.java) |
| [LC19] Remove Nth Node From End | Linked List / Two Pointers | Medium | `O(N)` | `O(1)` | [LC19RemoveNthNode.java](./linkedlist/mediumonsinglylinkedlist/LC19RemoveNthNode.java) |
| [LC160] Intersection of Two LLs | Linked List / Two Pointers | Easy* | `O(N+M)` | `O(1)` | [LC160IntersectionInLinkedList.java](./linkedlist/mediumonsinglylinkedlist/LC160InterSectionInLinkedList.java) |
| [LC234] Palindrome Linked List | Linked List / Two Pointers | Easy* | `O(N)` | `O(1)` | [LC234PalindromicLinkedList.java](./linkedlist/mediumonsinglylinkedlist/LC234PalindromicLinkedList.java) |
| [LC328] Odd Even Linked List | Linked List / Traversal | Medium | `O(N)` | `O(1)` | [LC_328OddEvenLinkedList.java](./linkedlist/mediumonsinglylinkedlist/LC_328OddEvenLinkedList.java) |
| [LC148] Sort List | Linked List / Merge Sort | Medium | `O(N log N)` | `O(1)` | [LC148SortSinglyLinkedList.java](./linkedlist/mediumonsinglylinkedlist/LC148SortSinglyLinkedList.java) |
| [GFG] Add 1 to a Number as LL | Linked List / Math | Medium | `O(N)` | `O(1)` | [GFG_AddOneToSinglyLinkedList.java](./linkedlist/mediumonsinglylinkedlist/GFG_AddOneToSinglyLinkedList.java) |

<br>

#### Doubly Linked List (Medium)
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [GFG] Find Pairs with Given Sum | Doubly LL / Two Pointers | Medium | `O(N)` | `O(1)` | [GFG_PairSumDoublyLinkedList.java](./linkedlist/mediumondoublylinkedlist/GFG_PairSumDoublyLinkedList.java) |
| [GFG] Remove All Occurrences of Key | Doubly LL / Traversal | Medium | `O(N)` | `O(1)` | [GFG_RemoveAllOccurrencesOfKey.java](./linkedlist/mediumondoublylinkedlist/GFG_RemoveAllOccurrencesOfKey.java) |
| [GFG] Remove Duplicates from Sorted DLL | Doubly LL / Traversal | Easy* | `O(N)` | `O(1)` | [GFG_RemoveDuplicates.java](./linkedlist/mediumondoublylinkedlist/GFG_RemoveDuplicates.java) |

<br>

#### Hard & Advanced
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC25] Reverse Nodes in k-Group | Linked List / Reversal | Hard | `O(N)` | `O(1)` | [LC25ReverseLinkedListInKGroups.java](./linkedlist/hard/LC25ReverseLinkedListInKGroups.java) |
| [LC61] Rotate List | Linked List / Two Pointers | Medium* | `O(N)` | `O(1)` | [LC61RotateSinglyListToRightByK.java](./linkedlist/hard/LC61RotateSinglyListToRightByK.java) |
| [LC138] Copy List with Random Pointer | Linked List / Deep Copy | Medium* | `O(N)` | `O(1)` | [LC138DeepCopyRandomPointers.java](./linkedlist/hard/LC138DeepCopyRandomPointers.java) |
| [GFG] Flattening a Linked List | Linked List / Merge | Hard | `O(N * M)` | `O(1)` | [GFG_FlattenLinkedList.java](./linkedlist/hard/GFG_FlattenLinkedList.java) |

---

### 6. Recursion

#### Basics
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC8] String to Integer (atoi) | Recursion / Strings | Medium | `O(N)` | `O(N)` | [LC8_AtoiRecursion.java](./recursion/basics/LC8_AtoiRecursion.java) |
| [GFG] Sort a Stack | Recursion / Stack | Medium | `O(N^2)` | `O(N)` | [GFG_SortStack.java](./recursion/basics/GFG_SortStack.java) |

<br>

#### Subsequence Patterns
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [Code360] Binary Strings w/o Consecutive 1s | Recursion / Backtracking | Medium | `O(2^N)` | `O(N)` | [Code360_GenerateBinaryStringWithoutConsecutive1.java](./recursion/subsequencepatterns/Code360_GenerateBinaryStringWithoutConsecutive1.java) |
| [GFG] Subsequence Sum Target | Recursion / Subsequences | Medium | `O(2^N)` | `O(N)` | [GFG_SubsequenceSumTarget.java](./recursion/subsequencepatterns/GFG_SubsequenceSumTarget.java) |
| [GFG] Count Subsequences with Sum K | Recursion / Subsequences | Medium | `O(2^N)` | `O(N)` | [CountSubsequenceWithSumK.java](./recursion/subsequencepatterns/CountSubsequenceWithSumK.java) |
| [GFG] Subset Sums | Recursion / Subsequences | Medium | `O(2^N)` | `O(2^N)` | [GFG_SubsetSums.java](./recursion/subsequencepatterns/GFG_SubSetSums.java) |
| [LC90] Subsets II | Recursion / Backtracking | Medium | `O(2^N * N)` | `O(N)`* | [LC90SubSetsSum2.java](./recursion/subsequencepatterns/LC90SubSetsSum2.java) |
| [LC39] Combination Sum | Recursion / Backtracking | Medium | `O(2^T)` | `O(T)` | [LC39CombinationSum.java](./recursion/subsequencepatterns/LC39CombinationSum.java) |
| [LC40] Combination Sum II | Recursion / Backtracking | Medium | `O(2^N)` | `O(N)`* | [LC40CombinationSum_2.java](./recursion/subsequencepatterns/LC40CombinationSum_2.java) |
| [LC216] Combination Sum III | Recursion / Backtracking | Medium | `O(9! / (K!*(9-K)!))` | `O(K)` | [LC216CombinationSum3.java](./recursion/subsequencepatterns/LC216CombinationSum3.java) |
| [LC17] Letter Combinations of a Phone Number| Recursion / Backtracking | Medium | `O(4^N * N)` | `O(N)` | [LC17_LetterCombinations.java](./recursion/subsequencepatterns/LC17_LetterCombinations.java) |
| [LC22] Generate Parentheses | Recursion / Backtracking | Medium | `O(4^N / sqrt(N))` | `O(N)` | [LC22GenerateParenthesis.java](./recursion/subsequencepatterns/LC22GenerateParenthesis.java) |

<br>

#### Hard Backtracking Problems
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC51] N-Queens | Backtracking | Hard | `O(N!)` | `O(N²)` | [LC51NQueens.java](./recursion/hard/LC51NQueens.java) |
| [LC131] Palindrome Partitioning | Backtracking / Strings | Medium | `O(N · 2^N)` | `O(N)` | [LC131PalindromePartitioning.java](./recursion/hard/LC131PalindromePartiotioning.java) |
| [LC139] Word Break | Backtracking + DP | Medium | `O(N^2 * M)` | `O(N)` | [LC139WordBreak.java](./recursion/hard/LC139WordBreak.java) |
| [GFG] Rat In A Maze | Backtracking / Strings | Medium | `O(3^(N ^ 2))` | `O(N^2)` | [GFG_RatInMaze.java](./recursion/hard/GFG_RatInMaze.java) |
| [LC37] Sudoku Solver | Backtracking | Hard | `O(9^(N^2))` | `O(N^2)` | [LC37SudokuSolver](./recursion/hard/LC37SudokuSolver.java) |

---
### 7. Stack and Queue
#### Implementation
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Stack Using Array| Stack/Array | Easy | `O(1)` | `O(N)` | [StackUsingArray.java](./stack/implementation/StackUsingArray.java) |
| Stack Using Queue| Stack /Queue | Easy | `O(N)` | `O(N)` | [StackUsingQueue.java](./stack/implementation/StackUsingQueue.java) |
| Queue Using Array| Queue /Array | Easy | `O(1)` | `O(N)` | [QueueUsingArray.java](./queue/implementation/QueueUsingArray.class) |

<br>

#### Basic
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC20] Valid Parentheses | Stack | Easy | `O(N)` | `O(N)` | [LC20ValidParentheses.java](./stack/LC20ValidParentheses.java) |
| [LC155] MinStack | Stack | Medium | `O(1)` | `O(N)` | [LC155MinStack.java.java](./stack/LC155MinStack.java) |

<br>

#### Monotonic Stack
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [GFG] Next Smaller Element | Stack / Monotonic Stack | Easy | `O(N)` | `O(N)` | [GFG_NextSmallerElement.java](./stack/monotonicstack/GFG_NextSmallerElement.java) |
| [LC42] Trapping Rain Water | Stack / Monotonic Stack | Hard | `O(N)` | `O(N)` | [LC42TrappingRainWater.java](./stack/monotonicstack/LC42TrappingRainWater.java) |
| [LC496] Next Greater Element I | Stack / Monotonic Stack | Easy | `O(N + M)` | `O(M)` | [LC496NextGreterElement.java](./stack/monotonicstack/LC496NextGreterElement.java) |
| [LC503] Next Greater Element II | Stack / Monotonic Stack | Medium | `O(N)` | `O(N)` | [LC503NextGreterElement2.java](./stack/monotonicstack/LC503NextGreterElement2.java) |
| [LC907] Sum of Subarray Minimums | Stack / Monotonic Stack | Medium | `O(N)` | `O(N)` | [LC907SumOfSubArrayMinimums.java](./stack/monotonicstack/LC907SumOfSubArrayMinimums.java) |
| [LC735] Asteroid Collisions | Stack / Monotonic Stack | Medium | `O(N)` | `O(N)` | [LC735AsteroidCollisions.java](./stack/monotonicstack/LC735AsteroidCollisions.java) |
| [LC2104] Sum of Subarray Ranges | Stack / Monotonic Stack | Medium | `O(N)` | `O(N)` | [LC2104SumOFSubArrayRange.java](./stack/monotonicstack/LC2104SumOFSubArrayRange.java) |
| [LC402] Remove K degits | Stack / Monotonic Stack / String| Medium | `O(N)` | `O(N)` | [LC402RemoveKDigits.java](./stack/monotonicstack/LC402RemoveKDigits.java) |
| [LC84] Largest Area Histogram | Stack / Monotonic Stack | Hard | `O(N)` | `O(N)` | [LC84LargestReactangeAreaHistogram.java](./stack/monotonicstack/LC84LargestReactangleAreaHistogram.java) |
| [LC85] Maximal Rectangle Area | Stack / Monotonic Stack / Matrix | Hard | `O(N * M)` | `O(M)` | [LC85MaximalAreaRectangle.java](./stack/monotonicstack/LC85MaximalAreaRectangle.java) |

#### Queue hard problems
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC239] Sliding Window Maximum | Monotonic Deque | Hard   | `O(N)`| `O(K)` | [LC239SlidingWindowMaximum.java](./queue/hard/LC239SlidingWindowMaximum.java) |


#### Stack hard problems
| Problem Name | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link |
| :--- | :--- | :--- | :--- | :--- | :--- |
| [LC901] Online Stock Span | Monotonic Stack | Medium | `O(1)` amort. | `O(N)` | [LC901StockSpanner.java](./stack/hard/LC901StockSpanner.java) |
| [GFG] The Celebrity Problem | Monotonic Stack | Medium | `O(N*N)` amort. | `O(N)` | [GFG_TheCelebrityProblem.java](./stack/hard/GFG_TheCelebrityProblem.java)|

---

## Repository Structure
```
DSA-Revision-Java/
├── arrays/
├── sorting/
├── binarysearch/
├── strings/
├── linkedlist/      
├── recursion/       ✅ Done
├── stack&queue/       ← current focus
└── ...more topics added as I progress
```
**File naming:** `[Platform + ProblemCountIfAny]_[ProblemName].java`
Example: `LC1TwoSum.java` (LC = LeetCode), `GFG_CountOccurrencesInSortedArray.java` (GFG = GeeksForGeeks)
Each file includes:
- Link to the original problem
- Pattern & key insight
- Step-by-step approach explanation
- Clean, optimized Java implementation
- Time & space complexity
---
## Progress
| Topic | Status |
| :--- | :--- |
| Basics | ✅ Done |
| Arrays | ✅ Done |
| Sorting | ✅ Done |
| Binary Search | ✅ Done |
| Strings | ✅ Done |
| Linked Lists | ✅ Done |
| Recursion | ✅ Done |
| Stack & Queue | 🔄 In Progress |
| Trees | ⏳ Pending |
| Graphs | ⏳ Pending |
| Dynamic Programming | ⏳ Pending |
---
## Current Focus — Stack and Queue
Having completed revision of **Basics, Arrays, Sorting, Binary Search, Strings, Linked Lists and Recursion**, the focus now shifts to **Stack & Queue**.
Topics to be covered:
- Basics of stack and queue
- Conversion problem
- Monotonic stack
- Queue problems
- Implementation problems
---
## Goal
Build strong DSA fundamentals and problem-solving speed to target backend-focused and product-based company roles.
