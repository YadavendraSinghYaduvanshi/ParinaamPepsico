/*
 * Copyright (C) 2011 - Riccardo Ciovati
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cpm.keyboard;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.view.KeyEvent;
import android.view.View;

import com.cpm.dailyEntry.DisplayPaidDetail;
import com.cpm.dailyEntry.DisplayPepsicoAddSKU;
import com.cpm.dailyEntry.EditSkuCompetitorActivity;
import com.cpm.dailyEntry.EditSkuList;
import com.cpm.dailyEntry.EditSkuOtherActivity;
import com.cpm.dailyEntry.SkuCompetitorActivity;
import com.cpm.dailyEntry.SkuList;
import com.cpm.dailyEntry.SkuOtherActivity;
import com.cpm.dailyEntry.SodMasterActivity;
import com.cpm.dailyEntry.SodTempOtherActivity;
import com.cpm.dailyEntry.SodTempPepsiActivity;

/***
 * Listener da associare ad un oggetto KeyboardView in modo tale che quando
 * viene premuto un tasto il corrispondente evento viene girato all'activity
 * passata al costruttore
 */
public class BasicOnKeyboardActionListener implements OnKeyboardActionListener {

	private Activity mTargetActivity;
	private int num;

	/***
	 * 
	 * @param targetActivity
	 *            Activity a cui deve essere girato l'evento
	 *            "pressione di un tasto sulla tastiera"
	 */
	public BasicOnKeyboardActionListener(Activity targetActivity, int num) {
		mTargetActivity = targetActivity;
		this.num = num;
	}

	@Override
	public void swipeUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onText(CharSequence text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRelease(int primaryCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPress(int primaryCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKey(int primaryCode, int[] keyCodes) {

		if (primaryCode == 0) {
			switch (num) {
			case 1:
				((EditSkuList) mTargetActivity).hide();
				break;

			case 2:
				((SkuList) mTargetActivity).hide();
				break;
			case 3:
				((SodTempPepsiActivity) mTargetActivity).hide();
				break;
			case 4:
				((SodMasterActivity) mTargetActivity).hide();
				break;
			case 5:
				((EditSkuCompetitorActivity) mTargetActivity).hide();
				break;
			case 6:
				((SkuOtherActivity) mTargetActivity).hide();
				break;
			case 7:
				((SkuCompetitorActivity) mTargetActivity).hide();
				break;
			case 8:
				((SodTempOtherActivity) mTargetActivity).hide();
				break;
			case 9:
				((EditSkuOtherActivity) mTargetActivity).hide();
				break;
				
			case 10:
				((DisplayPepsicoAddSKU) mTargetActivity).hide();
				break;
			default:
				break;
			}

		} else {
			long eventTime = System.currentTimeMillis();
			KeyEvent event = new KeyEvent(eventTime, eventTime,
					KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0,
					KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);

			mTargetActivity.dispatchKeyEvent(event);
		}
	}

}