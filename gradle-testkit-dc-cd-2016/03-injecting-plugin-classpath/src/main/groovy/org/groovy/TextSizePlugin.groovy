package org.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class TextSizePlugin implements Plugin<Project> {
    void apply(Project project) {
        project.apply plugin: 'base'
        
        project.task('generateTextFiles') {
            doLast {
                File textSrcDir = new File("$project.projectDir/generated")
                textSrcDir.mkdirs()
                File file1 = new File(textSrcDir, 'feldenkrais.txt')
                file1.createNewFile()
                file1.text << """
                    Make the impossible possible
                    Make the possible easy 
                    Make the easy elegant
                """
                File file2 = new File(textSrcDir, 'frameworkitis.txt')
                file2.createNewFile()
                file2.text << "Frameworkitis is the disease that a framework wants to do too much for you or it does it in a way that you don't want but you can't change it. It's fun to get all this functionality for free, but it hurts when the free functionality gets in the way. But you are now tied into the framework. To get the desired behavior you start to fight against the framework. And at this point you often start to lose, because it's difficult to bend the framework in a direction it didn't anticipate. Toolkits do not attempt to take control for you and they therefore do not suffer from frameworkitis."
            }
        }
    }
}