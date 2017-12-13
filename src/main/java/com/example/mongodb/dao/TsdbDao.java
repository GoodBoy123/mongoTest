package com.example.mongodb.dao;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;

import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.TsdbConstants;
import com.baidubce.services.tsdb.model.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class TsdbDao {
    static String ACCESS_KEY_ID = "3c456721fa32407b9ee89df2cc89651f";               // 用户的Access Key ID
    static String SECRET_ACCESS_KEY = "74613de0bcbb420a807e4b71bd652aa7";       // 用户的Secret Access Key
    static String ENDPOINT = "example.tsdb.iot.bj.baidubce.com";           // 用户的时序数据库域名
    // 创建配置
    private static BceClientConfiguration config = new BceClientConfiguration()
            .withCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY))
            .withEndpoint(ENDPOINT);
    private static final TsdbClient tsdbClient = new TsdbClient(config);
    public static TsdbClient getClient() {
        return tsdbClient;
    }

    //第一种field类型为temperature
    public void insertData() {
        TsdbClient tsdbClient = TsdbDao.getClient();
        // metric
        String METRIC = "cpu_idle";
        // 域
        String FIELD = "energy";
        String TAG_KEY = "xno";
        String TAG_VALUE = "S-1140";
        // 创建数据点
        List<Datapoint> datapoints = Arrays.asList(new Datapoint()
                .withMetric(METRIC)                                  // 设置Metric
                .withField(FIELD)                                    // 设置数据点域，可选，不填使用默认域名 value
                .addTag(TAG_KEY, TAG_VALUE)
                .addStringValue(1512459416698L, "57W"));   // 添加一个数据点

        tsdbClient.writeDatapoints(datapoints);
    }

    //第二种field类型为power ，并且插入多个数据
    public void insertData2(String[] datas) {
        TsdbClient tsdbClient = TsdbDao.getClient();
        // metric
        String METRIC = "cpu_idle";
        // 域
        String FIELD = "power";
        String TAG_KEY = "server";                                           // 标签名称
        String TAG_VALUE = "server1";                                        // 标签值
        Long[] times = new Long[]{1512380978873L , 1512387309839L , 1512387507032L };
        List<Datapoint> datapoints = new ArrayList<>();
        for(int i = 0 ; i < datas.length ; i++)
        {
            // 创建数据点
            Datapoint datapoint = new Datapoint()
                    .withMetric(METRIC)                                  // 设置Metric
                    .withField(FIELD)                                    // 设置数据点域，可选，不填使用默认域名 value
                    .addTag(TAG_KEY, TAG_VALUE)                          // 设置Tag
                    .addStringValue(times[i] , datas[i]);   // 添加一个数据点
            datapoints.add(datapoint);
        }
        tsdbClient.writeDatapoints(datapoints);
    }


    public void getMetrics(){
        TsdbClient tsdbClient = TsdbDao.getClient();
        // 获取Metric
        GetMetricsResponse response = tsdbClient.getMetrics();
        // 打印结果
        for(String metric : response.getMetrics()) {
            System.out.println(metric);
        }
    }

    public void getFields(){
        String METRIC = "wind";
        // 获取Field
        GetFieldsResponse response = tsdbClient.getFields(METRIC);
        // 打印结果
        for(Map.Entry<String, GetFieldsResponse.FieldInfo> entry : response.getFields().entrySet()) {
            System.out.println(entry.getKey() + ":");
            System.out.println("\t" + entry.getValue().getType());
        }
    }

    public void getTags(){
        TsdbClient tsdbClient = TsdbDao.getClient();
        // 设置需要获取tag的metric
        String METRIC = "cpu_idle";
        // 获取标签
        GetTagsResponse response = tsdbClient.getTags(METRIC);
        // 打印结果
        for(Map.Entry<String, List<String>> entry : response.getTags().entrySet()) {
            System.out.println(entry.getKey() + ":");
            for(String tagValue : entry.getValue()) {
                System.out.println("\t" + tagValue);
            }
        }
    }

    public void insertDataList(){
        TsdbClient tsdbClient = TsdbDao.getClient();
        String METRIC = "cpu_idle";
        String FIELD_1 = "temperature";
        String FIELD_2 = "energy";
        String TAG_KEY = "xno";
        String TAG_VALUE = "S-1140";

        Long time = System.currentTimeMillis();
        // 添加Double类型数据点
        Datapoint datapoint1 = new Datapoint()
                .withMetric(METRIC)
                .withField(FIELD_1)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time+1, 50)
                .addDoubleValue(time+2, 60)
                .addDoubleValue(time+3, 70)
                .addDoubleValue(time+4, 80);

        Datapoint datapoint2 = new Datapoint()
                .withMetric(METRIC)
                .withField(FIELD_2)
                .addTag(TAG_KEY, TAG_VALUE)
                .addStringValue(time+1, "50W")
                .addStringValue(time+2, "60W")
                .addStringValue(time+3, "70W")
                .addStringValue(time+4, "80W");
        tsdbClient.writeDatapoints(Arrays.asList(datapoint1 , datapoint2));
    }

    //单域查找
    public void getData(String metric, String field) throws IOException {
        TsdbClient tsdbClient = TsdbDao.getClient();
        String METRIC = metric;
        String FIELD = field;

        Fill fill = new Fill();
        fill.setValue(1L);
        fill.setType(TsdbConstants.FILL_TYPE_FIXED);
        fill.setInterval("1 hour");
        // 构造查询对象
//        GroupBy groupBy = new GroupBy();
//        groupBy.setName("Tag");
//        groupBy.setTags(Arrays.asList("city"));
        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
//                .withTags(Arrays.asList("city"))
                .withField(FIELD)
                .withFill(fill)
                //返回的tag,不能用于条件过滤
//                .withTags(Arrays.asList("sno"))
                .withFilters(new Filters()
                        .withRelativeStart("6 hours ago")
                        .addTag("city" , "杭州"))
//                        .addTag("zip_code" , "100000")
//                        .withRelativeStart("36 months ago")
//                        .withRelativeEnd("24 months ago"))
//                .addAggregator(new Aggregator()
//                        .withName(TsdbConstants.AGGREGATOR_NAME_AVG)
//                        .withSampling("7 days"))
//                .withGroupBy(Arrays.asList(groupBy))
                .withLimit(100));

        // 查询数据
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        // 打印结果
        for (Result result : response.getResults()) {
            System.out.println("Result:");
            for (Group group : result.getGroups()) {
                System.out.println("\tGroup:");
                System.out.println("\t\tTimeAndValue:" + group.getTimeAndValueList().size());
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    if (timeAndValue.isDouble()) {
                        System.out.println(
                                "\t\t\t[" + timeAndValue.getTime() + "," +
                                        timeAndValue.getDoubleValue() + "," +
//                                        timeAndValue.getStringValue(1) +
                                        "]");
                    } else if (timeAndValue.isLong()) {
                        System.out
                                .println("\t\t\t[" + timeAndValue.getTime() + "," + timeAndValue.getLongValue() +  "," +
                                        timeAndValue.getStringValue(1) + "]");
                    } else {
                        System.out.println(
                                "\t\t\t[" + timeAndValue.getTime() + "," + timeAndValue.getStringValue() +  "," );
//                                        timeAndValue.getStringValue(1) + "]");
                    }
                }
            }
        }

    }

    //多域查找
    public void queryMetrics() throws IOException{
        TsdbClient tsdbClient = TsdbDao.getClient();
        String METRIC = "cpu_idle";
        String FIELD1 = "temperature";
        String FIELD2 = "energy";
        String TAG= "xno";
        String fillValue = "1";
        // 构造查询对象
        List<String> fields = Arrays.asList(FIELD1, FIELD2);
        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withFields(fields)
//                .withFill(new Fill().withType("Fixed").withValue(fillValue).withInterval("1 millisecond"))
                .withFilters(new Filters()
                        .withAbsoluteStart(0)
                        //查询tag的键值符合下列条件的数据
                        .addTag("xno" , "S-1140")
                        .withValue(ValueFilter.createValueFilter(ValueFilter.NOT_EQUAL, "57")))
//                        .addTag("sno" , "1234"))
                .withLimit(100));
        // 查询数据
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        // 打印结果
        for(Result result : response.getResults()) {
            System.out.println("Result:");
            for(Group group : result.getGroups()) {
                System.out.println("\tGroup:");
                System.out.println("\t\tTimeAndValue:");
                for(Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    System.out.print("\t\t\t[" + timeAndValue.getTime() + "," + timeAndValue.getValue() + "," +
                            timeAndValue.getStringValue(1));
                    System.out.println("]");
                }
            }
        }

    }


    //插值查找
    public void queryByFill() throws IOException{
        String METRIC = "cpu_idle";
        String FIELD = "energy";

// 构造查询对象
        List<Query> queries = Arrays.asList(new Query()                          // 创建Query对象
                .withMetric(METRIC)                                              // 设置metric
                .withField(FIELD)                                                // 设置查询的域名，不设置表示查询默认域
                .withFilters(new Filters()                                       // 创建Filters对象
                        .withRelativeStart("5 seconds ago")                      // 设置相对的开始时间，这里设置为5秒前
                        .withRelativeEnd("1 second ago"))                        // 设置相对的结束时间，不设置则默认为到当前时间为止
                .withFill(new Fill()
                        .withType(TsdbConstants.FILL_TYPE_LINEAR)                // 设置插值类型，这里设置成线性插值
                        .withInterval("1 millisecond")                               // 设置插值间隔
                        .withMaxWriteInterval("1 second")));                   // 设置最大写入间隔

// 查询数据
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

// 打印结果
        for(Result result : response.getResults()) {
            System.out.println("Result:");
            for(Group group : result.getGroups()) {
                System.out.println("\tGroup:");

                for(GroupInfo groupInfo : group.getGroupInfos()) {
                    System.out.println("\t\tGroupInfo:");
                    System.out.println("\t\t\tName:" + groupInfo.getName());
                }
                System.out.println("\t\tTimeAndValue:");
                for(Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    if (timeAndValue.isDouble()) {
                        System.out.println("\t\t\t[" + timeAndValue.getTime() + "," + timeAndValue.getDoubleValue() + "]");
                    } else if(timeAndValue.isLong()) {
                        System.out.println("\t\t\t[" + timeAndValue.getTime() + "," + timeAndValue.getLongValue() + "]");
                    } else  {
                        System.out.println("\t\t\t[" + timeAndValue.getTime() + "," + timeAndValue.getStringValue() + "]");
                    }
                }
            }
        }
    }

}
