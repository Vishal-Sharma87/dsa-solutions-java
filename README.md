# DSA in Java — Clean Solutions & Deep Docs

> Production-style DSA solutions in Java. Every problem comes with approach breakdown,
> intuition, complexity analysis, and inline comments written like a developer — not a textbook.

---

## What This Repo Is

A reference-grade DSA solution set in Java — built for engineers who want more than
just "accepted" code.

Each solution documents:

- **Multiple approaches** with honest tradeoff analysis
- **Chosen approach** marked clearly (★)
- **Inline comments** that explain _why_, not just _what_
- **Time & Space complexity** per approach

Topics covered: Linked Lists · Strings · Sliding Window · Recursion · Backtracking
_(Stacks, DP, Graphs coming up)_

---

## Who This Is For

- Engineers preparing for SDE interviews at product companies
- Anyone who wants readable, well-documented Java solutions to reference
- Developers who think good code deserves good documentation

---

## Contributing

Found a better approach? Spotted a bug? PRs are welcome.
Open an issue or drop a suggestion — this repo gets better with more perspectives.

---

## 🗂️ Master Index

### 1. Sorting Algorithms

| Problem Name   | Topic                      | Difficulty | Time Complexity | Space Complexity | Solution Link                                      |
| :------------- | :------------------------- | :--------- | :-------------- | :--------------- | :------------------------------------------------- |
| Selection Sort | Sorting / Basics           | Easy       | `O(N^2)`        | `O(1)`           | [SelectionSort.java](./sorting/SelectionSort.java) |
| Bubble Sort    | Sorting / Basics           | Easy       | `O(N^2)`        | `O(1)`           | [BubbleSort.java](./sorting/BubbleSort.java)       |
| Insertion Sort | Sorting / Basics           | Easy       | `O(N^2)`        | `O(1)`           | [InsertionSort.java](./sorting/InsertionSort.java) |
| Merge Sort     | Sorting / Divide & Conquer | Medium     | `O(N log N)`    | `O(N)`           | [MergeSort.java](./sorting/MergeSort.java)         |
| Quick Sort     | Sorting / Divide & Conquer | Medium     | `O(N log N)`    | `O(log N)`       | [QuickSort.java](./sorting/QuickSort.java)         |

---

### 2. Array Challenges

#### Easy

| Problem Name                     | Topic                     | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                        |
| :------------------------------- | :------------------------ | :--------- | :-------------- | :--------------- | :--------------------------------------------------------------------------------------------------- |
| [LC1] Two Sum                    | Arrays / Hashing          | Easy       | `O(N)`          | `O(N)`           | [LC1TwoSum.java](./arrays/easy/LC1TwoSum.java)                                                       |
| [LC26] Remove Duplicates         | Arrays / Two Pointers     | Easy       | `O(N)`          | `O(1)`           | [LC26RemoveDuplicates.java](./arrays/easy/LC26RemoveDuplicates.java)                                 |
| [LC88] Merge Sorted Array        | Arrays / Two Pointers     | Easy       | `O(M+N)`        | `O(1)`           | [LC88MergeSortedArraysInOne.java](./arrays/easy/LC88MergeSortedArraysInOne.java)                     |
| [LC118] Pascal's Triangle        | Arrays / Math             | Easy       | `O(N^2)`        | `O(N^2)`         | [LC118PascalsTriangle.java](./arrays/easy/LC118PascalsTriangle.java)                                 |
| [LC136] Single Number            | Arrays / Bit Manipulation | Easy       | `O(N)`          | `O(1)`           | [LC136SingleNumber.java](./arrays/easy/LC136SIngleNumber.java)                                       |
| [LC169] Majority Element         | Arrays / Boyer-Moore      | Easy       | `O(N)`          | `O(1)`           | [LC169MajorityElement.java](./arrays/easy/LC169MajorityElement.java)                                 |
| [LC268] Missing Number           | Arrays / Math             | Easy       | `O(N)`          | `O(1)`           | [LC268MissingNumber.java](./arrays/easy/LC268MissingNumber.java)                                     |
| [LC283] Move Zeroes              | Arrays / Two Pointers     | Easy       | `O(N)`          | `O(1)`           | [LC283MoveZeroes.java](./arrays/easy/LC283MoveZeroes.java)                                           |
| [LC485] Max Consecutive Ones     | Arrays / Traversal        | Easy       | `O(N)`          | `O(1)`           | [LC485MaxConsecutiveOnes.java](./arrays/easy/LC485MaxConsecutiveOnes.java)                           |
| [GFG] Leaders in an Array        | Arrays / Traversal        | Easy       | `O(N)`          | `O(N)`           | [LeadersInArray.java](./arrays/easy/LeadersInArray.java)                                             |
| [GFG] Union of Two Sorted Arrays | Arrays / Two Pointers     | Easy       | `O(N+M)`        | `O(N+M)`         | [UnionOfTwoSortedArraysWithDuplicates.java](./arrays/easy/UnionOfTwoSortedArraysWithDuplicates.java) |

<br>

#### Medium

