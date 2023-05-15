@file:OptIn(ExperimentalCompilerApi::class)
@file:Suppress("unused")

package land.sungbin.plugin

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.CompilerConfigurationKey

private const val PluginId = "land.sungbin.function.printer"

internal val KEY_TAG = CompilerConfigurationKey<String>(
    "Tags to use for logging",
)
private val OPTION_TAG = CliOption(
    optionName = "tag",
    valueDescription = "String",
    description = KEY_TAG.toString(),
)

@AutoService(CommandLineProcessor::class)
class FPCommandLineProcessor : CommandLineProcessor {
    override val pluginId = PluginId

    override val pluginOptions = listOf(OPTION_TAG)

    override fun processOption(
        option: AbstractCliOption,
        value: String,
        configuration: CompilerConfiguration,
    ) {
        when (val optionName = option.optionName) {
            OPTION_TAG.optionName -> configuration.put(KEY_TAG, value)
            else -> error("Unknown plugin option: $optionName")
        }
    }
}
