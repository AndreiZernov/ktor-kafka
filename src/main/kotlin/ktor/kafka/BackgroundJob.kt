package ktor.kafka

import io.ktor.server.application.Application
import io.ktor.server.application.BaseApplicationPlugin
import io.ktor.util.AttributeKey
import mu.KotlinLogging
import java.io.Closeable
import kotlin.concurrent.thread

private val logger = KotlinLogging.logger {}
interface ClosableJob : Closeable, Runnable

class BackgroundJob(configuration: JobConfiguration) : Closeable {
    private val job = configuration.job
    private val name = configuration.name

    class JobConfiguration {
        var name: String? = null
        var job: ClosableJob? = null
    }

    object BackgroundJobFeature : BaseApplicationPlugin<Application, JobConfiguration, BackgroundJob> {
        override val key: AttributeKey<BackgroundJob> = AttributeKey("BackgroundJob")

        override fun install(pipeline: Application, configure: JobConfiguration.() -> Unit): BackgroundJob {
            val configuration = JobConfiguration().apply(configure)
            val backgroundJob = BackgroundJob(configuration)
            configuration.job?.let { thread(name = configuration.name) { it.run() } }
            return backgroundJob
        }
    }

    override fun close() {
        logger.info { "Closing $name job" }
        job?.close()
        logger.info { "Job $name closed" }
    }
}