| Problem Name                         | Topic                        | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                |
| :----------------------------------- | :--------------------------- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------------------------------- |
| [LC15] 3Sum                          | Arrays / Two Pointers        | Medium     | `O(N^2)`        | `O(1)`           | [LC15ThreeSum.java](./arrays/medium/LC15ThreeSum.java)                                       |
| [LC18] 4Sum                          | Arrays / Two Pointers        | Medium     | `O(N^3)`        | `O(1)`           | [LC18FourSum.java](./arrays/medium/LC18FourSum.java)                                         |
| [LC48] Rotate Image                  | Arrays / Matrix              | Medium     | `O(N^2)`        | `O(1)`           | [LC48RotateMatrix.java](./arrays/medium/LC48RotateMatrix.java)                               |
| [LC53] Maximum Subarray (Kadane)     | Arrays / DP                  | Medium     | `O(N)`          | `O(1)`           | [LC59MaximumSubarrayKadanesAlgo.java](./arrays/medium/LC59MaximumSubarrayKadanesAlgo.java)   |
| [LC54] Spiral Matrix                 | Arrays / Matrix              | Medium     | `O(M*N)`        | `O(1)`           | [LC54SpriralMatrix.java](./arrays/medium/LC54SpriralMatrix.java)                             |
| [LC56] Merge Intervals               | Arrays / Sorting             | Medium     | `O(N log N)`    | `O(N)`           | [LC56MergeIntervals.java](./arrays/medium/LC56MergeIntervals.java)                           |
| [LC73] Set Matrix Zeroes             | Arrays / Matrix              | Medium     | `O(M*N)`        | `O(1)`           | [LC73SetMatrixZeroes.java](./arrays/medium/LC73SetMatrixZeroes.java)                         |
| [LC75] Sort Colors                   | Arrays / Dutch National Flag | Medium     | `O(N)`          | `O(1)`           | [LC75SortColors.java](./arrays/medium/LC75SortColors.java)                                   |
| [LC128] Longest Consecutive Sequence | Arrays / Hashing             | Medium     | `O(N)`          | `O(N)`           | [LC128LongestConsecutiveSequence.java](./arrays/medium/LC128LongestConsecutiveSequence.java) |
| [LC189] Rotate Array                 | Arrays / Math                | Medium     | `O(N)`          | `O(1)`           | [LC189RotateArrayByK.java](./arrays/medium/LC189RotateArrayByK.java)                         |
| [LC2149] Rearrange Array by Sign     | Arrays / Two Pointers        | Medium     | `O(N)`          | `O(N)`           | [LC2149RearrangeArrayBySign.java](./arrays/medium/LC2149RearrangeArrayBySign.java)           |

<br>

#### Hard Problems

| Problem Name                       | Topic                     | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                    |
| :--------------------------------- | :------------------------ | :--------- | :-------------- | :--------------- | :----------------------------------------------------------------------------------------------- |
| [LC229] Majority Element II        | Arrays / Boyer-Moore      | Medium     | `O(N)`          | `O(1)`           | [LC229MajorityElements2.java](./arrays/medium/LC229MajorityElements2.java)                       |
| [IB/LC] Count Subarrays with XOR K | Arrays / Prefix XOR       | Medium     | `O(N)`          | `O(N)`           | [CountSubarrayXOREqualsK.java](./arrays/medium/CountSubarrayXOREqualsK.java)                     |
| [GFG] Find Missing & Repeating     | Arrays / Math             | Medium     | `O(N)`          | `O(1)`           | [GFG_FindMissingAndRepeatingNumber.java](./arrays/medium/GFG_FindMissingAndRepeatingNumber.java) |
| [GFG] Longest Subarray with Sum K  | Arrays / Prefix Sum       | Medium     | `O(N)`          | `O(N)`           | [GFG_LongestSubarrayWithSumK.java](./arrays/medium/GFG_LongestSubarrayWithSumK.java)             |
| [LC493] Reverse Pairs              | Arrays / Divide & Conquer | Hard       | `O(N log N)`    | `O(N)`           | [LC493ReversePairs.java](./arrays/hard/LC493ReversePairs.java)                                   |
| [GFG] Count Inversions             | Arrays / Divide & Conquer | Hard       | `O(N log N)`    | `O(N)`           | [GFG_CountInversion.java](./arrays/hard/GFG_CountInversion.java)                                 |

---

### 3. Binary Search

#### Binary Search On 1D Arrays

| Problem Name                           | Topic    | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                                |
| :------------------------------------- | :------- | :--------- | :-------------- | :--------------- | :----------------------------------------------------------------------------------------------------------- |
| [Basic] Find Index of Target           | BS on 1D | Easy       | `O(log N)`      | `O(1)`           | [FindIndexOfTarget.java](./binarysearch/on1DArrays/FindIndexOfTarget.java)                                   |
| [LC35] Search Insert Position          | BS on 1D | Easy       | `O(log N)`      | `O(1)`           | [LC35SearchInsertPosition.java](./binarysearch/on1DArrays/LC35SearchInsertPosition.java)                     |
| [GFG] Count Occurrences                | BS on 1D | Easy       | `O(log N)`      | `O(1)`           | [GFG_CountOccurrencesInSortedArray.java](./binarysearch/on1DArrays/GFG_CountOccurrencesInSortedArray.java)   |
| [LC34] Find First & Last Position      | BS on 1D | Medium     | `O(log N)`      | `O(1)`           | [LC34FindFirstAndLastOccurrences.java](./binarysearch/on1DArrays/LC34FindFirstAndLastOccurrences.java)       |
| [LC33] Search in Rotated Array         | BS on 1D | Medium     | `O(log N)`      | `O(1)`           | [LC33RotatedSortedArraySearch.java](./binarysearch/on1DArrays/LC33RotatedSortedArraySearch.java)             |
| [LC81] Search in Rotated Array II      | BS on 1D | Medium     | `O(log N)*`     | `O(1)`           | [LC81RotatedSortedArraySearch2.java](./binarysearch/on1DArrays/LC81RotatedSortedArraySearch2.java)           |
| [LC153] Min in Rotated Array           | BS on 1D | Medium     | `O(log N)`      | `O(1)`           | [LC153MinOfRotatedSortedArray.java](./binarysearch/on1DArrays/LC153MinOfRotatedSortedArray.java)             |
| [GFG] Find Rotation Count              | BS on 1D | Medium     | `O(log N)`      | `O(1)`           | [GFG_FindRotationCountInSortedArray.java](./binarysearch/on1DArrays/GFG_FindRotationCountInSortedArray.java) |
| [LC162] Find Peak Element              | BS on 1D | Medium     | `O(log N)`      | `O(1)`           | [LC162PeakElementInSortedArray.java](./binarysearch/on1DArrays/LC162PeakElementInSortedArray.java)           |
| [LC540] Single Element in Sorted Array | BS on 1D | Medium     | `O(log N)`      | `O(1)`           | [LC540SingleElementInSortedArray.java](./binarysearch/on1DArrays/LC540SingleElementInSortedArray.java)       |

