package com.example.adhishlal.fbaccountskitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;

public class TokenActivity extends Activity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        final Button signOut = (Button) findViewById(R.id.log_out_button);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountKit.logOut();
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                final TextView userId = (TextView) findViewById(R.id.user_id);
                userId.setText(account.getId());

                final TextView phoneNumber = (TextView) findViewById(R.id.user_phone);
                final PhoneNumber number = account.getPhoneNumber();
                phoneNumber.setText(number == null ? null : number.toString());

                final TextView email = (TextView) findViewById(R.id.user_email);
                email.setText(account.getEmail());
            }

            @Override
            public void onError(final AccountKitError error) {

                Log.d("Accounts Error ::::",""+error);
            }
        });
    }
}
