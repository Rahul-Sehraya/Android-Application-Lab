package com.example.layoutrelative;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView card1, card2, card3, card4;
    ImageView firstCard, secondCard;

    int firstImage, secondImage;
    boolean isBusy = false;

    Integer[] images = {
            R.drawable.android,
            R.drawable.android,
            R.drawable.apple,
            R.drawable.apple
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);

        // Shuffle images
        List<Integer> imageList = Arrays.asList(images);
        Collections.shuffle(imageList);

        card1.setTag(imageList.get(0));
        card2.setTag(imageList.get(1));
        card3.setTag(imageList.get(2));
        card4.setTag(imageList.get(3));

        View.OnClickListener clickListener = view -> {
            if (isBusy) return;

            ImageView card = (ImageView) view;

            // Prevent clicking same card twice
            if (card == firstCard) return;

            int image = (int) card.getTag();
            card.setImageResource(image);

            if (firstCard == null) {
                firstCard = card;
                firstImage = image;
            } else {
                secondCard = card;
                secondImage = image;
                isBusy = true;
                checkMatch();
            }
        };

        card1.setOnClickListener(clickListener);
        card2.setOnClickListener(clickListener);
        card3.setOnClickListener(clickListener);
        card4.setOnClickListener(clickListener);
    }

    private void checkMatch() {
        new Handler().postDelayed(() -> {

            if (firstImage == secondImage) {
                firstCard.setEnabled(false);
                secondCard.setEnabled(false);
            } else {
                firstCard.setImageResource(R.drawable.img1);
                    secondCard.setImageResource(R.drawable.img1);
            }

            firstCard = null;
            secondCard = null;
            isBusy = false;

        }, 800);
    }
}