<br>

#### Binary Search On Answers

| Problem Name                    | Topic         | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                             |
| :------------------------------ | :------------ | :--------- | :-------------- | :--------------- | :---------------------------------------------------------------------------------------- |
| [LC69] Sqrt(x)                  | BS on Answers | Easy       | `O(log N)`      | `O(1)`           | [LC69SquareRoot.java](./binarysearch/onAnswers/LC69SquareRoot.java)                       |
| [GFG] Nth Root of M             | BS on Answers | Easy       | `O(log M)`      | `O(1)`           | [GFG_nthRoot.java](./binarysearch/onAnswers/GFG_nthRoot.java)                             |
| [LC875] Koko Eating Bananas     | BS on Answers | Medium     | `O(N log M)`    | `O(1)`           | [LC875BananaEating.java](./binarysearch/onAnswers/LC875BananaEating.java)                 |
| [SPOJ/GFG] Aggressive Cows      | BS on Answers | Medium     | `O(N log M)`    | `O(1)`           | [GFG_AggressiveCows.java](./binarysearch/onAnswers/GFG_AggressiveCows.java)               |
| [GFG] Allocate Minimum Pages    | BS on Answers | Hard       | `O(N log M)`    | `O(1)`           | [GFG_BookAllocation.java](./binarysearch/onAnswers/GFG_BookAllocation.java)               |
| [LC410] Split Array Largest Sum | BS on Answers | Hard       | `O(N log M)`    | `O(1)`           | [LC410SplitArrayLargestSum.java](./binarysearch/onAnswers/LC410SplitArrayLargestSum.java) |

<br>

#### Binary Search On 2D Matrix

| Problem Name                             | Topic    | Difficulty | Time Complexity         | Space Complexity | Solution Link                                                                        |
| :--------------------------------------- | :------- | :--------- | :---------------------- | :--------------- | :----------------------------------------------------------------------------------- |
| [LC74] Search a 2D Matrix                | BS on 2D | Medium     | `O(log(M*N))`           | `O(1)`           | [LC74SortedMatrixSearch.java](./binarysearch/on2DMatrix/LC74SortedMatrixSearch.java) |
| [LC240] Search a 2D Matrix II            | BS on 2D | Medium     | `O(M + N)`              | `O(1)`           | [LC240MatrixSearch2.java](./binarysearch/on2DMatrix/LC240MatrixSearch2.java)         |
| [GFG] Row with Max 1s                    | BS on 2D | Medium     | `O(M + N)`              | `O(1)`           | [GFG_RowWithMaxOne.java](./binarysearch/on2DMatrix/GFG_RowWithMaxOne.java)           |
| [LC1901] Find a Peak Element II          | BS on 2D | Medium     | `O(M log N)`            | `O(1)`           | [LC1901PeakElement2.java](./binarysearch/on2DMatrix/LC1901PeakElement2.java)         |
| [GFG] Median in a Row-Wise Sorted Matrix | BS on 2D | Hard       | `O(M log(MAX) * log N)` | `O(1)`           | [GFG_MatrixMedian.java](./binarysearch/on2DMatrix/GFG_MatrixMedian.java)             |

---

### 4. Strings

#### Easy

| Problem Name                          | Topic                  | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                 |
| :------------------------------------ | :--------------------- | :--------- | :-------------- | :--------------- | :-------------------------------------------------------------------------------------------- |
| [LC151] Reverse Words in a String     | Strings / Two Pointers | Medium\*   | `O(N)`          | `O(N)`           | [LC151ReverseWords.java](./strings/easy/LC151ReverseWords.java)                               |
| [LC242] Valid Anagram                 | Strings / Hashing      | Easy       | `O(N)`          | `O(1)`           | [LC242Anagram.java](./strings/easy/LC242Anagram.java)                                         |
| [LC796] Rotate String                 | Strings / Matching     | Easy       | `O(N)`          | `O(N)`           | [LC796RotateString.java](./strings/easy/LC796RotateString.java)                               |
| [LC1021] Remove Outermost Parentheses | Strings / Stack        | Easy       | `O(N)`          | `O(N)`           | [LC1021RemoveOutermostParenthesis.java](./strings/easy/LC1021RemoveOutermostParenthesis.java) |
| [LC1903] Largest Odd Number in String | Strings / Math         | Easy       | `O(N)`          | `O(1)`           | [LC1903LargestOddNumInString.java](./strings/easy/LC1903LargestOddNumInString.java)           |

<br>

#### Medium

| Problem Name                             | Topic                   | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                               |
| :--------------------------------------- | :---------------------- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------------------------------ |
| [LC5] Longest Palindromic Substring      | Strings / Expand Center | Medium     | `O(N^2)`        | `O(1)`           | [LC5LongestPalindromicSubstring.java](./strings/medium/LC5LongestPalindromicSubstring.java) |
| [LC1781] Sum of Beauty of All Substrings | Strings / Hashing       | Medium     | `O(N^2)`        | `O(1)`           | [LC1781BeautyOfString.java](./strings/medium/LC1781BeautyOfString.java)                     |

---

### 5. Linked List

#### Singly Linked List (Basics)

