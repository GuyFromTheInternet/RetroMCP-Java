package org.mcphackers.mcp.tools;

public class Throttler {
    private final int opsPerSecond;
    private final long throttle;
    private long lastActionTime = 0;
    private int ops = 0;

    public Throttler(int opsPerSecond) {
        this.opsPerSecond = opsPerSecond;
        this.throttle = 1000 / opsPerSecond;
    }

    public void throttle() {
        ops++;
        if (System.currentTimeMillis() - lastActionTime > 1000) {
            ops = 0;
            lastActionTime = System.currentTimeMillis();
        }
        if (ops > opsPerSecond) {
            try {
                Thread.sleep(throttle);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
