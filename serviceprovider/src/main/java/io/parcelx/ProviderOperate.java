package io.parcelx;

import io.parcelx.jsonrpc.ApiException;
import io.parcelx.open.api.sdk.v1.ProviderApi;
import io.parcelx.open.api.sdk.v1.model.ParcelInfoResult;
import io.parcelx.open.api.sdk.v1.model.ParcelListQueryParam;
import io.parcelx.open.api.sdk.v1.model.ParcelListQueryResult;
import io.parcelx.open.api.sdk.v1.model.ParcelNoParam;
import io.parcelx.utils.Constants;

/**
 * @author Administrator
 * @version 1.0
 * @Description
 * @date 2019/7/25 16:53
 */
public class ProviderOperate {

    /**
     * 根据ParcelNo获取包裹信息
     * @param parcelNoParam 包裹编号参数
     * @return 返回包裹信息
     * @date 2019/7/25 16:13
     */
    public static ParcelInfoResult getParcelInfo(ParcelNoParam parcelNoParam) {
        try {
            return getProviderApi().getParcelInfo(parcelNoParam);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询包裹列表
     *
     * @param queryParam 查询参数
     * @return 查询结果集
     * @date 2019/7/25 11:23
     */
    public static ParcelListQueryResult queryParcelList(ParcelListQueryParam queryParam) {
        try {
            return getProviderApi().queryParcelList(queryParam);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ProviderApi getProviderApi() {
        return new ProviderApi(Constants.url, Constants.apiKey, Constants.apiSecret);
    }
}