| Problem Name                 | Topic                | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                      |
| :--------------------------- | :------------------- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------------------------------------- |
| Convert Array to Linked List | Linked List / Basics | Easy       | `O(N)`          | `O(N)`           | [ArrayToLinkedList.java](./linkedlist/singlylinkedlist/ArrayToLinkedList.java)                     |
| Print Linked List            | Linked List / Basics | Easy       | `O(N)`          | `O(1)`           | [PrintLinkedList.java](./linkedlist/singlylinkedlist/PrintLinkedList.java)                         |
| Find Length of Linked List   | Linked List / Basics | Easy       | `O(N)`          | `O(1)`           | [LinkedListLength.java](./linkedlist/singlylinkedlist/LinkedListLength.java)                       |
| Search in Linked List        | Linked List / Basics | Easy       | `O(N)`          | `O(1)`           | [CheckIfTargetExistsInLL.java](./linkedlist/singlylinkedlist/CheckIfTargetExistsInLL.java)         |
| Insert Node in Singly LL     | Linked List / Basics | Easy       | `O(N)`\*        | `O(1)`           | [InsertionInSinglyLinkedList.java](./linkedlist/singlylinkedlist/InsertionInSinglyLinkedList.java) |
| Delete Node in Singly LL     | Linked List / Basics | Easy       | `O(N)`\*        | `O(1)`           | [DeleteInSinglyLinkedList.java](./linkedlist/singlylinkedlist/DeleteInSinglyLinkedList.java)       |

<br>

#### Singly Linked List (Medium)

| Problem Name                      | Topic                      | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                                                      |
| :-------------------------------- | :------------------------- | :--------- | :-------------- | :--------------- | :--------------------------------------------------------------------------------------------------------------------------------- |
| [LC876] Middle of the Linked List | Linked List / Fast & Slow  | Easy\*     | `O(N)`          | `O(1)`           | [LC876MidOfLL.java](./linkedlist/mediumonsinglylinkedlist/LC876MidOfLL.java)                                                       |
| [LC141] Linked List Cycle         | Linked List / Fast & Slow  | Easy\*     | `O(N)`          | `O(1)`           | [LC141CycleInSinglyLL.java](./linkedlist/mediumonsinglylinkedlist/LC141CycleInSinglyLL.java)                                       |
| [LC142] Linked List Cycle II      | Linked List / Fast & Slow  | Medium     | `O(N)`          | `O(1)`           | [LC142StartOfCycleLengthSinglyLinkedList.java](./linkedlist/mediumonsinglylinkedlist/LC142StartOfCycleLengthSinglyLinkedList.java) |
| [GFG] Length of Loop in LL        | Linked List / Fast & Slow  | Medium     | `O(N)`          | `O(1)`           | [GFG_LengthOfLoopOfLinkedList.java](./linkedlist/mediumonsinglylinkedlist/GFG_LengthOfLoopOfLinkedList.java)                       |
| [LC19] Remove Nth Node From End   | Linked List / Two Pointers | Medium     | `O(N)`          | `O(1)`           | [LC19RemoveNthNode.java](./linkedlist/mediumonsinglylinkedlist/LC19RemoveNthNode.java)                                             |
| [LC160] Intersection of Two LLs   | Linked List / Two Pointers | Easy\*     | `O(N+M)`        | `O(1)`           | [LC160IntersectionInLinkedList.java](./linkedlist/mediumonsinglylinkedlist/LC160InterSectionInLinkedList.java)                     |
| [LC234] Palindrome Linked List    | Linked List / Two Pointers | Easy\*     | `O(N)`          | `O(1)`           | [LC234PalindromicLinkedList.java](./linkedlist/mediumonsinglylinkedlist/LC234PalindromicLinkedList.java)                           |
| [LC328] Odd Even Linked List      | Linked List / Traversal    | Medium     | `O(N)`          | `O(1)`           | [LC_328OddEvenLinkedList.java](./linkedlist/mediumonsinglylinkedlist/LC_328OddEvenLinkedList.java)                                 |
| [LC148] Sort List                 | Linked List / Merge Sort   | Medium     | `O(N log N)`    | `O(1)`           | [LC148SortSinglyLinkedList.java](./linkedlist/mediumonsinglylinkedlist/LC148SortSinglyLinkedList.java)                             |
| [GFG] Add 1 to a Number as LL     | Linked List / Math         | Medium     | `O(N)`          | `O(1)`           | [GFG_AddOneToSinglyLinkedList.java](./linkedlist/mediumonsinglylinkedlist/GFG_AddOneToSinglyLinkedList.java)                       |

<br>

#### Doubly Linked List (Medium)

| Problem Name                            | Topic                    | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                                  |
| :-------------------------------------- | :----------------------- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------------------------------------------------- |
| [GFG] Find Pairs with Given Sum         | Doubly LL / Two Pointers | Medium     | `O(N)`          | `O(1)`           | [GFG_PairSumDoublyLinkedList.java](./linkedlist/mediumondoublylinkedlist/GFG_PairSumDoublyLinkedList.java)     |
| [GFG] Remove All Occurrences of Key     | Doubly LL / Traversal    | Medium     | `O(N)`          | `O(1)`           | [GFG_RemoveAllOccurrencesOfKey.java](./linkedlist/mediumondoublylinkedlist/GFG_RemoveAllOccurrencesOfKey.java) |
| [GFG] Remove Duplicates from Sorted DLL | Doubly LL / Traversal    | Easy\*     | `O(N)`          | `O(1)`           | [GFG_RemoveDuplicates.java](./linkedlist/mediumondoublylinkedlist/GFG_RemoveDuplicates.java)                   |

<br>

#### Hard Problems

| Problem Name                          | Topic                      | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                |
| :------------------------------------ | :------------------------- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------------------------------- |
| [LC25] Reverse Nodes in k-Group       | Linked List / Reversal     | Hard       | `O(N)`          | `O(1)`           | [LC25ReverseLinkedListInKGroups.java](./linkedlist/hard/LC25ReverseLinkedListInKGroups.java) |
| [LC61] Rotate List                    | Linked List / Two Pointers | Medium\*   | `O(N)`          | `O(1)`           | [LC61RotateSinglyListToRightByK.java](./linkedlist/hard/LC61RotateSinglyListToRightByK.java) |
| [LC138] Copy List with Random Pointer | Linked List / Deep Copy    | Medium\*   | `O(N)`          | `O(1)`           | [LC138DeepCopyRandomPointers.java](./linkedlist/hard/LC138DeepCopyRandomPointers.java)       |
| [GFG] Flattening a Linked List        | Linked List / Merge        | Hard       | `O(N * M)`      | `O(1)`           | [GFG_FlattenLinkedList.java](./linkedlist/hard/GFG_FlattenLinkedList.java)                   |

