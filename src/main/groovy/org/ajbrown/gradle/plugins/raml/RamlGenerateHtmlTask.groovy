package org.ajbrown.gradle.plugins.raml

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.raml.parser.visitor.RamlDocumentBuilder

/**
 *
 * @author A.J. Brown <aj@ajbrown.org>
 */
class RamlGenerateHtmlTask extends DefaultTask {

    @Optional
    @OutputFile
    def outputFile =  new File( project.buildDir.path,  "api.html" )

    def htmlGenerator = new RamlHtmlGenerator()

    @TaskAction
    def generateHtml() {

        def raml = new RamlDocumentBuilder().build( project.extensions.findByType(RamlExtension).source  )
        def output = htmlGenerator.generateHtml( raml )

        outputFile.parentFile.mkdirs()
        outputFile.text = output

        logger.info  "API documentation generated in ${outputFile.absolutePath}"
    }

}
