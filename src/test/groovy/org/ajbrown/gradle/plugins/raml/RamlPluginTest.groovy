package org.ajbrown.gradle.plugins.raml

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class RamlPluginTest {

    @Test
    void "plugin is applied"() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'raml'

        assert project.tasks.findByName('raml') instanceof RamlTask
        assert project.tasks.findByName('ramlValidate') instanceof RamlValidateTask
    }
}