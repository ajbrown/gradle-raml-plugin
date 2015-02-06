package com.haximusprime

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * This is the main plugin file. Put a description of your plugin here.
 */
class RamlPlugin implements Plugin<Project> {
    void apply(Project project) {

        project.task('raml', type:RamlTask)
    }
}
