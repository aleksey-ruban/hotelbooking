package com.github.aleksey_ruban.hotelbooking.helpers;

import java.util.ArrayList;
import java.util.List;

public class ListHelper {
    public static <T> List<List<T>> partitionList(List<T> list, int size) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }
}
