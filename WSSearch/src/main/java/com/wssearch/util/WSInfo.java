package com.wssearch.util;

/**
 * Created by cristph on 2017/4/26.
 */

public class WSInfo {

    private String ah;
    private String ajlx;
    private String spcx;
    private String wslx;
    private String fymc;
    private String wsmc;
    private String matchText;

    private String _id;

    private String xmlPath;

    private String cprq;

    private float score;

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAjlx() {
        return ajlx;
    }

    public void setAjlx(String ajlx) {
        this.ajlx = ajlx;
    }

    public String getSpcx() {
        return spcx;
    }

    public void setSpcx(String spcx) {
        this.spcx = spcx;
    }

    public String getWslx() {
        return wslx;
    }

    public void setWslx(String wslx) {
        this.wslx = wslx;
    }

    public String getFymc() {
        return fymc;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getCprq() {
        return cprq;
    }

    public void setCprq(String cprq) {
        this.cprq = cprq;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getWsmc() {
        return wsmc;
    }

    public void setWsmc(String wsmc) {
        this.wsmc = wsmc;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMatchText() {
        return matchText;
    }

    public void setMatchText(String matchText) {
        this.matchText = matchText;
    }

    @Override
    public String toString() {
        return "WSInfo{" +
                "ah='" + ah + '\'' +
                ", ajlx='" + ajlx + '\'' +
                ", spcx='" + spcx + '\'' +
                ", wslx='" + wslx + '\'' +
                ", fymc='" + fymc + '\'' +
                ", wsmc='" + wsmc + '\'' +
                ", matchText='" + matchText + '\'' +
                ", _id='" + _id + '\'' +
                ", xmlPath='" + xmlPath + '\'' +
                ", cprq='" + cprq + '\'' +
                ", score=" + score +
                '}';
    }
}
