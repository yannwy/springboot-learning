
    public static void main(String[] args) {
        List<JSONObject>  itemList = new ArrayList<>();
        JSONObject i1  = new JSONObject();
        i1.put("biz_date","2021-01-01");
        i1.put("pay_amount","100");
        i1.put("order_cnt","200");

        JSONObject i2  = new JSONObject();
        i2.put("biz_date","2021-01-07");
        i2.put("pay_amount","101");
        i2.put("order_cnt","202");
        itemList.add(i1);
        itemList.add(i2);

        Map<String , JSONObject> map = new HashMap<>();
        for(JSONObject   item :itemList ){
            map.put(item.getString("biz_date"),item);
        }
        System.out.println(map);


        List<String>  dateList = Arrays.asList("2021-01-01","2021-01-02","2021-01-03","2021-01-04","2021-01-05","2021-01-06","2021-01-07");
        JSONObject lineInfo = JSONObject.parseObject("{\"pay_amount\":\"销售金额\",\"order_cnt\":\"销售订单数\"}");
        Set<String>  keySet = lineInfo.keySet();


        List<JSONObject>  newList= new ArrayList<>();
        for(String bizDate:dateList){
            if(map.containsKey(bizDate)){
                newList.add(map.get(bizDate));
            }else{
                JSONObject  newItem = new JSONObject();
                newItem.put("biz_date" , bizDate);
                //keySet.stream().forEach(key-> newItem.put(key , 0));
                
                for(String key:keySet){
                    newItem.put(key , 0);
                }
                newList.add(newItem);
            }
        }
        
        System.out.println(JSONObject.toJSONString(newList));

    }