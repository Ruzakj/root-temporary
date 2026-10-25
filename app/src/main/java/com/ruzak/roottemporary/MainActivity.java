package com.ruzak.roottemporary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class MainActivity extends Activity {
    private LinearLayout root;
    private TextView result;
    private String report = "";

    private String kernelRelease() {
        try (BufferedReader r = new BufferedReader(new FileReader("/proc/sys/kernel/osrelease"))) {
            String value = r.readLine();
            return value == null ? "Unavailable" : value;
        } catch (IOException | SecurityException e) {
            return System.getProperty("os.version", "Unavailable");
        }
    }

    private void add(LinearLayout parent, String value, int size, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(0xFFECEFF4);
        t.setPadding(0, 8, 0, 8);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        parent.addView(t, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private boolean anyFile(String... paths) {
        for (String path : paths) if (new File(path).exists()) return true;
        return false;
    }

    private String buildReport() {
        StringBuilder s = new StringBuilder();
        s.append("Root Temporary — Safe Compatibility Check\n\n");
        s.append("Manufacturer: ").append(Build.MANUFACTURER).append('\n');
        s.append("Brand: ").append(Build.BRAND).append('\n');
        s.append("Model: ").append(Build.MODEL).append('\n');
        s.append("Device: ").append(Build.DEVICE).append('\n');
        s.append("Fingerprint: ").append(Build.FINGERPRINT).append('\n');
        s.append("Android: ").append(Build.VERSION.RELEASE).append(" (SDK ").append(Build.VERSION.SDK_INT).append(")\n");
        s.append("Security patch: ").append(Build.VERSION.SECURITY_PATCH).append('\n');
        s.append("Kernel: ").append(kernelRelease()).append('\n');
        s.append("Build ID: ").append(Build.DISPLAY).append("\n\n");
        s.append("SAFE CHECKS (not an exploit test)\n");
        s.append("Debuggable build flag: ").append(Build.isDebuggable() ? "YES" : "NO").append('\n');
        s.append("Test-keys build tag: ").append(Build.TAGS != null && Build.TAGS.contains("test-keys") ? "YES" : "NO").append('\n');
        s.append("Common su binary path found: ").append(anyFile("/system/bin/su", "/system/xbin/su", "/sbin/su", "/vendor/bin/su") ? "YES (presence only)" : "NO").append('\n');
        s.append("Exploit compatibility: NOT DETERMINED\n");
        s.append("These checks do not verify kernel vulnerability, do not attempt privilege escalation, and cannot grant root. A missing su file does not prove the device is unrooted.\n");
        return s.toString();
    }

    private void runChecks() {
        report = buildReport();
        result.setText(report);
    }

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(0xFF17191F);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(24, 32, 24, 32);
        scroll.addView(root);
        setContentView(scroll);

        add(root, "Root Temporary — Safe Check", 24, true);
        add(root, "Read-only compatibility checks for DFRoot. No exploit is executed and no system files are modified.", 15, false);
        Button check = new Button(this);
        check.setText("Jalankan Pemeriksaan");
        root.addView(check, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        check.setOnClickListener(v -> runChecks());

        Button share = new Button(this);
        share.setText("Bagikan / Simpan Laporan");
        root.addView(share, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        share.setOnClickListener(v -> {
            if (report.isEmpty()) runChecks();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Root Temporary compatibility report");
            intent.putExtra(Intent.EXTRA_TEXT, report);
            startActivity(Intent.createChooser(intent, "Bagikan laporan"));
        });

        add(root, "HASIL PEMERIKSAAN", 18, true);
        result = new TextView(this);
        result.setTextColor(0xFFECEFF4);
        result.setTextSize(14);
        result.setTextIsSelectable(true);
        root.addView(result, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        add(root, "PENTING", 18, true);
        add(root, "Status kompatibilitas exploit tetap NOT DETERMINED sampai ada bukti teknis yang tepercaya untuk kombinasi kernel dan firmware ini. Build APK yang sukses bukan bukti perangkat rentan.", 15, false);
        runChecks();
    }
}
