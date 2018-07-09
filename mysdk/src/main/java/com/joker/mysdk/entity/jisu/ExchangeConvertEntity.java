package com.joker.mysdk.entity.jisu;

/**
 * Created by tv on 2018/7/9.
 */

public class ExchangeConvertEntity extends JiSuBaseEntity {

    /**
     * result : {"from":"CNY","to":"USD","fromname":"人民币","toname":"美元","updatetime":"2018-07-09 12:55:00","rate":"0.1509","camount":"1.509"}
     */

    private ExchangeConvertBean result;

    public ExchangeConvertBean getResult() {
        return result;
    }

    public void setResult(ExchangeConvertBean result) {
        this.result = result;
    }

    public static class ExchangeConvertBean {
        /**
         * from : CNY
         * to : USD
         * fromname : 人民币
         * toname : 美元
         * updatetime : 2018-07-09 12:55:00
         * rate : 0.1509
         * camount : 1.509
         */

        private String from;
        private String to;
        private String fromname;
        private String toname;
        private String updatetime;
        private String rate;
        private String camount;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getFromname() {
            return fromname;
        }

        public void setFromname(String fromname) {
            this.fromname = fromname;
        }

        public String getToname() {
            return toname;
        }

        public void setToname(String toname) {
            this.toname = toname;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getCamount() {
            return camount;
        }

        public void setCamount(String camount) {
            this.camount = camount;
        }
    }
}
