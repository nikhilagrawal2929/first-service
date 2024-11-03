package com.espire.partition;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

public class ColumnRangePartition implements Partitioner {
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        int max = 1000;
        int min = 1;
        int target = (max - min) / gridSize + 1;

        Map<String, ExecutionContext> executionContextMap = new HashMap<>();
        int number=0;
        int start=min;
        int end= start + target -1;

        while(start<=target){
            ExecutionContext executionContext = new ExecutionContext();
            executionContextMap.put("Partition"+number,executionContext);

            if(end>=max){
                end=max;
            }
            executionContext.put("minValue",start);
            executionContext.put("maxValue",end);
            start+=target;
            end+=target;
            number++;
        }
        System.out.println("Partition result" + executionContextMap.toString());
        return executionContextMap;
    }
}
