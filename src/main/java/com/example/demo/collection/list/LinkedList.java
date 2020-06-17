package com.example.demo.collection.list;

import java.util.Objects;

public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    protected class Node {
        private LinkedList linkedList;
        private Node pre;
        private Node next;
        private Object object;

        private Node(LinkedList linkedList, Object object) {
            this.linkedList = linkedList;
            this.object = object;
        }
    }

    public Node createNode(LinkedList linkedList, Object object) {
        return new Node(linkedList, object);
    }

    public void addToTail(Object object) {
        Node node = createNode(this, object);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
        size++;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(6 * size);
        sb.append("[");
        if (head != null) {
            sb.append(head.object.toString());
        }
        Node node = head.next;
        while (node != null) {
            sb.append(",").append(node.object.toString());
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList that = (LinkedList) o;
        return size == that.size &&
                head.equals(that.head) &&
                tail.equals(that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail, size);
    }
}