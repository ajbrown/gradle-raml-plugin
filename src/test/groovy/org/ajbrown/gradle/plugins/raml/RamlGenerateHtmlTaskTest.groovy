package org.ajbrown.gradle.plugins.raml

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.internal.AbstractTask
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

/**
 *
 * @author A.J. Brown <aj@ajbrown.org>
 */
class RamlGenerateHtmlTaskTest {

    private Project project
    private AbstractTask task

    @Before
    void setup() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: 'raml'
        task = project.tasks.findByName('ramlGenerateHtml')
    }

    @Test
    void "HTML is generated from valid RAML"() {

        project.extensions.findByType(RamlExtension).source = 'https://raw.githubusercontent.com/raml-org/raml-tutorial-200/master/jukebox-api.raml'

        task.execute()

        assertTrue task.state.didWork
        assertNull task.state.failure
    }
}