---

### 6. Recursion

#### Basics

| Problem Name                   | Topic               | Difficulty | Time Complexity | Space Complexity | Solution Link                                                       |
| :----------------------------- | :------------------ | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------ |
| [LC8] String to Integer (atoi) | Recursion / Strings | Medium     | `O(N)`          | `O(N)`           | [LC8_AtoiRecursion.java](./recursion/basics/LC8_AtoiRecursion.java) |
| [GFG] Sort a Stack             | Recursion / Stack   | Medium     | `O(N^2)`        | `O(N)`           | [GFG_SortStack.java](./recursion/basics/GFG_SortStack.java)         |

<br>

#### Subsequence Patterns

| Problem Name                                 | Topic                    | Difficulty | Time Complexity       | Space Complexity | Solution Link                                                                                                                                |
| :------------------------------------------- | :----------------------- | :--------- | :-------------------- | :--------------- | :------------------------------------------------------------------------------------------------------------------------------------------- |
| [Code360] Binary Strings w/o Consecutive 1s  | Recursion / Backtracking | Medium     | `O(2^N)`              | `O(N)`           | [Code360_GenerateBinaryStringWithoutConsecutive1.java](./recursion/subsequencepatterns/Code360_GenerateBinaryStringWithoutConsecutive1.java) |
| [GFG] Subsequence Sum Target                 | Recursion / Subsequences | Medium     | `O(2^N)`              | `O(N)`           | [GFG_SubsequenceSumTarget.java](./recursion/subsequencepatterns/GFG_SubsequenceSumTarget.java)                                               |
| [GFG] Count Subsequences with Sum K          | Recursion / Subsequences | Medium     | `O(2^N)`              | `O(N)`           | [CountSubsequenceWithSumK.java](./recursion/subsequencepatterns/CountSubsequenceWithSumK.java)                                               |
| [GFG] Subset Sums                            | Recursion / Subsequences | Medium     | `O(2^N)`              | `O(2^N)`         | [GFG_SubsetSums.java](./recursion/subsequencepatterns/GFG_SubSetSums.java)                                                                   |
| [LC90] Subsets II                            | Recursion / Backtracking | Medium     | `O(2^N * N)`          | `O(N)`\*         | [LC90SubSetsSum2.java](./recursion/subsequencepatterns/LC90SubSetsSum2.java)                                                                 |
| [LC39] Combination Sum                       | Recursion / Backtracking | Medium     | `O(2^T)`              | `O(T)`           | [LC39CombinationSum.java](./recursion/subsequencepatterns/LC39CombinationSum.java)                                                           |
| [LC40] Combination Sum II                    | Recursion / Backtracking | Medium     | `O(2^N)`              | `O(N)`\*         | [LC40CombinationSum_2.java](./recursion/subsequencepatterns/LC40CombinationSum_2.java)                                                       |
| [LC216] Combination Sum III                  | Recursion / Backtracking | Medium     | `O(9! / (K!*(9-K)!))` | `O(K)`           | [LC216CombinationSum3.java](./recursion/subsequencepatterns/LC216CombinationSum3.java)                                                       |
| [LC17] Letter Combinations of a Phone Number | Recursion / Backtracking | Medium     | `O(4^N * N)`          | `O(N)`           | [LC17_LetterCombinations.java](./recursion/subsequencepatterns/LC17_LetterCombinations.java)                                                 |
| [LC22] Generate Parentheses                  | Recursion / Backtracking | Medium     | `O(4^N / sqrt(N))`    | `O(N)`           | [LC22GenerateParenthesis.java](./recursion/subsequencepatterns/LC22GenerateParenthesis.java)                                                 |

<br>

#### Hard Backtracking Problems

| Problem Name                    | Topic                  | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                          |
| :------------------------------ | :--------------------- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------------------------- |
| [LC51] N-Queens                 | Backtracking           | Hard       | `O(N!)`         | `O(N²)`          | [LC51NQueens.java](./recursion/hard/LC51NQueens.java)                                  |
| [LC131] Palindrome Partitioning | Backtracking / Strings | Medium     | `O(N · 2^N)`    | `O(N)`           | [LC131PalindromePartitioning.java](./recursion/hard/LC131PalindromePartiotioning.java) |
| [LC139] Word Break              | Backtracking + DP      | Medium     | `O(N^2 * M)`    | `O(N)`           | [LC139WordBreak.java](./recursion/hard/LC139WordBreak.java)                            |
| [GFG] Rat In A Maze             | Backtracking / Strings | Medium     | `O(3^(N ^ 2))`  | `O(N^2)`         | [GFG_RatInMaze.java](./recursion/hard/GFG_RatInMaze.java)                              |
| [LC37] Sudoku Solver            | Backtracking           | Hard       | `O(9^(N^2))`    | `O(N^2)`         | [LC37SudokuSolver](./recursion/hard/LC37SudokuSolver.java)                             |

---

### 7. Stack and Queue

#### Implementation

| Problem Name      | Topic        | Difficulty | Time Complexity | Space Complexity | Solution Link                                                        |
| :---------------- | :----------- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------- |
| Stack Using Array | Stack/Array  | Easy       | `O(1)`          | `O(N)`           | [StackUsingArray.java](./stack/implementation/StackUsingArray.java)  |
| Stack Using Queue | Stack /Queue | Easy       | `O(N)`          | `O(N)`           | [StackUsingQueue.java](./stack/implementation/StackUsingQueue.java)  |
| Queue Using Array | Queue /Array | Easy       | `O(1)`          | `O(N)`           | [QueueUsingArray.java](./queue/implementation/QueueUsingArray.class) |

