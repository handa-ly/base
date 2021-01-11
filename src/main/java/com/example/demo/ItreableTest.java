package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @Author: hanDa
 * @Date: 2021/1/11 10:43
 * @Version:1.0
 * @Description:
 */
public class ItreableTest {
    public interface Iterator {
        //遍历到下一个元素
        public Object next();

        //是否已经遍历到尾部
        public boolean hasNext();

        //删除当前指向的元素
        public boolean remove();
    }

    public class ConcreteIterator implements Iterator {
        private Vector vector = new Vector();
        //定义当前游标
        public int cursor = 0;

        @SuppressWarnings("unchecked")
        public ConcreteIterator(Vector _vector) {
            this.vector = _vector;
        }

        //判断是否到达尾部
        @Override
        public boolean hasNext() {
            if (this.cursor == this.vector.size()) {
                return false;
            } else {
                return true;
            }
        }

        //返回下一个元素
        @Override
        public Object next() {
            Object result = null;
            if (this.hasNext()) {
                result = this.vector.get(this.cursor++);
            } else {
                result = null;
            }
            return result;
        }

        //删除当前元素
        @Override
        public boolean remove() {
            this.vector.remove(this.cursor);
            return true;
        }
    }

    public interface Aggregate {
        //是容器必然有元素的增加
        public void add(Object object);

        //减少元素
        public void remove(Object object);

        //由迭代器来遍历所有的元素
        public Iterator iterator();
    }

    public class ConcreteAggregate implements Aggregate {
        //容纳对象的容器
        private Vector vector = new Vector();

        //增加一个元素
        @Override
        public void add(Object object) {
            this.vector.add(object);
        }

        //返回迭代器对象
        @Override
        public Iterator iterator() {
            return new ConcreteIterator(this.vector);
        }

        //删除一个元素
        @Override
        public void remove(Object object) {
            this.remove(object);
        }
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("ss");
        list.add("ss");
        java.util.Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}