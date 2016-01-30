package com.fatel.mamtv1;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Administrator on 24/10/2558.
 */
public class PostureCollection {

    private static PostureCollection instance = null;
    private ArrayList<Posture> collection = new ArrayList<>();
    private static int count=0;
    private PostureCollection(){

    }
    public static PostureCollection getInstance(Context context){
        if (instance == null) {
            instance = new PostureCollection();
            instance.initial(context);
        }
        return instance;
    }

    public void addImage(int image,String description,int mode,Context context){
        Posture posture = new Posture(count,image,description,mode);
        posture.save(context);
        collection.add(posture);
        count++;
    }

    public void initial (Context context){
        if(Posture.getPostureCount(context)<13) {
            addImage(R.drawable.pos1_1, "1.ตามองตรง \n2.หมุนคอไปขวามือช้าๆ \n3.หมุนกลับ \n4.หมุนคอไปซ้ายมือช้าๆ",1,context);
            addImage(R.drawable.pos1_2, "1.ตามองตรง \n2.เอียงคอไปขวามือช้าๆ \n3.เอียงกลับ \n4.เอียงคอไปซ้ายมือช้าๆ",1, context);
            addImage(R.drawable.pos1_3, "คอ",1, context);
            addImage(R.drawable.pos2_1, "ไหล่",2, context);
            addImage(R.drawable.pos2_2, "ไหล่",2, context);
            addImage(R.drawable.pos3_1, "หลัง",3, context);
            addImage(R.drawable.pos3_2, "หลัง",3, context);
            addImage(R.drawable.pos3_3, "หลัง",3, context);
            addImage(R.drawable.pos3_4, "หลัง",3, context);
            addImage(R.drawable.pos3_5, "หลัง",3, context);
            addImage(R.drawable.pos4_1, "ข้อมือ",4, context);
            addImage(R.drawable.pos4_2, "ข้อมือ",4, context);
            addImage(R.drawable.pos4_3, "ข้อมือ",4, context);
            addImage(R.drawable.pos5_1, "เอว",5, context);
            addImage(R.drawable.pos5_2, "เอว",5, context);
            addImage(R.drawable.pos6_1, "ขา",5, context);
            addImage(R.drawable.pos6_2, "ขา",6, context);
            addImage(R.drawable.pos6_3, "ขา",6, context);
            addImage(R.drawable.pos6_4, "ขา",6, context);
            addImage(R.drawable.pos6_5, "ขา",6, context);
            addImage(R.drawable.pos6_6, "ขา",6, context);
            addImage(R.drawable.pos6_7, "ขา",6, context);
            addImage(R.drawable.pos6_8, "ขา",6, context);

        }
        else{
            for(int i=0;i<Posture.getPostureCount(context);i++){
                Posture posture=Posture.find(i,context);
                collection.add(posture);
            }

        }

    }
    public int size(){
        return collection.size();
    }
    public Posture getPosture(int id){
        return collection.get(id);
    }
    public ArrayList<Posture> getPosture(int[] id){
        ArrayList<Posture> imgcoll = new ArrayList<>();
        for (int i=0;i<id.length;i++){
            imgcoll.add(collection.get(id[i]));
        }
        return imgcoll;
    }
    public ArrayList<Posture> getPostureMode(int mode,Context context){
        ArrayList<Posture> imgcoll = Posture.findMode(mode,context);
        return  imgcoll;

    }




}
