package com.joker.mysdk.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tv on 2018/7/2.
 */

public class MovieListEntity {


    /**
     * control : {"expires":1800}
     * status : 0
     * data : {"hasNext":true,"movies":[{"showInfo":"今天205家影院放映2592场","late":false,"sn":0,"cnms":0,"src":"","scm":"是考验人性还是自我救赎？","sc":8.6,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2018-06-29上映","dur":132,"showDate":"","img":"http://p0.meituan.net/165.220/movie/c6382117be87ac04fcc81fa02df815944946929.jpg","nm":"动物世界","imax":true,"snum":222893,"preSale":0,"vd":"","dir":"韩延","star":"李易峰,迈克尔·道格拉斯,周冬雨","cat":"动作,悬疑,冒险","wish":194088,"3d":true,"pn":345,"time":"","id":1198178},{"showInfo":"今天198家影院放映1521场","late":false,"sn":0,"cnms":0,"src":"","scm":"寻找恐龙的欧文与克莱尔发现\u2026\u2026","sc":8.5,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2018-06-15上映","dur":128,"showDate":"","img":"http://p1.meituan.net/165.220/movie/3d17aa5ee07f5d66239d8393bcb8fe5196556.jpg","nm":"侏罗纪世界2","imax":true,"snum":572333,"preSale":0,"vd":"","dir":"胡安·安东尼奥·巴亚纳","star":"克里斯·帕拉特,布莱丝·达拉斯·霍华德,泰德·拉文","cat":"动作,冒险,科幻","wish":621367,"3d":true,"pn":62,"time":"","id":341628},{"showInfo":"今天190家影院放映1312场","late":false,"sn":0,"cnms":0,"src":"","scm":"","sc":9,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2018-06-22上映","dur":126,"showDate":"","img":"http://p1.meituan.net/165.220/movie/6383f5cb72f994370f9e817eaa495aaf428644.jpg","nm":"超人总动员2","imax":true,"snum":85632,"preSale":0,"vd":"","dir":"布拉德·伯德","star":"格雷格·T·尼尔森,霍利·亨特,莎拉·沃威尔","cat":"动画,动作,冒险","wish":67501,"3d":true,"pn":121,"time":"","id":338385},{"showInfo":"今天198家影院放映1260场","late":false,"sn":0,"cnms":0,"src":"","scm":"看海上监狱大营救，如何越狱成功！","sc":5.1,"ver":"2D","rt":"2018-06-29上映","dur":93,"showDate":"","img":"http://p1.meituan.net/165.220/movie/95f246163e1b11702685fd8b01421182466294.jpg","nm":"金蝉脱壳2","imax":false,"snum":74339,"preSale":0,"vd":"","dir":"史蒂芬·C·米勒","star":"西尔维斯特·史泰龙,黄晓明,戴夫·巴蒂斯塔","cat":"动作,惊悚,犯罪","wish":147973,"3d":false,"pn":100,"time":"","id":1197181},{"showInfo":"2018-07-06 本周五上映","late":false,"sn":0,"cnms":0,"src":"","scm":"印度代神药，良心及时现","sc":9.6,"ver":"2D/IMAX 2D/中国巨幕","rt":"本周五上映","dur":117,"showDate":"","img":"http://p0.meituan.net/165.220/movie/238e2dc36beae55a71cabfc14069fe78236351.jpg","nm":"我不是药神","imax":true,"snum":41166,"preSale":1,"vd":"","dir":"文牧野","star":"徐峥,周一围,王传君","cat":"剧情,喜剧","wish":112962,"3d":false,"pn":149,"time":"","id":1200486}]}
     */

    private ControlBean control;
    private String status;
    private DataBean data;

    public ControlBean getControl() {
        return control;
    }

    public void setControl(ControlBean control) {
        this.control = control;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ControlBean {
        /**
         * expires : 1800
         */

        private int expires;

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }
    }

    public static class DataBean {
        /**
         * hasNext : true
         * movies : [{"showInfo":"今天205家影院放映2592场","late":false,"sn":0,"cnms":0,"src":"","scm":"是考验人性还是自我救赎？","sc":8.6,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2018-06-29上映","dur":132,"showDate":"","img":"http://p0.meituan.net/165.220/movie/c6382117be87ac04fcc81fa02df815944946929.jpg","nm":"动物世界","imax":true,"snum":222893,"preSale":0,"vd":"","dir":"韩延","star":"李易峰,迈克尔·道格拉斯,周冬雨","cat":"动作,悬疑,冒险","wish":194088,"3d":true,"pn":345,"time":"","id":1198178},{"showInfo":"今天198家影院放映1521场","late":false,"sn":0,"cnms":0,"src":"","scm":"寻找恐龙的欧文与克莱尔发现\u2026\u2026","sc":8.5,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2018-06-15上映","dur":128,"showDate":"","img":"http://p1.meituan.net/165.220/movie/3d17aa5ee07f5d66239d8393bcb8fe5196556.jpg","nm":"侏罗纪世界2","imax":true,"snum":572333,"preSale":0,"vd":"","dir":"胡安·安东尼奥·巴亚纳","star":"克里斯·帕拉特,布莱丝·达拉斯·霍华德,泰德·拉文","cat":"动作,冒险,科幻","wish":621367,"3d":true,"pn":62,"time":"","id":341628},{"showInfo":"今天190家影院放映1312场","late":false,"sn":0,"cnms":0,"src":"","scm":"","sc":9,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2018-06-22上映","dur":126,"showDate":"","img":"http://p1.meituan.net/165.220/movie/6383f5cb72f994370f9e817eaa495aaf428644.jpg","nm":"超人总动员2","imax":true,"snum":85632,"preSale":0,"vd":"","dir":"布拉德·伯德","star":"格雷格·T·尼尔森,霍利·亨特,莎拉·沃威尔","cat":"动画,动作,冒险","wish":67501,"3d":true,"pn":121,"time":"","id":338385},{"showInfo":"今天198家影院放映1260场","late":false,"sn":0,"cnms":0,"src":"","scm":"看海上监狱大营救，如何越狱成功！","sc":5.1,"ver":"2D","rt":"2018-06-29上映","dur":93,"showDate":"","img":"http://p1.meituan.net/165.220/movie/95f246163e1b11702685fd8b01421182466294.jpg","nm":"金蝉脱壳2","imax":false,"snum":74339,"preSale":0,"vd":"","dir":"史蒂芬·C·米勒","star":"西尔维斯特·史泰龙,黄晓明,戴夫·巴蒂斯塔","cat":"动作,惊悚,犯罪","wish":147973,"3d":false,"pn":100,"time":"","id":1197181},{"showInfo":"2018-07-06 本周五上映","late":false,"sn":0,"cnms":0,"src":"","scm":"印度代神药，良心及时现","sc":9.6,"ver":"2D/IMAX 2D/中国巨幕","rt":"本周五上映","dur":117,"showDate":"","img":"http://p0.meituan.net/165.220/movie/238e2dc36beae55a71cabfc14069fe78236351.jpg","nm":"我不是药神","imax":true,"snum":41166,"preSale":1,"vd":"","dir":"文牧野","star":"徐峥,周一围,王传君","cat":"剧情,喜剧","wish":112962,"3d":false,"pn":149,"time":"","id":1200486}]
         */

