/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author umangjain
 */
public class jobList {
    
    private int jid;
    
    private String title;
    
    private String desc;
    
    private int pay;

    /**
     * Get the value of pay
     *
     * @return the value of pay
     */
    public int getPay() {
        return pay;
    }

    /**
     * Set the value of pay
     *
     * @param pay new value of pay
     */
    public void setPay(int pay) {
        this.pay = pay;
    }


    /**
     * Get the value of desc
     *
     * @return the value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Set the value of desc
     *
     * @param desc new value of desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }


    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Get the value of jid
     *
     * @return the value of jid
     */
    public int getJid() {
        return jid;
    }

    /**
     * Set the value of jid
     *
     * @param jid new value of jid
     */
    public void setJid(int jid) {
        this.jid = jid;
    }

}

