package ir.shariaty.naglgol;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApi {
    @GET("quote/random")
    Call<Quote> getRandomQuote();
}