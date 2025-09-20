package com.wingflare.lib.scheduler;



public class DefaultExceptionHandler implements TaskExceptionHandler {

    @Override
    public void handleException(Task task, Throwable throwable) {
        System.err.println("Task " + task.getId() + " execution failed: " + throwable.getMessage());
        throwable.printStackTrace();
    }

}
