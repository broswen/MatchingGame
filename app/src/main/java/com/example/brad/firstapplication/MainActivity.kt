package com.example.brad.firstapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {


    private val random = Random()
    private val imageAmount = 16
    private var selected = mutableListOf<ImageView>()
    private var images = mutableListOf<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var image1 = findViewById(R.id.imageView1) as ImageView
        var image2 = findViewById(R.id.imageView2) as ImageView
        var image3 = findViewById(R.id.imageView3) as ImageView
        var image4 = findViewById(R.id.imageView4) as ImageView
        var image5 = findViewById(R.id.imageView5) as ImageView
        var image6 = findViewById(R.id.imageView6) as ImageView
        var image7 = findViewById(R.id.imageView7) as ImageView
        var image8 = findViewById(R.id.imageView8) as ImageView
        var image9 = findViewById(R.id.imageView9) as ImageView
        var image10 = findViewById(R.id.imageView10) as ImageView
        var image11 = findViewById(R.id.imageView11) as ImageView
        var image12 = findViewById(R.id.imageView12) as ImageView
        var image13 = findViewById(R.id.imageView13) as ImageView
        var image14 = findViewById(R.id.imageView14) as ImageView
        var image15 = findViewById(R.id.imageView15) as ImageView
        var image16 = findViewById(R.id.imageView16) as ImageView
        var resetButton = findViewById<Button>(R.id.resetButton)

        images.add(image1)
        images.add(image2)
        images.add(image3)
        images.add(image4)
        images.add(image5)
        images.add(image6)
        images.add(image7)
        images.add(image8)
        images.add(image9)
        images.add(image10)
        images.add(image11)
        images.add(image12)
        images.add(image13)
        images.add(image14)
        images.add(image15)
        images.add(image16)

        generateTags()

        resetButton.setOnClickListener {
            resetImages()
            generateTags()
            Toast.makeText(applicationContext, "Reset!", Toast.LENGTH_SHORT).show()
        }

        for(image in images){
            image.setOnClickListener {
               if(image.getTag(R.string.matched_tag) == 1 || image in selected){

               }else{
                   if(selected.size == 0){
                       Log.d("debug", "size 0")
                       selected.add(image)
                       showImage(image)
                   }else if(selected.size == 1){
                       Log.d("debug", "size 1")
                       selected.add(image)
                       showImage(image)

                       Log.d("debug", selected[0].getTag(R.string.tag_key).toString())
                       Log.d("debug", selected[1].getTag(R.string.tag_key).toString())

                       if(selected[0].getTag(R.string.tag_key) == image.getTag(R.string.tag_key)){
                           selected[0].setTag(R.string.matched_tag, 1)
                           image.setTag(R.string.matched_tag, 1)
                           selected.clear()
                           if(numberOfMatched() == 16){
                               Toast.makeText(applicationContext, "You Won!", Toast.LENGTH_SHORT).show()
                           }
                       }
                   }else if(selected.size == 2){
                       Log.d("debug", "size 2")
                       for(imagez in selected){
                            imagez.setImageResource(R.drawable.hidden)
                       }
                       selected.clear()
                       selected.add(image)
                       showImage(image)
                   }
               }
            }
        }
    }

    fun showImage(image: ImageView){
        when(image.getTag(R.string.tag_key).toString()) {
            "1" -> image.setImageResource(R.drawable.e1)
            "2" -> image.setImageResource(R.drawable.e2)
            "3" -> image.setImageResource(R.drawable.e3)
            "4" -> image.setImageResource(R.drawable.e4)
            "5" -> image.setImageResource(R.drawable.e5)
            "6" -> image.setImageResource(R.drawable.e6)
            "7" -> image.setImageResource(R.drawable.e7)
            "8" -> image.setImageResource(R.drawable.e8)
        }
    }

    fun resetImages(){
        for(image in images){
            image.setImageResource(R.drawable.hidden)
            image.setTag(R.string.matched_tag, 0)
        }
    }

    fun numberOfMatched(): Int{
        var x = 0
        for(image in images){
            if(image.getTag(R.string.matched_tag) == 1) x++
        }
        return x
    }

    fun resetUnmatched(){
        for(image in images){
            if(image.getTag(R.string.matched_tag) == 0) image.setImageResource(R.drawable.hidden)
        }
    }

    fun generateTags(){
        var numbers = mutableListOf(1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8)
        Log.d("debug", "adding number tags to imageviews")
        for(image in images){
            var k = random.nextInt(numbers.size)
            image.setTag(R.string.tag_key, numbers[k])
            numbers.removeAt(k)
        }
    }
}

