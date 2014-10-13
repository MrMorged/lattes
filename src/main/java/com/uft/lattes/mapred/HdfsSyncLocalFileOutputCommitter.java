///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.uft.lattes.mapred;
//
//import com.nearinfinity.blur.thrift.BlurClient;
//import com.nearinfinity.blur.thrift.generated.AnalyzerDefinition;
//import com.nearinfinity.blur.thrift.generated.Blur.Iface;
//import com.nearinfinity.blur.thrift.generated.TableDescriptor;
//import java.io.File;
//import java.io.IOException;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.JobContext;
//import org.apache.hadoop.mapreduce.JobStatus.State;
//import org.apache.hadoop.mapreduce.TaskAttemptContext;
//import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
//
///**
// *
// * @author adelmir
// */
//public class HdfsSyncLocalFileOutputCommitter extends FileOutputCommitter {
//
//    public static final String PREFIX_LUCENE_INDEX_PART = "/home/adelmir/lattes/lucene/index";
//
//    private final FileSystem localFileSystem;
//    private final File localScratchPath;
//
//    private final FileSystem hdfsFileSystem;
//    private final Path hdfsSyncPath;
//
//    public HdfsSyncLocalFileOutputCommitter(File localScratchPath, Path hdfsSyncPath, TaskAttemptContext context) throws IOException {
//        super(hdfsSyncPath, context);
//
//        Configuration conf = context.getConfiguration();
//
//        this.localFileSystem = FileSystem.getLocal(conf);
//        this.localScratchPath = localScratchPath;
//
//        this.hdfsFileSystem = FileSystem.get(conf);
//        this.hdfsSyncPath = hdfsSyncPath;
//    }
//
//    public File getLocalScratchPath() {
//        return localScratchPath;
//    }
//
//    @Override
//    public void abortJob(JobContext context, State state) throws IOException {
//        //deleteLocalScratchPath();
//        super.abortJob(context, state);
//    }
//
//    @Override
//    public void abortTask(TaskAttemptContext context) {
//        //deleteLocalScratchPath();
//        super.abortTask(context);
//    }
//
//    @Override
//    // copia os arquivos locais para o HDFS
//    public void commitTask(TaskAttemptContext context) throws IOException {
//        if (localScratchPath.exists()) {
//            // percorre a arvore de diretórios local e copia usando copyFromLocalFile() 
//            // invoca o context.process(), pra garantir que o processo não morra.
//            syncToHdfs(context);
//        }
//        super.commitTask(context);
//       // deleteLocalScratchPath();
//    }
//
//    @Override
//    public boolean needsTaskCommit(TaskAttemptContext context) throws IOException {
//        return localScratchPath.exists() || super.needsTaskCommit(context);
//    }
//
//    private void syncToHdfs(TaskAttemptContext context) throws IOException {
//        if (!hdfsFileSystem.mkdirs(hdfsSyncPath)) {
//            throw new IOException(String.format("Não foi possível criar o diretório no HDFS [%s] para sincronização dos índices", hdfsSyncPath));
//        }
//
//        // Cria subdiretório para os arquivos de índice gerados pelo lucene.
//        Path indexPartHdfsFilePath = new Path(hdfsSyncPath, PREFIX_LUCENE_INDEX_PART + context.getTaskAttemptID().getTaskID().getId());
//
//        if (!hdfsFileSystem.mkdirs(indexPartHdfsFilePath)) {
//            throw new IOException(String.format("Não foi possível criar o diretório no HDFS [%s] para sincronização dos índices!", indexPartHdfsFilePath));
//        }
//
//        for (File localFile : localScratchPath.listFiles()) {
//            context.progress();
//
//            Path localFilePath = new Path("file://" + localFile.getPath());
//
//            if (!localFileSystem.exists(localFilePath)) {
//                throw new IOException(String.format("Não é possível encontrar o arquivos [%s]!", localFilePath));
//            }
//
//            Path hdfsFilePath = new Path(indexPartHdfsFilePath, localFile.getName());
//            if (hdfsFileSystem.exists(hdfsFilePath)) {
//                throw new IOException(String.format("O arquivo [%s] ja existe no HDFS!", hdfsFilePath));
//            }
//
//            hdfsFileSystem.copyFromLocalFile(localFilePath, hdfsFilePath);
//        }
//        // Blur realiza a Função MapReduce.
//        Iface client = BlurClient.getClient("controller1:40010");//para controle do trift Client
//        //TableDescriptor tableDescriptor = client.describe();
//
//        Job job = new Job(new Configuration());
//        job.setJarByClass(BlurMapReduce.class);
//        job.setMapperClass(BlurMapReduce.class);
//        job.setInputFormatClass(TextInputFormat.class);
//
//        AnalyzerDefinition ad = new AnalyzerDefinition();
//
//        TableDescriptor td = new TableDescriptor();
//        td.setShardCount(16);
//        
//       //  Location in HDFS
//        td.setTableUri("hdfs://<localhost>:<9000>/lattes/lucene/index");
//        td.setAnalyzerDefinition(ad);
//    }
//    
////    private void deleteLocalScratchPath() {
////        try {
////            FileUtils.deleteDirectory(localScratchPath);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//
//}
