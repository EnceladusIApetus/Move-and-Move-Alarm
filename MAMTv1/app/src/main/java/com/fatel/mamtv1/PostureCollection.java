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

    public void addImage(int image,String description,Context context){
        Posture posture = new Posture(count,image,description);
        posture.save(context);
        collection.add(posture);
        count++;
    }

    public void initial (Context context){
        if(Posture.getPostureCount(context)<13) {
            addImage(R.drawable.pos1_1, "ตามองตรง \nหมุนคอไปขวามือช้าๆ \nหมุนกลับ \nหมุนคอไปซ้ายมือช้าๆ",context);
            addImage(R.drawable.pos1_2, "ตามองตรง \nเอียงคอไปขวามือช้าๆ \nเอียงกลับ \nเอียงคอไปซ้ายมือช้าๆ", context);
            addImage(R.drawable.pos1_3, "ยืดต้นขา ยืดเข่า", context);
            addImage(R.drawable.pos2_1, "บริหารข้อเท้า", context);
            addImage(R.drawable.pos2_2, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos3_1, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos3_2, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos3_3, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos3_4, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos3_5, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos4_1, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos4_2, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos4_3, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos5_1, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos5_2, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos6_1, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos6_2, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos6_3, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos6_4, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos6_5, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos6_6, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos6_7, "ยืดกล้ามเนื้อคอ", context);
            addImage(R.drawable.pos6_8, "ยืดกล้ามเนื้อคอ", context);

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




}
