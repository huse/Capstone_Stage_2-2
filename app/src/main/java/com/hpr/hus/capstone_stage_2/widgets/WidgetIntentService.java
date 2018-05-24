package com.hpr.hus.capstone_stage_2.widgets;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class WidgetIntentService extends IntentService {

    private final static String INGREDIENT_LIST = "INGREDIENT_LIST";
    public static String TAG = "hhh WidgetIntentService";
    int counter = 0;

    public WidgetIntentService() {
        super("UpdateMessageService");
    }

    public static void startWidget(Context context, ArrayList<String> stringArrayList) {

        /*for (String s : stringArrayList){

            Log.v(TAG,"stringArrayList:   "  + s  );
        }*/
        Intent intent = new Intent(context, WidgetIntentService.class);
        intent.putExtra(INGREDIENT_LIST, stringArrayList);
        context.startService(intent);
    }

    private void updatngWidget(ArrayList<String> fromActivityIngredientsList) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.putExtra(INGREDIENT_LIST, fromActivityIngredientsList);
        sendBroadcast(intent);
    }

    @Override
    protected void onHandleIntent(Intent widgetIntent) {
        if (widgetIntent != null) {
            ArrayList<String> fromActivityIngredientsList = widgetIntent.getExtras().getStringArrayList(INGREDIENT_LIST);
            updatngWidget(fromActivityIngredientsList);

        }
    }


}
