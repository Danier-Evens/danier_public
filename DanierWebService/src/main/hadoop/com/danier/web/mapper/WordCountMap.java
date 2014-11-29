package com.danier.web.mapper;

import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 单词计数map任务
 * @author Danier
 * @data 2014-11-19
 */
public class WordCountMap extends Mapper<LongWritable, Text, Text, LongWritable> {

    private Text text = new Text();

    private LongWritable writable = new LongWritable(1l);

    @Override
    protected void map(LongWritable key, Text value, Context context) {
        try {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                text.set(itr.nextToken());
                context.write(text, writable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
