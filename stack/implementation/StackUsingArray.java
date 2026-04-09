package stack.implementation;

import exception.StackIsEmptyException;
import exception.StackIsFullException;

public class StackUsingArray {

    private int topIndex;

    private int[] stack;

    private int capacity;

    private int stackSize;

    public StackUsingArray(int capacity) {
        if (capacity <= 0)
            throw new RuntimeException("Stack size must be positive");

        this.capacity = capacity;
        this.stack = new int[capacity];
        this.topIndex = 0;
        this.stackSize = 0;
    }

    // push
    public void push(int data) {
        if (stackSize == capacity)
            throw new StackIsFullException("Stack is full, cannot perfoem push operation");

        stack[topIndex] = data;
        topIndex++;
        stackSize++;
    }

    // pop
    public int pop() {
        if (stackSize == 0)
            throw new StackIsEmptyException("Unable to pop, Stack is Empty");

        int poppedElement = stack[topIndex];
        topIndex--;
        stackSize--;
        return poppedElement;
    }

    // top
    public int top() {
        if (stackSize == 0)
            throw new StackIsEmptyException("Unable to get top, Stack is Empty");

        return stack[topIndex];
    }

    public int size() {
        return stackSize;
    }

    // isEmpty
    public boolean isEmpty() {
        return stackSize == 0;
    }

    // isFull
    public boolean isFull() {
        return stackSize == capacity;
    }

}
