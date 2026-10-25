package com.ruzak.roottemporary;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class MainActivity extends Activity {
    private String kernelRelease() {
        try (BufferedReader r = new BufferedReader(new FileReader("/proc/sys/kernel/osrelease"))) {
            String value = r.readLine();
            return value == null ? "Unavailable" : value;
        } catch (IOException | SecurityException e) {
            return System.getProperty("os.version", "Unavailable");
        }
    }
    private void add(LinearLayout root, String value, int size, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value); t.setTextSize(size); t.setTextColor(0xFFECEFF4);
        t.setPadding(0, 9, 0, 9);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        root.addView(t, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        ScrollView scroll = new ScrollView(this); scroll.setBackgroundColor(0xFF17191F);
        LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(24, 32, 24, 32); scroll.addView(root); setContentView(scroll);
        add(root, "Root Temporary — Diagnostics", 24, true);
        add(root, "Read-only report. This app does not run an exploit, modify the kernel, or grant root.", 15, false);
        add(root, "DEVICE", 18, true);
        add(root, "Manufacturer: " + Build.MANUFACTURER, 15, false);
        add(root, "Brand: " + Build.BRAND, 15, false);
        add(root, "Model: " + Build.MODEL, 15, false);
        add(root, "Device: " + Build.DEVICE, 15, false);
        add(root, "Build fingerprint: " + Build.FINGERPRINT, 12, false);
        add(root, "Android: " + Build.VERSION.RELEASE + " (SDK " + Build.VERSION.SDK_INT + ")", 15, false);
        add(root, "Security patch: " + Build.VERSION.SECURITY_PATCH, 15, false);
        add(root, "Kernel release: " + kernelRelease(), 12, false);
        add(root, "Build ID: " + Build.DISPLAY, 12, false);
        add(root, "COMPATIBILITY STATUS", 18, true);
        add(root, "Not determined. Device properties alone cannot prove a kernel exploit is vulnerable. Compare this report with a trusted device-specific compatibility record and the upstream verification logic.", 15, false);
        add(root, "A successful APK build is not proof of root compatibility. Attach this report and the complete DFRoot log for further audit.", 15, false);
    }
}
