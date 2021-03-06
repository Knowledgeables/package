/*

 */

package im.maqu.matk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import im.maqu.matk.constant.CstBase;


/**
 * @author : Maqu
 * @date   : 2014-2-28
 * @desc   : SharedPreferences工具类
 * 
 * 	public method
 * 	<li>putString(Context, String, String)			保存字符串数据 </li>
 * 	<li>getString(Context, String, String)			获取字符串数据 </li>
 * 	<li>putInt(Context, String, int)				保存整型数据 </li>
 * 	<li>getInt(Context, String, int)				获取整型数据 </li>
 * 	<li>putLong(Context, String, long)				获取长整型数据 </li>
 * 	<li>getLong(Context, String, long)				获取长整型数据 </li>
 * 	<li>putBoolean(Context, String, boolean)		获取布尔值数据 </li>
 * 	<li>getBoolean(Context, String, boolean)		获取布尔值数据 </li>
 * 	<li>putFloat(Context, String, float)			获取浮点型数据 </li>
 * 	<li>getFloat(Context, String, float)			获取浮点型数据 </li>
 * 
 */
public class UtilPref {
	/**
	 * 保存字符串数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putString(Context context, String key, String value) {
		if(context == null || UtilString.isBlank(key)) {
			return ;
		}

		SharedPreferences pref = context
				.getSharedPreferences(CstBase.PROJECT, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 获取字符串数据
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context, String key, String defValue) {
		if(context == null || UtilString.isBlank(key)) {
			return defValue;
		}

		SharedPreferences pref = context
				.getSharedPreferences(CstBase.PROJECT, Context.MODE_PRIVATE);
		return pref.getString(key, defValue);
	}


	/**
	 * 保存整型数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putInt(Context context, String key, int value) {
		if(context == null || UtilString.isBlank(key)) {
			return ;
		}

		SharedPreferences pref = context
				.getSharedPreferences(CstBase.PROJECT, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 保存整型数据
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(Context context, String key, int defValue) {
		if(context == null || UtilString.isBlank(key)) {
			return defValue;
		}

		SharedPreferences pref = context
				.getSharedPreferences(CstBase.PROJECT, Context.MODE_PRIVATE);
		return pref.getInt(key, defValue);
	}


	/**
	 * 保存长整型数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putLong(Context context, String key, long value) {
		if(context == null || UtilString.isBlank(key)) {
			return ;
		}

		SharedPreferences pref = context
				.getSharedPreferences(CstBase.PROJECT, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 * 保存长整型数据
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static long getLong(Context context, String key, long defValue) {
		if(context == null || UtilString.isBlank(key)) {
			return defValue;
		}

		SharedPreferences pref = context
				.getSharedPreferences(CstBase.PROJECT, Context.MODE_PRIVATE);
		return pref.getLong(key, defValue);
	}

	/**
	 * 保存布尔值数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putBoolean(Context context, String key, boolean value) {
		if(context == null || UtilString.isBlank(key)) {
			return ;
		}

		SharedPreferences pref = context
				.getSharedPreferences(CstBase.PROJECT, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 保存布尔值数据
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue) {
		if(context == null || UtilString.isBlank(key)) {
			return defValue;
		}

		SharedPreferences pref = context
				.getSharedPreferences(CstBase.PROJECT, Context.MODE_PRIVATE);
		return pref.getBoolean(key, defValue);
	}


	/**
	 * 保存浮点型数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putFloat(Context context, String key, float value) {
		if(context == null || UtilString.isBlank(key)) {
			return ;
		}

		SharedPreferences pref = context
				.getSharedPreferences(CstBase.PROJECT, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	/**
	 * 保存浮点型数据
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static float getFloat(Context context, String key, float defValue) {
		if(context == null || UtilString.isBlank(key)) {
			return defValue;
		}

		SharedPreferences pref = context
				.getSharedPreferences(CstBase.PROJECT, Context.MODE_PRIVATE);
		return pref.getFloat(key, defValue);
	}
}
