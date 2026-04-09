package stack.implementation;

import exception.StackIsEmptyException;
import exception.StackIsFullException;
import queue.implementation.QueueUsingArray;

public class StackUsingQueue {
    private QueueUsingArray queue;

    private int topElement;

    public StackUsingQueue(int capacity) {
        if (capacity <= 0)
            throw new RuntimeException("Capacity must be positive");

        this.queue = new QueueUsingArray(capacity);

        // acts as null
        this.topElement = -1;
    }

    // O(1)
    public void push(int data) {
        if (queue.isFull())
            throw new StackIsFullException("Stack is Full, cannot perform push operation");

        topElement = data;

        queue.enqueue(data);
    }

    // pop O(n)
    public int pop() {
        if (queue.isEmpty())
            throw new StackIsEmptyException("Stack is Empty cannot perform pop operation");

        int currSize = queue.size();

        while (currSize > 1) {
            int elementToShift = queue.dequeue();

            topElement = elementToShift;

            queue.enqueue(elementToShift);

            currSize--;
        }

        return queue.dequeue();
    }

    // top O(1)
    public int top() {
        if (queue.isEmpty())
            throw new StackIsEmptyException("Stack is Empty cannot perform top operation");

        return topElement;
    }

    // isEmpty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // isFull
    public boolean isFull() {
        return queue.isFull();
    }

    // size
    public int size() {
        return queue.size();
    }

}
