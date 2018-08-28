package com.joker.zahuo.utils;

import android.app.Application;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Created by PengXJ on 2018/8/28.
 */

public class SophixUtils {

    /**热修复初始化*/
    public static void initSophix(Application context) {
        SophixManager.getInstance().setContext(context)
                .setAppVersion("")
                .setAesKey(null)
                .setSecretMetaData("App ID","App Secret","RSA密钥")
//                .setUnsupportedModel("modelName",sdkVersionInt) //把不支持的设备加入黑名单，加入后不会进行热修复。
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(int mode, int code, String info, int handlePatchVersion) {
                        //补丁加载回调通知
                        if(code == PatchStatus.CODE_LOAD_SUCCESS){
                            // 表明补丁加载成功
                        }else if(code == PatchStatus.CODE_LOAD_RELAUNCH){
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，
                        }else{
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
        SophixManager.getInstance().queryAndLoadNewPatch();
    }


//    int CODE_LOAD_SUCCESS = 1;//加载阶段, 成功
//    int CODE_ERR_INBLACKLIST = 4;//加载阶段, 失败设备不支持
//    int CODE_REQ_NOUPDATE = 6;//查询阶段, 没有发布新补丁
//    int CODE_REQ_NOTNEWEST = 7;//查询阶段, 补丁不是最新的
//    int CODE_DOWNLOAD_SUCCESS = 9;//查询阶段, 补丁下载成功
//    int CODE_DOWNLOAD_BROKEN = 10;//查询阶段, 补丁文件损坏下载失败
//    int CODE_UNZIP_FAIL = 11;//查询阶段, 补丁解密失败
//    int CODE_LOAD_RELAUNCH = 12;//预加载阶段, 需要重启
//    int CODE_REQ_APPIDERR = 15;//查询阶段, appid异常
//    int CODE_REQ_SIGNERR = 16;//查询阶段, 签名异常
//    int CODE_REQ_UNAVAIABLE = 17;//查询阶段, 系统无效
//    int CODE_REQ_SYSTEMERR = 22;//查询阶段, 系统异常
//    int CODE_REQ_CLEARPATCH = 18;//查询阶段, 一键清除补丁
//    int CODE_PATCH_INVAILD = 20;//加载阶段, 补丁格式非法
//    //查询阶段的code说明
//    int CODE_QUERY_UNDEFINED = 31;//未定义异常
//    int CODE_QUERY_CONNECT = 32;//连接异常
//    int CODE_QUERY_STREAM = 33;//流异常
//    int CODE_QUERY_EMPTY = 34;//请求空异常
//    int CODE_QUERY_BROKEN = 35;//请求完整性校验失败异常
//    int CODE_QUERY_PARSE = 36;//请求解析异常
//    int CODE_QUERY_LACK = 37;//请求缺少必要参数异常
//    //预加载阶段的code说明
//    int CODE_PRELOAD_SUCCESS = 100;//预加载成功
//    int CODE_PRELOAD_UNDEFINED = 101;//未定义异常
//    int CODE_PRELOAD_HANDLE_DEX = 102;//dex加载异常
//    int CODE_PRELOAD_NOT_ZIP_FORMAT = 103;//基线dex非zip格式异常
//    int CODE_PRELOAD_REMOVE_BASEDEX = 105;//基线dex处理异常
//    //加载阶段的code说明 分三部分dex加载, resource加载, lib加载
//    //dex加载
//    int CODE_LOAD_UNDEFINED = 71;//未定义异常
//    int CODE_LOAD_AES_DECRYPT = 72;//aes对称解密异常
//    int CODE_LOAD_MFITEM = 73;//补丁SOPHIX.MF文件解析异常
//    int CODE_LOAD_COPY_FILE = 74;//补丁拷贝异常
//    int CODE_LOAD_SIGNATURE = 75;//补丁签名校验异常
//    int CODE_LOAD_SOPHIX_VERSION = 76;//补丁和补丁工具版本不一致异常
//    int CODE_LOAD_NOT_ZIP_FORMAT = 77;//补丁zip解析异常
//    int CODE_LOAD_DELETE_OPT = 80;//删除无效odex文件异常
//    int CODE_LOAD_HANDLE_DEX = 81;//加载dex异常
//    // 反射调用异常
//    int CODE_LOAD_FIND_CLASS = 82;
//    int CODE_LOAD_FIND_CONSTRUCTOR = 83;
//    int CODE_LOAD_FIND_METHOD = 84;
//    int CODE_LOAD_FIND_FIELD = 85;
//    int CODE_LOAD_ILLEGAL_ACCESS = 86;
//    //resource加载
//    public static final int CODE_LOAD_RES_ADDASSERTPATH = 123;//新增资源补丁包异常
//    //lib加载
//    int CODE_LOAD_LIB_UNDEFINED = 131;//未定义异常
//    int CODE_LOAD_LIB_CPUABIS = 132;//获取primaryCpuAbis异常
//    int CODE_LOAD_LIB_JSON = 133;//json格式异常
//    int CODE_LOAD_LIB_LOST = 134;//lib库不完整异常
//    int CODE_LOAD_LIB_UNZIP = 135;//解压异常
//    int CODE_LOAD_LIB_INJECT = 136;//注入异常

}
