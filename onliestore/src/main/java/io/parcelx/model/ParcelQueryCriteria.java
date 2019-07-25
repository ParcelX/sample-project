package io.parcelx.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ParcelQueryCriteria {

    /**
     * 查询已创建状态的包裹
     */
    CREATED("CREATED"),
    /**
     * 查询已经提交状态的包裹
     */
    COMMITTED("COMMITTED"),
    /**
     * 查询正在处理中的包裹
     */
    PROCESSING("PROCESSING"),
    /**
     * 查询还没有面单数据的包裹
     */
    MISSING_LABEL("MISSING_LABEL"),
    /**
     * 查询还没有物流单号的包裹
     */
    MISSING_TRACKING_NUMBER("MISSING_TRACKING_NUMBER"),
    /**
     * 查询异常的包裹
     */
    EXCEPTION("EXCEPTION"),
    /**
     * 查询服务完成的包裹
     */
    SERVICE_COMPLETE("SERVICE_COMPLETE"),
    /**
     * 查询已经被电商确认服务完成的包裹
     */
    CONFIRM_COMPLETE("CONFIRM_COMPLETE"),
    /**
     * 查询所有失败的包裹
     */
    FAILED("FAILED");

    @JsonValue
    private final String criteria;

    ParcelQueryCriteria(String criteria) {
        this.criteria = criteria;
    }

    @JsonCreator
    public static ParcelQueryCriteria fromValue(String value) {
        for (ParcelQueryCriteria criteria : ParcelQueryCriteria.values()) {
            if (criteria.criteria.equals(value)) {
                return criteria;
            }
        }
        return null;
    }
}
