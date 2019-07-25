package io.parcelx;

import io.parcelx.open.api.sdk.v1.model.*;
import io.parcelx.utils.JsonConverterUtil;
import org.junit.Test;

public class AppTest {

    /**
     * 测试查询包裹列表
     * @date 2019/7/25 16:45
     */
    @Test
    public void queryParcelList() {
        ParcelListQueryParam queryParam = new ParcelListQueryParam();
        queryParam.setCriteria(ParcelQueryCriteria.COMMITTED);
        ParcelListQueryResult result = ProviderOperate.queryParcelList(queryParam);
        String jsonString = JsonConverterUtil.getJsonString(result);
        System.out.println(jsonString);
    }

    /**
     * 测试查询包裹详细信息
     * @date 2019/7/25 16:49
     */
    @Test
    public void getParcelInfo(){
        ParcelNoParam parcelNoParam = new ParcelNoParam();
        parcelNoParam.setType(ParcelNoType.ParcelNo);
        parcelNoParam.setValue("PX244284668003000");
        ParcelInfoResult result = ProviderOperate.getParcelInfo(parcelNoParam);
        String jsonString = JsonConverterUtil.getJsonString(result);
        System.out.println(jsonString);
    }
}
