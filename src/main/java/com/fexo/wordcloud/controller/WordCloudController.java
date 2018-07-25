package com.fexo.wordcloud.controller;

import com.fexo.wordcloud.entry.Message;
import com.fexo.wordcloud.service.WordCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *  @author  wangfeixiong
 */
@RestController
public class WordCloudController {

   @Autowired
  private WordCloudService service;

    @PostMapping("getImg")
    public Message<String> getWordCloudImg(@RequestBody  String text) throws IOException {
       String filePath= service.getImg("", text);
        return new Message<>(200, filePath);
    }
}
