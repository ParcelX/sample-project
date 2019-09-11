package io.parcelx;

import io.parcelx.jsonrpc.ApiBatchResponse;
import io.parcelx.jsonrpc.ApiException;
import io.parcelx.jsonrpc.ApiRequest;
import io.parcelx.jsonrpc.ApiResponse;
import io.parcelx.open.api.sdk.v1.ProviderApi;
import io.parcelx.open.api.sdk.v1.model.*;
import io.parcelx.utils.Constants;
import io.parcelx.utils.JsonConverterUtil;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务商基本操作调用
 *
 * @author xinfeng
 * @version 1.0
 * @Description
 * @date 2019/7/25 16:53
 */
public class ProviderOperate {

    public static void main(String[] args) throws ParseException, ApiException {
        // 1.批量查询包裹
        //queryParcelList();
        // 2.根据包裹单号查询
        //getParcelInfo();
        // 3.查询面单信息
        //getLabel();
        // 3.上报包裹编号
        //reportTrackingNumber();
        // 4.服务商对包裹进行称重
        // reportWeight();
        // 5.服务商上报物流轨迹事件
        //reportTrackingEvent();
        // 6.上报服务完成
        //reportServiceComplete();
        // 7.上报包裹异常
        //reportException();
        // 8.上报面单信息
        //reportLabel();
        // 9.批量上报物流记录
        reportTrackingEventList();
    }

