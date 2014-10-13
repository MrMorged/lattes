/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uft.lattes.mapred;

/**
 *
 * @author adelmir
 */
/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.IOException;

//import org.apache.blur.mapreduce.lib.BlurMapReduceUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class BlurExampleIndexer {

  public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
    Configuration configuration = new Configuration();
    String[] otherArgs = new GenericOptionsParser(configuration, args).getRemainingArgs();
    if (otherArgs.length != 3) {
      System.err.println("Usage: blurexampleindexer <in> <connection string> <table>");
      System.exit(2);
    }

    String input = otherArgs[0];
    String connectionStr = otherArgs[1];
    String table = otherArgs[2];

    Job job = new Job(configuration, "Blur Indexer");
    job.setInputFormatClass(TextInputFormat.class);
    job.setNumReduceTasks(0);
    //job.setMapperClass(BlurExampleMapper.class);
    //BlurMapReduceUtil.setupWriterJob(job, connectionStr, table);

    FileInputFormat.addInputPath(job, new Path(input));
    boolean waitForCompletion = job.waitForCompletion(true);
    System.exit(waitForCompletion ? 0 : 1);
  }
}
