package org.ajbrown.gradle.plugins.raml

import org.junit.Test
import org.raml.parser.visitor.RamlDocumentBuilder
import org.raml.parser.visitor.RamlValidationService

/**
 * Created by ajbrown on 2/9/15.
 */
class RamlHtmlGeneratorTest {

    def ramlSource = 'https://raw.githubusercontent.com/raml-org/raml-tutorial-200/master/jukebox-api.raml'
    def generator = new RamlHtmlGenerator()

    @Test
    void "generator can process the RAML 200 tutorial spec"() {
        def raml = new RamlDocumentBuilder().build( ramlSource )
        def output = generator.generateHtml( raml )

        assert output
    }
}
