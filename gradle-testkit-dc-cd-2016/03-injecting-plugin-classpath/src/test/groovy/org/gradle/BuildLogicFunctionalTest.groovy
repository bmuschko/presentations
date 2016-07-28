package org.gradle

import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.*
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class BuildLogicFunctionalTest extends Specification {
    @Rule final TemporaryFolder testProjectDir = new TemporaryFolder()
    File buildFile
    def pluginClasspath

    def setup() {
        def pluginClasspathResource = getClass().classLoader.findResource("plugin-classpath.txt")
        if (pluginClasspathResource == null) {
            throw new IllegalStateException("Did not find plugin classpath resource, run 'testClasses' build task.")
        }

        pluginClasspath = pluginClasspathResource.readLines().collect { "'$it'" }.join(', ')
        buildFile = testProjectDir.newFile('build.gradle')
    }
    
    def "can apply plugin"() {
        given:
        buildFile << """
            buildscript {
                dependencies {
                    classpath files($pluginClasspath)
                }
            }
            
            apply plugin: 'org.gradle.textsize'
        """
        
        when:
        def result = GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .withArguments('generateTextFiles')
            .build()

        then:
        result.task(":generateTextFiles").outcome == SUCCESS
        new File("$testProjectDir.root/generated/feldenkrais.txt").exists()
        new File("$testProjectDir.root/generated/frameworkitis.txt").exists()
    }
}