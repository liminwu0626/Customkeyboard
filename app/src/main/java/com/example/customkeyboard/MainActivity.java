package com.example.customkeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eddy
 * @desc MainActivity
 * @date 2019-09-18 16:00
 */
public class MainActivity extends AppCompatActivity {
    private BaseInputBoard baseInputBoard;
    private AutoPopLayout autoPopLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoPopLayout = findViewById(R.id.autoPopLayout);
        EditText et_safe_keyboard = findViewById(R.id.et_safe_keyboard);
        LinearLayout ll_safe_keyboard = findViewById(R.id.ll_safe_keyboard);
        List<EditText> ets = new ArrayList<>();
        ets.add(et_safe_keyboard);
        baseInputBoard = new SoftInputBoard(this);
        autoPopLayout.hideSoftInputMethod(ets, new WeakReference<>(this), ll_safe_keyboard);
        autoPopLayout.initSoftInputBoard(baseInputBoard);
    }

    // 当点击返回键时, 如果软键盘正在显示, 则隐藏软键盘并是此次返回无效
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (autoPopLayout.isKeybordShow()) {
                autoPopLayout.closeKeyboard();
                return false;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
