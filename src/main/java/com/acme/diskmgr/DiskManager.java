package com.acme.diskmgr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class DiskManager
{
    /**
     * simple demo to run windows check-disk 'chkdsk' command.
     * Similar code can run mac OS (unix) commands like " du -c -d1 ".
     */
    public static String checkDiskStatus()
    {
        Runtime rt = Runtime.getRuntime();

        try {
            Process chkProcess = rt.exec(new String[]{"chkdsk", "c:"});

            /**
             * This will read the output coming from the command (into our input), and collect
             * all of the output into one string.
             */
            String result = new BufferedReader(new InputStreamReader(chkProcess.getInputStream()))
                                            .lines().collect(Collectors.joining("\n"));
            return result;

        } catch (IOException e) { e.printStackTrace(); }

        return "Unable to obtain disk status";
    }
}
