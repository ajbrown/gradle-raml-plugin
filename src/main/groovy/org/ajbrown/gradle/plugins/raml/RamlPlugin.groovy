package org.ajbrown.gradle.plugins.raml

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * This is the main plugin file. Put a description of your plugin here.
 */
class RamlPlugin implements Plugin<Project> {

    void apply(Project project) {

        project.extensions.create "raml", RamlExtension

        project.task('raml', type:RamlTask)

        //Add the validation task
        def validationTask = project.tasks.create( 'ramlValidate', RamlValidateTask )
        validationTask.description = 'Validates RAML documentation'
        validationTask.group = 'Documentation'
    }
}
