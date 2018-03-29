
package com.utils.library.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {
    private AppUtils() {
        
    }
    
    /**
     * 隐藏输入框
     * @param view view
     */
    public static void hideSoftInput(View view) {
        if (view != null && view.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    
    /**
     * 显示输入框
     * @param view view
     */
    public static void showSoftInput(final View view) {
        view.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                InputMethodManager m = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }

        }, 200);
    }

    /**
     * 移动光标到文本框最后
     * @param editText EditText
     */
    public static void moveSelectionToEnd(EditText editText) {
        Editable etext = editText.getText();
        Selection.setSelection(etext, etext.length());
    }

    /**
     * 获取版本号
     * @param context 上下文对象
     */
    public static int getVersionCode(Context context) {
        PackageInfo info = getPackageInfo(context);
        if (info != null) {
            return info.versionCode;
        }

        return -1;
    }
    
    /**
     * 获取版本名
     * @return 版本名称
     */
    public static String getVersionName(Context context){
        String versionName;
        
        //获取版本类型
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (NameNotFoundException e) {
            versionName = "other";
        }
        
        return versionName;
    }

    /**
     * 获取包信息
     * @param context 上下文对象
     */
    public static PackageInfo getPackageInfo(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info;
        } catch (NameNotFoundException e) {
        }

        return null;
    }

    /**
     * 是否包含中文
     * @param sequence 验证字符串
     * @return 是否包含中文
     */
    public static boolean isContainChinese(String sequence) {
        final String format = "[\\u4E00-\\u9FA5\\uF900-\\uFA2D]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(sequence);
        return matcher.find();
    }

    /**
     * 判断邮箱格式
     * @param str 验证字符串
     * @return 是否邮箱
     */
    public static boolean isEmail(String str) {
        String check = "\\w+([-.]\\w+)*@\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证是否是手机号码
     * @param str 验证字符串
     * @return 是否手机号
     */
    public static boolean isMobile(String str) {
        String NUM = "+86";
        boolean flag = false;
        if (TextUtils.isEmpty(str)) {
            return flag;
        } else {
            if (str.indexOf(NUM) > -1) {
                str = str.substring(NUM.length(), str.length());
            }
            if (str.charAt(0) == '0') {
                str = str.substring(1, str.length());
            }
            String rex = "^1\\d{10}$";
            str = removeBlanks(str);
            if (str.matches(rex)) {
                flag = true;
            }
            return flag;
        }
    }

    /**
     * 删除字符串中的空白符
     * @param content 字符串内容
     * @return String 删除后的内容
     */
    public static String removeBlanks(String content) {
        if (content == null) {
            return null;
        }
        StringBuffer buff = new StringBuffer();
        buff.append(content);
        for (int i = buff.length() - 1; i >= 0; i--) {
            if (' ' == buff.charAt(i) || ('\n' == buff.charAt(i)) || ('\t' == buff.charAt(i)) || ('\r' == buff.charAt(i))) {
                buff.deleteCharAt(i);
            }
        }
        return buff.toString();
    }

    /**
     * 18位或者15位身份证验证 18位的最后一位可以是字母x
     * @param text 字符串
     * @return 是否是身份证
     */
    public static boolean personIdValidation(String text) {
        boolean flag = false;
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        flag = text.matches(regx) || text.matches(reg1) || text.matches(regex);
        return flag;
    }

    /**
     * 点击输入法中“下一个”将焦点与光标跳转到下一输入框中
     * @param currentEt 当前的输入框
     * @param nextEt 下一个输入框
     */
    public static void setInputType(final EditText currentEt, final EditText nextEt) {
        currentEt.setSingleLine(true); // android:singleLine=”true”
        currentEt.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        currentEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    currentEt.clearFocus();
                    nextEt.requestFocus();
                    return true;
                }
                return false;
            }
        });
    }
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