<br>

#### Easy

| Problem Name             | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link                                                  |
| :----------------------- | :---- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------- |
| [LC20] Valid Parentheses | Stack | Easy       | `O(N)`          | `O(N)`           | [LC20ValidParentheses.java](./stack/LC20ValidParentheses.java) |
| [LC155] MinStack         | Stack | Medium     | `O(1)`          | `O(N)`           | [LC155MinStack.java.java](./stack/LC155MinStack.java)          |

<br>

#### Monotonic Stack

| Problem Name                     | Topic                            | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                            |
| :------------------------------- | :------------------------------- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------------------------------------------- |
| [GFG] Next Smaller Element       | Stack / Monotonic Stack          | Easy       | `O(N)`          | `O(N)`           | [GFG_NextSmallerElement.java](./stack/monotonicstack/GFG_NextSmallerElement.java)                        |
| [LC42] Trapping Rain Water       | Stack / Monotonic Stack          | Hard       | `O(N)`          | `O(N)`           | [LC42TrappingRainWater.java](./stack/monotonicstack/LC42TrappingRainWater.java)                          |
| [LC496] Next Greater Element I   | Stack / Monotonic Stack          | Easy       | `O(N + M)`      | `O(M)`           | [LC496NextGreterElement.java](./stack/monotonicstack/LC496NextGreterElement.java)                        |
| [LC503] Next Greater Element II  | Stack / Monotonic Stack          | Medium     | `O(N)`          | `O(N)`           | [LC503NextGreterElement2.java](./stack/monotonicstack/LC503NextGreterElement2.java)                      |
| [LC907] Sum of Subarray Minimums | Stack / Monotonic Stack          | Medium     | `O(N)`          | `O(N)`           | [LC907SumOfSubArrayMinimums.java](./stack/monotonicstack/LC907SumOfSubArrayMinimums.java)                |
| [LC735] Asteroid Collisions      | Stack / Monotonic Stack          | Medium     | `O(N)`          | `O(N)`           | [LC735AsteroidCollisions.java](./stack/monotonicstack/LC735AsteroidCollisions.java)                      |
| [LC2104] Sum of Subarray Ranges  | Stack / Monotonic Stack          | Medium     | `O(N)`          | `O(N)`           | [LC2104SumOFSubArrayRange.java](./stack/monotonicstack/LC2104SumOFSubArrayRange.java)                    |
| [LC402] Remove K degits          | Stack / Monotonic Stack / String | Medium     | `O(N)`          | `O(N)`           | [LC402RemoveKDigits.java](./stack/monotonicstack/LC402RemoveKDigits.java)                                |
| [LC84] Largest Area Histogram    | Stack / Monotonic Stack          | Hard       | `O(N)`          | `O(N)`           | [LC84LargestReactangeAreaHistogram.java](./stack/monotonicstack/LC84LargestReactangleAreaHistogram.java) |
| [LC85] Maximal Rectangle Area    | Stack / Monotonic Stack / Matrix | Hard       | `O(N * M)`      | `O(M)`           | [LC85MaximalAreaRectangle.java](./stack/monotonicstack/LC85MaximalAreaRectangle.java)                    |

#### Queue hard problem

| Problem Name                   | Topic           | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                 |
| :----------------------------- | :-------------- | :--------- | :-------------- | :--------------- | :---------------------------------------------------------------------------- |
| [LC239] Sliding Window Maximum | Monotonic Deque | Hard       | `O(N)`          | `O(K)`           | [LC239SlidingWindowMaximum.java](./queue/hard/LC239SlidingWindowMaximum.java) |

#### Stack hard problems

| Problem Name                | Topic           | Difficulty | Time Complexity | Space Complexity | Solution Link                                                             |
| :-------------------------- | :-------------- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------------ |
| [LC901] Online Stock Span   | Monotonic Stack | Medium     | `O(1)` amort.   | `O(N)`           | [LC901StockSpanner.java](./stack/hard/LC901StockSpanner.java)             |
| [GFG] The Celebrity Problem | Monotonic Stack | Medium     | `O(N*N)` amort. | `O(N)`           | [GFG_TheCelebrityProblem.java](./stack/hard/GFG_TheCelebrityProblem.java) |

---

### 8. Design Problems

#### LRU cache

| Problem Name      | Topic         | Difficulty | Time Complexity | Space Complexity | Solution Link                                     |
| :---------------- | :------------ | :--------- | :-------------- | :--------------- | :------------------------------------------------ |
| [LC146] LRU cache | DLL / HashMap | Medium     | `O(1)` amort.   | `O(N)`           | [LC146LRUCache.java](./design/LC146LRUCache.java) |

---

### 9. Sliding Window and Two Pointer

#### Medium Problems