    /**
     * 查询包裹列表
     *
     * @return 查询结果集
     * @date 2019/7/25 11:23
     */
    public static void queryParcelList() {
        try {
            // 创建查询参数
            ParcelListQueryParam queryParam = new ParcelListQueryParam();
            /*
             * 可以设置开始时间 queryParam.setStartTime()
             * 可以设置结束时间 queryParam.setEndTime()
             * 可以设置页码，默认为0 queryParam.setPageNum()
             * 可以设置每页条数，默认为20 queryParam.setPageSize()
             */
            // 查询已提交（即已下单）状态的包裹
            queryParam.setCriteria(ParcelQueryCriteria.COMMITTED);
            // 返回结果
            ParcelListQueryResult result = getProviderApi().queryParcelList(queryParam);
            if (result != null) {
                // 将java对象转换json字符串输出
                JsonConverterUtil.getJsonString(result);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据ParcelNo获取包裹信息
     *
     * @return 返回包裹信息
     * @date 2019/7/25 16:13
     */
    public static void getParcelInfo() {
        try {
            // 创建查询参数
            ParcelNoParam param = new ParcelNoParam();
            // 编号类型，默认是parcelNo
            param.setType(ParcelNoType.ParcelNo);
            // 设置编号
            param.setValue("PX244284668003000");
            // 查询结果
            ParcelInfoResult result = getProviderApi().getParcelInfo(param);
            if (result != null) {
                // 转换成json字符串并输出
                JsonConverterUtil.getJsonString(result);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询面单信息
     *
     * @date 2019/7/26 15:44
     */
    public static void getLabel() {
        try {
            // 创建查询参数
            ParcelNoParam param = new ParcelNoParam();
            // 编号类型，默认是parcelNo
            param.setType(ParcelNoType.ParcelNo);
            // 设置编号
            param.setValue("PX21d9333f0003000");
            // 查询结果
            ParcelLabelResult result = getProviderApi().getLabel(param);
            if (result != null) {
                // 转换成json字符串并输出?
                JsonConverterUtil.getJsonString(result);
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上报物流单号
     *
     * @date 2019/7/26 12:32
     */
    public static void reportTrackingNumber() {
        try {
            // 创建查询参数
            ParcelNoParam param = new ParcelNoParam();
            // 编号类型，默认是parcelNo
            param.setType(ParcelNoType.ParcelNo);
            // 设置编号
            param.setValue("PX25dfa2500c03000");
            // 物流单号
            String trackingNo = "FS100000001";
            // 执行成功后无返回结果，不抛异常，即为正常执行
            getProviderApi().reportTrackingNumber(param, trackingNo);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务商对包裹进行称重
     *
     * @date 2019/7/26 12:45
     */
    public static void reportWeight() {
        try {
            // 创建查询参数
            ParcelNoParam param = new ParcelNoParam();
            // 编号类型，默认是parcelNo
            param.setType(ParcelNoType.ParcelNo);
            // 设置编号
            param.setValue("PX25dfa2500c03000");
            // 重量
            Double weight = 2D;
            // 执行成功后无返回结果，不抛异常，即为正常执行
            getProviderApi().reportWeight(param, weight);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上报物流轨迹
     * 服务上报完成之前可多次更新
     *
     * @date 2019/7/26 13:27
     */
    public static void reportTrackingEvent() {
        try {
            // 创建编号参数
            ParcelNoParam parcelNoParam = new ParcelNoParam();
            // 编号类型，默认是parcelNo
            parcelNoParam.setType(ParcelNoType.ParcelNo);
            // 设置编号
            parcelNoParam.setValue("PX25dfa2500c03000");
            // 创建物流轨迹事件参数
            TrackingEventParam eventParam = new TrackingEventParam();
            // 物流轨迹code
            eventParam.setCode(TrackingCode.DELIVERING);
            // 物流轨迹消息
            eventParam.setMessage("快递小哥土行孙正在配送中，上海市宝山区长江南路1231号");
            // 物流轨迹地点
            eventParam.setLocation("上海市宝山区");
            // 物流轨迹备注
            eventParam.setRemark("测试信息");
            // 物流轨迹事件
            eventParam.setTime(new Date());
            // 执行成功后无返回结果，不抛异常，即为正常执行
            getProviderApi().reportTrackingEvent(parcelNoParam, eventParam);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量上报物流记录
     *
     * @date 2019/9/10 18:43
     */
    public static void reportTrackingEventList() throws ParseException, ApiException {
        // 创建编号参数
        ParcelNoParam parcelNoParam = new ParcelNoParam();
        // 编号类型，默认是parcelNo
        parcelNoParam.setType(ParcelNoType.ParcelNo);
        // 设置编号
        parcelNoParam.setValue("PX280b74f33003000");

        // 第一条track
        TrackingEventParam track1 = new TrackingEventParam();
        track1.setRemark("测试数据");
        track1.setLocation("韩国首尔");
        track1.setMessage("韩国01海外仓已拣货");
        track1.setCode(TrackingCode.COLLECT);
        track1.setRemark("韩国01海外仓已拣货");

        // 第二条track
        TrackingEventParam track2 = new TrackingEventParam();
        track2.setRemark("测试数据");
        track2.setLocation("韩国首尔");
        track2.setMessage("韩国01海外仓已发货");
        track2.setCode(TrackingCode.SHIPPED);
        track2.setRemark("韩国01海外仓已发货");
        track2.setTime(new Date());
        // 批量请求参数
        List<ApiRequest> apiRequestList = new ArrayList<ApiRequest>();
        ApiRequest apiRequest1 = new ApiRequest("reportTrackingEvent", parcelNoParam, track1);
        ApiRequest apiRequest2 = new ApiRequest("reportTrackingEvent", parcelNoParam, track2);
        apiRequestList.add(apiRequest1);
        apiRequestList.add(apiRequest2);
        // 发起请求
        ApiBatchResponse batchResponse = getProviderApi().batch(apiRequestList);
        List<ApiResponse> apiResponseList = batchResponse.getApiResponseList();
        // 如果不为空，则有错误信息
        if (!apiResponseList.isEmpty()) {
            // 处理错误信息
            apiResponseList.toString();
        }
    }

    /**
     * 上报服务完成
     *
     * @date 2019/7/26 14:37
     */
    public static void reportServiceComplete() {
        try {
            // 创建编号参数
            ParcelNoParam parcelNoParam = new ParcelNoParam();
            // 编号类型，默认是parcelNo
            parcelNoParam.setType(ParcelNoType.ParcelNo);
            // 设置编号
            parcelNoParam.setValue("PX25dfa2500c03000");
            // 创建服务完成参数
            ServiceCompleteInfoParam serviceComplete = new ServiceCompleteInfoParam();
            // 服务完成消息
            serviceComplete.setMessage("服务已完成,已由本人签收");
            // 服务完成位置
            serviceComplete.setLocation("上海B港口");
            // 服务完成时间
            serviceComplete.setTime(new Date());
            // 执行成功后无返回结果，不抛异常，即为正常执行
            getProviderApi().reportServiceComplete(parcelNoParam, serviceComplete);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上报包裹异常
     *
     * @date 2019/7/26 14:47
     */
    public static void reportException() {
        try {
            // 创建编号参数
            ParcelNoParam parcelNoParam = new ParcelNoParam();
            // 编号类型，默认是parcelNo
            parcelNoParam.setType(ParcelNoType.ParcelNo);
            // 设置编号
            parcelNoParam.setValue("PX244284668003000");
            // 创建异常参数
            ParcelExceptionParam exceptionParam = new ParcelExceptionParam();
            // 异常编码，只可以使用ParcelExceptionCode枚举中的编码
            exceptionParam.setCode(ParcelExceptionCode.PLACE_ORDER_ERROR);
            // 异常消息
            exceptionParam.setMessage("订单编号重复");
            // 异常位置
            exceptionParam.setLocation("上海B港口1号位");
            // 异常上报时间
            exceptionParam.setTime(new Date());
            // 执行成功后无返回结果，不抛异常，即为正常执行
            getProviderApi().reportException(parcelNoParam, exceptionParam);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上报面单
     *
     * @date 2019/7/26 15:11
     */
    public static void reportLabel() {
        try {
            // 创建编号参数
            ParcelNoParam parcelNoParam = new ParcelNoParam();
            // 编号类型，默认是parcelNo
            parcelNoParam.setType(ParcelNoType.ParcelNo);
            // 设置编号
            parcelNoParam.setValue("PX21d9333f0003000");
            // 创建面单参数
            ParcelLabelParam labelParam = new ParcelLabelParam();
            // 面单打印纸类型：1 特殊纸张(10*15厘米）、2 A4纸张(21*29.7厘米)、3 国内面单(18*10厘米)4 标签纸 (10*10厘米)、5 标签纸(8.05*9厘米)。目前仅支持4 (10*10厘米)，如有其他需要请与我们联系
            labelParam.setLabelType("4");
            // 生成面单的格式: PDF文件、JPG文件。默认PDF文件
            labelParam.setLabelFormat("JSON");
            // 生成面单文件的Base64的编码
            labelParam.setLabelContent("{\"parcel_no\":\"PX21d9333f0003000\",\"order_no\":\"66666666\",\"tracking_no\":\"11112222\",\"reference_no\":\"234-11-2234-28\",\"provider\":{\"name\":\"圆通\",\"id\":\"px11223344\"},\"sender\":{\"country\":\"CN\",\"region_l1\":\"江苏省\",\"region_l2\":\"南京市\",\"address\":\"xxx街yyy号2单元1103\",\"postcode\":\"200012\",\"company_name\":\"sender company\",\"name\":\"frist last\",\"phone\":\"13422223333\",\"email\":\"xx@abc.com\"},\"recipient\":{\"country\":\"CN\",\"region_l1\":\"江苏省\",\"region_l2\":\"南京市\",\"address\":\"xxx街yyy号2单元1103\",\"postcode\":\"200012\",\"company_name\":\"sender company\",\"name\":\"frist last\",\"phone\":\"13422223333\",\"email\":\"xx@abc.com\"}}");
            // 包裹的国际快递单号
            labelParam.setTrackingNo("T10000000004");
            // 执行成功后无返回结果，不抛异常，即为正常执行
            getProviderApi().reportLabel(parcelNoParam, labelParam);
        } catch (ApiException e) {
            e.printStackTrace();
        }

    }

    private static ProviderApi getProviderApi() {
        return new ProviderApi(Constants.url, Constants.apiKey, Constants.apiSecret);
    }
}
