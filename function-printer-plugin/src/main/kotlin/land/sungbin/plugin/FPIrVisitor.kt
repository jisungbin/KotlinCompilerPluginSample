package land.sungbin.plugin

import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.types.classFqName
import org.jetbrains.kotlin.ir.util.fqNameWhenAvailable
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid

internal class FPIrVisitor(
    private val logger: MessageCollector,
    private val loggingTag: String,
) : IrElementVisitorVoid {
    override fun visitModuleFragment(declaration: IrModuleFragment) {
        declaration.files.forEach { file ->
            file.accept(this, null)
        }
    }

    override fun visitFile(declaration: IrFile) {
        declaration.declarations.forEach { item ->
            item.accept(this, null)
        }
    }

    override fun visitFunction(declaration: IrFunction) {
        val render = buildString {
            append(declaration.fqNameWhenAvailable!!.asString() + "(")
            val parameters = declaration.valueParameters.iterator()
            while (parameters.hasNext()) {
                val parameter = parameters.next()
                append(parameter.name.asString())
                append(": ${parameter.type.classFqName!!.shortName().asString()}")
                if (parameters.hasNext()) append(", ")
            }
            append(")")
        }
        logger.report(CompilerMessageSeverity.WARNING, "[$loggingTag] $render")
    }
}