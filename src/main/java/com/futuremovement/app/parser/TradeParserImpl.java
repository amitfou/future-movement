package com.futuremovement.app.parser;

import com.futuremovement.app.domain.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TradeParserImpl implements TradeParser<ClassPathResource> {

    @Autowired
    private TradeAssembler tradeAssembler;
    @Override
    public List<Trade> parse(ClassPathResource resource) {
        try (Stream<String> stream = Files.lines(resource.getFile().toPath())) {
            return stream.map(line -> tradeAssembler.assemble(line))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
