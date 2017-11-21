package com.xz.app.transfer.domain;

import java.math.BigDecimal;

public class Price {
    private Integer id;

    private Integer ac02id;

    private Long ac01id;

    private String aa01002;

    private String aa02001;

    private String aa02002;

    private BigDecimal ac02001;

    private BigDecimal ac02002;

    private BigDecimal ac02003;

    private BigDecimal ac02004;

    private String aa11001;

    private Integer ab13004;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAc02id() {
        return ac02id;
    }

    public void setAc02id(Integer ac02id) {
        this.ac02id = ac02id;
    }

    public Long getAc01id() {
        return ac01id;
    }

    public void setAc01id(Long ac01id) {
        this.ac01id = ac01id;
    }

    public String getAa01002() {
        return aa01002;
    }

    public void setAa01002(String aa01002) {
        this.aa01002 = aa01002 == null ? null : aa01002.trim();
    }

    public String getAa02001() {
        return aa02001;
    }

    public void setAa02001(String aa02001) {
        this.aa02001 = aa02001 == null ? null : aa02001.trim();
    }

    public String getAa02002() {
        return aa02002;
    }

    public void setAa02002(String aa02002) {
        this.aa02002 = aa02002 == null ? null : aa02002.trim();
    }

    public BigDecimal getAc02001() {
        return ac02001;
    }

    public void setAc02001(BigDecimal ac02001) {
        this.ac02001 = ac02001;
    }

    public BigDecimal getAc02002() {
        return ac02002;
    }

    public void setAc02002(BigDecimal ac02002) {
        this.ac02002 = ac02002;
    }

    public BigDecimal getAc02003() {
        return ac02003;
    }

    public void setAc02003(BigDecimal ac02003) {
        this.ac02003 = ac02003;
    }

    public BigDecimal getAc02004() {
        return ac02004;
    }

    public void setAc02004(BigDecimal ac02004) {
        this.ac02004 = ac02004;
    }

    public String getAa11001() {
        return aa11001;
    }

    public void setAa11001(String aa11001) {
        this.aa11001 = aa11001 == null ? null : aa11001.trim();
    }

    public Integer getAb13004() {
        return ab13004;
    }

    public void setAb13004(Integer ab13004) {
        this.ab13004 = ab13004;
    }
}