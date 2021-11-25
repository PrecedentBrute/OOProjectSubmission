package monopoly;

public class SyncInput {
    private String input;
    private boolean isReady = false;

    SyncInput() {
        input = "";
    }

    public synchronized void setInput(String s) {
        while (isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        input = s;
        isReady = true;
        notify();
    }

    public synchronized String getInput() {
        while(!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isReady = false;
        notify();
        return input;
    }

    public boolean isReady() {
        return isReady;
    }
}

