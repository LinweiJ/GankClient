package com.wega.gankclient.utils;


import android.content.Context;
import android.widget.Toast;


public class ToastUtil {


	private  Toast mToast;


	private static ToastUtil mInstance;

    private ToastUtil(){


	}

	public static ToastUtil getInstance(){
		if(mInstance==null)
		{
			mInstance=new ToastUtil();
		}
		return mInstance;
	}

	public  Toast showToastDefault(Context context, String message) {
		if (mToast != null) {
			mToast.cancel();
		}

		mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		mToast.show();
		return mToast;
	}


	public  void cancelToast()
	{
		if(mToast!=null)
		{
			mToast.cancel();
		}
	}

}