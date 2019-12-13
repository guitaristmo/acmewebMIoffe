package com.acme.statusmgr;

import com.acme.statusmgr.CommandExecution.GetBasicServerStatusCommand;
import com.acme.statusmgr.CommandExecution.GetDetailedServerStatusCommand;
import com.acme.statusmgr.CommandExecution.GetDiskStatusCommand;
import com.acme.statusmgr.CommandExecution.SimpleExecutor;
import com.acme.statusmgr.beans.DiskStatus;
import com.acme.statusmgr.beans.ServerStatusInterface;
import com.acme.statusmgr.beans.complex.ComplexServerStatusFactory;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CommandTests
{
    @Test
    public void basicStatusServerCommandTest()
    {
        //Arrange
        GetBasicServerStatusCommand cmd = new GetBasicServerStatusCommand(1, "Server Status requested by ", "Noach");
        SimpleExecutor executor = new SimpleExecutor(cmd);

        //Act
        executor.executeCommand();
        ServerStatusInterface result = cmd.getResult();

        //Assert
        assertEquals("Server is up", result.getStatusDesc());
        assertEquals(1, result.getId());
    }

    @Test
    public void detailedStatusServerCommandTest()
    {
        //Arrange
        ArrayList<String> details = new ArrayList<>();
        details.add("operations");
        details.add("memory");
        GetDetailedServerStatusCommand cmd = new GetDetailedServerStatusCommand(1, "Server Status requested by ",
                                        "Bob", details, "complex", new ComplexServerStatusFactory());
        SimpleExecutor executor = new SimpleExecutor(cmd);

        //Act
        executor.executeCommand();
        ServerStatusInterface result = cmd.getResult();

        //Assert
        assertEquals("Server is up, and is operating normally, and its memory is Running low", result.getStatusDesc());
        assertEquals(1, result.getId());
    }

    @Test
    public void diskStatusCommandTest()
    {
        //Arrange
        GetDiskStatusCommand cmd = new GetDiskStatusCommand(1, "Disk Status requested by ", "Noach");
        SimpleExecutor executor = new SimpleExecutor(cmd);

        //Act
        executor.executeCommand();
        DiskStatus result = cmd.getResult();

        //Assert
        assertEquals("chkdsk c:", result.getDiskCommand());
        assertEquals(1, result.getId());
    }
}