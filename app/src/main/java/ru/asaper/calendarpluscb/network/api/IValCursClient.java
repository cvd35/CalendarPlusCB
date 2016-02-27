package ru.asaper.calendarpluscb.network.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.asaper.calendarpluscb.network.models.ValCurs;

public interface IValCursClient {
    @GET("XML_daily.asp")
    Call<ValCurs> getCurses(@Query("date_req") String date);
}
