package com.mphasis.parent.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mphasis.parent.entity.FileLoad;

@Component
public class FileLoadProcessor implements ItemProcessor<FileLoad, FileLoad> {

        @Autowired
        private JdbcTemplate jdbcTemplate;
        
        @Override
        public FileLoad process(FileLoad item) throws Exception {
            String sql = "SELECT COUNT(*) FROM file_load WHERE file_name = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, item.getFileName());
            System.out.println("Processing record with fileName: " + item.getFileName() + " | Count result: " + count);

            if (count != null && count > 0) {
                System.out.println("Skipping record with fileName: " + item.getFileName());
                return null;
            }
            System.out.println("Processing new record with fileName: " + item.getFileName());
            return item;
        }
    }
