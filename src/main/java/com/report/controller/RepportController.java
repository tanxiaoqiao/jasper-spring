package com.report.controller;

import com.report.annotation.JpaPage;
import com.report.repository.PDFBeanRepository;
import com.report.util.ExportUtil;
import com.report.util.JpaUtils;
import net.sf.jasperreports.engine.*;
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
import java.util.HashMap;
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

    @GetMapping("/{reportName}")
    public void getReportByParam(@PathVariable("reportName") String reportName,
                                 @RequestParam(required = false) Map<String, Object> parameters,
                                 HttpServletResponse response) throws JRException, IOException, SQLException {

        parameters = parameters == null ? new HashMap<>() : parameters;
        ClassPathResource resource = new ClassPathResource("jasper/" + reportName + ".jasper");
        InputStream jasperStream = resource.getInputStream();

        JasperReport report = (JasperReport) JRLoader.loadObject(jasperStream);
      //  JasperReport report = JasperCompileManager.compileReport("Users/xiaoqiap/"+ reportName + ".jrxml");

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource.getConnection());        // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    }
    @GetMapping
    @JpaPage
    public void downLoad( HttpServletResponse response) throws JRException, IOException, SQLException {
        Page all = pdfBeanRepository.findAll(JpaUtils.getSpecification(), JpaUtils.getPageRequest());
        ExportUtil.exportPdf(all.getContent(),"ca",response);

    }
}
