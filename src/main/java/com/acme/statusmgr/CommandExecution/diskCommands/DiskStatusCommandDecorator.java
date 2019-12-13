package com.acme.statusmgr.CommandExecution.diskCommands;

import com.acme.statusmgr.beans.DiskStatus;

public abstract class DiskStatusCommandDecorator implements IDiskStatusCommand
{
    private IDiskStatusCommand baseDiskStatusCommand;

    @Override
    public DiskStatus getResult() { return baseDiskStatusCommand.getResult(); }

    @Override
    public void execute() { baseDiskStatusCommand.execute(); }
}
