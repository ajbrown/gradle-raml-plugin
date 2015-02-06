package com.haximusprime

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class RamlPluginTest {
    @Test
    void pluginIsApplied() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'raml'


        def task = project.tasks.findByName('raml')
        assert task instanceof RamlTask
    }
}