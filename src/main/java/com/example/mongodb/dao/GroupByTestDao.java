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

@Repository
public class GroupByTestDao {
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

    public void insertDataHZ() {
        TsdbClient tsdbClient = GroupByTestDao.getClient();
        // metric
        //风力
        String METRIC = "windTest";
        String TAG_KEY = "city";
        String TAG_VALUE = "杭州";
        Long time = System.currentTimeMillis();
        List<Datapoint> datapoints = new ArrayList<>();
        Datapoint datapoint = new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 4)
                .addDoubleValue(time + 86400000 , 3)
                .addDoubleValue(time + 172800000 , 6 )
                .addDoubleValue(time + 259200000 , 2)
                .addDoubleValue(time + 345600000 , 4);

        //降雨量
        String METRIC_2 = "rainTest";
        Datapoint datapoint2 = new Datapoint()
                .withMetric(METRIC_2)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 400)
                .addDoubleValue(time + 86400000 , 300)
                .addDoubleValue(time + 172800000 , 600 )
                .addDoubleValue(time + 259200000 , 200)
                .addDoubleValue(time + 345600000 , 400);
        //温度
        String METRIC_3 = "temperatureTest";
        Datapoint datapoint3 = new Datapoint()
                .withMetric(METRIC_3)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 23)
                .addDoubleValue(time + 86400000 , 27)
                .addDoubleValue(time + 172800000 ,  20)
                .addDoubleValue(time + 259200000 , 23)
                .addDoubleValue(time + 345600000 , 24);
        datapoints.add(datapoint);
