package io.parcelx.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum ParcelNoType {
    ParcelNo("PARCEL_NO"), OrderNo("ORDER_NO"), TrackingNo("TRACKING_NO");

    public static final ParcelNoType Default = ParcelNo;
    private final String value;

    ParcelNoType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ParcelNoType fromValue(String value) {
        for (ParcelNoType type : ParcelNoType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return ParcelNoType.Default;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
