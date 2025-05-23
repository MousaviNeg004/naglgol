package ir.shariaty.naglgol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView TextView;
    Button Btn;
    ImageButton shareBtn;
    ImageView ImageView;
    LottieAnimationView animationView;

    // انیمیشن‌ها همچنان هستن
    int[] animations = {
            R.raw.animate1,
            R.raw.animate2,
            R.raw.animate3,
            R.raw.animate4
    };

    // متغیر برای ذخیره نقل قول فعلی
    String currentQuote = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView = findViewById(R.id.TextView);
        Btn = findViewById(R.id.Btn);
        shareBtn = findViewById(R.id.shareBtn);
        ImageView = findViewById(R.id.ImageView);
        animationView = findViewById(R.id.lottieAnim);

        // لود عکس با Glide
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

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareQuote();
            }
        });
    }

    private void showRandomQuote() {
        QuoteApi api = ApiClient.getClient().create(QuoteApi.class);
        Call<Quote> call = api.getRandomQuote();

        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                if (response.isSuccessful() && response.body() != null) {
                    currentQuote = response.body().getText();
                    TextView.setText(currentQuote);
                    showRandomAnimation();
                } else {
                    currentQuote = "";
                    TextView.setText("خطا در دریافت نقل‌قول");
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                currentQuote = "";
                TextView.setText("ارتباط با سرور برقرار نشد");
            }
        });
    }

    private void showRandomAnimation() {
        int index = (int) (Math.random() * animations.length);
        animationView.setAnimation(animations[index]);
        animationView.playAnimation();
    }

    private void shareQuote() {
        if (currentQuote.isEmpty()) return;

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "اشتراک گذاری نقل قول");
        startActivity(shareIntent);
    }
}