package id.beneranindonesia.pusaka.api;

import java.util.HashMap;
import java.util.List;

import id.beneranindonesia.pusaka.models.Contents;
import id.beneranindonesia.pusaka.models.Mission;
import id.beneranindonesia.pusaka.utils.TokenManager;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetDataService {

    @FormUrlEncoded
    @POST("/token")
    Call<Token_1> getToken(@FieldMap HashMap<String, String> body);

    @FormUrlEncoded
    @POST("/content/list")
    Call<Contents> getMissions(@HeaderMap HashMap<String, String> headers, @FieldMap HashMap<String, String> body);

}