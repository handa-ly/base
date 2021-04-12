package com.example.demo.heap;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: hanDa
 * @Date: 2021/4/6 16:50
 * @Version:1.0
 * @Description:
 */
public class HeapTest {
    public static void main(String[] args) {

        List<ListDspFileDataMapping> list = new ArrayList<>();
        for (int i =0;i<10;i++){
            ListDspFileDataMapping listDspFileDataMapping = new ListDspFileDataMapping();
            listDspFileDataMapping.setClickNumber(new BigDecimal(i+1));
            ListDspFileDataMapping listDspFileDataMapping1 = new ListDspFileDataMapping();
            listDspFileDataMapping1.setClickNumber(new BigDecimal(i+1));
            ListDspFileDataMapping listDspFileDataMapping3 = new ListDspFileDataMapping();
            listDspFileDataMapping3.setClickNumber(new BigDecimal(10-(i+1)));
            list.add(listDspFileDataMapping);
            list.add(listDspFileDataMapping1);
            list.add(listDspFileDataMapping3);
        }
        list.forEach(aa->{
            System.out.println("ss");
        });
        System.out.println(list.toString());
        heapSortOrderbyClickNumberDes(list);
        System.out.println(list.toString());

    }
    private static void heapSortOrderbyClickNumberDes(List<ListDspFileDataMapping> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(it -> {
            if(null == it.getClickNumber()) {
                it.setClickNumber(BigDecimal.ONE);
            }
        });
        long start = System.currentTimeMillis();
        //从第一个非叶子结点从下至上，从右至左调整结构
        for (int i =( list.size()-1) / 2; i >= 0; i--) {
            headAdjust(list, list.size(), i);
        }
        //排序，降最大的节点放在堆尾，然后从根节点重新调整
        for (int i = list.size() - 1; i > 0; i--) {
            ListDspFileDataMapping temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);
            headAdjust(list, i, 0);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(String.format("堆排序耗时：【%s】, list size：【%s】", time > 1000 ? (time/1000) + " 秒"  : time + " 毫秒", list.size()));
    }

    /**
     * 调整堆（小堆顶）
     * @param list 待排序序列
     * @param length 待排序队尾元素索引
     * @param parent 父节点
     */
    private static void headAdjust(List<ListDspFileDataMapping> list, int length, int parent) {
        //将temp作为父节点
        ListDspFileDataMapping temp = list.get(parent);
        int index = 2 * parent + 1; // 左节点
        while (index < length) {
            int rightIndex = index + 1; // 右节点
            // 如果有右孩子结点，并且右孩子结点的值小于左孩子结点，则选取右孩子结点
            if (rightIndex < length &&  list.get(index).getClickNumber().compareTo(list.get(rightIndex).getClickNumber()) == 1) {
                index++;
            }
            // 如果父结点的值已经小于孩子结点的值，则直接结束
            if(temp.getClickNumber().compareTo(list.get(index).getClickNumber()) < 1) {
                break;
            }
            // 把孩子结点的值赋给父结点
            list.set(parent, list.get(index));
            //选取孩子结点的左孩子结点,继续向下筛选
            parent = index;
            index = 2 * index + 1;
        }
        list.set(parent, temp);
    }
}