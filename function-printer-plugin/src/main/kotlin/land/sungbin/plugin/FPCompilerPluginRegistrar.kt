@file:OptIn(ExperimentalCompilerApi::class)
@file:Suppress("unused")

package land.sungbin.plugin

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration

@AutoService(CompilerPluginRegistrar::class)
class FPCompilerPluginRegistrar : CompilerPluginRegistrar() {
    override val supportsK2 = false

    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        val logger = configuration.get(CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY, MessageCollector.NONE)
        val loggingTag = requireNotNull(configuration[KEY_TAG])
        IrGenerationExtension.registerExtension(FPIrExtension(logger, loggingTag))
    }
}