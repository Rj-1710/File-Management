package com.mphasis.parent.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.dao.FileLoadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest // Loads full application context
@AutoConfigureMockMvc // Enables MockMvc for API testing
public class FileLoadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FileLoadRepository fileLoadRepository;

    @Autowired
    private ObjectMapper objectMapper;

	/*
	 * @Test public void testCreateFileLoad_Success() throws Exception { String
	 * fileLoadJson =
	 * "{ \"fileName\": \"test.csv\", \"status\": \"NEW\", \"recordCount\": 10 }";
	 * 
	 * mockMvc.perform(post("/api/file-loads/create")
	 * .contentType(MediaType.APPLICATION_JSON) .content(fileLoadJson))
	 * .andExpect(status().isOk())
	 * .andExpect(jsonPath("$.fileName").value("test.csv"));
	 * 
	 * assert fileLoadRepository.exists("test.csv"); }
	 */

    @Test
    public void testGetFileLoad_NotFound() throws Exception {
        mockMvc.perform(get("/api/file-loads/file/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSearchFileLoad_Success() throws Exception {
        FileLoad file1 = new FileLoad("search1.csv", "PROCESSED", 10);
        FileLoad file2 = new FileLoad("search2.csv", "PROCESSED", 15);
        fileLoadRepository.saveAll(List.of(file1, file2));

        String searchJson = "{ \"status\": \"PROCESSED\" }";

        mockMvc.perform(get("/api/file-loads/files")
                .contentType(MediaType.APPLICATION_JSON)
                .content(searchJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testDeleteFileLoad_Success() throws Exception {
        FileLoad fileLoad = new FileLoad("delete.csv", "NEW", 5);
        fileLoadRepository.save(fileLoad);

        mockMvc.perform(delete("/api/file-loads/delete/" + fileLoad.getId()))
                .andExpect(status().isNoContent());

        assert !fileLoadRepository.existsById(fileLoad.getId());
    }
}
