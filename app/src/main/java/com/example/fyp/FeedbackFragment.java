package com.example.fyp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.webkit.WebView;
import android.webkit.WebSettings;


public class FeedbackFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);

        Button button1 = (Button) v.findViewById(R.id.check);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://fes.utar.edu.my/2018/07/13/important-student-survey-may-2018")));
            }
        });

        Button button2 = (Button) v.findViewById(R.id.survey);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://portal.utar.edu.my/loginPageV2.jsp?catid=00")));
            }
        });

//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://fes.utar.edu.my/2018/07/13/important-student-survey-may-2018")));
//        WebView browser = (WebView) v.findViewById(R.id.webview);
//        browser.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        browser.loadUrl("https://portal.utar.edu.my/loginPageV2.jsp?catid=00");

        return v;
    }
}
