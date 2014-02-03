/*
 * Copyright 2014 Jakub Spatny
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kuba.actionbarhidingscrollview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class ActionBarHidingScrollView extends ScrollView {

	public ActionBarHidingScrollView(Context context) {
		super(context);
	}

	public ActionBarHidingScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ActionBarHidingScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		Activity activity = (Activity) getContext();
		if (activity != null) {

			int scrollview_height = this.getHeight();

			int diff = oldt - t;
			int ab_height = activity.getActionBar().getHeight();
			View view = (View) this.getChildAt(this.getChildCount() - 1);
			
			if (diff < -20 || (t > ab_height + 20 && t < ab_height * 2 && diff < 0)) {
				if (t + scrollview_height > view.getBottom() - ab_height - 20) return;
				activity.getActionBar().hide();
				return;
			}

			if (diff > 20 || t < 10) {
				activity.getActionBar().show();
			}

		}

	}

}
