package com.fatel.mamtv1;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Administrator on 31/10/2558.
 */
public class ActivityHandle {

    int[] imageId = new int[] {-1,-1,-1,-1};
    int[] modeSelect = new int[] {-1,-1};

    public ActivityHandle(){
        //use selected mode from alarm
        modeRandom(new int[]{1, 2, 3, 4, 5, 6});

    }
    public int[] getImageId(){
        return imageId;

    }
    public void modeRandom(int[] mode){

        int x;
        int i=0;
        while (i<2){
            x=(int)(Math.random() * (mode.length));
            modeSelect[i]=mode[x];
            i++;
        }

    }

    public void random(){
        for(int i=0;i<4;i++){
            boolean same=true;
            int x=0;
            while(same){
                same=false;
                x=(int)(Math.random() * 13);
                for(int j=0;j<i;j++) {
                    if (x == imageId[j]){
                        same=true;
                        break;
                    }
                }
            }
            imageId[i]=x;
        }

    }
    public ArrayList<Posture> getRandomPosture(Context context){
        PostureCollection postureCollection= PostureCollection.getInstance(context);
        ArrayList<Posture> randomPosture = new ArrayList<>();
        for(int i=0;i<2;i++){
            ArrayList<Posture> modePosture = postureCollection.getPostureMode(modeSelect[i], context);
            int x;
            do {
                x = (int) (Math.random() * modePosture.size());

            }while (i>0&&modePosture.get(x).getIdPosture()==randomPosture.get(i-1).getIdPosture());
            randomPosture.add(modePosture.get(x));
        }

        return  randomPosture;
    }

}
