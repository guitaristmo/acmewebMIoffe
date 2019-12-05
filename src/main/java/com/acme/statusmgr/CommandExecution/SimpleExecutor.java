package com.acme.statusmgr.CommandExecution;

public class SimpleExecutor
{
    ICommand command;

    public SimpleExecutor(ICommand command){this.command = command;}

    public void executeCommand(){command.execute();}
}
