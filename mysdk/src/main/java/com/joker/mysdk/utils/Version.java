package com.joker.mysdk.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


public class Version {
	private static Context context;

	/**
	 * 获取当前版本号
	 *
	 * @return
	 */
	public static String getVersionName(Context context) {
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(),
					0);
			LogUtil.d(packInfo.versionName);
			return packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 获取当前App的versionCode（从1开始），如果没找到（NameNotFoundException），返回0
	 *
	 * @Title: getVersionCode
	 * @Description: TODO
	 * @return
	 */
	public static int getVersionCode(Context context) {
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(),
					0);
			LogUtil.d("versionCode = " + packInfo.versionCode);
			return packInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取设备id（IMEI号）
	 *
	 * @return
	 */
	public static String getDeviceID(Context context) {
		if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			return "";
		}
		String resultStr = ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		if (TextUtils.isEmpty(resultStr)) {
			resultStr = "";
		}

		return resultStr;
	}

	/**
	 * Created by PengXJ on 2018/3/2.
	 * 新获取设备id（IMEI号）(自定义)
	 *
	 * imei由15位数字组成，
	 * 前6位(TAC)是型号核准号码，代表手机类型。
	 * 接着2位(FAC)是最后装配号，代表产地。
	 * 后6位(SNR)是串号，代表生产顺序号。
	 * 最后1位 (SP)是检验码。
	 *
	 * 计算公式
	 * IMEI = 14位随机数 + IMEI生成检验码
	 *
	 * 检验码计算：
	 * (1).将偶数位数字分别乘以2，分别计算个位数和十位数之和
	 * (2).将奇数位数字相加，再加上上一步算得的值
	 * (3).如果得出的数个位是0则校验位为0，否则为10减去个位数
	 * @return IMEI号
	 */
	public static String getDeviceIdCustom2(){
		// 随机数    获致随机数14位
		String random = RandomUntil.getNumAppoint(14);
		// 设备ID（IMEI号） IMEI = 14位随机数 + IMEI生成检验码
		String deviceIDCustom = ImeiUtils.getImeiBy14(random);
		return deviceIDCustom;
	}

	/**
	 * 获取设备id（IMEI号）
	 */
	public static String getDeviceID(Activity activity) {
		if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			return "";
		}
//		Context context = BaseApplication.getAppContext();
		String diviceID = ((TelephonyManager) activity
				.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		if (TextUtils.isEmpty(diviceID)) {
			diviceID = getMacAddress(activity);
		}
		return diviceID;
	}

	/**
	 * 获取手机mac地址<br/>
	 * 错误返回12个0
	 */
	public static String getMacAddress(Context context) {
		// 获取mac地址：
		String macAddress = "000000000000";
		try {
			WifiManager wifiMgr = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = (null == wifiMgr ? null : wifiMgr
					.getConnectionInfo());
			if (null != info) {
				if (!TextUtils.isEmpty(info.getMacAddress()))
					macAddress = info.getMacAddress().replace(":", "");
				else
					return macAddress;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return macAddress;
		}
		return macAddress;
	}
	// modify(E) by jiangsy 2016-09-27 for 横版改竖版
}
