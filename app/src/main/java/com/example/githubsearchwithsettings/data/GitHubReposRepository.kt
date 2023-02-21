package com.example.githubsearchwithsettings.data

import android.text.TextUtils
import com.example.githubsearchwithsettings.api.GitHubService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This class manages the data operations associated with GitHub repositories.
 */
class GitHubReposRepository(
    private val service: GitHubService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    /**
     * This method executes a new query to the GitHub API's "search repositories" method.  It is
     * a suspending function and executes within the coroutine context specified by the
     * `dispatcher` argument to the Repository class's constructor.
     *
     * @param query The search query term to send to the GitHub API.
     *
     * @return Returns a Kotlin Result object wrapping the list of GitHubRepo objects that
     *   represent the search results.  If the API query is unsuccessful for some reason, the
     *   Exception associated with the Result object will provide more info about why the query
     *   failed.
     */
    suspend fun loadRepositoriesSearch(query: String): Result<List<GitHubRepo>> =
        withContext(dispatcher) {
            try {
                val response = service.searchRepositories(query)
                if (response.isSuccessful) {
                    Result.success(response.body()?.items ?: listOf())
                } else {
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    /*
     * This method constructs a complete GitHub search query that incorporates not only a specific
     * query term (from the parameter `query`) but also search qualifiers for a particular user,
     * set of languages, and the number of "good first issues".  See the GitHub API documentation
     * for more details about how this query is constructed:
     *
     * https://docs.github.com/en/rest/search#constructing-a-search-query
     */
    private fun buildGitHubQuery(
        query: String,
        user: String?,
        languages: Set<String>?,
        firstIssues: Int
    ) : String {
        /*
         * e.g. "android user:square language:kotlin language:java good-first-issues:>=2"
         */
        var fullQuery = query
        if (!TextUtils.isEmpty(user)) {
            fullQuery += " user:$user"
        }
        if (languages != null && languages.isNotEmpty()) {
            fullQuery += languages.joinToString(separator = " language:", prefix = " language:")
        }
        if (firstIssues > 0) {
            fullQuery += " good-first-issues:>=$firstIssues"
        }
        return fullQuery
    }
}