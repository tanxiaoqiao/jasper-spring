package com.report.controller;

import com.report.annotation.JpaPage;
import com.report.entity.Content;
import com.report.entity.PDFBean;
import com.report.repository.ContentRepository;
import com.report.repository.PDFBeanRepository;
import com.report.util.ExportUtil;
import com.report.util.JpaUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Kris
 * @Date: 2019-07-16  16:30
 */
@RestController
public class RepportController {

    @Resource
    private DataSource dataSource;

    @Autowired
    ApplicationContext appContext;

    @Autowired
    PDFBeanRepository pdfBeanRepository;


    @Autowired
    ContentRepository contentRepository;

    @GetMapping("/{reportName}")
    @JpaPage
    public void getReportByParam(@PathVariable("reportName") String reportName,
                                 HttpServletResponse response) throws JRException, IOException, SQLException {

        ClassPathResource resource = new ClassPathResource("jasper/" + reportName + ".jasper");
        InputStream jasperStream = resource.getInputStream();
        JasperReport report = (JasperReport) JRLoader.loadObject(jasperStream);
        Page<PDFBean> all = pdfBeanRepository.findAll(JpaUtils.getSpecification(), JpaUtils.getPageRequest());
        Page<Content> table = contentRepository.findAll(JpaUtils.getSpecification(), JpaUtils.getPageRequest());
        Map<String, Object> m = new HashMap<>();
        List<String> personList = new ArrayList<>();
                personList.add("person1");
                personList.add("person2");
                personList.add("person3");
        m.put("content_list", personList); //将子报表的jasper传入主报表的参数"sub2"；

//将子报表的datasource传给主报表的参数"datasetsub2"；
      //  m.put("datasetsub2", new JRBeanCollectionDataSource(sub2datasource));
        for (int i = 0; i < table.getContent().size(); i++) {
            all.getContent().get(i).setContent_name(table.getContent().get(i).getName());
        }
        JRDataSource ds = new JRBeanCollectionDataSource(all.getContent(), true);

        JasperPrint jasperPrint2 = JasperFillManager.fillReport(report, m, ds);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint2, outputStream);

    }

    @GetMapping
    @JpaPage
    public void downLoad(@RequestParam("reportName") String reportName, HttpServletResponse response) throws JRException, IOException, SQLException {
        Page all = pdfBeanRepository.findAll(JpaUtils.getSpecification(), JpaUtils.getPageRequest());
        ExportUtil.exportPdf(all.getContent(), reportName, response);

    }
}
