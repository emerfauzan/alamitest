package com.fauzan.alamitest.worker;

import com.fauzan.alamitest.bean.Nasabah;

public class WorkerBenefit implements Runnable {
    private Nasabah nasabah;

    public WorkerBenefit(Nasabah nasabah) {
        this.nasabah = nasabah;
    }

    @Override
    public void run() {
        this.nasabah.calculateBenefit(Thread.currentThread().getId());
    }
}
