package com.fauzan.alamitest.worker;

import com.fauzan.alamitest.bean.Nasabah;

public class WorkerAverageBalance implements Runnable {
    private Nasabah nasabah;

    public WorkerAverageBalance(Nasabah nasabah) {
        this.nasabah = nasabah;
    }

    @Override
    public void run() {
        this.nasabah.calculateAverageBalanced(Thread.currentThread().getId());
    }
}
