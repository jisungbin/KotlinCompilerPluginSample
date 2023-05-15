package land.sungbin.plugin

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

internal class FPIrExtension(
  private val logger: MessageCollector,
  private val loggingTag: String,
) : IrGenerationExtension {
  override fun generate(
    moduleFragment: IrModuleFragment,
    pluginContext: IrPluginContext,
  ) {
    moduleFragment.accept(FPIrVisitor(logger, loggingTag), null)
  }
}
