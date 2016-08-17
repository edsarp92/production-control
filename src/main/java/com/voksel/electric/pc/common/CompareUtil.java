package com.voksel.electric.pc.common;

import com.voksel.electric.pc.domain.CompareData;
import com.voksel.electric.pc.domain.entity.FormLogData;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by edsarp on 8/17/16.
 */
public class CompareUtil{

    public CompareUtil() {
    }

    public static List<CompareData> doCompare(Class<?> clazz, Object oldData, Object newData) throws Exception{

            Field[] fields = clazz.getDeclaredFields();
            List<CompareData> listData=new ArrayList<CompareData>();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            for (Field field : fields) {
                field.setAccessible(true);
                Object o=field.get(oldData);
                Object n=field.get(newData);
                CompareData data=new CompareData();
                o = o == null ? "" : o;
                n = n == null ? "" : n;
                if (!o.equals(n)){
                    if (n instanceof String){
                        data.setField(field.getName());
                        data.setOldData((String)o);
                        data.setNewData((String)n);
                        listData.add(data);
                    }else if (n instanceof Integer) {
                        data.setField(field.getName());
                        data.setOldData(String.valueOf(o.toString()));
                        data.setNewData(String.valueOf(o.toString()));
                        listData.add(data);
                    }else if(n instanceof BigDecimal){
                        data.setField(field.getName());
                        data.setOldData(String.valueOf(o.toString()));
                        data.setNewData(String.valueOf(o.toString()));
                        listData.add(data);
                    }else if(n instanceof Date){
                        if (o.equals("") || o == null) {
                            data.setField(field.getName());
                            data.setOldData("");
                            data.setNewData(String.valueOf(sdf.format(n)));
                            listData.add(data);
                        }else if (n.equals("") || n == null) {
                            data.setField(field.getName());
                            data.setOldData(String.valueOf(sdf.format(o)));
                            data.setNewData("");
                            listData.add(data);
                        } else if (!sdf.format(oldData).equals(sdf.format(n))){
                            data.setField(field.getName());
                            data.setOldData(String.valueOf(sdf.format(o)));
                            data.setNewData(String.valueOf(sdf.format(n)));
                            listData.add(data);
                        }
                    }else if (n instanceof Timestamp){
                        data.setField(field.getName());
                        data.setOldData(String.valueOf(sdf.format(o)));
                        data.setNewData(String.valueOf(sdf.format(n)));
                        listData.add(data);
                    }
                }

            }
            return listData;

    }
}