| Problem Name                                                  | Topic          | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                                                                 |
| :------------------------------------------------------------ | :------------- | :--------- | :-------------- | :--------------- | :-------------------------------------------------------------------------------------------------------------------------------------------- |
| [LC3] Longest Substring Without Repeating Characters          | Sliding Window | Medium     | `O(N)`          | `O(1)`           | [LC3LongestSubstringWithoutRepeatingCharacters.java](./slidingwindow/medium/LC3LongestSubstringWithoutRepeatingCharacters.java)               |
| [LC1004] Max Consecutive Ones III                             | Sliding Window | Medium     | `O(N)`          | `O(1)`           | [LC1004MaxConsecutiveOnesIII.java](./slidingwindow/medium/LC1004MaxConsecutiveOnesIII.java)                                                   |
| [LC904] Fruit Into Baskets                                    | Sliding Window | Medium     | `O(N)`          | `O(1)`           | [LC904FruitIntoBaskets.java](./slidingwindow/medium/LC904FruitIntoBaskets.java)                                                               |
| [LC424] Longest Repeating Character Replacement               | Sliding Window | Medium     | `O(N)`          | `O(1)`           | [LC424LongestRepeatingCharacterReplacement.java](./slidingwindow/medium/LC424LongestRepeatingCharacterReplacement.java)                       |
| [LC930] Binary Subarrays With Sum                             | Sliding Window | Medium     | `O(N)`          | `O(1)`           | [LC930BinarySubarraysWithSum.java](./slidingwindow/medium/LC930BinarySubarraysWithSum.java)                                                   |
| [LC1248] Count Number of Nice Subarrays                       | Sliding Window | Medium     | `O(N)`          | `O(1)`           | [LC1248CountNumberOfNiceSubarrays.java](./slidingwindow/medium/LC1248CountNumberOfNiceSubarrays.java)                                         |
| [LC1358] Number of Substrings Containing All Three Characters | Sliding Window | Medium     | `O(N)`          | `O(1)`           | [LC1358NumberOfSubstringsContainingAllThreeCharacters.java](./slidingwindow/medium/LC1358NumberOfSubstringsContainingAllThreeCharacters.java) |
| [LC1423] Maximum Points You Can Obtain From Cards             | Sliding Window | Medium     | `O(N)`          | `O(1)`           | [LC1423MaxPointsFromCards.java](./slidingwindow/medium/LC1423MaxPointsFromCards.java)                                                         |

<br>

#### Hard Problems

| Problem Name                                | Topic          | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                                             |
| :------------------------------------------ | :------------- | :--------- | :-------------- | :--------------- | :-------------------------------------------------------------------------------------------------------- |
| [GFG] Longest Substring with K Uniques      | Sliding Window | Medium     | `O(N)`          | `O(1)`           | [GFG_LongestSubstringWithKUniques.java](./slidingwindow/hard/GFG_LongestSubstringWithKUniques.java)       |
| [LC992] Subarrays with K Different Integers | Sliding Window | Hard       | `O(N)`          | `O(N)`           | [LC992SubarraysWithKDistinctIntegers.java](./slidingwindow/hard/LC992SubarraysWithKDistinctIntegers.java) |
| [LC76 ] Minimum Window Substring            | Sliding Window | Hard       | `O(N + M)`      | `O(1)`           | [LC76MinimumWindowSubstring.java](./slidingwindow/hard/LC76MinimumWindowSubstring.java)                   |

---

### 10. Heaps

#### Medium Problems Heaps

