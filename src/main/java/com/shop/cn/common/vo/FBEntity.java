package com.shop.cn.common.vo;

import java.io.Serializable;


/**
 * Base class to derive entity classes from.
 *
 * @author Chun Cao
 */

public class FBEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
    	return id;
    }

    public void setId(Long id) {
    	this.id = id;
    }

    /*
         * (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }

        FBEntity that = (FBEntity) obj;

        return this.id.equals(that.getId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
