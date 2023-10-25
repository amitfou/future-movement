package com.futuremovement.app.web;

import ch.qos.logback.core.OutputStreamAppender;
import com.futuremovement.app.service.TradeSummaryService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStreamWriter;

@RestController
public class TradeSummaryController {

    @Autowired
    private TradeSummaryService service;
    @GetMapping(value = "/summaryReport")
    public void getSummary(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"report.csv\"");
        service.tradeSummaryReport(response.getWriter());
    }}
