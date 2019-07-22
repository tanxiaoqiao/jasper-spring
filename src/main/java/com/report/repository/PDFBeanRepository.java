package com.report.repository;

import com.report.entity.PDFBean;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Kris
 * @Date: 2019-07-16  16:32
 */
@Repository
public  interface PDFBeanRepository extends CrudRepository<PDFBean,Long> , JpaSpecificationExecutor<PDFBean> {

}
