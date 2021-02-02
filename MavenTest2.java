import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class MavenTest2 {

    public static void main(String[] args) {

        List<JSONObject> dataList = new ArrayList<>();

        JSONObject i1 = new JSONObject();

        i1.put("biz_date", "2021-01-10");
        i1.put("delivery_quantity", 6);
        i1.put("pay_per_order", 243.112);
        i1.put( "order_cnt", 6881);
        i1.put("refund_rate", 3.0E-4);
        i1.put("pay_amount", 1672850.590);
        i1.put( "delivery_interval_avg", 6.98);
        i1.put( "sale_type", "total");
        i1.put( "sale_quantity", 9169);

        JSONObject i2 = new JSONObject();

        i2.put("biz_date", "2021-01-11");
        i2.put("delivery_quantity", 3);
        i2.put("pay_per_order", 243.1);
        i2.put( "order_cnt", 342);
        i2.put("refund_rate", 3.0E-4);
        i2.put("pay_amount", 1672242);
        i2.put( "delivery_interval_avg", 7.32);
        i2.put( "sale_type", "total");
        i2.put( "sale_quantity", 92423);

        dataList.add(i1);
        dataList.add(i2);

        System.out.println(dataList);


        JSONObject rawData = new JSONObject();
        rawData.put("code","00000");
        rawData.put("msg",null);
        rawData.put("data",dataList);
        rawData.put("total",null);
        rawData.put("success",true);

        System.out.println(rawData);

        // 获取数据api返回结果
        List<JSONObject> dayItems = rawData.getJSONArray("data").toJavaList(JSONObject.class);

        System.out.println("dayItems: "+ dayItems);



        Map<String,JSONObject> map = new HashMap<>();
        for (JSONObject item : dayItems){
            map.put(item.getString("biz_date"),item);
        }

        System.out.println("map: "+map);

        List<String> dateList = DateUtils.getDateList(2021,0,10,2021,0,13);

        System.out.println("dateList: "+ dateList);

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


        // 按天转换为折线节点格式
        List<List<LineNode>> dayNodesList = newList.stream().map(dayItem-> new MavenTest2().getSingleDayNodes(dayItem, lineInfo)).collect(Collectors.toList());

        System.out.println(dayNodesList);

        // 降维
        List<LineNode> lineNodeList =  new MavenTest2().dr(dayNodesList, lineInfo.keySet().size());

        System.out.println(lineNodeList);

    }

    /**
     * 将单日数据处理为折线节点格式
     * @param dayItem 单日数据
     * @param lineInfo excel请求信息
     * @return
     */
    public List<LineNode> getSingleDayNodes(JSONObject dayItem, JSONObject lineInfo){
        Set<String> keys = lineInfo.keySet();
        List<LineNode> singleDayNodes = new ArrayList<>();
        String handleDate = dayItem.getString("biz_date");
        keys.forEach(key -> {
            Number value = getValueByKey(dayItem , key);
            String type = lineInfo.getString(key);
            singleDayNodes.add(new LineNode(handleDate, value, type));
        });
        return singleDayNodes;
    }

    /**
     * 根据 key 取单日数据对应 Y轴的值value
     * @param dayItem 单日数据
     * @param key key
     * @return
     */
    public Number getValueByKey(JSONObject dayItem, String key){
//        if(!dayItem.containsKey(key)){
//            throw new Exception("");
//            throw new IOException("折线信息设置错误");
//        }
        Object value = dayItem.get(key);
        // 如果是百分率，要去掉百分号
        if(value!=null && value.toString().contains("%")){
            value =Double.parseDouble(value.toString().replace("%",""));
        }
        return (Number)value;
    }

    /**
     * 将二维数组降维为一维数组
     * @param someDaysNodes 多天节点二维数组（PS：一天节点是一维数组）
     * @return
     */
    private List<LineNode> dr(List<List<LineNode>> someDaysNodes, Integer keysSize){
        List<LineNode> allNodes = new ArrayList<>();
        for(int i =0 ; i < keysSize ; i++) {
            for (List<LineNode> singleDayNodes : someDaysNodes) {
                allNodes.add(singleDayNodes.get(i));
            }
        }
        return allNodes;
    }




}
