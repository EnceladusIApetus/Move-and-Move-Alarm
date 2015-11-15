package com.fatel.mamtv1;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Administrator on 24/10/2558.
 */
public class ImageCollection {

    private static ArrayList<Image> imageCollection=new ArrayList<>();
    private static int count=1;
    //private ImageHelper helper = new ImageHelper();

    public static void addImage(int image,String description,Context context){
        Image img = new Image(count,image,description,context);
        imageCollection.add(img);
        count++;

    }

    public static void initial (Context context){
        addImage(R.drawable.ex1, "บริหารกล้ามเนื้อคอ",context);
        addImage(R.drawable.ex2,"ยืดกล้ามเนื้อแขน",context);
        addImage(R.drawable.ex3,"บริหารกล้ามเนื้อคอ",context);
        addImage(R.drawable.ex4,"บริหารกล้ามเนื้อคอ",context);
        addImage(R.drawable.ex5,"ยืดกล้ามเนื้อขา",context);
        addImage(R.drawable.ex6,"บริหารข้อเท้า",context);
        addImage(R.drawable.ex7,"ยืดกล้ามเนื้อขา",context);
        addImage(R.drawable.ex8, "ยืดกล้ามเนื้อส่วนแขน",context);
        addImage(R.drawable.ex9,"ยืดกล้ามเนื้อส่วนแขน",context);

    }
    public static int size(){
        return imageCollection.size();
    }
    public static Image getImageById(int id){
        return imageCollection.get(id);
    }
    public static ArrayList<Image> getImageById(int[] id){
        ArrayList<Image> imgcoll = new ArrayList<>();
        for (int i=0;i<id.length;i++){
            imgcoll.add(imageCollection.get(id[i]));
        }
        return imgcoll;
    }




}