package k4ustu3h.monocons.data.repository

import android.app.Application
import android.util.Log
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import java.io.FileNotFoundException
import k4ustu3h.monocons.data.api.GitHubContributorsAPI
import k4ustu3h.monocons.data.model.GitHubContributor
import k4ustu3h.monocons.ui.destination.about.coreContributorIds
import k4ustu3h.monocons.util.isIzzyBuild
import kotlinx.serialization.json.Json

@SingleIn(AppScope::class)
@Inject
class GitHubContributorsRepository(
    private val api: GitHubContributorsAPI,
    private val application: Application,
    private val contributorIds: List<Long> = coreContributorIds,
) {
    private val jsonParser = Json { ignoreUnknownKeys = true }

    suspend fun getTopContributors(): List<GitHubContributor> {
        return if (isIzzyBuild) {
            try {
                val jsonString = application.assets.open("contributors.json").bufferedReader()
                    .use { it.readText() }

                if (jsonString.isBlank() || jsonString.trim() == "[]") {
                    emptyList()
                } else {
                    jsonParser.decodeFromString<List<GitHubContributor>>(jsonString)
                        .filterNot { contributorIds.contains(it.id) }
                        .sortedByDescending { it.contributions }
                }
            } catch (e: FileNotFoundException) {
                Log.e("GitHubContributors", "contributors.json not found in assets", e)
                emptyList()
            }
        } else {
            api.getContributors().filterNot { contributorIds.contains(it.id) }
                .sortedByDescending { it.contributions }
        }
    }
}
