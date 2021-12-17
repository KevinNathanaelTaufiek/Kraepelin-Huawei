package com.kevinnt.kraepelinmobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton;
import com.kevinnt.kraepelinmobile.menus.HighScoreFragment;
import com.kevinnt.kraepelinmobile.menus.MainFragment;
import com.kevinnt.kraepelinmobile.models.GameSets;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl_container;
    private Button btn_high_score, btnLogout;
    private TextView tvName;
    private GameSets game_setting = new GameSets();
    private com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton btnLogin;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl_container = findViewById(R.id.fl_container);
        btn_high_score = findViewById(R.id.btn_high_score);

        playSong();

        getSupportFragmentManager().beginTransaction().replace(fl_container.getId(), new MainFragment(this)).commit();

        tvName = findViewById(R.id.tv_name);
        btnLogin = findViewById(R.id.HuaweiIdAuthButton);
        btnLogout = findViewById(R.id.HuaweiIdSignOutButton);
        btnLogout.setVisibility(View.GONE);

        btn_high_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(fl_container.getId(), new HighScoreFragment(MainActivity.this)).addToBackStack(null).commit();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silentSignInByHwId();

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                tvName.setText("Hi, Guest!");
                btnLogout.setVisibility(View.GONE);
                btnLogin.setVisibility(View.VISIBLE);
            }
        });

//        findViewById(R.id.HuaweiIdCancelAuthButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cancelAuthorization();
//            }
//        });
    }


    private void playSong(){
        mediaPlayer = MediaPlayer.create(this, R.raw.harverstmoon);

        try{
            mediaPlayer.prepare();
        }catch (IllegalStateException ex){
            ex.printStackTrace();
        }catch (IOException ex1){
            ex1.printStackTrace();
        }
        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playSong();
            }
        });
    }

    // AccountAuthService provides a set of APIs, including silentSignIn, getSignInIntent, and signOut.
    private AccountAuthService mAuthService;

    // Set HUAWEI ID sign-in authorization parameters.
    private AccountAuthParams mAuthParam;
    private static final int REQUEST_CODE_SIGN_IN = 1000;
    private static final String TAG = "Account";

    /**
     * Silent sign-in: If a user has authorized your app and signed in, no authorization or sign-in screen will appear during subsequent sign-ins, and the user will directly sign in.
     * After a successful silent sign-in, the HUAWEI ID information will be returned in the success event listener.
     * If the user has not authorized your app or signed in, the silent sign-in will fail. In this case, your app will show the authorization or sign-in screen to the user.
     */
    private void silentSignInByHwId() {
        // 1. Use AccountAuthParams to specify the user information to be obtained, including the user ID (OpenID and UnionID), email address, and profile (nickname and picture).
        // 2. By default, DEFAULT_AUTH_REQUEST_PARAM specifies two items to be obtained, that is, the user ID and profile.
        // 3. If your app needs to obtain the user's email address, call setEmail().
        mAuthParam = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setEmail()
                .createParams();

        // Use AccountAuthParams to build AccountAuthService.
        mAuthService = AccountAuthManager.getService(this, mAuthParam);

        // Use silent sign-in to sign in with a HUAWEI ID.
        Task<AuthAccount> task = mAuthService.silentSignIn();
        task.addOnSuccessListener(new OnSuccessListener<AuthAccount>() {
            @Override
            public void onSuccess(AuthAccount authAccount) {
                // The silent sign-in is successful. Process the returned account object AuthAccount to obtain the HUAWEI ID information.
                dealWithResultOfSignIn(authAccount);
                btnLogin.setVisibility(View.GONE);
                btnLogout.setVisibility(View.VISIBLE);
                tvName.setText(authAccount.getDisplayName());
                //bisa masukin intent

            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                // The silent sign-in fails. Use the getSignInIntent() method to show the authorization or sign-in screen.
                if (e instanceof ApiException) {
                    ApiException apiException = (ApiException) e;
                    Intent signInIntent = mAuthService.getSignInIntent();
                    // If your app appears in full-screen mode duing user sign-in, that is, with no satus bar at the top of the phone screen, add the following parameter in the intent:
                    // intent.putExtra(CommonConstant.RequestParams.IS_FULL_SCREEN, true);
                    // Check the details in this FAQ.
                    signInIntent.putExtra(CommonConstant.RequestParams.IS_FULL_SCREEN, true);
                    startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN);
                }
            }
        });
    }

    /**
     * Process the returned AuthAccount object to obtain the HUAWEI ID information.
     *
     * @param authAccount AuthAccount object, which contains the HUAWEI ID information.
     */
    private void dealWithResultOfSignIn(AuthAccount authAccount) {
        Log.i(TAG, "display name:" + authAccount.getDisplayName());
        Log.i(TAG, "photo uri string:" + authAccount.getAvatarUriString());
        Log.i(TAG, "photo uri:" + authAccount.getAvatarUri());
        Log.i(TAG, "email:" + authAccount.getEmail());
        Log.i(TAG, "openid:" + authAccount.getOpenId());
        Log.i(TAG, "unionid:" + authAccount.getUnionId());
    }

    private void signOut() {
        Task<Void> signOutTask = mAuthService.signOut();
        signOutTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i(TAG, "signOut Success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "signOut fail");
            }
        });
    }

    private void cancelAuthorization() {
        Task<Void> task = mAuthService.cancelAuthorization();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i(TAG, "cancelAuthorization success");
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i(TAG, "cancelAuthorization failure:" + e.getClass().getSimpleName());
            }
        });
    }


    public FrameLayout getFl_container() {
        return fl_container;
    }

    public void setFl_container(FrameLayout fl_container) {
        this.fl_container = fl_container;
    }

    public Button getBtn_high_score() {
        return btn_high_score;
    }

    public void setBtn_high_score(Button btn_high_score) {
        this.btn_high_score = btn_high_score;
    }

    public Button getBtnLogout() {
        return btnLogout;
    }

    public void setBtnLogout(Button btnLogout) {
        this.btnLogout = btnLogout;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public GameSets getGame_setting() {
        return game_setting;
    }

    public void setGame_setting(GameSets game_setting) {
        this.game_setting = game_setting;
    }

    public HuaweiIdAuthButton getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(HuaweiIdAuthButton btnLogin) {
        this.btnLogin = btnLogin;
    }
}