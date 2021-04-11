package com.shop.cn.common.vo;


/**
 * This class represents all passive objects in the system.
 *
 * @author Chun Cao
 */
public class FBObject extends FBEntity {

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    private String oid;

}
