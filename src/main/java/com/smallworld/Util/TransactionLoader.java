package com.smallworld.Util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.smallworld.Model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j

public class TransactionLoader {

    @Value("classpath:${filePath}")
    private Resource transactionFile;

    public List<Transaction> loadTransactions() {
        try {
            InputStream inputStream = transactionFile.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(reader, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
