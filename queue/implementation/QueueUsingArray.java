package queue.implementation;

import exception.QueueIsEmptyException;
import exception.QueueIsFullException;

public class QueueUsingArray {

    private int[] queue;

    private int capacity;

    private int frontIndex;

    private int rearIndex;

    private int queueSize;

    public QueueUsingArray(int capacity) {

        if (capacity <= 0)
            throw new RuntimeException("Queue size must be positive");

        this.capacity = capacity;

        this.queue = new int[capacity];

        this.frontIndex = 0; // index of next element to pop

        this.rearIndex = 0; // index of next element to insert

        this.queueSize = 0;
    }

    // enqueue
    public void enqueue(int data) {
        if (queueSize == capacity)
            throw new QueueIsFullException("Queue is at capacity, can't enqueu more elements");

        queueSize++;

        queue[rearIndex] = data;

        rearIndex = (rearIndex + 1) % capacity;
    }

    // dequeue
    public int dequeue() {
        if (queueSize == 0)
            throw new QueueIsEmptyException("Queue is empty, can't dequeue");

        queueSize--;

        int dequeuedElement = queue[frontIndex];

        frontIndex = (frontIndex + 1) % capacity;

        if (queueSize == 0) {
            // reset pointers
            frontIndex = 0;
            rearIndex = 0;
        }

        return dequeuedElement;
    }

    // peek
    public int peek() {
        if (queueSize == 0)
            throw new QueueIsEmptyException("Queue is empty, No element to peek");

        return queue[frontIndex];
    }

    // size
    public int size() {
        return queueSize;
    }

    // isEmpty
    public boolean isEmpty() {
        return queueSize == 0;
    }

    // isFull
    public boolean isFull() {
        return queueSize == capacity;
    }

}
