package com.acme.statusmgr.CommandExecution.diskCommands;

import com.acme.statusmgr.CommandExecution.ICommand;
import com.acme.statusmgr.beans.DiskStatus;
import com.acme.statusmgr.beans.RequestedDiskStatus;

/**
 * Interface for DiskStatus Commands
 * Used to define what a DiskStatus command does
 * for proxy pattern, to make proxies over the
 * read getDiskStatusCommand
 */
public interface IDiskStatusCommand extends ICommand
{
    RequestedDiskStatus getResult();
}