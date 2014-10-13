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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author adelmir
 */
public class LattesMap extends Mapper<LongWritable, Text, Text, Text> {

    // Create Path, BufferedReader and Text variables
    Path file_path;
    BufferedReader buffer_reader;
    Text tweet_values = new Text();

    /**
     * @param key
     * @param value
     * @param context
     */
    public void map(LongWritable key, Text value, Context context) {
        try {

            // Create configuration for Map
            Configuration map_config = new Configuration();

            // Load Hadoop core files in configuration
            map_config.addResource(new Path("/user/adelmir/hadoop-1.2.1/bin/core-site.xml"));
            map_config.addResource(new Path("/user/adelmir/hadoop-1.2.1/bin/hdfs-site.xml"));

            // Create variables
            String searchkeyword = "";

            // Open file from the file path
            file_path = new Path("/home/adelmir/pdf/arquivo.txt");
            FileSystem file_system = FileSystem.get(URI.create("/home/adelmir/hadoop-1.2.1/src/examples/keys.txt"), new Configuration());

            // Load buffer reader
            buffer_reader = new BufferedReader(new InputStreamReader(file_system.open(file_path)));

            while (buffer_reader.ready()) {
                searchkeyword = buffer_reader.readLine().trim();

            }

            // Get key value
            final Text key_value = new Text(searchkeyword);

            // Check value and take decision
            if (value == null) {
                return;
            } else {
                StringTokenizer string_tokens = new StringTokenizer(value.toString(), ",");
                int count = 0;

                while (string_tokens.hasMoreTokens()) {
                    count++;
                    if (count <= 1) {
                        continue;
                    }

                    String new_tweet_value = string_tokens.nextToken().toLowerCase().trim().replaceAll("\\*", "");

                    if (new_tweet_value.contains(searchkeyword.toLowerCase().trim())) {
                        tweet_values.set(new_tweet_value);
                        context.write(key_value, tweet_values);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
