package com.danier.web.reducer;

import java.util.Iterator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 单词计数reduce任务
 * @author Danier
 * @data 2014-11-19
 */
public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) {
        try {
            int sum = 0;
            Iterator<LongWritable> iterator = values.iterator();
            while (iterator.hasNext()) {
                sum += iterator.next().get();
            }
            if (sum >= 0) {
                context.write(key, new LongWritable(sum));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
