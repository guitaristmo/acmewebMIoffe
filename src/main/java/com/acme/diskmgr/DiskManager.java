package com.acme.diskmgr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Class that manages info about the disk
 * Singleton Object
 * Keeps track on the number of times
 * the disk command is currently running
 */
public class DiskManager
{
    private final static String[] diskCommand = new String[]{"cmd", "/C", "Dir", "/S", "C:\\*.java"};
    private static DiskManager instance = new DiskManager();
    private int testsRunning;

    private DiskManager(){}
    public static DiskManager getInstance(){return instance;}

    /**
     * checks the disk status with the chkdsk command on disk c:
     * @return string representing current status of the c: disk
     */
    public String checkDiskStatus()
    {
        testsRunning++;
        Runtime rt = Runtime.getRuntime();

        try {
            Process chkProcess = rt.exec(diskCommand);

            /*
              This will read the output coming from the command (into our input), and collect
              all of the output into one string.
             */
            String status = new BufferedReader(new InputStreamReader(chkProcess.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));

            testsRunning--;
            return status;

        } catch (IOException e) { e.printStackTrace(); }

        testsRunning--;
        return "Unable to obtain disk status";
    }

    public int numTestsRunning(){return testsRunning;}
}