//        datapoints.add(datapoint2);
        datapoints.add(datapoint3);
        tsdbClient.writeDatapoints(datapoints);
    }

    public void insertDataWZ() {
        TsdbClient tsdbClient = GroupByTestDao.getClient();
        // metric
        //风力
        String METRIC = "windTest";
        String TAG_KEY = "city";
        String TAG_VALUE = "温州";
        Long time = System.currentTimeMillis();
        List<Datapoint> datapoints = new ArrayList<>();
        Datapoint datapoint = new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 2)
                .addDoubleValue(time + 86400000 , 1)
                .addDoubleValue(time + 172800000 , 3 )
                .addDoubleValue(time + 259200000 , 5)
                .addDoubleValue(time + 345600000 , 6);

        //降雨量
        String METRIC_2 = "rainTest";
        Datapoint datapoint2 = new Datapoint()
                .withMetric(METRIC_2)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 100)
                .addDoubleValue(time + 86400000 , 200)
                .addDoubleValue(time + 172800000 , 300 )
                .addDoubleValue(time + 259200000 , 200)
                .addDoubleValue(time + 345600000 , 240);
        //温度
        String METRIC_3 = "temperatureTest";
        Datapoint datapoint3 = new Datapoint()
                .withMetric(METRIC_3)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 26)
                .addDoubleValue(time + 86400000 , 27)
                .addDoubleValue(time + 172800000 ,  28)
                .addDoubleValue(time + 259200000 , 25)
                .addDoubleValue(time + 345600000 , 26);
        datapoints.add(datapoint);
        datapoints.add(datapoint2);
        datapoints.add(datapoint3);
        tsdbClient.writeDatapoints(datapoints);
    }

    public void insertDataBZ() {
        TsdbClient tsdbClient = GroupByTestDao.getClient();
        // metric
        //风力
        String METRIC = "windTest";
        String TAG_KEY = "city";
        String TAG_VALUE = "北京";
        Long time = System.currentTimeMillis();
        List<Datapoint> datapoints = new ArrayList<>();
        Datapoint datapoint = new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 3)
                .addDoubleValue(time + 86400000 , 4)
                .addDoubleValue(time + 172800000 , 3 )
                .addDoubleValue(time + 259200000 , 4)
                .addDoubleValue(time + 345600000 , 6);

        //降雨量
        String METRIC_2 = "rainTest";
        Datapoint datapoint2 = new Datapoint()
                .withMetric(METRIC_2)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 100)
                .addDoubleValue(time + 86400000 , 150)
                .addDoubleValue(time + 172800000 , 90 )
                .addDoubleValue(time + 259200000 , 230)
                .addDoubleValue(time + 345600000 , 220);
        //温度
        String METRIC_3 = "temperatureTest";
        Datapoint datapoint3 = new Datapoint()
                .withMetric(METRIC_3)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 16)
                .addDoubleValue(time + 86400000 , 17)
                .addDoubleValue(time + 172800000 ,  18)
                .addDoubleValue(time + 259200000 , 15)
                .addDoubleValue(time + 345600000 , 16);
        datapoints.add(datapoint);
        datapoints.add(datapoint2);
        datapoints.add(datapoint3);
        tsdbClient.writeDatapoints(datapoints);
    }

    public void insertDataZZ(){
        TsdbClient tsdbClient = GroupByTestDao.getClient();
        // metric
        //风力
        String METRIC = "windTest";
        String TAG_KEY = "city";
        String TAG_VALUE = "郑州";
        Long time = System.currentTimeMillis();
        List<Datapoint> datapoints = new ArrayList<>();
        Datapoint datapoint = new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 3)
                .addDoubleValue(time + 86400000 , 3.5)
                .addDoubleValue(time + 172800000 , 3.8 )
                .addDoubleValue(time + 259200000 , 5.2)
                .addDoubleValue(time + 345600000 , 3.6);

        //降雨量
        String METRIC_2 = "rainTest";
        Datapoint datapoint2 = new Datapoint()
                .withMetric(METRIC_2)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 100)
                .addDoubleValue(time + 86400000 , 120)
                .addDoubleValue(time + 172800000 , 132 )
                .addDoubleValue(time + 259200000 , 232)
                .addDoubleValue(time + 345600000 , 220);
        //温度
        String METRIC_3 = "temperatureTest";
        Datapoint datapoint3 = new Datapoint()
                .withMetric(METRIC_3)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 13)
                .addDoubleValue(time + 86400000 , 12)
                .addDoubleValue(time + 172800000 ,  13)
                .addDoubleValue(time + 259200000 , 15)
                .addDoubleValue(time + 345600000 , 12);
        datapoints.add(datapoint);
        datapoints.add(datapoint2);
        datapoints.add(datapoint3);
        tsdbClient.writeDatapoints(datapoints);
    }

    public void insertDataDL(){
        TsdbClient tsdbClient = GroupByTestDao.getClient();
        // metric
        //风力
        String METRIC = "windTest";
        String TAG_KEY = "city";
        String TAG_VALUE = "大连";
        Long time = System.currentTimeMillis();
        List<Datapoint> datapoints = new ArrayList<>();
        Datapoint datapoint = new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 4)
                .addDoubleValue(time + 86400000 , 4.5)
                .addDoubleValue(time + 172800000 , 3.2 )
                .addDoubleValue(time + 259200000 , 5)
                .addDoubleValue(time + 345600000 , 4.6);

        //降雨量
        String METRIC_2 = "rainTest";
        Datapoint datapoint2 = new Datapoint()
                .withMetric(METRIC_2)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 200)
                .addDoubleValue(time + 86400000 , 250)
                .addDoubleValue(time + 172800000 , 370 )
                .addDoubleValue(time + 259200000 , 232)
                .addDoubleValue(time + 345600000 , 210);
        //温度
        String METRIC_3 = "temperatureTest";
        Datapoint datapoint3 = new Datapoint()
                .withMetric(METRIC_3)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 19)
                .addDoubleValue(time + 86400000 , 11)
                .addDoubleValue(time + 172800000 ,  18)
                .addDoubleValue(time + 259200000 , 13)
                .addDoubleValue(time + 345600000 , 17);
        datapoints.add(datapoint);
        datapoints.add(datapoint2);
        datapoints.add(datapoint3);
        tsdbClient.writeDatapoints(datapoints);
    }

    public void insertDataWL(){
        TsdbClient tsdbClient = GroupByTestDao.getClient();
        // metric
        //风力
        String METRIC = "windTest";
        String TAG_KEY = "city";
        String TAG_VALUE = "温岭";
        Long time = System.currentTimeMillis();
        List<Datapoint> datapoints = new ArrayList<>();
        Datapoint datapoint = new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 3)
                .addDoubleValue(time + 86400000 , 2.5)
                .addDoubleValue(time + 172800000 , 3.2 )
                .addDoubleValue(time + 259200000 , 4)
                .addDoubleValue(time + 345600000 , 4.1);

        //降雨量
        String METRIC_2 = "rainTest";
        Datapoint datapoint2 = new Datapoint()
                .withMetric(METRIC_2)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 210)
                .addDoubleValue(time + 86400000 , 260)
                .addDoubleValue(time + 172800000 , 270 )
                .addDoubleValue(time + 259200000 , 235)
                .addDoubleValue(time + 345600000 , 211);
        //温度
        String METRIC_3 = "temperatureTest";
        Datapoint datapoint3 = new Datapoint()
                .withMetric(METRIC_3)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 15)
                .addDoubleValue(time + 86400000 , 13)
                .addDoubleValue(time + 172800000 ,  14)
                .addDoubleValue(time + 259200000 , 11)
                .addDoubleValue(time + 345600000 , 12);
        datapoints.add(datapoint);
        datapoints.add(datapoint2);
        datapoints.add(datapoint3);
        tsdbClient.writeDatapoints(datapoints);
    }

    public void insertDataWC(){
        TsdbClient tsdbClient = GroupByTestDao.getClient();
        // metric
        //风力
        String METRIC = "windTest";
        String TAG_KEY = "city";
        String TAG_VALUE = "文成";
        Long time = System.currentTimeMillis();
        List<Datapoint> datapoints = new ArrayList<>();
        Datapoint datapoint = new Datapoint()
                .withMetric(METRIC)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 2)
                .addDoubleValue(time + 86400000 , 2.5)
                .addDoubleValue(time + 172800000 , 3.4 )
                .addDoubleValue(time + 259200000 , 2.6)
                .addDoubleValue(time + 345600000 , 3.6);

        //降雨量
        String METRIC_2 = "rainTest";
        Datapoint datapoint2 = new Datapoint()
                .withMetric(METRIC_2)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 210)
                .addDoubleValue(time + 86400000 , 220)
                .addDoubleValue(time + 172800000 , 270 )
                .addDoubleValue(time + 259200000 , 282)
                .addDoubleValue(time + 345600000 , 220);
        //温度
        String METRIC_3 = "temperatureTest";
        Datapoint datapoint3 = new Datapoint()
                .withMetric(METRIC_3)
                .addTag(TAG_KEY, TAG_VALUE)
                .addDoubleValue(time , 18)
                .addDoubleValue(time + 86400000 , 17)
                .addDoubleValue(time + 172800000 ,  18)
                .addDoubleValue(time + 259200000 , 19)
                .addDoubleValue(time + 345600000 , 16);
        datapoints.add(datapoint);
        datapoints.add(datapoint2);
        datapoints.add(datapoint3);
        tsdbClient.writeDatapoints(datapoints);
    }

    public void getDataByGroup() throws IOException{
        TsdbClient tsdbClient = GroupByTestDao.getClient();
        String METRIC = "windTest";
        String METRIC_2 = "rainTest";
        String METRIC_3 = "temperatureTest";
        GroupBy groupBy = new GroupBy();
        groupBy.setName("Tag");
        groupBy.setTags(Arrays.asList("city"));
        List<Query> queries = new ArrayList<>();
        Query query = new Query().withMetric(METRIC)
                        .withTags(Arrays.asList("city"))
                        .withFilters(new Filters()
                                .withRelativeStart("3 hours ago"))
                        .addAggregator(new Aggregator()
                                .withName(TsdbConstants.AGGREGATOR_NAME_AVG)
                                .withSampling("1 day"))
                        .withGroupBy(Arrays.asList(groupBy))
                        .withLimit(10);
        Query query2 = new Query().withMetric(METRIC_2)
                .withTags(Arrays.asList("city"))
                .withFilters(new Filters()
                        .withRelativeStart("3 hours ago"))
                .addAggregator(new Aggregator()
                        .withName(TsdbConstants.AGGREGATOR_NAME_AVG)
                        .withSampling("1 day"))
                .withGroupBy(Arrays.asList(groupBy))
                .withLimit(10);
        Query query3 = new Query().withMetric(METRIC_3)
                .withTags(Arrays.asList("city"))
                .withFilters(new Filters()
                        .withRelativeStart("3 hours ago"))
                .addAggregator(new Aggregator()
                        .withName(TsdbConstants.AGGREGATOR_NAME_AVG)
                        .withSampling("1 day"))
                .withGroupBy(Arrays.asList(groupBy))
                .withLimit(10);

        queries.add(query);
        queries.add(query2);
        queries.add(query3);
        // 查询数据
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);

        // 打印结果
        for (Result result : response.getResults()) {
            System.out.println("Result:");
            for (Group group : result.getGroups()) {
                System.out.println("\tGroup:");
                for (GroupInfo groupInfo : group.getGroupInfos()) {
                    System.out.println("\t\tGroupInfo:");
                    System.out.println("\t\t\tName:" + groupInfo.getName());
                }
                System.out.println("\t\tTimeAndValue:" + group.getTimeAndValueList().size());
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    if (timeAndValue.isDouble()) {
                        System.out.println(
                                "\t\t\t[" + timeAndValue.getTime() + "," +
                                        timeAndValue.getDoubleValue(0) + "," +
                                        timeAndValue.getStringValue(1) +
//                                        timeAndValue.getStringValue(2) +
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

    public void getDayRain() throws IOException
    {
        TsdbClient tsdbClient = GroupByTestDao.getClient();
        String METRIC = "rainTest";
        Query query = new Query().withMetric(METRIC)
                .withFilters(new Filters()
                        .withAbsoluteStart(0))
                .addAggregator(new Aggregator()
                        .withName(TsdbConstants.AGGREGATOR_NAME_SUM)
                        .withSampling("1 day"));
        // 查询数据
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(Arrays.asList(query));

        // 打印结果
        for (Result result : response.getResults()) {
            System.out.println("Result:");
            for (Group group : result.getGroups()) {
                System.out.println("\tGroup:");
                for (GroupInfo groupInfo : group.getGroupInfos()) {
                    System.out.println("\t\tGroupInfo:");
                    System.out.println("\t\t\tName:" + groupInfo.getName());
                }
                System.out.println("\t\tTimeAndValue:" + group.getTimeAndValueList().size());
                for (Group.TimeAndValue timeAndValue : group.getTimeAndValueList()) {
                    if (timeAndValue.isDouble()) {
                        System.out.println(
                                "\t\t\t[" + timeAndValue.getTime() + "," +
                                        timeAndValue.getDoubleValue(0) + "]");
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

}
