package com.xz.app.transfer.domain;

import java.math.BigDecimal;

/**
 * @author  wuhy on 2017/11/18.
 */
public class DataTransEntity {
    private int ac02id;
    private int ac01id;
    private String aa01002;/*名称*/
    private String aa02001;/*等级*/
    private  String aa02002;/*单位*/
    private BigDecimal ac02001;/*零售价*/
    private BigDecimal ac02002;/*批发价*/
    private BigDecimal ac02003;/*超市价*/
    private BigDecimal ac02004;/*集市价*/
    private String aa11001;/*市场名称*/
    private String ab13004;/*期号*/
    private String aa11002;/*区县编码*/

    public String getAa11002() {
        return aa11002;
    }

    public void setAa11002(String aa11002) {
        this.aa11002 = aa11002;
    }

    public void setAc02id(int ac02id) {
        this.ac02id = ac02id;
    }

    public int getAc02id() {

        return ac02id;
    }

    public void setAc01id(int ac01id) {
        this.ac01id = ac01id;
    }

    public void setAa01002(String aa01002) {
        this.aa01002 = aa01002;
    }

    public void setAa02001(String aa02001) {
        this.aa02001 = aa02001;
    }

    public void setAa02002(String aa02002) {
        this.aa02002 = aa02002;
    }

    public void setAc02001(BigDecimal ac02001) {
        this.ac02001 = ac02001;
    }

    public void setAc02002(BigDecimal ac02002) {
        this.ac02002 = ac02002;
    }

    public void setAc02003(BigDecimal ac02003) {
        this.ac02003 = ac02003;
    }

    public void setAc02004(BigDecimal ac02004) {
        this.ac02004 = ac02004;
    }

    public void setAa11001(String aa11001) {
        this.aa11001 = aa11001;
    }

    public void setAb13004(String ab13004) {
        this.ab13004 = ab13004;
    }

    public int getAc01id() {
        return ac01id;
    }

    public String getAa01002() {
        return aa01002;
    }

    public String getAa02001() {
        return aa02001;
    }

    public String getAa02002() {
        return aa02002;
    }

    public BigDecimal getAc02001() {
        return ac02001;
    }

    public BigDecimal getAc02002() {
        return ac02002;
    }

    public BigDecimal getAc02003() {
        return ac02003;
    }

    public BigDecimal getAc02004() {
        return ac02004;
    }

    public String getAa11001() {
        return aa11001;
    }

    public String getAb13004() {
        return ab13004;
    }
}
