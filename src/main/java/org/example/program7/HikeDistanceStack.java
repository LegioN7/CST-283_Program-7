package org.example.program7;

/**
 * The HikeDistanceStack class represents a stack of HikeDistance objects.
 * It provides methods to manipulate the stack such as push, pop, peek, and clear.
 */
public class HikeDistanceStack {

    /**
     * The array to store the HikeDistance objects.
     */
    final HikeDistance[] stackArray;

    /**
     * The size of the stack.
     */
    private final int stackSize;

    /**
     * The index of the top element in the stack.
     */
    private int top;

    /**
     * Constructs a HikeDistanceStack with the specified size.
     *
     * @param size the size of the stack
     */
    public HikeDistanceStack(int size) {
        stackArray = new HikeDistance[size];
        stackSize = size;
        top = 0;
    }

    /**
     * Pushes a new HikeDistance object onto the stack.
     *
     * @param newItem the new HikeDistance object to be pushed onto the stack
     */
    public void push(HikeDistance newItem) {
        stackArray[top] = newItem;
        top++;
    }

    /**
     * Pops the top HikeDistance object from the stack.
     *
     * @return the popped HikeDistance object
     */
    public HikeDistance pop() {
        top--;
        return stackArray[top];
    }

    /**
     * Peeks at the top HikeDistance object in the stack.
     *
     * @return the top HikeDistance object in the stack
     */
    public HikeDistance peek() {
        if (isEmpty()) {
            return null;
        }
        return stackArray[top - 1];
    }

    /**
     * Clears the stack.
     */
    public void clear() {
        top = 0;
    }

    /**
     * Checks if the stack is full.
     *
     * @return true if the stack is full, false otherwise
     */
    public boolean isFull() {
        return top == stackSize;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Returns the index of the top element in the stack.
     *
     * @return the index of the top element in the stack
     */
    public int getTop() {
        return top;
    }
}