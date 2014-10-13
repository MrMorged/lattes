/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.mapred;

import java.io.File;
import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author adelmir
 */
public class HdfsSyncLocalFileOutputFormat<K, V> extends FileOutputFormat<K, V> {

    public static final String PARAMETER_LOCAL_SCRATCH_PATH = "/home/adelmir/lattes/lucene/index";

    private HdfsSyncLocalFileOutputFormat committer;

    private HdfsSyncLocalFileOutputFormat(File localScratchPath, Path outputPath, TaskAttemptContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public synchronized OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException {
//
//        if (committer == null) {
//            // Create temporary local directory on the local file system as pass it to the committer.
//            File localScratchPath = new File(context.getConfiguration().get(PARAMETER_LOCAL_SCRATCH_PATH) + File.separator + "scratch" + File.separator + context.getTaskAttemptID().toString() + File.separator);
//
//            committer = new HdfsSyncLocalFileOutputFormat(localScratchPath, super.getOutputPath(context), context);
//        }
//
//        return committer;
//    }

    @Override
    public RecordWriter<K, V> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        return new RecordWriter<K, V>() {
            @Override
            public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            }

            @Override
            public void write(K key, V val) throws IOException, InterruptedException {
            }
        };
    }
}
