package org.ajbrown.gradle.plugins.raml

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options
import com.github.jknack.handlebars.helper.StringHelpers
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import groovy.util.logging.Slf4j
import org.markdown4j.Markdown4jProcessor
import org.raml.model.Raml

/**
 *
 * @author A.J. Brown <aj@ajbrown.org>
 */
@Slf4j
class RamlHtmlGenerator {


    def generateHtml( Raml raml ) {

        def loader = new ClassPathTemplateLoader( "/templates/raml", ".handlebars" )
        def handlebars = new Handlebars( loader )
        handlebars.registerHelper( "emptyResourceCheck", new EmptyResourceCheckHelper() )
        handlebars.registerHelper( "md", new MarkdownHelper() )
        handlebars.registerHelper( "lock", new LockIconHelper() )
        handlebars.registerHelpers( StringHelpers )

        handlebars.infiniteLoops = true

        def template = handlebars.compile( "template" )
        def output = template.apply( raml )

        output
    }


    class MarkdownHelper implements Helper<String> {

        def processor = new Markdown4jProcessor()

        @Override
        CharSequence apply(String o, Options options) throws IOException {
            def output = processor.process( o )
            new Handlebars.SafeString( output )
        }
    }

    class LockIconHelper implements  Helper<Collection> {

        @Override
        CharSequence apply(Collection collection, Options options) throws IOException {
            if( collection.count{ it } > 0 ) {
                return new Handlebars.SafeString( ' <span class="glyphicon glyphicon-lock" title="Authentication required"></span>' )
            }

            return ''
        }
    }

    class EmptyResourceCheckHelper implements Helper<Object> {

        /**
         * Apply the helper to the context.
         *
         * @param resource @param options The options object.
         * @return A string result.
         * @throws IOException If a template cannot be loaded.
         */
        @Override
        CharSequence apply(Object resource, Options options) throws IOException {

            def exists = resource.actions?.size() > 0 || (resource?.description && resource?.parentUri)
            if( !exists ) {
                return ""
            }

            if( exists ) {
                return options.fn( resource )
            }
        }
    }

}
