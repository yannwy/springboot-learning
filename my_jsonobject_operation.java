import com.alibaba.fastjson.JSONObject;
import utils.DateUtils;

import javax.sound.midi.Soundbank;
import java.util.*;

public class MavenTest {

    //仔细学习如何处理JSONObject,思路很重要
    public static void main(String[] args) {
        List<JSONObject> itemList = new ArrayList<>();
        JSONObject i1 = new JSONObject();
        i1.put("biz_date","2021-01-01");
        i1.put("pay_amount",100);
        i1.put("order_cnt",100);
        i1.put("sale_quantity",100);
        i1.put("delivery_quantity_sum",100);
        i1.put("pay_per_order",100);
        i1.put("delivery_interval_avg",100);
        i1.put("refund_rate",100);


        JSONObject i2 = new JSONObject();
        i2.put("biz_date","2021-01-04");
        i2.put("pay_amount",200);
        i2.put("order_cnt",200);
        i2.put("sale_quantity",200);
        i2.put("delivery_quantity_sum",200);
        i2.put("pay_per_order",200);
        i2.put("delivery_interval_avg",200);
        i2.put("refund_rate",200);

        itemList.add(i1);
        itemList.add(i2);


        System.out.println(itemList);

        Map<String,JSONObject> map = new HashMap<>();
        for (JSONObject item : itemList){
            map.put(item.getString("biz_date"),item);
        }

        System.out.println(map);

        List<String> dateList = DateUtils.getDateList(2021,0,1,2021,0,10);

        System.out.println(dateList);
        // lineInfo

        JSONObject lineInfo = JSONObject.parseObject("{\"pay_amount\":\"销售金额\",\"order_cnt\":\"销售订单数\",\"sale_quantity\":\"销售件数\",\"delivery_quantity_sum\":\"发货件数\",\"pay_per_order\":\"笔单价\",\"delivery_interval_avg\":\"平均发货时长(小时)\",\"refund_rate\":\"售后率\"}");
        //keySet
        Set<String> keySet = lineInfo.keySet();
        System.out.println(keySet);

        List<JSONObject> newList = new ArrayList<>();
        for (String bizDate:dateList){
            if(map.containsKey(bizDate)){
                newList.add(map.get(bizDate));
            }else {
                JSONObject newItem = new JSONObject();
                newItem.put("biz_date",bizDate);
                for(String key:keySet){
                    newItem.put(key,0);
                }
                newList.add(newItem);
            }

        }

        System.out.println(JSONObject.toJSONString(newList));

    }
}
