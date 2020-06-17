package com.example.demo.queue;/**
 * @Author handa
 * Description:
 * Date: Created in 10:42 2020/1/13
 * Modified By:
 */


import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/13 10:42
 */
public class ArrayQueue<T> {
    private T[] queue;
    private int head;//头部
    private int tail;//尾部
    private int n;

    public ArrayQueue(int count) {
        this.n = count;
        queue = (T[]) new Object[count];
    }

    public ArrayQueue() {
        this(10);
    }

    public boolean enQueue(T t) {
        if (head == n) {//队列容器已满
            if (head == 0) {
                System.out.println("队列已满，没有队列移出");
                return false;
            } else {
                System.out.println("队列已满，有队列移出，进行数据搬移，然后插入队列！");
                //移动将头部移动到0位置，其他平移，入队保证连续性
                for (int i = head; i < tail; i++) {
                    queue[i - head] = queue[i];
                }
                //重置移动后的tail下标
                tail -= head;
                //重置队头
                head = 0;
            }
        }
        System.out.println("插入队列：" + t);
        queue[tail++] = t;
        return true;
    }

    public T deQueue() {
        if (head == tail) return null;
        T t = (T) queue[head++];
        System.out.println("移出队列：" + t);
        return t;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(4);
        queue.enQueue(2);
        System.out.println(toString(queue));
        queue.enQueue(3);
        System.out.println(toString(queue));
        queue.deQueue();
        System.out.println(toString(queue));
        queue.enQueue(4);
        System.out.println(toString(queue));
    }

    public static String toString(ArrayQueue<Integer> queue) {
        return Arrays.stream(queue.queue).map(Objects::toString).collect(Collectors.toList()).toString();
    }
}
