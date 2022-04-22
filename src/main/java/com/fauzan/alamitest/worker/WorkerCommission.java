package com.fauzan.alamitest.worker;

import com.fauzan.alamitest.bean.Nasabah;

public class WorkerCommission implements Runnable {
    private Nasabah nasabah;
    private int amount;

    public WorkerCommission(Nasabah nasabah, int amount) {
        this.nasabah = nasabah;
        this.amount = amount;
    }

    @Override
    public void run() {
        nasabah.addCommission(amount, Thread.currentThread().getId());
    }
}
