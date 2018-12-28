/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.filter;

/**
 *
 * @author Gold
 */
public class Log {
    private History[] history;

    private String[] input;

    private String errors;

    private String authentication;

    private String time_spent;

    private String attempts;

    private String start_time;

    private String success;

    private String mobile;

    public History[] getHistory ()
    {
        return history;
    }

    public void setHistory (History[] history)
    {
        this.history = history;
    }

    public String[] getInput ()
    {
        return input;
    }

    public void setInput (String[] input)
    {
        this.input = input;
    }

    public String getErrors ()
    {
        return errors;
    }

    public void setErrors (String errors)
    {
        this.errors = errors;
    }

    public String getAuthentication ()
    {
        return authentication;
    }

    public void setAuthentication (String authentication)
    {
        this.authentication = authentication;
    }

    public String getTime_spent ()
    {
        return time_spent;
    }

    public void setTime_spent (String time_spent)
    {
        this.time_spent = time_spent;
    }

    public String getAttempts ()
    {
        return attempts;
    }

    public void setAttempts (String attempts)
    {
        this.attempts = attempts;
    }

    public String getStart_time ()
    {
        return start_time;
    }

    public void setStart_time (String start_time)
    {
        this.start_time = start_time;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public String getMobile ()
    {
        return mobile;
    }

    public void setMobile (String mobile)
    {
        this.mobile = mobile;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [history = "+history+", input = "+input+", errors = "+errors+", authentication = "+authentication+", time_spent = "+time_spent+", attempts = "+attempts+", start_time = "+start_time+", success = "+success+", mobile = "+mobile+"]";
    }
}
