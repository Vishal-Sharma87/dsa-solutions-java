package stack.hard;

import java.util.Stack;

// Created at: 19-April-2026
// Last revised at: 19-April-2026
// https://leetcode.com/problems/online-stock-span/

/*
 * Problem Description:
 *
 * Design a class that collects daily stock prices and returns the span of the
 * current day's price. The span is defined as the number of consecutive days
 * (including today) for which the price was less than or equal to today's price.
 *
 * Example:
 *   Prices fed in order: [100, 80, 60, 70, 60, 75, 85]
 *
 *   next(100) -> 1   (no previous days)
 *   next(80)  -> 1   (100 > 80, stops immediately)
 *   next(60)  -> 1   (80 > 60)
 *   next(70)  -> 2   (60 <= 70, but 80 > 70)
 *   next(60)  -> 1   (70 > 60)
 *   next(75)  -> 4   (60 <= 75, 70 <= 75, 60 <= 75, but 80 > 75)
 *   next(85)  -> 6   (75 <= 85, then absorbs its accumulated span of 4, etc.)
 *
 * Constraints:
 *   1 <= price <= 10^5
 *   At most 10^4 calls to next()
 */

/*
 * Approaches:
 *
 * 1. Brute Force
 *    Idea     : Store all prices seen so far. For each new price, walk backwards
 *               and count consecutive days with price <= today.
 *    Time     : O(n) per call, O(n^2) overall
 *    Space    : O(n)
 *    Drawback : Re-scans already-dominated days on every call.
 *
 * ★ 2. Monotonic Stack with Span Accumulation
 *    Idea      : Maintain a stack of (price, span) pairs in strictly decreasing
 *                order of price. When a new price arrives, pop all pairs whose
 *                price <= current and absorb their spans. The popped days are
 *                guaranteed to never block any future price again, so they're
 *                safe to collapse. Push (currentPrice, accumulatedSpan).
 *    Time      : O(1) amortised per call — each day is pushed and popped once.
 *    Space     : O(n) — stack holds at most n pairs across all calls.
 *    Key Insight: Storing the span alongside the price lets us skip over already-
 *                 processed runs in O(1) instead of re-walking them.
 */

/*
 * Method to solve (Monotonic Stack with Span Accumulation):
 *
 * Initialisation:
 *   1. Create an empty stack of Pair(price, span).
 *
 * next(price):
 *   1. Start span = 1 (today always counts).
 *   2. While stack is not empty and top.price <= price:
 *      a. Pop the top pair and add its span to current span.
 *   3. Push Pair(price, span) onto the stack.
 *   4. Return span.
 */

class Pair {
    int price;
    int span;

    public Pair(int p, int s) {
        this.price = p;
        this.span = s;
    }
}

class LC901StockSpanner {

    private Stack<Pair> st;

    public LC901StockSpanner() {
        this.st = new Stack<>();
    }

    /**
     * Accepts today's stock price and returns its span.
     * Span = number of consecutive days up to and including today
     * where the price was <= today's price.
     *
     * @param price today's stock price
     * @return the span for today
     */
    public int next(int price) {
        int span = 1; // today itself

        // absorb all days that are now dominated by today's price
        while (!st.isEmpty() && st.peek().price <= price)
            span += st.pop().span;

        st.push(new Pair(price, span));
        return span;
    }
}

/*
 * Your StockSpanner object will be instantiated and called as such:
 * LC901StockSpanner obj = new LC901StockSpanner();
 * int param_1 = obj.next(price);
 */

// Time Complexity : O(1) amortised per next() call — each price is pushed and
// popped at most once
// Space Complexity : O(n) — stack holds at most one pair per day seen so far