| Problem Name                             | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                |
| ---------------------------------------- | ----- | ---------- | --------------- | ---------------- | ---------------------------------------------------------------------------- |
| [LC215] Kth Smallest Element in an Array | Heap  | Medium     | `O(n log k)`    | ``O(k)`          | [BASIC] [FindKthSmallest.java](./heap/medium/Basic_KthSmallestElement.java)  |
| [LC215] Kth Largest Element in an Array  | Heap  | Medium     | `O(n log k)`    | `O(k)`           | [LC215FindKthLargest.java](./heap/medium/LC215FindKthLargest.java)           |
| [LC215] Merge K Sorted Lists             | Heap  | Medium     | `O(n log k)`    | `O(k)`           | [LC215FindKthLargest.java](./heap/medium/LC23MergeKSortedLists.java)         |
| [LC1331] Array Rank Transform            | Heap  | Medium     | `O(N log N)`    | `O(N)`           | [LC1331ArrayRankTransform.java](./heap/medium/LC1331RankTransformArray.java) |
| [LC846] Hand of Straights                | Heap  | Medium     | `O(N² / k)`     | `O(N)`           | [LC846IsNStraightHand.java](./heap/medium/LC846HandOfStraights.java)         |

<br>

#### Hard Problems Heap

| Problem Name                            | Topic                 | Difficulty | Time Complexity         | Space Complexity | Solution Link                                                                |
| --------------------------------------- | --------------------- | ---------- | ----------------------- | ---------------- | ---------------------------------------------------------------------------- |
| [GFG] Minimum Cost to Connect Ropes     | Greedy / Heap         | Hard       | `O(n log n)`            | `O(n)`           | [GFG_MinCostToConnectRopes.java](./heap/hard/GFG_MinCostToConnectRopes.java) |
| [LC703] Kth Largest Element in a Stream | Heap / Priority Queue | Easy       | `O(n log k) / O(log k)` | `O(k)`           | [LC703KthLargest.java](./heap/hard/LC703KthLargest.java)                     |
| [GFG] Maximum Sum Combination           | Heap / Priority Queue | Medium     | `O(n² log k)`           | `O(k)`           | [GFG_MaximumSumCombination.java](./heap/hard/GFG_MaximumSumCombination.java) |
| [LC658] Find K Closest Elements         | Heap / Priority Queue | Medium     | `O(log n + k log k)`    | `O(k)`           | [LC658FindKClosestElements.java](./heap/hard/LC658FindKClosestElements.java) |
| [LC347] Top K Frequent Elements         | Heap / Priority Queue | Medium     | `O(n log k)`            | `O(n)`           | [LC347TopKFrequentElements.java](./heap/hard/LC347TopKFrequentElements.java) |

---

### 11. Tree

#### Traversal

| Problem Name          | Topic    | Difficulty | Time Complexity | Space Complexity | Solution Link                                                         |
| :-------------------- | :------- | :--------- | :-------------- | :--------------- | :-------------------------------------------------------------------- |
| Level Order Traversal | Tree/BFS | Easy       | `O(N)`          | `O(N)`           | [LevelOrderTraversal.java](./tree/traversal/LevelOrderTraversal.java) |
| Inorder Traversal     | Tree/DFS | Easy       | `O(N)`          | `O(H)`           | [InorderTraversal.java](./tree/traversal/InorderTraversal.java)       |
| Inorder Traversal     | Tree/DFS | Easy       | `O(N)`          | `O(H)`           | [InorderTraversal.java](./tree/traversal/InorderTraversal.java)       |
| Preorder Traversal    | Tree/DFS | Easy       | `O(N)`          | `O(H)`           | [PreorderTraversal.java](./tree/traversal/PreorderTraversal.java)     |

<br>

#### Binary Tree Medium Problems

| Problem Name                                      | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                          |
| :------------------------------------------------ | :---- | :--------- | :-------------- | :--------------- | :------------------------------------------------------------------------------------- |
| [LC104] MaximumDepthOfBinaryTree                  | Tree  | Easy       | `O(n)`          | `O(h)`           | [LC104MaximumDepthOfBinaryTree](./tree/medium/LC104MaximumDepthOfBinaryTree.java)      |
| [LC110] Balanced Binary Tree                      | Tree  | Easy       | `O(n)`          | `O(h)`           | [LC110BalancedBinaryTree.java](./tree/medium/LC110BalancedBinaryTree.java)             |
| [LC543] Diameter of Binary Tree                   | Tree  | Easy       | `O(n)`          | `O(h)`           | [LC543DiameterOfBinaryTree.java](./tree/medium/LC543DiameterOfBinaryTree.java)         |
| [LC124] Binary Tree Maximum Path Sum              | Tree  | Hard       | `O(n)`          | `O(h)`           | [LC124BinaryTreeMaximumPathSum.java](./tree/medium/LC124BinaryTreeMaximumPathSum.java) |
| [LC100] Same Tree                                 | Trees | Easy       | `O(n)`          | `O(h)`           | [LC100SameTree.java](./tree/medium/LC100SameTree.java)                                 |
| [LC103] Binary Tree Zigzag Level Order Traversal  | Trees | Medium     | `O(n)`          | `O(n)`           | [LC103ZigzagLevelOrder.java](./tree/medium/LC103ZigzagLevelOrder.java)                 |
| [GFG] Boundary Traversal of Binary Tree           | Trees | Medium     | `O(n)`          | `O(n)`           | [GFG_BoundaryTraversal.java](./tree/medium/GFG_BoundaryTraversal.java)                 |
| [LC987] Vertical Order Traversal of a Binary Tree | Trees | Hard       | `O(n log n)`    | `O(n)`           | [LC987VerticalOrderTraversal.java](./tree/medium/LC987VerticalOrderTraversal.java)     |
| [LC101] Symmetric Tree                            | Trees | Easy       | `O(N)`          | `O(H)`           | [LC101SymmetricTree.java](./tree/medium/LC101SymmetricTree.java)                       |

<br>

#### Binary Tree Views

| Problem Name                        | Topic | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                      |
| :---------------------------------- | :---- | :--------- | :-------------- | :--------------- | :--------------------------------------------------------------------------------- |
| [GFG] Top View of Binary Tree       | Trees | Medium     | `O(N log N)`    | `O(N)`           | [GFG_TopViewOfBinaryTree.java](./tree/view/GFG_TopViewOfBinaryTree.java)           |
| [GFG] Bottom View of Binary Tree    | Trees | Medium     | `O(N log N)`    | `O(N)`           | [GFG_BottomViewOfBinaryTree.java](./tree/view/GFG_BottomViewOfBinaryTree.java)     |
| [LC199] Binary Tree Right Side View | Trees | Medium     | `O(N)`          | `O(H)`           | [LC199BinaryTreeRightSideView.java](./tree/view/LC199BinaryTreeRightSideView.java) |

<br>

#### Binary Tree hard problems

| Problem Name                                    | Topic       | Difficulty | Time Complexity | Space Complexity | Solution Link                                                                        |
| :---------------------------------------------- | :---------- | :--------- | :-------------- | :--------------- | :----------------------------------------------------------------------------------- |
| [LC257] Binary Tree Paths                       | Trees       | Easy       | `O(N * H)`      | `O(H)`           | [LC257BinaryTreePaths.java](./tree/hard/LC257BinaryTreePaths.java)                   |
| [LC236] Lowest Common Ancestor of a Binary Tree | Trees       | Medium     | `O(N)`          | `O(H)`           | [LC236LowestCommonAncestor.java](./tree/hard/LC236LowestCommonAncestor.java)         |
| [LC662] Maximum Width of Binary Tree            | Trees + BFS | Medium     | `O(N)`          | `O(W)`           | [LC662MaximumWidthOfBinaryTree.java](./tree/hard/LC662MaximumWidthOfBinaryTree.java) |
| [GFG] Children Sum Property                     | Tree        | Hard       | `O(n)`          | `O(h)`           | [GFG_ChildrenSumProperty.java](./tree/hard/GFG_ChildrenSumProperty.java)             |

---

## Repository Structure

```
dsa-solutions-java/
├── arrays/
├── sorting/
├── binarysearch/
├── strings/
├── linkedlist/
├── recursion/
├── stack/
├── queue/
├── slindingwindow/
├── heaps/      ✅ Done
├── tree/       ← current focus
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

| Topic               | Status         |
| :------------------ | :------------- |
| Basics              | ✅ Done        |
| Arrays              | ✅ Done        |
| Sorting             | ✅ Done        |
| Binary Search       | ✅ Done        |
| Strings             | ✅ Done        |
| Linked Lists        | ✅ Done        |
| Recursion           | ✅ Done        |
| Stack & Queue       | ✅ Done        |
| Sliding window      | ✅ Done        |
| Heap                | ✅ Done        |
| Trees               | 🔄 In Progress |
| Graphs              | ⏳ Pending     |
| Dynamic Programming | ⏳ Pending     |

---

## Current Focus — Heaps

Having completed revision of **Basics, Arrays, Sorting, Binary Search, Strings, Linked Lists, Recursion, stack, queue, Sliding Window, Heaps**, the focus now shifts to **Trees**.

Topics to be covered:**Tree**

- Basics of tree
- Traversal both resursive and Iterative
- Height and Width based problem patterns on Tree
- View Based pattern problems
- Tree hard problems

---

## Goal

Build strong DSA fundamentals and problem-solving speed to target backend-focused and product-based company roles.
