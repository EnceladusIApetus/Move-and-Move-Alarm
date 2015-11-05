package com.fatel.mamtv1;

import java.util.ArrayList;

/**
 * Created by Administrator on 24/10/2558.
 */
public class ImageCollection {

    private static ImageCollection instance = null;
    private static ArrayList<Image> imageCollection=new ArrayList<>();
    private static int count=0;
    private ImageHelper helper;
    private ImageCollection(){

    }
    public static ImageCollection getInstance(){
        if(instance == null){
            instance = new ImageCollection();
        }
        return instance;
    }

    public void addImage(int image,String description){

        Image img = new Image(count,image,description);
        imageCollection.add(img);
        count++;

    }

    public void initial (){

        addImage(R.drawable.ex1, "บริหารกล้ามเนื้อคอ");
        addImage(R.drawable.ex2,"ยืดกล้ามเนื้อแขน");
        addImage(R.drawable.ex3,"บริหารกล้ามเนื้อคอ");
        addImage(R.drawable.ex4,"บริหารกล้ามเนื้อคอ");
        addImage(R.drawable.ex5,"ยืดกล้ามเนื้อขา");
        addImage(R.drawable.ex6,"บริหารข้อเท้า");
        addImage(R.drawable.ex7,"ยืดกล้ามเนื้อขา");
        addImage(R.drawable.ex8, "ยืดกล้ามเนื้อส่วนแขน");
        addImage(R.drawable.ex9,"ยืดกล้ามเนื้อส่วนแขน");

    }
    public int size(){
        return imageCollection.size();
    }
    public Image getImageById(int id){
        return imageCollection.get(id);
    }
    public ArrayList<Image> getImageById(int[] id){
        ArrayList<Image> imgcoll = new ArrayList<>();
        for (int i=0;i<id.length;i++){
            imgcoll.add(imageCollection.get(id[i]));
        }
        return imgcoll;
    }




}
