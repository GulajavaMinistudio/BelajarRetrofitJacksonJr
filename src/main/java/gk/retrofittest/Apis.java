package gk.retrofittest;


/**
 * Created by Gulajava Ministudio on 11/13/15.
 */

import gk.retrofittest.models.ModelRespon;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public interface Apis {


    /**
     * TES AMBIL DATA
     **/

    //https://api.github.com/repos/GulajavaMinistudio/BelajarVolleys
    @GET("/repos/{owner}/{repo}")
    Call<String> getDataStrings(@Path("owner") String ownername, @Path("repo") String reposname);


    @GET("/repos/{owner}/{repo}")
    Call<ModelRespon> getDataModels(@Path("owner") String ownername, @Path("repo") String reposname);


}
