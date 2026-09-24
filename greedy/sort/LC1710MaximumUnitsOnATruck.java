package greedy.sort;
// Created at: 25-September-2026

// Last revised at: 25-September-2026
// Link: https://leetcode.com/problems/maximum-units-on-a-truck/

/*
Problem Description:
--------------------
Statement:
You are given boxTypes where boxTypes[i] = [numberOfBoxes, unitsPerBox].

Each box type contains a certain number of boxes, and every box of that
type contains the same number of units.

A truck can carry at most truckSize boxes.

Return the maximum total number of units that can be loaded onto the truck.

Example:
Input:
boxTypes = [[1,3],[2,2],[3,1]]
truckSize = 4

Output:
8

Explanation:
Take 1 box with 3 units and 2 boxes with 2 units each, plus 1 box
with 1 unit.

Total = 3 + 2 + 2 + 1 = 8.

Constraints:
- 1 <= boxTypes.length <= 1000
- 1 <= numberOfBoxes <= 1000
- 1 <= unitsPerBox <= 1000
- 1 <= truckSize <= 10^6
*/

/*
Approach 1: Brute Force

Idea:
Try different combinations of box types while respecting the truck capacity
and select the combination that gives the maximum number of units.

Time Complexity:
Exponential in the number of box types.

Space Complexity:
O(n)

Drawbacks:
Exploring combinations is unnecessary because every box has a clear value
based on its units per box.
*/

/*
Approach 2: Greedy + Sorting

Idea:
Sort box types by units per box in descending order.

Take as many boxes as possible from the type with the highest units per box,
then move to the next type.

For each type:
- Determine how many boxes can fit.
- Add their units to the result.
- Reduce the remaining truck capacity.

A box with more units per box always contributes at least as much as a box
with fewer units, so processing higher-value boxes first maximizes the total.

Time Complexity:
O(n log n)

Space Complexity:
O(n) for sorting implementation overhead.

Drawbacks:
The input array is modified by sorting.

Key Insight:
Prioritize the box type with the highest units per box because every truck
slot should be filled with the highest available unit value.
*/

/*
Method to Solve:
----------------
1. Sort box types by units per box in descending order.
2. Start with the full truck capacity.
3. Take as many boxes as possible from the current type.
4. Add the corresponding units to the total.
5. Reduce the remaining truck capacity.
6. Continue until all box types are processed or the truck is full.
7. Return the total units.
*/

// Time Complexity: O(n log n)
// Space Complexity: O(n) sorting implementation overhead

import java.util.Arrays;

public class LC1710MaximumUnitsOnATruck {

    /**
     * Calculates the maximum number of units that can be loaded onto the truck.
     *
     * @param boxTypes  box types containing number of boxes and units per box
     * @param truckSize maximum number of boxes the truck can carry
     * @return maximum number of units that can be loaded
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> Integer.compare(b[1], a[1]));

        int unitsFilled = 0;

        for (int[] boxType : boxTypes) {
            if (truckSize == 0) {
                break;
            }

            int count = boxType[0];
            int units = boxType[1];

            int allowed = Math.min(truckSize, count);

            unitsFilled += allowed * units;
            truckSize -= allowed;
        }

        return unitsFilled;
    }
}