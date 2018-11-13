package com.fexo.wordcloud.controller;

import com.fexo.wordcloud.service.WordCloudService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangfeixiong
 */
@Api(description = "生成词语的接口类")
@RestController
public class WordCloudController {

    @Autowired
    private WordCloudService service;

    @ApiOperation(value = "获取词云图片的方法")
    @PostMapping("getWordCouldImage")
    public void getWordCloudImg(@ApiParam(value = "生成词云图片的文字") @RequestBody String text,
                                HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        service.getImg(text, response.getOutputStream());
    }
}
