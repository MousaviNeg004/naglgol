package ir.shariaty.naglgol;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView TextView;
    Button Btn;
    ImageView ImageView;
    LottieAnimationView animationView;

    String[] quotes = {
            "زندگی کوتاه‌تر از آن است که به بدی بگذرد.",
            "امید، مهم‌ترین نیروی زندگی است.",
            "باور داشته باش، موفقیت نزدیکه.",
            "هیچ‌وقت دیر نیست برای شروع دوباره.",
            "لبخند بزن، دنیا قشنگ‌تر میشه!"
    };

    int[] animations = {
            R.raw.animate1,
            R.raw.animate2,
            R.raw.animate3,
            R.raw.animate4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView = findViewById(R.id.TextView);
        Btn = findViewById(R.id.Btn);
        ImageView = findViewById(R.id.ImageView);
        animationView = findViewById(R.id.lottieAnim);

        String imageUrl = "https://th.bing.com/th/id/OIP.KNDZ-nHK9QR9RWZFRBTrrQAAAA?rs=1&pid=ImgDetMain";
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .into(ImageView);

        showRandomQuote();

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomQuote();
                animationView.playAnimation();
            }
        });
    }

    private void showRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        TextView.setText(quotes[index]);

        showRandomAnimation();
    }
    private void showRandomAnimation() {
        Random random = new Random();
        int index = random.nextInt(animations.length);
        animationView.setAnimation(animations[index]);
        animationView.playAnimation();
    }
}