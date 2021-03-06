package com.woncheol.yuljeon.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.woncheol.yuljeon.R;
import com.woncheol.yuljeon.fragment.Bap;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

class BapViewHolder {
	public TextView mMorning;

	public TextView mLunch;

	public TextView mNight;

	public TextView mDate;

	public TextView mCalender;
}

public class BapListViewAdapter extends BaseAdapter {
	private Context mContext = null;
	private ArrayList<BapListData> mListData = new ArrayList<BapListData>();
	private Calendar currentTime = Calendar.getInstance();

	public BapListViewAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	public void addItem(String mCalender, String mDate, String mMorning,
			String mLunch, String mNight) {
		BapListData addInfo = new BapListData();
		addInfo.mCalender = mCalender;
		addInfo.mDate = mDate;
		addInfo.mMorning = mMorning;
		addInfo.mLunch = mLunch;
		addInfo.mNight = mNight;

		mListData.add(addInfo);
	}

	public void clearData() {
		mListData.clear();
	}

	@Override
	public int getCount() {
		return mListData.size();
	}

	@Override
	public Object getItem(int position) {
		return mListData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BapViewHolder holder;
		if (convertView == null) {
			holder = new BapViewHolder();

			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.bap_list_item, null);

			holder.mDate = (TextView) convertView.findViewById(R.id.mDate);
			holder.mCalender = (TextView) convertView
					.findViewById(R.id.mCalender);
			holder.mMorning = (TextView) convertView
					.findViewById(R.id.mMorning);
			holder.mLunch = (TextView) convertView.findViewById(R.id.mLunch);
			holder.mNight = (TextView) convertView.findViewById(R.id.mNight);

			convertView.setTag(holder);
		} else {
			holder = (BapViewHolder) convertView.getTag();
		}

		BapListData mData = mListData.get(position);

		String mDate = mData.mDate;
		if ("일요일".equals(mDate)) {
			holder.mCalender.setTextColor(Color.RED);
			holder.mDate.setTextColor(Color.RED);
		} else if ("토요일".equals(mDate)) {
			holder.mCalender.setTextColor(Color.BLUE);
			holder.mDate.setTextColor(Color.BLUE);
		} else {
			holder.mCalender.setTextColor(Color.BLACK);
			holder.mDate.setTextColor(Color.BLACK);
		}

		String Calender = mData.mCalender;
		holder.mCalender.setText(Calender);
		holder.mDate.setText(mDate);

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd(E)",
				Locale.KOREA);

		try {
			Calendar Date = Calendar.getInstance();
			Date.setTime(sdFormat.parse(Calender));

			LinearLayout bapListLayout = (LinearLayout) convertView
					.findViewById(R.id.bapListLayout);

			if (Date.get(Calendar.YEAR) == currentTime.get(Calendar.YEAR)
					&& Date.get(Calendar.MONTH) == currentTime
							.get(Calendar.MONTH)
					&& Date.get(Calendar.DAY_OF_MONTH) == currentTime
							.get(Calendar.DAY_OF_MONTH)) {

				bapListLayout.setBackgroundColor(mContext.getResources()
						.getColor(R.color.background));

				if (position == 0)
					Bap.mListView.setSelection(position);
				else
					Bap.mListView.setSelection(position - 1);

			} else {
				bapListLayout.setBackgroundColor(mContext.getResources()
						.getColor(android.R.color.transparent));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		String mMorning = mData.mMorning;
		String mLunch = mData.mLunch;
		String mNight = mData.mNight;

		if (MealCheck(mMorning))
			holder.mMorning.setText("");
		else
			holder.mMorning.setText(mMorning.trim());

		if (MealCheck(mLunch))
			holder.mLunch.setText("데이터가 존재하지 않습니다");
		else
			holder.mLunch.setText(mLunch.trim());

		if (MealCheck(mNight))
			holder.mNight.setText("");
		else
			holder.mNight.setText(mNight.trim());

		return convertView;
	}

	private boolean MealCheck(String Meal) {
		if ("".equals(Meal) || " ".equals(Meal) || Meal == null)
			return true;
		return false;
	}
}

class BapListData {
	public String mCalender;
	public String mDate;

	public String mMorning;
	public String mLunch;
	public String mNight;
}