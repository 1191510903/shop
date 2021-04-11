package com.shop.cn.common.vo;
import com.shop.cn.common.annotation.Description;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * This class represents all active subjects.
 *
 * @author Chun Cao
 */

public class FBSubject extends FBEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;


	@Column(unique = true)
    private String identifier;

    private FBSubject superior;
    
    //物理分页
    @Description("页码")
    @Transient
    private int pageNum;

    @Description("每页显示条数")
    @Transient
    private int pageSize;
    
    protected FBSubject() {
        super();
    }

    public FBSubject(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public FBSubject getSuperior() { return superior; }

    public void setSuperior(FBSubject superior) {
        this.superior = superior;
    }
    public int getPageNum() {
    	if( pageNum > 0){
    		return pageNum;
    	}else{
    		return 1;
    	}
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {

    		return pageSize;

	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}