package org.gradle

import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.*
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class BuildLogicFunctionalTest extends Specification {    
    File projectDir

    def setup() {
        String projectDirPath = System.properties['projectDir']
        
        if (!projectDirPath) {
            throw new IllegalStateException("Need to provide system property named 'projectDir'")
        }
        
        projectDir = new File(projectDirPath)
        new File(projectDir, 'build').deleteDir()
    }

    def "creates text file"() {        
        when:
        def result = GradleRunner.create()
            .withProjectDir(projectDir)
            .withArguments('textsize')
            .forwardOutput()
            .build()

        then:
        result.output.contains('Generating textfile')
        result.task(":textsize").outcome == SUCCESS
        new File(projectDir, 'build/textsize.txt').exists()
    }
}