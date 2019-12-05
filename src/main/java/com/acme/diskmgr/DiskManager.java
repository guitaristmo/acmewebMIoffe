package com.acme.diskmgr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Class that manages info about the disk
 */
public class DiskManager
{
    private final static String[] diskCommand = new String[]{"cmd", "/C", "Dir", "/S", "C:\\*.java"};
    /**
     * checks the disk status with the chkdsk command on disk c:
     * @return string representing current status of the c: disk
     */
    public static String checkDiskStatus()
    {
        Runtime rt = Runtime.getRuntime();

        try {
            Process chkProcess = rt.exec(diskCommand);

            /*
              This will read the output coming from the command (into our input), and collect
              all of the output into one string.
             */
            return new BufferedReader(new InputStreamReader(chkProcess.getInputStream()))
                                            .lines().collect(Collectors.joining("\n"));

        } catch (IOException e) { e.printStackTrace(); }

        return "Unable to obtain disk status";
    }
}
