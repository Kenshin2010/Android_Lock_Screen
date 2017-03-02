package nhatlinh.app.androidlockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.WindowManager;

public class MainActivity extends Activity{

	private static final int ADMIN_INTENT = 15;
	private static final String description = "Some Description About Your Admin";
	private DevicePolicyManager mDevicePolicyManager; 
	private ComponentName mComponentName;

	private PowerManager pm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_lock);

		mDevicePolicyManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
		mComponentName = new ComponentName(this, MyAdminReceiver.class);
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,description);
		startActivityForResult(intent, ADMIN_INTENT);

		boolean isAdmin = mDevicePolicyManager.isAdminActive(mComponentName);
		if (isAdmin) {
			mDevicePolicyManager.lockNow();
		}
		finish();
	}
}