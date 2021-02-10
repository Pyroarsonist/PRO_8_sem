package com.gmail.velikiydan.task_2;

public class Drop {
    // Message sent from producer
    // to consumer.
    private int number;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    synchronized int take() {
        // Wait until message is
        // available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        notifyAll();

        return number;
    }

    synchronized void put(int number) {
        // Wait until message has
        // been retrieved.
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.number = number;
        // Notify consumer that status
        // has changed.
        notifyAll();
    }
}