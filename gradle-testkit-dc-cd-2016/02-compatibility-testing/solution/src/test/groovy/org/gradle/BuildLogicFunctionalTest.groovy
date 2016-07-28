package org.gradle

import org.gradle.testkit.runner.GradleRunner
import static org.gradle.testkit.runner.TaskOutcome.*
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import spock.lang.Unroll

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

    @Unroll
    def "creates text file [#gradleVersion]"() {        
        when:
        def result = GradleRunner.create()
            .withProjectDir(projectDir)
            .withArguments('textsize')
            .forwardOutput()
            .withGradleVersion(gradleVersion)
            .build()

        then:
        result.output.contains('Generating textfile')
        result.task(":textsize").outcome == SUCCESS
        new File(projectDir, 'build/textsize.txt').exists()
        
        where:
        gradleVersion << ['2.8', '2.9', '2.10']
    }
}