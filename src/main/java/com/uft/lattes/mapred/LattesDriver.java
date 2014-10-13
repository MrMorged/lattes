/*
 * Copyright 2014 adelmir.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uft.lattes.mapred;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 *
 * @author adelmir
 */
public class LattesDriver {

    public static void main(String[] args) throws Exception {

        // Initiate configuration	
        Configuration configx = new Configuration();

        // Add resource files
        configx.addResource(new Path("/user/adelmir/hadoop-1.2.1/bin/core-site.xml"));
        configx.addResource(new Path("/user/adelmir/hadoop-1.2.1/bin/hdfs-site.xml"));

        // Create MapReduce job 
        Job lattesmapjob = new Job(configx, "MapRedDriver.class");
        lattesmapjob.setJarByClass(LattesDriver.class);
        lattesmapjob.setJobName("MapRed MapReduce Job");

        // Set output kay and value class
        lattesmapjob.setOutputKeyClass(Text.class);
        lattesmapjob.setOutputValueClass(Text.class);

        // Set Map class
        lattesmapjob.setMapperClass(LattesMap.class);

        // Set Combiner class
        lattesmapjob.setCombinerClass(LattesReducer.class);

        // Set Reducer class
        lattesmapjob.setReducerClass(LattesReducer.class);

        // Set Map output key and value classes
        lattesmapjob.setMapOutputKeyClass(Text.class);
        lattesmapjob.setMapOutputValueClass(Text.class);

        // Set number of reducer tasks
        lattesmapjob.setNumReduceTasks(10);

        // Set input and output format classes
        lattesmapjob.setInputFormatClass(TextInputFormat.class);
        lattesmapjob.setOutputFormatClass(TextOutputFormat.class);

        // Set input and output path
        FileInputFormat.addInputPath(lattesmapjob, new Path("/home/adelmir/hadoop-1.2.1/src/examples/input"));
        FileOutputFormat.setOutputPath(lattesmapjob, new Path("/home/adelmir/hadoop-1.2.1/src/examples/output"));

        // Start MapReduce job
        lattesmapjob.waitForCompletion(true);
    }
}
