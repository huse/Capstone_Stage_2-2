package com.hpr.hus.capstone_stage_2.widgets;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.hpr.hus.capstone_stage_2.R;

import java.util.List;

public class WidgetService extends RemoteViewsService {
    List<String> messagesList;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridWidgetView(this.getApplicationContext(), intent);
    }

    class GridWidgetView implements RemoteViewsService.RemoteViewsFactory {
        Context mContext = null;

        public GridWidgetView(Context context, Intent intent) {
            mContext = context;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            messagesList = WidgetProvider.messagesListInProvider;
        }

        @Override
        public void onDestroy() {
        }

        @Override
        public int getCount() {
            return messagesList.size();
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews view = new RemoteViews(mContext.getPackageName(), R.layout.widget_itmes);
            view.setTextViewText(R.id.widget_text_view, messagesList.get(position));
            Intent fillInIntent = new Intent();
            view.setOnClickFillInIntent(R.id.widget_text_view, fillInIntent);
            return view;
        }


    }


}

