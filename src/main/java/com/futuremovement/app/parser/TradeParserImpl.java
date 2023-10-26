package com.futuremovement.app.parser;

import com.futuremovement.app.domain.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TradeParserImpl implements TradeParser<Resource> {
    private static Logger LOG = LoggerFactory.getLogger(TradeParserImpl.class);

    @Autowired
    private TradeAssembler<String> tradeAssembler;
    @Override
    public List<Trade> parse(Resource resource) {
        LOG.info("parsing the given trade input file from - {}", resource.toString());
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(resource.getInputStream())).lines()) {
            return stream.map(line -> tradeAssembler.assemble(line))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOG.error("Failed to parse the resource " + resource, e);
            throw new RuntimeException("Cannot read the resource.", e);
        }
    }
}
