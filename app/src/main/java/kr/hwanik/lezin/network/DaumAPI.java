package kr.hwanik.lezin.network;

import kr.hwanik.lezin.model.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hwanik on 2017. 4. 8..
 */

public interface DaumAPI {
    @GET("/search/image")
    Observable<Response> getImages(@Query("apikey") String apiKey,
                                   @Query("q") CharSequence q,
                                   @Query("output") String output);
}
