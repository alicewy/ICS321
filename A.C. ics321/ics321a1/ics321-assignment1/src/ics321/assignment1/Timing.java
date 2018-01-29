package ics321.assignment1;

public class Timing {
    long startTime;
    long stopTime;
    long millisPerOperation = 0;
    long maxResponseTimeMillis = 0;
    long sleepTime = 0;

    boolean pauseCalled = false;

    public Timing() {
        this(0, 0);
    }

    public Timing(long millisPerOperation) {
        this(millisPerOperation, 0);
    }

    public Timing(long millisPerOperation, long maxResponseTimeMillis) {
        stopTime = 0;
        startTime = 0;
        this.millisPerOperation = millisPerOperation;
        this.maxResponseTimeMillis = maxResponseTimeMillis;
    }

    public void start() {
        startTime = System.currentTimeMillis();
        sleepTime = 0;
        pauseCalled = false;
    }

    public void stop() {
        stopTime = System.currentTimeMillis();
    }

    public void pause() {
        pauseCalled = true;
        sleepTime = millisPerOperation - (stopTime - startTime);
        if (sleepTime <= 0)
            sleepTime = 0;
        try {
            if (sleepTime > 0)
                Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(long milliSeconds) {
        try {
            if (milliSeconds > 0)
                Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String print(String leader) {
        String ret = leader + " = "
                     + (stopTime - startTime) + " msec";
        if (pauseCalled) {

            ret = ret + "\n\tAllotted time " + millisPerOperation + " msec"
                  + "\n\tSleep time " + sleepTime + " msec";
            String bar = printBar();
            ret = ret + "\n\t" + bar;
        }
        return ret;
    }

    String printBar() {
        int barLength = 20;
        int i = 0;
        String bar = "";
        long elapsedTimeMillis = stopTime - startTime;

        //20 second bar
        long elapsedTimeDots = elapsedTimeMillis/1000;
        for (int p = 1; p<=23; p++) {
            if (p == 1) bar = bar + "0";
            if (p <= 22)
                if (elapsedTimeDots >= p) bar = bar + "*";
                else bar = bar + ".";
            else if (elapsedTimeDots >= p) bar = bar + ">";
            else bar = bar + ".";
            if (p == 5) bar = bar + "5";
            if (p == 10) bar = bar + "10";
            if (p == 15) bar = bar + "15";
            if (p == 20) bar = bar + "20";
        }
        /*
        // 5 second bar
        long elapsedTimeDots = elapsedTimeMillis/250;
        for (int p = 1; p<=23; p++) {
        	if (p == 1) bar = bar + "0";
        	if (p <= 22)
        		if (elapsedTimeDots >= p) bar = bar + "*"; else bar = bar + ".";
        	else
        		if (elapsedTimeDots >= p) bar = bar + ">"; else bar = bar + ".";
        	if (p == 20) bar = bar + "5";
        	if (p == 40) bar = bar + "10";
        	if (p == 60) bar = bar + "15";
        	if (p == 80) bar = bar + "20";
        }
        */
        // print elapsed time in msec
        bar = bar + " ";
        if (elapsedTimeMillis < 10) bar = bar + "    " +  elapsedTimeMillis + " msec, ";
        else if (elapsedTimeMillis < 100) bar = bar + "   " +  elapsedTimeMillis + " msec, ";
        else if (elapsedTimeMillis < 1000) bar = bar + "  " +  elapsedTimeMillis + " msec, ";
        else if (elapsedTimeMillis < 10000) bar = bar + " " +  elapsedTimeMillis + " msec, ";
        else bar = bar + elapsedTimeMillis + " msec, ";

        bar = " " + bar;
        if (millisPerOperation == 0) bar = " " + bar;
        if (millisPerOperation > elapsedTimeMillis) bar = " " + bar;
        else bar = "A" + bar;
        if (maxResponseTimeMillis == 0) bar = " " + bar;
        if (maxResponseTimeMillis > elapsedTimeMillis) bar = " " + bar;
        else bar = "R" + bar;

        return bar;
    }

    String printBar_old() {
        int barLength = 20;
        int i = 0;
        String bar = "";
        if (pauseCalled) {
            int opLength = new Long(barLength * (stopTime - startTime)
                                    / millisPerOperation).intValue();
            for (i = 0; i < Math.min(opLength, barLength); i++)
                bar = bar + "*";
            for (i = opLength; i < barLength; i++)
                bar = bar + ".";
            bar = bar + "|";
            for (i = barLength; i < Math.min(opLength, barLength * 2); i++)
                bar = bar + "*";
            for (i = Math.max(opLength, barLength); i < barLength * 2; i++)
                bar = bar + ".";
            if (opLength > barLength * 2)
                bar = bar + "?";
            else
                bar = bar + "|";
            bar = bar + " " + (stopTime - startTime) + " msec, " + millisPerOperation + " msec allotted\t";
        }
        return bar;
    }
}
