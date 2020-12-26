package com.mxcsystem.demo.entity.WX;

public class WXMessage {
    private String id;
    private String openid;
    private String question;
    private String answer;

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getOpenid () {
        return openid;
    }

    public void setOpenid (String openid) {
        this.openid = openid;
    }

    public String getQuestion () {
        return question;
    }

    public void setQuestion (String question) {
        this.question = question;
    }

    public String getAnswer () {
        return answer;
    }

    public void setAnswer (String answer) {
        this.answer = answer;
    }
}
