package com.fauzan.alamitest.utils;

import com.fauzan.alamitest.bean.Nasabah;
import com.opencsv.bean.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

public class FileUtil {
    private String inputFile;
    private String outputFile;
    private List<Nasabah> nasabahList;

    public FileUtil(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void read(){
        try (FileReader fileReader = new FileReader(this.inputFile)) {
            CsvToBean<Nasabah> csvToBean = new CsvToBeanBuilder<Nasabah>(fileReader)
                    .withType(Nasabah.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .build();

            this.nasabahList = csvToBean.parse();
        } catch (Exception ex){
            new CustomLogger(FileUtil.class).error(ex);
        }

    }

    public void write(){
        try (Writer fileWriter = new FileWriter(this.outputFile)){
            fileWriter.append("id;Nama;Age;Balanced;No 2b Thread-No;No 3 Thread-No;Previous Balanced;Average Balanced;No 1 Thread-No;Free Transfer;No 2a Thread-No");
            fileWriter.append("\n");

            ColumnPositionMappingStrategy<Nasabah> mappingStrategy = new ColumnPositionMappingStrategy<Nasabah>();
            mappingStrategy.setType(Nasabah.class);

            String[] columns = new String[]
                    { "id","name", "age", "balanced", "thread2b", "thread3", "previousBalanced","averageBalanced","thread1","freeTransfer","thread2a" };
            mappingStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<Nasabah> beanToCsv = new StatefulBeanToCsvBuilder<Nasabah>(fileWriter)
                    .withMappingStrategy(mappingStrategy)
                    .withSeparator(';')
                    .build();

            beanToCsv.write(this.nasabahList);
        } catch (Exception ex){
            new CustomLogger(FileUtil.class).error(ex);
        }

    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public List<Nasabah> getNasabahList() {
        return nasabahList;
    }

    public void setNasabahList(List<Nasabah> nasabahList) {
        this.nasabahList = nasabahList;
    }
}