        private boolean hasNext;
        private List<MoviesBean> movies;

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public static class MoviesBean {
            /**
             * showInfo : 今天205家影院放映2592场
             * late : false
             * sn : 0
             * cnms : 0
             * src :
             * scm : 是考验人性还是自我救赎？
             * sc : 8.6
             * ver : 2D/3D/IMAX 3D/中国巨幕/全景声
             * rt : 2018-06-29上映
             * dur : 132
             * showDate :
             * img : http://p0.meituan.net/165.220/movie/c6382117be87ac04fcc81fa02df815944946929.jpg
             * nm : 动物世界
             * imax : true
             * snum : 222893
             * preSale : 0
             * vd :
             * dir : 韩延
             * star : 李易峰,迈克尔·道格拉斯,周冬雨
             * cat : 动作,悬疑,冒险
             * wish : 194088
             * 3d : true
             * pn : 345
             * time :
             * id : 1198178
             */

            private String showInfo;
            private boolean late;
            private int sn;
            private int cnms;
            private String src;
            private String scm;
            private double sc;
            private String ver;
            private String rt;
            private int dur;
            private String showDate;
            private String img;
            private String nm;
            private boolean imax;
            private int snum;
            private int preSale;
            private String vd;
            private String dir;
            private String star;
            private String cat;
            private int wish;
            @SerializedName("3d")
            private boolean _$3d;
            private int pn;
            private String time;
            private int id;

            public String getShowInfo() {
                return showInfo;
            }

            public void setShowInfo(String showInfo) {
                this.showInfo = showInfo;
            }

            public boolean isLate() {
                return late;
            }

            public void setLate(boolean late) {
                this.late = late;
            }

            public int getSn() {
                return sn;
            }

            public void setSn(int sn) {
                this.sn = sn;
            }

            public int getCnms() {
                return cnms;
            }

            public void setCnms(int cnms) {
                this.cnms = cnms;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getScm() {
                return scm;
            }

            public void setScm(String scm) {
                this.scm = scm;
            }

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public String getShowDate() {
                return showDate;
            }

            public void setShowDate(String showDate) {
                this.showDate = showDate;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public boolean isImax() {
                return imax;
            }

            public void setImax(boolean imax) {
                this.imax = imax;
            }

            public int getSnum() {
                return snum;
            }

            public void setSnum(int snum) {
                this.snum = snum;
            }

            public int getPreSale() {
                return preSale;
            }

            public void setPreSale(int preSale) {
                this.preSale = preSale;
            }

            public String getVd() {
                return vd;
            }

            public void setVd(String vd) {
                this.vd = vd;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getCat() {
                return cat;
            }

            public void setCat(String cat) {
                this.cat = cat;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }

            public boolean is_$3d() {
                return _$3d;
            }

            public void set_$3d(boolean _$3d) {
                this._$3d = _$3d;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return "MoviesBean{" +
                        "showInfo='" + showInfo + '\'' +
                        ", late=" + late +
                        ", sn=" + sn +
                        ", cnms=" + cnms +
                        ", src='" + src + '\'' +
                        ", scm='" + scm + '\'' +
                        ", sc=" + sc +
                        ", ver='" + ver + '\'' +
                        ", rt='" + rt + '\'' +
                        ", dur=" + dur +
                        ", showDate='" + showDate + '\'' +
                        ", img='" + img + '\'' +
                        ", nm='" + nm + '\'' +
                        ", imax=" + imax +
                        ", snum=" + snum +
                        ", preSale=" + preSale +
                        ", vd='" + vd + '\'' +
                        ", dir='" + dir + '\'' +
                        ", star='" + star + '\'' +
                        ", cat='" + cat + '\'' +
                        ", wish=" + wish +
                        ", _$3d=" + _$3d +
                        ", pn=" + pn +
                        ", time='" + time + '\'' +
                        ", id=" + id +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "hasNext=" + hasNext +
                    ", movies=" + movies +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MovieListEntity{" +
                "control=" + control +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
