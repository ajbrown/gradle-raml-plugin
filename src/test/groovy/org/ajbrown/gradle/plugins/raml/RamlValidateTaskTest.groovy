package org.ajbrown.gradle.plugins.raml

import org.gradle.api.Project
import org.gradle.api.tasks.TaskExecutionException
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertNull
import static org.junit.Assert.assertTrue

/**
 * Unit test for {@link RamlValidateTask}
 *
 * @author A.J. Brown <aj@ajbrown.org>
 */
class RamlValidateTaskTest {

    private Project project
    private RamlValidateTask task

    @Before
    void setup() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: 'raml'
        task = project.tasks.findByName('ramlValidate')
    }

    @Test( expected = TaskExecutionException )
    void "validation will fail for an invalid document"() {

        project.extensions.findByType(RamlExtension).source = "simple-invalid.raml"

        task.execute()

        assertTrue task.state.didWork
        assertTrue task.state.failure instanceof TaskExecutionException
    }

    @Test
    void "validation will not fail for RAML 200 tutorial (valid definition)"() {
        project.extensions.findByType(RamlExtension).source = 'https://raw.githubusercontent.com/raml-org/raml-tutorial-200/master/jukebox-api.raml'

        task.execute()

        assertTrue task.state.didWork
        assertNull task.state.failure
    }

}
