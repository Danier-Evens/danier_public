package com.danier.web.control;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.danier.web.mapper.WordCountMap;
import com.danier.web.reducer.WordCountReducer;

/**
 * @author Danier
 * @data 2014-11-19
 */
public class WordCountJob {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("please input init path!!");
            return;
        }
        try {
            runJob(args);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void runJob(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "WordCountJob");
        job.setJarByClass(WordCountJob.class);
        job.setMapOutputKeyClass(Text.class);   // map输出key格式
        job.setMapOutputValueClass(LongWritable.class); // map输出value格式
        FileInputFormat.setInputPaths(job, new Path(args[0]));// 待处理数据输入路径
        FileOutputFormat.setOutputPath(job, new Path(args[2]));// 处理后的结构管输出路径
        job.setMapperClass(WordCountMap.class);
        job.setReducerClass(WordCountReducer.class);
        job.waitForCompletion(true);
        if (job.isSuccessful()) {
            System.exit(0);
        }
    }
}
