package com.fauzan.alamitest.bean;

import com.opencsv.bean.CsvBindByName;

public class Nasabah {
    @CsvBindByName(column = "id")
    private long id;
    @CsvBindByName(column = "Nama")
    private String name;
    @CsvBindByName(column = "Age")
    private int age;
    @CsvBindByName(column = "Balanced")
    private long balanced;
    @CsvBindByName(column = "No 2b Thread-No")
    private long thread2b;
    @CsvBindByName(column = "No 3 Thread-No")
    private long thread3;
    @CsvBindByName(column = "Previous Balanced")
    private long previousBalanced;
    @CsvBindByName(column = "Average Balanced")
    private long averageBalanced;
    @CsvBindByName(column = "No 1 Thread-No")
    private long thread1;
    @CsvBindByName(column = "Free Transfer")
    private int freeTransfer;
    @CsvBindByName(column = "No 2a Thread-No")
    private long thread2a;

    public Nasabah(){

    }

    public Nasabah(long id, String name, int age, long balanced, long thread2b, long thread3, long previousBalanced, long averageBalanced, long thread1, int freeTransfer, long thread2a) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.balanced = balanced;
        this.thread2b = thread2b;
        this.thread3 = thread3;
        this.previousBalanced = previousBalanced;
        this.averageBalanced = averageBalanced;
        this.thread1 = thread1;
        this.freeTransfer = freeTransfer;
        this.thread2a = thread2a;
    }

    public void calculateAverageBalanced(long threadNo){
        this.averageBalanced = (balanced + previousBalanced) / 2;
        this.thread1 = threadNo;
    }

    public void calculateBenefit(long threadNo){
        if (this.balanced >= 100 && this.balanced <= 150){
            this.freeTransfer = 5;
            this.thread2a = threadNo;
        } else if (this.balanced > 150) {
            this.balanced += 25;
            this.thread2b = threadNo;
        }
    }

    public void addCommission(int amount, long threadNo){
        this.balanced += amount;
        this.thread3 = threadNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getBalanced() {
        return balanced;
    }

    public void setBalanced(long balanced) {
        this.balanced = balanced;
    }

    public long getPreviousBalanced() {
        return previousBalanced;
    }

    public void setPreviousBalanced(long previousBalanced) {
        this.previousBalanced = previousBalanced;
    }

    public long getAverageBalanced() {
        return averageBalanced;
    }

    public void setAverageBalanced(long averageBalanced) {
        this.averageBalanced = averageBalanced;
    }

    public int getFreeTransfer() {
        return freeTransfer;
    }

    public void setFreeTransfer(int freeTransfer) {
        this.freeTransfer = freeTransfer;
    }

    public long getThread2b() {
        return thread2b;
    }

    public void setThread2b(long thread2b) {
        this.thread2b = thread2b;
    }

    public long getThread3() {
        return thread3;
    }

    public void setThread3(long thread3) {
        this.thread3 = thread3;
    }

    public long getThread1() {
        return thread1;
    }

    public void setThread1(long thread1) {
        this.thread1 = thread1;
    }

    public long getThread2a() {
        return thread2a;
    }

    public void setThread2a(long thread2a) {
        this.thread2a = thread2a;
    }
}
