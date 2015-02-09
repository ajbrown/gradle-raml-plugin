package org.ajbrown.gradle.plugins.raml

import org.gradle.api.DefaultTask
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction
import org.raml.parser.visitor.RamlValidationService


/**
 * A Gradle task which validates a RAML specification.
 *
 * @author A.J. Brown <aj@ajbrown.org>
 */
class RamlValidateTask extends DefaultTask {

    File getDestination() {
        project.file( destination )
    }

    /**
     * Validates the RAML file specified in the {@link RamlExtension}'a source property.  Any validation errors will be
     * sent as log output, and a {@link org.gradle.api.tasks.TaskExecutionException} will be thrown if there are any
     * validation errors.
     */
    @TaskAction
    def validateRamlDoc() {

        def ext = project.extensions.findByType(RamlExtension)
        def results = RamlValidationService.createDefault().validate( ext.source )

        results.each{ result ->
            def level = LogLevel.(result.level.toString())

            def fileInfo = result.includeContext.peek()

            logger.log( level, "Validation error in ${fileInfo.includeName}@${result.line}:${result.startColumn} : " +
                    "${result.message}" )
        }

        assert results.count{ !it.valid } == 0
    }
}
