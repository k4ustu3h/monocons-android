package k4ustu3h.monocons.data.repository

import android.app.Application
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import k4ustu3h.monocons.LawniconsScope
import k4ustu3h.monocons.R
import k4ustu3h.monocons.data.api.GitHubContributorsAPI
import k4ustu3h.monocons.data.model.GitHubContributor
import k4ustu3h.monocons.util.isIzzyBuild
import kotlinx.serialization.json.Json

val coreContributorIds = listOf(
    // Remove Patryk from contributors list, as per https://t.me/lawnchairci/1557
    29139614,
    // Remove renovate-bot from contributors list, since we don't count bots as contributors
    56888459,
    // GitHub Actions bot
    41898282,
    // suphon-t
    8080853,
    // SuperDragonXD
    70206496,
    // Chefski
    100310118,
    // x9136
    60105060,
    // Goooler
    10363352,
    // Grabstertv
    49114212,
)

@SingleIn(LawniconsScope::class)
@Inject
class GitHubContributorsRepository(
    private val api: GitHubContributorsAPI,
    private val application: Application,
) {
    private val jsonParser = Json { ignoreUnknownKeys = true }

    suspend fun getTopContributors(): List<GitHubContributor> {
        return if (isIzzyBuild) {
            val jsonString =
                application.resources.openRawResource(R.raw.contributors).bufferedReader()
                    .use { it.readText() }

            if (jsonString.isBlank() || jsonString.trim() == "[]") {
                emptyList()
            } else {
                jsonParser.decodeFromString<List<GitHubContributor>>(jsonString)
                    .filter { it.id !in coreContributorIds }.sortedByDescending { it.contributions }
            }
        } else {
            api.getContributors().filter { it.id !in coreContributorIds }
                .sortedByDescending { it.contributions }
        }
    }
}
