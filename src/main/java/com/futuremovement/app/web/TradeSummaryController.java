package com.futuremovement.app.web;

import com.futuremovement.app.service.TradeSummaryService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

;

@RestController
public class TradeSummaryController {

    private static Logger LOG = LoggerFactory.getLogger(TradeSummaryController.class);

    @Autowired
    private TradeSummaryService service;

    @GetMapping(value = "/summaryReport")
    @CrossOrigin(origins = "*")
    public void getSummary(HttpServletResponse response) throws IOException {
        LOG.info("Received request for trade summary report");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"report.csv\"");
        service.tradeSummaryReport(response.getWriter());
    }}
