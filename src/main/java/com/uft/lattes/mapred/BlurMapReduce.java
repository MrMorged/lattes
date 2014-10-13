///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.uft.lattes.mapred;
//
//import com.nearinfinity.blur.thrift.BlurClient;
//import com.nearinfinity.blur.thrift.generated.Blur.Iface;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
//import org.apache.hadoop.util.GenericOptionsParser;
//
////import com.nearinfinity.blur.mapreduce.BlurMapper;
////import com.nearinfinity.blur.mapreduce.BlurTask;
////import com.nearinfinity.blur.mapreduce.example.BlurExampleIndexer;
//import java.io.IOException;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.Mapper.Context;
//
///**
// *
// * @author adelmir
// */
//public class BlurMapReduce extends Mapper {
//
//    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
//        Configuration configuration = new Configuration();
//        String[] otherArgs = new GenericOptionsParser(configuration, args).getRemainingArgs();
//        if (otherArgs.length != 2) {
//            System.err.println("Usage: blurindexer <in> <out>");
//            System.exit(2);
//        }
//        Iface client = BlurClient.getClient("controller1:40010");//para controle do trift Client
//////        TableDescriptor tableDescriptor = client.describe();
////
////        Job job = new Job(new Configuration());
////        job.setJarByClass(BlurMapReduce.class);
////        job.setMapperClass(BlurMapReduce.class);
////        job.setInputFormatClass(TextInputFormat.class);
////
////        AnalyzerDefinition ad = new AnalyzerDefinition();
////
////        TableDescriptor td = new TableDescriptor();
////        td.setShardCount(16);
//        
//        // Location in HDFS
////        td.setTableUri("hdfs://<localhost>:<9000>/lattes/lucene/table-map");
////        td.setAnalyzerDefinition(ad);
//
////    BlurTask blurTask = new BlurTask();
////    blurTask.setTableDescriptor(td);
////    blurTask.setSpinLockPath("/copy-locks");
////    blurTask.setZookeeperConnectionStr("localhost");
////    blurTask.setMaxNumberOfConcurrentCopies(10);
//        // The copy locks are used to throttle how many concurrent
//        // copies from the reducers are occuring at the same time.
//        // This is normally needed because the indexing cluster is
//        // typically larger in size than the blur cluster.
////    Job job = blurTask.configureJob(new Configuration());
////    job.setJarByClass(BlurExampleMapper.class);
//////    job.setMapperClass(BlurExampleMapper.class);
////    job.setInputFormatClass(TextInputFormat.class);
////    job.setOutputFormatClass(TextOutputFormat.class);
////
////    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
////    FileOutputFormat.setOutputPath(job, new Path(otherArgs[1], "job-" + System.currentTimeMillis()));
////    System.exit(job.waitForCompletion(true) ? 0 : 1);
////  }
////  public static class BlurExampleMapper extends BlurMapper<LongWritable, Text> {
////    @Override
////    protected void map(LongWritable k, Text value, Context context) throws IOException, InterruptedException {
////       Reset record
////      _record.clearColumns();
////
////      // Set row id
////      _record.setRowId("rowid");
////
////      // Set record id
////      _record.setRecordId("recordid");
////
////      // Set column family
////      _record.setColumnFamily("cf1");
////      
////      _record.addColumn("name", "value");
////
////      // Set the key which is usual the rowid
////      byte[] bs = _record.getRowId().getBytes();
////      _key.set(bs, 0, bs.length);
////      context.write(_key, _record);
////      _recordCounter.increment(1);
////      context.progress();
////    }
////  }
//    }
//}
