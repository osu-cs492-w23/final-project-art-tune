package com.example.githubsearchwithsettings.api

import com.example.githubsearchwithsettings.data.GitHubSearchResults
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is a Retrofit interface encapsulating communication with the GitHub API.
 */
interface GitHubService {
    /**
     * This method represents a call to the `GET /search/repositories` method of the GitHub API:
     *
     * https://docs.github.com/en/rest/search#search-repositories
     *
     * @param query This represents the search query term that will be passed to the GitHub API
     *   as the query string parameter `q`.
     * @param sort This represents the criterion on which to sort the search results returned by
     *   the GitHub API and is passed to the API as the query string parameter `sort`.  Refer to
     *   the documentation linked above for more information on this parameter.
     *
     * @return Returns a Retrofit Call<> object representing an HTTP request to be made to the
     *   `GET /search/repositories` method of the GitHub API.  The response body of a successful
     *   request will be a `GitHubSearchResults` object representing the search results.
     */
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String? = "stars"
    ) : Response<GitHubSearchResults>

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        /**
         * This method can be called as `GitHubService.create()` to create an object implementing
         * the GitHubService interface and which can be used to make calls to the GitHub API.
         */
        fun create() : GitHubService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(GitHubService::class.java)
        }
    }
}