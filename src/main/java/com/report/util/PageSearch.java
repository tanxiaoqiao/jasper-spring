package com.report.util;

import com.report.constant.OrderType;
import com.report.constant.PageOperator;
import lombok.Data;

import java.util.Map;

@Data
public class PageSearch {
    private String keyword;                        //关键字

    private Integer pi = 0;                            //页码

    private Integer ps = 20;                            //一页显示的项目数

    private OrderType orderType = OrderType.DESC;
    private String orderColumn = "id";

    private PageOperator opsType = PageOperator.LIST;

    private Map<String, Object> pageParameter;
    private Map<String, Object> exportParameter;


    private Map<String, Object[]> filterParameter;
    private Map<String, Object> rangeMaxParameter;
    private Map<String, Object> rangeMinParameter;

    public void setOpsType(PageOperator pageOperator) {
    }
}
