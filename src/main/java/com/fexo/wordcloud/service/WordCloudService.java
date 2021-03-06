package com.fexo.wordcloud.service;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 *  生成词云
 *  @author  fexo
 */
@Service
public class WordCloudService {



    public String getImg(String text,OutputStream outputStream) throws IOException {
        //建立词频分析器，设置词频，以及词语最短长度，此处的参数配置视情况而定即可
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);

        //引入中文解析器
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());
        //指定文本文件路径，生成词频集合
        List<String> stringList = Arrays.asList(text.split("\\n"));
        final List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load(stringList);
        //设置图片分辨率
        Dimension dimension = new Dimension(800,800);
        //此处的设置采用内置常量即可，生成词云对象
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        //设置边界及字体
        wordCloud.setPadding(2);
        java.awt.Font font = new java.awt.Font("STSong-Light", 2, 20);
        //设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));
        wordCloud.setKumoFont(new KumoFont(font));
        //设置背景色
        wordCloud.setBackgroundColor(new Color(255,255,255));
        //设置背景图层为圆形
        wordCloud.setBackground(new CircleBackground(400));
        wordCloud.setFontScalar(new SqrtFontScalar(12, 45));
        //生成词云
        wordCloud.build(wordFrequencyList);
        wordCloud.writeToStreamAsPNG(outputStream);

        String   fileName= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        return fileName.toString() + ".png";
    }

}
