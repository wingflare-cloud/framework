package com.wingflare.lib.mybatis.plus.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态表名存储器
 *
 * @author shaoyuyao
 * @date 2023/3/10 18:52
 */
public class DynamicTableNameBuffer {

    private List<DynamicTableNameData> data;

    private Map<String, DynamicTableNameData> map;

    private DynamicTableNameBuffer(List<DynamicTableNameData> data) {
        this.data = data;
    }

    public List<DynamicTableNameData> getData() {
        return data;
    }

    public Map<String, DynamicTableNameData> getMap() {
        if (null == map) {
            map = new HashMap<>(getDataSize());
            data.forEach(x -> map.put(x.getBeforeTableName(), x));
        }

        return map;
    }

    public int getDataSize() {
        if (null != data) {
            return data.size();
        }

        return 0;
    }

    public static DynamicTableNameBuffer build(String beforeTableName, String afterTableName) {
        List<DynamicTableNameData> data = new ArrayList<>();
        data.add(DynamicTableNameData.of(beforeTableName, afterTableName));
        return new DynamicTableNameBuffer(data);
    }

    public DynamicTableNameBuffer add(String beforeTableName, String afterTableName) {
        data.add(DynamicTableNameData.of(beforeTableName, afterTableName));
        return this;
    }

    @Override
    public String toString() {
        return "DynamicTableNameBuffer{" +
                "data=" + data +
                ", map=" + map +
                '}';
    }
}
