package com.joker.mysdk.entity.jisu;

import java.util.List;

/**
 * Created by tv on 2018/7/9.
 */

public class AllExchangeCurrencyEntity extends JiSuBaseEntity {

    private List<ExchangeCurencyBean> result;

    public List<ExchangeCurencyBean> getResult() {
        return result;
    }

    public void setResult(List<ExchangeCurencyBean> result) {
        this.result = result;
    }

    public static class ExchangeCurencyBean {
        /**
         * currency : USD
         * name : 美元
         */

        private String currency;
        private String name;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
