package com.beiyou.util.data;

import com.beiyou.auth.TYPE;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DateSourceUtils extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> dataSourceKey =
            new InheritableThreadLocal<String>();
    public static void setDataSourceKey(TYPE type){
        System.out.println("-------------->" + type.getType());
        dataSourceKey.set(type.getType());
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
}

