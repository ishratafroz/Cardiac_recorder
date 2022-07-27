package com.example.cardiacrecorder;

public class cardiaclist implements Comparable<cardiaclist> {
    String sys_v, dias_v, pulse_v, pulse_status, pressure_status, d, t;

    public cardiaclist(String sys_v, String dias_v, String pulse_v,
                       String pulse_status, String pressure_status, String d, String t) {
        this.sys_v = sys_v;
        this.dias_v = dias_v;
        this.pressure_status = pressure_status;
        this.pulse_v = pulse_v;
        this.pulse_status = pulse_status;
        this.d = d;
        this.t = t;
        // constructor
    }

    public cardiaclist() {
    }

    public String getSys_v() {
        return sys_v;
    }

    public void setSys_v(String sys_v) {
        this.sys_v = sys_v;
    }

    public String getDias_v() {
        return dias_v;
    }

    public void setDias_v(String dias_v) {
        this.dias_v = dias_v;
    }

    public String getPulse_v() {
        return pulse_v;
    }

    public void setPulse_v(String pulse_v) {
        this.pulse_v = pulse_v;
    }

    public String getPulse_status() {
        return pulse_status;
    }

    public void setPulse_status(String pulse_status) {
        this.pulse_status = pulse_status;
    }

    public String getPressure_status() {
        return pressure_status;
    }

    public void setPressure_status(String pressure_status) {
        this.pressure_status = pressure_status;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public int compareTo(cardiaclist o) {
        return this.sys_v.compareTo(o.sys_v);
    }
}