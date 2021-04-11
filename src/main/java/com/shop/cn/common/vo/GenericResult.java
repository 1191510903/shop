package com.shop.cn.common.vo;

/**
 * 泛型结果类<br>
 * 可返回指定类型的结果
 *
 * @author zhangyk
 *
 */
public class GenericResult<T> extends Result {

    private static final long serialVersionUID = 6884388024939222192L;

    /** 结果对象 */
    protected T data;

    private Page page;

    /**
     * 获取结果对象
     * @return 结果对象
     */
    public T getData() {
        return data;
    }
    /**
     * 设置结果对象
     * @param object 结果对象
     */
    public void setData(T data) {
        this.data = data;
    }
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }



}
